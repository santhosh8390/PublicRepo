package com.iiht.fse.BuyerService.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.iiht.fse.BuyerService.model.BuyerResponse;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class BuyerApiGatewayHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
}
