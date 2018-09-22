package com.augmenter.app.utils;

import org.springframework.util.Assert;

import com.augmenter.app.exceptions.InputValidationException;

public class Validator {
	private Validator(){}

	public static void validateRegex(String regexValidationPattern, String paramValue) {
		Assert.notNull(paramValue, "Parameter Value is Null.");
		
		if (! paramValue.matches(regexValidationPattern))
			throw new InputValidationException("Input does not match the validation pattern!");
		
	}
}
