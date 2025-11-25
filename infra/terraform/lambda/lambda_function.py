import json

def lambda_handler(event, context):
    print("📥 Evento recibido desde SQS:")
    print(json.dumps(event, indent=4))

    try:
        for record in event.get("Records", []):
            body = record.get("body")
            print(f"💬 Mensaje SQS recibido: {body}")

            # Intentar parsear JSON si el mensaje lo es
            try:
                data = json.loads(body)
                print(f"📦 JSON parseado: {data}")
            except Exception:
                print("Mensaje no es JSON. Procesando como texto...")

            # Aquí va tu lógica real
            print("⚙ Procesando mensaje...")

        return {"status": "OK", "mensaje": "Procesado correctamente"}

    except Exception as e:
        print("❌ ERROR procesando mensaje SQS:", str(e))
        raise e
