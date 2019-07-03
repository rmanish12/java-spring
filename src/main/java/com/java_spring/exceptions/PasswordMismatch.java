package com.java_spring.exceptions;

@SuppressWarnings("serial")
public class PasswordMismatch extends RuntimeException{
	public PasswordMismatch(String errorMessage) {
		super(errorMessage);
	}
}
