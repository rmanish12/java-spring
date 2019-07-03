package com.java_spring.exceptions;

@SuppressWarnings("serial")
public class UserNotFound extends RuntimeException{
	public UserNotFound(String errorMessage) {
		super(errorMessage);
	}
}
