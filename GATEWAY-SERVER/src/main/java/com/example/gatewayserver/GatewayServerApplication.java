package com.example.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    // custom routing configuration
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
//        return routeLocatorBuilder.routes()
//                .route(p-> p
//                        .path("/istad/users-service/**")
//                        .filters(f->
//                                f.rewritePath("/istad/users-service/(?<segment>.*)","/${segment}")
//                                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()) // add Response Header filter
//                        )
//                        .uri("lb://USERS-SERVICE"))
//                .route(p-> p
//                        .path("/istad/spring-oauth/**")
//                        .filters(f->
//                                f.rewritePath("/istad/spring-oauth/(?<segment>.*)","/${segment}")
//                                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
//                        )
//                        .uri("lb://AUTH-SERVICE")
//                )
//                .route(p-> p
//                        .path("/istad/cards-service/**")
//                        .filters(f->
//                                f.rewritePath("/istad/cards-service/(?<segment>.*)","/${segment}")
//                                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
//                                        .tokenRelay()
//                        )
//                        .uri("lb://CARDS-SERVICE")).build();
//
//    }

}
