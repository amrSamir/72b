package com.OJToolkit.client.Exceptions;

import java.io.Serializable;

/**
 * @author 72B
 *         This exception is thrown due to trying to access a function when you
 *         are not logged in
 */
@SuppressWarnings("serial")
public class NotLoggedInException extends Exception implements Serializable {

	/**
	 * This exception is thrown due to trying to access a function when you are
	 * not logged in
	 */
	public NotLoggedInException() {
		super();
	}

	/**
	 * This exception is thrown due to trying to access a function when you are
	 * not logged in
	 * 
	 * @param message
	 *            The message to be displayed
	 */
	public NotLoggedInException(String message) {
		super(message);
	}
}
