package com.shan.app.web.errors;

public class MenuNotExistException extends RuntimeException {

	public MenuNotExistException() { }
	
	public MenuNotExistException(String message) {
		super(message);
	}
	
	public MenuNotExistException(Throwable cause) {
		super(cause);
	}
	
	public MenuNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
