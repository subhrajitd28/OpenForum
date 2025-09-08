package com.subhrajit.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {


    @Bean
    public OpenAPI expenseAPI() {
        return new OpenAPI()
                .info(new Info().title("OpenForum API")
                        .description("API for Open Forum Application")
                        .version("v0.0.1")
                        .license(new License().name("Apache License Version 2.0").url("https://subhrajitdeb.me")))
                .externalDocs(new ExternalDocumentation()
                        .description("OpenForum Wiki")
                        .url("https://openforum.wiki/docs"));
    }
}
