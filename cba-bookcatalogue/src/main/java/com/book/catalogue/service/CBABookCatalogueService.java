package com.book.catalogue.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.book.catalogue.exception.BookException;
import com.book.catalogue.model.Book;

/**
 * Service implementation class for create, update, delete, retrieve operations
 * of book catalogue
 * 
 * @author Akila
 *
 */

@Service("bookService")
public class CBABookCatalogueService {

	// Maintain a map - with key as Title (unique) and Book object
	static private Map<Long, Book> books = new HashMap<Long, Book>();
	static long bookCnt = 0;

	/**
	 * Create or Update operation implemented in this method. Input parameter is
	 * Book object. Logic: - Verify Book title in books map. If No, insert new
	 * instance of the book in books map. - If its exists, Update ISBN, Authors from
	 * request to existing book instance and return the same as response.
	 * 
	 * @param book
	 * @return book Book
	 */
	public Book createBook(Book bookObj) {		
		List<Book> matchingBooks = books.values().stream()
				.filter(book -> book.getTitle().equalsIgnoreCase(bookObj.getTitle())).collect(Collectors.toList());
		if (matchingBooks.isEmpty()) {
			books.put(bookCnt++, bookObj);
		} else {
			throw new BookException(bookObj.getTitle() + " exists in our catalogue - if you are updating details, please use /updateBook");
		}

		return bookObj;
	}
	
	/**
	 * Update operation - Identify the book with its title - if found update the instance. Else create a new book.
	 * @param bookObj
	 * @return
	 */
	public Book updateBook(Book bookObj) {

		Optional<Entry<Long, Book>> bookId = books.entrySet().stream().filter(book -> book.getValue().getTitle().equalsIgnoreCase(bookObj.getTitle())).findFirst();
		if(bookId.isPresent()) {
			books.put(bookId.get().getKey(), bookObj);
		}else {
			createBook(bookObj);
		}
		
		return bookObj;
	}

	/**
	 * Delete opertaion - to remove an instance of book by its title.
	 * 
	 * @param title
	 * @return
	 */

	public String deleteBook(String title) {
		boolean isRemoved = books.entrySet().removeIf(b -> b.getValue().getTitle().equalsIgnoreCase(title));
		if (isRemoved) {			
			return "Removed";
		}
		return "No such book exists in our catalogue - can you confirm again?";

	}

	/**
	 * GET opertaion - Search books by title, authors or isbn number.
	 * 
	 * @param value
	 * @return
	 */
	public List<Book> getBooks(String value) {
		List<Book> matchingBooks = new ArrayList<Book>();
		List<Book> booksList = new ArrayList<Book>(books.values());
		if (!books.isEmpty()) {
			matchingBooks = booksList.stream()
					.filter(book -> book.getIsbnNumber().toLowerCase().contains(value.toLowerCase())
							|| book.getTitle().toLowerCase().contains(value.toLowerCase())
							|| String.join(",", book.getAuthors()).toLowerCase().contains(value.toLowerCase()))
					.collect(Collectors.toList());
			
			
			// matchingBooks.forEach(book -> message.append(book.toString()).append("\n"));
			// This is required if you want response in a String format.
		}

		return matchingBooks;
	}

}
