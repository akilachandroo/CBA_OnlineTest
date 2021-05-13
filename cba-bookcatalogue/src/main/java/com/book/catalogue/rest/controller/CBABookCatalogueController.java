package com.book.catalogue.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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

@RestController
public class CBABookCatalogueController {

	@Autowired
	private CBABookCatalogueService bookService;

	

	@ApiOperation(value = "Create a book in our catalogue")
	@RequestMapping(value = "/createBook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
		// Validate if all the parameters are sent in the request - have added
		// annotations at the field level.
		if (book == null) {
			throw new BookException("Book doesn't have title/author/ISBN/published date.");
		} else {
			book = bookService.createBook(book);
		}
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}	
	
	
	@ApiOperation(value = "Update book by its Title")
	@RequestMapping(value = "/updateBook", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) {
		// Validate if all the parameters are sent in the request - have added
		// annotations at the field level.
		if (book == null) {
			throw new BookException("Book doesn't have title/author/ISBN/published date.");
		} else {
			book = bookService.updateBook(book);
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
		List<Book> books = bookService.getBooks(value);		
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a book by Title")
	@RequestMapping(value = "/deleteBook/{title}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBook(@PathVariable String title) {

		if (title == null) {
			throw new BookException("Title of the book is required to remove it from Dialogue");
		}
		String message = bookService.deleteBook(title);
		return new ResponseEntity<String>("\"" + title + "\" - " + message, HttpStatus.OK);
	}

	@ExceptionHandler({ BookException.class })
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

}
