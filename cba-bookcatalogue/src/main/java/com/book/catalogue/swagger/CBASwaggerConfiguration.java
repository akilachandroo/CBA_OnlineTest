package com.book.catalogue.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration

public class CBASwaggerConfiguration {
	@Bean
	public Docket api() {
		System.out.println("************************************ SWAGGER **************************");
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
