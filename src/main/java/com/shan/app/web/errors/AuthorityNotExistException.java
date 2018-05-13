package com.shan.app.web.errors;

public class AuthorityNotExistException extends RuntimeException {
	
	public AuthorityNotExistException() { }
	
	public AuthorityNotExistException(String message) {
		super(message);
	}
	
	public AuthorityNotExistException(Throwable cause) {
		super(cause);
	}
	
	public AuthorityNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
