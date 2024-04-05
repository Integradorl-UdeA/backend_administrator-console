package com.consola.lis.configuration;


import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${console.openapi.prod-url}")
    private String prodUrl;

    @Value("${console.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI swagger() {

        Info info = new Info()
                .title("CONSOLE API")
                .version("1.0")
                .description("This API exposes endpoints to manage console API .");

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        // Define security requirement for endpoints that require Bearer token
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        // Create OpenAPI object and configure servers, info, security schemes, and security requirements
        return new OpenAPI()
                .info(info)
                .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("bearerAuth", securityScheme))
                .addSecurityItem(securityRequirement);


    }
}
