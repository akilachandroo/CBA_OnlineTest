package com.book.catalogue.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger configuration for our API.
 * @author Akila
 *
 */
@Configuration

public class CBASwaggerConfiguration {
	@Bean
	public Docket api() {		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.book.catalogue.rest.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
	}

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Book Catalogue - Try it out !!")
				.description("Microservices built for CBA Book Catalogue")
				.version("1.0").build();
	}

}
