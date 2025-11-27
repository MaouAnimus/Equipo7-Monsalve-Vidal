package com.huertohogar.coremicroservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SqsConfig {

    @Value("${aws.region}")
    private String region;

    @Bean
    public SqsClient sqsClient() {

        String finalRegion = (region == null || region.isBlank())
                ? "us-east-1"
                : region;

        return SqsClient.builder()
                .region(Region.of(finalRegion))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
    }
}
