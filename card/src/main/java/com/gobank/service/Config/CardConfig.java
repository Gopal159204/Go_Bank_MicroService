package com.gobank.service.Config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardConfig {
    @Bean
    public OpenAPI CustomApi(){
        return  new OpenAPI().info(
                new Info()
                        .title("This Is Card Service")
                        .description("This Is Card Service And the Information of Card Holder Of Account Holder")
                        .version("V2 Version")
                        .contact(new Contact()
                                .name("Gopal Deshmukh")
                                .url("www.demo.com")

                        )
                        .license(new License()
                                .name("Card Api and this is Licenses of Card Api")
                                .url("www.demo.com")

                        )

        ).externalDocs(new ExternalDocumentation()
                .description("This is External document")
                .url("www.demo.com")

        );

    }
}
