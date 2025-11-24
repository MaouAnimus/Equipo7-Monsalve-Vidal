provider "aws" {
  region = var.region
}

data "aws_caller_identity" "current" {}

resource "aws_sqs_queue" "carrito_queue" {
  name = "carrito-queue"
  visibility_timeout_seconds = 30
  message_retention_seconds = 1209600
}

data "aws_iam_policy_document" "lambda_assume_role" {
  statement {
    actions = ["sts:AssumeRole"]
    principals {
      type = "Service"
      identifiers = ["lambda.amazonaws.com"]
    }
  }
}

resource "aws_iam_role" "lambda_exec" {
  name = "huertohogar_lambda_exec_${data.aws_caller_identity.current.account_id}"
  assume_role_policy = data.aws_iam_policy_document.lambda_assume_role.json
}

resource "aws_iam_role_policy_attachment" "lambda_basic" {
  role       = aws_iam_role.lambda_exec.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

data "aws_iam_policy_document" "lambda_sqs" {
  statement {
    actions = [
      "sqs:ReceiveMessage",
      "sqs:DeleteMessage",
      "sqs:GetQueueAttributes",
      "sqs:ChangeMessageVisibility"
    ]
    resources = [aws_sqs_queue.carrito_queue.arn]
  }
}

resource "aws_iam_policy" "lambda_sqs_policy" {
  name   = "lambda_sqs_policy_${data.aws_caller_identity.current.account_id}"
  policy = data.aws_iam_policy_document.lambda_sqs.json
}

resource "aws_iam_role_policy_attachment" "lambda_sqs_attach" {
  role       = aws_iam_role.lambda_exec.name
  policy_arn = aws_iam_policy.lambda_sqs_policy.arn
}

resource "aws_lambda_function" "carrito_processor" {
  function_name = "carritoProcessor"
  filename      = "${path.module}/lambda/carrito_lambda.zip"
  source_code_hash = filebase64sha256("${path.module}/lambda/carrito_lambda.zip")
  handler       = "index.handler"
  runtime       = "nodejs18.x"
  role          = aws_iam_role.lambda_exec.arn
  timeout       = 30
  environment {
    variables = {
      SQS_URL = aws_sqs_queue.carrito_queue.id
      PRODUCTO_URL = "https://api.example.com/producto"
      USUARIO_URL = "https://api.example.com/usuario"
    }
  }
}

resource "aws_lambda_event_source_mapping" "sqs_mapping" {
  event_source_arn = aws_sqs_queue.carrito_queue.arn
  function_name    = aws_lambda_function.carrito_processor.arn
  batch_size       = 5
  enabled          = true
}

resource "aws_apigatewayv2_api" "gateway" {
  name          = "huertohogar-gateway"
  protocol_type = "HTTP"
}

resource "aws_apigatewayv2_integration" "lambda_integration" {
  api_id = aws_apigatewayv2_api.gateway.id
  integration_type = "AWS_PROXY"
  integration_uri  = aws_lambda_function.carrito_processor.invoke_arn
  payload_format_version = "2.0"
}

resource "aws_apigatewayv2_route" "post_enqueue" {
  api_id = aws_apigatewayv2_api.gateway.id
  route_key = "POST /enqueue"
  target    = "integrations/${aws_apigatewayv2_integration.lambda_integration.id}"
}

resource "aws_apigatewayv2_stage" "default" {
  api_id      = aws_apigatewayv2_api.gateway.id
  name        = "$default"
  auto_deploy = true
}

resource "aws_db_instance" "postgres" {
  identifier = "huertohogar-postgres"
  engine = "postgres"
  instance_class = "db.t3.micro"
  name = var.db_name
  username = var.db_username
  password = var.db_password
  skip_final_snapshot = true
  publicly_accessible = true
  allocated_storage = 20
}
