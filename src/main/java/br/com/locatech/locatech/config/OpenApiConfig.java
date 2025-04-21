package br.com.locatech.locatech.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI locaTech() {
        return new OpenAPI()
                .info(
                        new Info().title("LocaTech API")
                                .description("Projeto desenvolvido para locadora de carros")
                                .version("v0.0.1")
                                .license(new License().name("Gustavo Bomfim").url("https://github.com/GustavoBomfim"))
                );
    }
}
