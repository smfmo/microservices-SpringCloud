package io.github.smfmo.mscloudgateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/clients/**").uri("lb://msclients"))
                .build();
    }
}

