package com.gatway.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);



	}
	@Bean
	public RouteLocator goBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder){
		return  routeLocatorBuilder.routes()
				.route(p-> p
						.path("/gobank/account/**")
						.filters(f-> f.rewritePath("/gobank/account/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://ACCOUNT"))
				.route(p-> p
						.path("/gobank/loan/**")
						.filters(f-> f.rewritePath("/gobank/loan/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))

						.uri("lb://LOAN"))
				.route(p-> p
						.path("/gobank/card/**")
						.filters(f-> f.rewritePath("/gobank/card/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))

						.uri("lb://CARD")).build();
	}

}
