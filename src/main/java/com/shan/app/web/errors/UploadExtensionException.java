package com.shan.app.web.errors;

public class UploadExtensionException extends RuntimeException {

	public UploadExtensionException() { }

	public UploadExtensionException(String message) {
		super(message);
	}
	
	public UploadExtensionException(Throwable cause) {
		super(cause);
	}

	public UploadExtensionException(String message, Throwable cause) {
		super(message, cause);
	}
}
