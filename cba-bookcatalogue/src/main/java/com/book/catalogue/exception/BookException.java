package com.book.catalogue.exception;

/**
 * Book Exception class - to capture validation failures in HTTP operations
 * 
 * @author Akila
 *
 */
public class BookException extends RuntimeException {

	private static final long serialVersionUID = -4261192889595737691L;
	
	/*
	 * Defines error message
	 */
	private String errorMessage;

	/**
	 * Returns the errorMessage
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Constructor of Exception class which is raised from Service implementation.
	 * @param errorMessage
	 */
	public BookException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public BookException() {
		super();
	}

	public BookException(String message, Throwable cause) {
		super(message, cause);
	}

}
