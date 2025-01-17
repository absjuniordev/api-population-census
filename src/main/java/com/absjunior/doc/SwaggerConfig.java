package com.absjunior.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("CENCUS")
                    .version("1.0.0")
                    .description("Censo populacional")
                    .contact(new io.swagger.v3.oas.models.info.Contact()
                        .name("Arnaldo Junior")
                        .url("https://github.com/absjuniordev")
                        .email("abs.junnior@hotmail.com")));
    }
}
