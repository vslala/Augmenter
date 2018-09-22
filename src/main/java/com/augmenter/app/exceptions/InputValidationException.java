package com.augmenter.app.exceptions;

public class InputValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InputValidationException(){
		super();
	}
	
	public InputValidationException(String msg) {
		super(msg);
	}
	
}
