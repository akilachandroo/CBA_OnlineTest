package com.book.catalogue.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.catalogue.exception.BookException;
import com.book.catalogue.exception.ErrorResponse;
import com.book.catalogue.model.Book;
import com.book.catalogue.service.CBABookCatalogueService;

import io.swagger.annotations.ApiOperation;

/**
 * Controller class that implements CRUD operations for Book Catalogue
 * 
 * @author Akila
 *
 */
@RestController
public class CBABookCatalogueController {

	Logger log = LoggerFactory.getLogger(CBABookCatalogueController.class);

	@Autowired
	private CBABookCatalogueService bookService;

	@ApiOperation(value = "Create a book in our catalogue")
	@RequestMapping(value = "/createBook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {		
		if (book == null) {
			throw new BookException("Book doesn't have title/author/ISBN/published date.");
		} else {
			book = bookService.createBook(book);
			log.info("Book - " + book.getTitle() + " added in our catalogue");
		}
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update book by its Title")
	@RequestMapping(value = "/updateBook", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) {		
		if (book == null) {
			throw new BookException("Book doesn't have title/author/ISBN/published date.");
		} else {
			book = bookService.updateBook(book);
			log.info("Book - " + book.getTitle() + " updated in our catalogue");
		}
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}

	/**
	 * Method to return the list of books based on criteria
	 * 
	 * @return Map<String, Book> books
	 */
	@ApiOperation(value = "Get Books by Title or Author or ISBN")
	@RequestMapping(value = "/getBooks/{value}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> getBooks(@PathVariable String value) {
		log.info("Retriving books based on input - " + value);
		List<Book> books = bookService.getBooks(value);
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	/**
	 * Method to delete a book by Title and return a message whether its removed or
	 * not.
	 * 
	 * @param title
	 * @return ResponseEntity<String>
	 */
	@ApiOperation(value = "Delete a book by Title")
	@RequestMapping(value = "/deleteBook/{title}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBook(@PathVariable String title) {

		if (title == null) {
			throw new BookException("Title of the book is required to remove it from Dialogue");
		}
		String message = bookService.deleteBook(title);
		log.info("Book - " + title + " deleted from our catalogue!");
		return new ResponseEntity<String>("\"" + title + "\" - " + message, HttpStatus.OK);
	}

	/**
	 * Exception handler for BookException
	 * 
	 * @param ex
	 * @return ResponseEntity<ErrorResponse>
	 */
	@ExceptionHandler({ BookException.class })
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());

		log.error("Book exception: " + ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

}
