package com.huertohogar.carritomicroservice.sqs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
@RequiredArgsConstructor
public class SqsService {

    private final SqsClient sqsClient;

    public void enviarMensaje(String queueUrl, String mensaje) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(mensaje)
                .build();

        sqsClient.sendMessage(request);
    }
}
