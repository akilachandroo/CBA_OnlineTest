package com.book.catalogue.cbabookcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@ComponentScan(basePackages="com.book.catalogue.swagger,com.book.catalogue.model,com.book.catalogue.rest.controller, com.book.catalogue.service")
@SpringBootApplication
@EnableSwagger2
public class CBABookCatalogueApplication {

	public static void main(String[] args) {	
		SpringApplication.run(CBABookCatalogueApplication.class, args);
	}
	
}
