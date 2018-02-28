package com.shan.app.web.errors;

public class AdminDuplicatedException extends RuntimeException {
	
	public AdminDuplicatedException() {
	}

	public AdminDuplicatedException(String message) {
		super(message);
	}
	
	public AdminDuplicatedException(Throwable cause) {
		super(cause);
	}

	public AdminDuplicatedException(String message, Throwable cause) {
		super(message, cause);
	}

}
