package com.sankadilshan.myday.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguratoin {

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI =  new OpenAPI();
         return openAPI.addSecurityItem(new SecurityRequirement())
                .components(new Components().addSecuritySchemes("bearer-key", createAPIKeySchema()))
                .info(new Info().description("MyDay Service Rest API")
                        .description("all the backend rest service")
                        .version("1.0")
                        .contact(new Contact().name("Sanka Dilshan").email("sankadilshan@gmail.com"))
                        .license(new License().name("License of API")
                        .url("API license URL")));
    }

    private SecurityScheme createAPIKeySchema() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .description("please provide valid bearer token")
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
