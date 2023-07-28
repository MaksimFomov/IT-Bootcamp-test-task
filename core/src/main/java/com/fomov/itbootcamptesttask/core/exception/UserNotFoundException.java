package com.fomov.itbootcamptesttask.core.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Exception e) {
		super(e);
	}

	public UserNotFoundException(String message, Exception e) {
		super(message, e);
	}
}

