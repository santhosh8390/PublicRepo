package com.iiht.fse.BuyerService.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI()
                .info(new Info().title("Buyer Service")
                        .description("Rest API for Buyer service"));
    }
}
