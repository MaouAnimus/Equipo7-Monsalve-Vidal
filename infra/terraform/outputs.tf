output "sqs_url" {
  value = aws_sqs_queue.carrito_queue.id
}
output "lambda_name" {
  value = aws_lambda_function.carrito_processor.function_name
}
output "api_gateway_url" {
  value = aws_apigatewayv2_stage.default.invoke_url
}
output "rds_endpoint" {
  value = aws_db_instance.postgres.address
}
