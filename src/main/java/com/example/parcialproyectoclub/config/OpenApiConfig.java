package com.example.parcialproyectoclub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI parcialProyectoClubOpenApi() {
        return new OpenAPI().info(new Info()
                .title("API Proyecto Club")
                .description("Gestión básica de usuarios y clubes")
                .version("1.0.0")
                .contact(new Contact().name("Equipo Proyecto Club")));
    }
}
