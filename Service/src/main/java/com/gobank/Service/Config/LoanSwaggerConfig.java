package com.gobank.Service.Config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoanSwaggerConfig {
    @Bean
    public OpenAPI customeApi() {
      return   new OpenAPI().info( new Info()
                .title("This is Loan Microservice")
                .description("In loan microservice we  haved saved the  loan details of the customers")
                .version("v2 version of the document")
                .contact(new Contact()
                        .name("Gopal Deshmukh").url("www.demo.com"))
                .license(new License()
                        .name("Loan Licenece")
                        .url("www.laonLicenec.com"))
                ).externalDocs(new ExternalDocumentation()
                .description("This is external document of the loan service")
                .url("www.external.com"));



    }
}
