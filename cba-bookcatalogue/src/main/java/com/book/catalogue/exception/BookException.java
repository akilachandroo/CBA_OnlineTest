package com.book.catalogue.exception;

public class BookException extends RuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4261192889595737691L;
	
	private String errorMessage;
	 
	public String getErrorMessage() {
		return errorMessage;
	}
	
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
