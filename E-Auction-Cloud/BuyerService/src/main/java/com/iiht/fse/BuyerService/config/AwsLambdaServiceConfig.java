package com.iiht.fse.BuyerService.config;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.fse.BuyerService.model.BuyerResponse;
import com.iiht.fse.BuyerService.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class AwsLambdaServiceConfig {

    private final BuyerService buyerService;

    @Bean
    public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> fetchBidDetails() {
        return (proxyRequestEvent) -> {
            APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
            responseEvent.setStatusCode(HttpStatus.OK.value());
            try {
                responseEvent.setBody(new ObjectMapper().writeValueAsString(buyerService.fetchBidDetails(proxyRequestEvent.getQueryStringParameters().get("productId"))));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            responseEvent.setHeaders(Collections.singletonMap("Content-type" , "application/json"));
            return responseEvent;
        };
    }
}
