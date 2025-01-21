package com.example.bookapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Example",
                version = "v1",
                description = "This is a sample API for demonstrating Springdoc OpenAPI.",
                contact = @Contact(
                        name = "Your Name",
                        email = "your.email@example.com",
                        url = "https://yourwebsite.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local server")
        }
)
public class SwaggerConfiguration {
    // Anda dapat menambahkan lebih banyak konfigurasi jika diperlukan
}
