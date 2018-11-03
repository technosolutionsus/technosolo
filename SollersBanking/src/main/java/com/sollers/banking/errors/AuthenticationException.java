package com.sollers.banking.errors;

public class AuthenticationException extends SollersSecurityException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new authentication exception.
	 */
	protected AuthenticationException() {
	}

	/**
	 * Creates a new instance of AuthenticationException.
	 * 
	 * @param userMessage the message displayed to the user
	 * @param logMessage  the message logged
	 */
	public AuthenticationException(String userMessage, String logMessage) {
		super(userMessage, logMessage);
	}

	/**
	 * Instantiates a new authentication exception.
	 * 
	 * @param userMessage the message displayed to the user
	 * @param logMessage  the message logged
	 * @param cause       the root cause
	 */
	public AuthenticationException(String userMessage, String logMessage, Throwable cause) {
		super(userMessage, logMessage, cause);
	}
}