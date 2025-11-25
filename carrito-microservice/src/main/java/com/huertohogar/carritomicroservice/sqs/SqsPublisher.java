package com.huertohogar.carritomicroservice.sqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

@Component
public class SqsPublisher {

    private static final Logger logger = LoggerFactory.getLogger(SqsPublisher.class);

    private final SqsClient sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    public SqsPublisher(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void publicar(String mensaje) {
        try {
            if (queueUrl == null || queueUrl.isBlank()) {
                logger.error("ERROR: QUEUE_URL es nula o vacía. No se puede enviar mensaje a SQS.");
                return;
            }

            logger.info("Enviando mensaje a SQS...");
            logger.info("Cola: {}", queueUrl);
            logger.info("Mensaje: {}", mensaje);

            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(mensaje)
                    .build();

            sqsClient.sendMessage(request);

            logger.info("Mensaje enviado exitosamente a SQS");

        } catch (SqsException e) {
            logger.error("Error enviando mensaje a SQS: {}", e.awsErrorDetails().errorMessage());
        } catch (Exception e) {
            logger.error("Error inesperado enviando mensaje a SQS", e);
        }
    }
}
