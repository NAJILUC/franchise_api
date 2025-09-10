package com.nequi.franchise.franchise.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        Server localServer = new Server();
        localServer.url("http://localhost:8080/franchise-api/v1");

        Contact contact = new Contact();
        contact.setEmail("najiluc@gmail.com");

        Info info = new Info();
        info.setTitle("Franchise api");
        info.version("1.0");
        info.contact(contact);
        return new OpenAPI().info(info).servers(List.of(localServer));
    }

}
