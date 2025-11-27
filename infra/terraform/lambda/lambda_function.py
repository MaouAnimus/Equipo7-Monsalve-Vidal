import json

def lambda_handler(event, context):
    for record in event["Records"]:
        body = json.loads(record["body"])

        print("Evento SQS recibido:", body)

        productId = body["productId"]
        newStock = body["newStock"]

        print(f"Cambió el stock del producto {productId} a {newStock}")

    return {"status": "OK"}
