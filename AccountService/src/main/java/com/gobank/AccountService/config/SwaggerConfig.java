package com.gobank.AccountService.config;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
    @Bean
    public OpenAPI customApi(){
        return  new OpenAPI().info(new Info()
                .title("this is microservice bulid application")
                .description("we are bulidng  the bank accoun micro")
                .version("v2")
                .contact(new io.swagger.v3.oas.models.info.Contact()
                .name("Gopal")
                        .email("goapldeshmukh@gmial.com")
                        .url("www.micro.com")

        ).license(new License()
                        .name("Licenece Of APi")
                                .url("www.demo.com")

                        )

        ).externalDocs(new ExternalDocumentation()
                .description("This is externaldcoumention for the api and we have use some of this")
                .url("www.swagger-ui/index.html")
        );


}}
