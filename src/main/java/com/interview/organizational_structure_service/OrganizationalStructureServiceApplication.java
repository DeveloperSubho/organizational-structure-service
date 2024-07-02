package com.interview.organizational_structure_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OrganizationalStructureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationalStructureServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() { return new RestTemplate(); }

	@Bean
	public WebMvcConfigurer corsConfigurer() {

		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedOrigins("*")
						.allowedHeaders("*");
			}
		};
	}

}
