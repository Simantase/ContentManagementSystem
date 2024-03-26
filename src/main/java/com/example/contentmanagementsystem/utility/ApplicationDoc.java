package com.example.contentmanagementsystem.utility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
@Configuration
@OpenAPIDefinition
public class ApplicationDoc {
	Contact contact() {
		return new Contact().name("SIMANTA SEN")
				.email("simantasen96@gmail.com")
				.url("https://www.testyantra.com/");
	}
	@Bean
	Info info() {
		return new Info().title("Content Management System")
				.description("Basic CRUD Operations")
				.version("v1").contact(contact());
	}
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}

}
