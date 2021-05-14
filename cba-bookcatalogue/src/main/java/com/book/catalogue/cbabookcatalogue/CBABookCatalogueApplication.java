package com.book.catalogue.cbabookcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Start up class of Book Catalogue application
 * @author Akila
 *
 */
@ComponentScan(basePackages="com.book.catalogue.swagger,com.book.catalogue.model,com.book.catalogue.rest.controller, com.book.catalogue.service")
@SpringBootApplication
@EnableSwagger2
public class CBABookCatalogueApplication {

	public static void main(String[] args) {	
		SpringApplication.run(CBABookCatalogueApplication.class, args);
	}
	
}
