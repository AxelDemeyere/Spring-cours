package org.app.exo2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cinémathèque API")
                        .description("API pour gérer films et réalisateurs")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("AxelDemeyere")));
    }
}