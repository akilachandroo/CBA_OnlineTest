package com.book.catalogue.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.book.catalogue.model.Book;

/**
 * Class provide unit test cases for each of the operations defined in our
 * microservice - GET, POST, Delete and Put.
 * 
 * @author Akila
 *
 */

public class CBABookCatalogueControllerTest {

	static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	public void testBookOperations() {

		// Create Book instance
		Book book = new Book();
		book.setTitle("Book 1");
		book.setIsbnNumber("1234512345123");
		book.setPublishedDate("20/01/2020");
		book.getAuthors().add("Akila");

		// Check if there any validations errors
		Set<ConstraintViolation<Book>> violations = validator.validate(book, Default.class);
		assertEquals(0, violations.size(), "There are validation errors in request object sent!");

		// POST operation
		ResponseEntity<Book> entity = new RestTemplate().postForEntity("http://localhost:8080/cba-books/createBook",
				book, Book.class);
		Book body = entity.getBody();
		HttpStatus statusCode = entity.getStatusCode();

		// Success case - where status code and title are same.
		assertEquals(HttpStatus.CREATED, statusCode, "Expected successful 201 response - received a failure code");
		assertEquals(book.getTitle(), body.getTitle(), "Book's Title not same - Creation have gone wrong!");

		// UPDATE Operation - add another author
		book.getAuthors().add("Second name");
		new RestTemplate().put("http://localhost:8080/cba-books/updateBook", book, Book.class);

		// Retrieve Object - To see if the object is updated
		Map<String, String> params = new HashMap<String, String>();
		Book[] returnedBook = new RestTemplate().getForObject("http://localhost:8080/cba-books/getBooks/Book 1",
				Book[].class, params);

		// This means we dont have Book updated in our catalogue
		assertFalse(returnedBook.length == 0);

		List<Book> booksList = Arrays.asList(returnedBook);
		// verify if you have book with author second name - which we updated
		assertTrue(booksList.stream().anyMatch(b -> b.getAuthors().contains("Second name")));

		// Verify if other name is present in author
		// verify if you have book with author second name - which we updated
		assertTrue("Book with Author doesn't exist!", booksList.stream().anyMatch(b -> b.getAuthors().contains("zyz")));
		
		//DELETE - Book 1 and verify if the object has been deleted
		new RestTemplate().delete("http://localhost:8080/cba-books/deleteBook/Book 1");
		returnedBook = new RestTemplate().getForObject("http://localhost:8080/cba-books/getBooks/Book 1",
				Book[].class, params);
		assertTrue("Book 1 is deleted", returnedBook.length ==0 );
	}

}
