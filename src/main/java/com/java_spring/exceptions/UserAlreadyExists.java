package com.java_spring.exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExists extends RuntimeException{
	public UserAlreadyExists(String errorMessage) {
		super(errorMessage);
	}
}
