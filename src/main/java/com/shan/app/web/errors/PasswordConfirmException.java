package com.shan.app.web.errors;

public class PasswordConfirmException extends RuntimeException {

	public PasswordConfirmException() { }
	
	public PasswordConfirmException(String message) {
		super(message);
	}
	
	public PasswordConfirmException(Throwable cause) {
		super(cause);
	}
	
	public PasswordConfirmException(String message, Throwable cause) {
		super(message, cause);
	}
}
