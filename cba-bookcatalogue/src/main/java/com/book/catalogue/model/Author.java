package com.book.catalogue.model;

/**
 * This class represents the properties/attributes of an Author
 * 
 * @author Akila Chandrasekaran
 *
 */

public class Author {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3981179585793174176L;
	
	private String firstName;
	private String lastName;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
