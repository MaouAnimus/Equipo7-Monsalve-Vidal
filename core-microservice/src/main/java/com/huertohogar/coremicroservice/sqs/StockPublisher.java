package com.huertohogar.coremicroservice.sqs;   
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class StockPublisher {

    private final SqsClient sqsClient;

    @Value("${sqs.queue.url}")
    private String queueUrl;

    public StockPublisher(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void publicarCambioStock(Long productId, Integer newStock) {
        String json = String.format(
                "{\"productId\": %d, \"newStock\": %d}",
                productId, newStock
        );

        sqsClient.sendMessage(
                SendMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .messageBody(json)
                        .build()
        );

        System.out.println("📤 STOCK EVENTO SQS ENVIADO: " + json);
    }
}


