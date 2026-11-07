package com.lab.proveedores.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String ESQUEMA_API_KEY = "ApiKey";

    // Habilita el boton Authorize de Swagger para enviar el header X-API-KEY
    @Bean
    public OpenAPI documentacionApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Proveedores")
                        .version("1.0.0")
                        .description("Administracion de proveedores con almacenamiento en memoria"))
                .addSecurityItem(new SecurityRequirement().addList(ESQUEMA_API_KEY))
                .components(new Components().addSecuritySchemes(ESQUEMA_API_KEY,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("X-API-KEY")));
    }
}
