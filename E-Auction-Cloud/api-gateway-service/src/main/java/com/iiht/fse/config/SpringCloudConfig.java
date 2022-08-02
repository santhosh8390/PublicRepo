package com.iiht.fse.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("sellerId", r->r.path("/e-auction/api/v1/seller/**").uri("lb://SELLERSERVICE"))
                .route("buyerId", r->r.path("/e-auction/api/v1/buyer/**").uri("lb://BUYERSERVICE"))
                .build();
    }
}
