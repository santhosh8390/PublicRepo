package com.iiht.fse.SellerService.config;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.fse.SellerService.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class AwsLambdaServiceConfig {

    private final SellerService sellerService;

    private final AmazonSNSClient amazonSNSClient;

    private final AwsConfig awsConfig;

    @Bean
    public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> deleteProduct() {
        return (proxyRequestEvent) -> {
            APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
            responseEvent.setStatusCode(HttpStatus.OK.value());
            try {
                responseEvent.setBody(new ObjectMapper().writeValueAsString(sellerService.deleteProduct(proxyRequestEvent.getPathParameters().get("productId"))));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            responseEvent.setHeaders(Collections.singletonMap("Content-type", "application/json"));
            sendEmail(responseEvent.getBody());
            return responseEvent;
        };
    }

    private void sendEmail(String message) {
        final PublishRequest publishRequest = new PublishRequest(awsConfig.getSnsTopicArn(), message, "Delete product call result");
        final PublishResult result = amazonSNSClient.publish(publishRequest);
        System.out.println("Email sent successfully. " + result.getMessageId());
    }
}
