package com.mercadolibre.mutantes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * In order to see Swagger documentation enter:
 * http://localhost:8080/swagger-ui.html for local implementation
 * http://mutantdnasearcher-env.d7npg9kb7g.us-east-2.elasticbeanstalk.com/swagger-ui.html from server
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mercadolibre.mutantes.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Mutant DNA Searcher REST API",
                "The goal of this API is to identify mutants from a DNA sequence.",
                "API TOS",
                "Terms of service",
                new Contact("Alonso, Matias", "www.example.com", "alonso.matias17@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}