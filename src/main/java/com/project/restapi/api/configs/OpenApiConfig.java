package com.project.restapi.api.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${springdoc.version}") String apiVersion,
            @Value("${springdoc.api.title}") String apiTitle,
            @Value("${springdoc.api.description}") String apiDescription,
            @Value("${springdoc.api.contactname}") String apiContactName,
            @Value("${springdoc.api.contactemail}") String apiContactEmail,
            @Value("${springdoc.api.licence}") String apiLicence) {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer")))
                .info(new Info()
                        .title(apiTitle)
                        .description(apiDescription)
                        .version(apiVersion)
                        .contact(new Contact().name(apiContactName).email(apiContactEmail))
                        .license(new License().name(apiLicence).url("http://springdoc.org"))
                );
    }
}
