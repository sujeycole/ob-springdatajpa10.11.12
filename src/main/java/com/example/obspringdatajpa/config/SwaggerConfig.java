package com.example.obspringdatajpa.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * http://localhost:8080/swagger-ui/
 *  herramientas que nos ayudan a documentar nuestras APIs
 */


@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Spring Boot Book API REST",
                "Library Api rest docs",
                "1.0",
                "http://www.google.com",
                new Contact("Mindre", "http://www.google.com", "mindre@example.com"),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());
    }
}