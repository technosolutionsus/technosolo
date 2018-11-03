package com.sollers.banking.errors;

import org.apache.log4j.Logger;

public class SollersSecurityException extends Exception {

	protected static final long serialVersionUID = 1L;

	/** The logger. */
	protected final static Logger logger = Logger.getLogger(SollersSecurityException.class);

	/**
	 *
	 */
	protected String logMessage = null;

	protected String errorCode = null;

	/**
	 * Instantiates a new sollers security exception.
	 */
	protected SollersSecurityException() {
	}

	/**
	 * Instantiates a new sollers security exception with a user message. (Needed by anything which subclasses this.)
	 *
	 * @param userMessage Message displayed to user.
	 */
	protected SollersSecurityException(String userMessage) {
		super(userMessage);
	}

	/**
	 * Instantiates a new sollers security exception with a user message and cause. (Needed by anything which subclasses this.)
	 *
	 * @param userMessage Message displayed to user.
	 * @param cause       The cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates
	 *                    that the cause is nonexistent or unknown.)
	 */
	protected SollersSecurityException(String userMessage, Throwable cause) {
		// hidden
		super(userMessage, cause);
	}

	/**
	 * Creates a new instance of SollersSecurityException. This exception is automatically logged, so that simply by using this API,
	 * applications will generate an extensive security log.
	 * 
	 * @param userMessage the message displayed to the user
	 * @param logMessage  the message logged
	 */
	public SollersSecurityException(String userMessage, String logMessage) {
		super(userMessage);
		this.logMessage = logMessage;
	}

	public SollersSecurityException(String errorCode, String userMessage, String logMessage) {
		super(userMessage);
		this.errorCode = errorCode;
		this.logMessage = logMessage;
	}

	/**
	 * Creates a new instance of SollersSecurityException that includes a root cause Throwable.
	 * 
	 * @param userMessage the message displayed to the user
	 * @param logMessage  the message logged
	 * @param cause       the cause
	 */
	public SollersSecurityException(String userMessage, String logMessage, Throwable cause) {
		super(userMessage, cause);
		this.logMessage = logMessage;
	}

	/**
	 * Returns message meant for display to users
	 *
	 * @return a String containing a message that is safe to display to users
	 */
	public String getUserMessage() {
		return getMessage();
	}

	/**
	 * Returns a message that is safe to display in logs, but may contain sensitive information and therefore probably should not be displayed
	 * to users.
	 * 
	 * @return a String containing a message that is safe to display in logs, but probably not to users as it may contain sensitive information.
	 */
	public String getLogMessage() {
		return logMessage;
	}
}