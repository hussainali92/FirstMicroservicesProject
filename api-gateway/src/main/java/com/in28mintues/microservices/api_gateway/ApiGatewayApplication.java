package com.in28mintues.microservices.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/currency-exchange/**")
						.uri("lb://CURRENCY-EXCHANGE"))
				.route(p -> p
						.path("/currency-conversion/**")
						.uri("lb://CURRENCY-CONVERSION"))
				.route(p -> p
						.path("/currency-conversion-feign/**")
						.uri("lb://CURRENCY-CONVERSION"))
				.build();
	}
}


