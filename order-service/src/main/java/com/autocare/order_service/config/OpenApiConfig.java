package com.autocare.order_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    // Access the swagger UI by localhost:8080/swagger-ui.html
    @Bean
    public OpenAPI orderServiceApi(){
        return new OpenAPI().info(new Info().title("Order Service")
                .description("Order service documentation")
                .version("1.0")
                .license(new License().name("Apache 1.2")));
    }
}