package com.consola.lis.configuration;


import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {

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

        return new OpenAPI()
                .info(info)
                .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("bearerAuth", securityScheme));



    }
}
