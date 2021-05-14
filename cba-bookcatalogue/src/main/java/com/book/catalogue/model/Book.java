/**
 * 
 */
package com.book.catalogue.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;
import org.springframework.util.StringUtils;

/**
 * This class represents the properties/attributes of a Book
 * 
 * @author Akila Chandrasekaran
 *
 */
public class Book {

	/**
	 * 
	 */
	private static final long serialVersionUID = 718885586829203868L;

	@NotNull(message = "Book Title must be provided")
	@NotBlank(message = "Book Title cannot be empty")
	private String title;

	@NotNull
	@NotEmpty
	private List<String> authors;

	@NotNull
	@ISBN(type = Type.ISBN_13, message = "ISBN should be 13-digit")
	private String isbnNumber;

	@NotNull
	private String publishedDate;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the authors
	 */
	public List<String> getAuthors() {
		if(authors == null || authors.isEmpty()) {
			authors = new ArrayList<String>();
		}
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	/**
	 * @return the isbnNumber
	 */
	public String getIsbnNumber() {
		return isbnNumber;
	}

	/**
	 * @param isbnNumber the isbnNumber to set
	 */
	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	/**
	 * @return the publishedDate
	 */
	public String getPublishedDate() {
		return publishedDate;
	}

	/**
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public boolean hasNull() {
		return (StringUtils.isEmpty(this.getTitle()) || this.getIsbnNumber() == null || this.getPublishedDate() == null
				|| this.getAuthors() == null || this.getAuthors().size() == 0 || String.join(",", authors).isEmpty());
	
	}

	public String toString() {
		return "About Book --------> Title : " + this.title + ", ISBN Number :" + this.isbnNumber + ", Published Date: "
				+ this.publishedDate + ", Authors : " + String.join(",", authors);
	}

}
