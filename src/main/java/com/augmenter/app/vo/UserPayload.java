package com.augmenter.app.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Varun Shrivastava
 *
 */
public class UserPayload {
	
	@Getter @Setter private String webhookJsonKey;
	@Getter @Setter private String queryPlaceholderName;
	@Getter @Setter private String defaultValue;

	private UserPayload() {}
	
	private UserPayload(String webhookJsonKey, String queryPlaceholderName, String defaultValue) {
		this.webhookJsonKey = webhookJsonKey;
		this.queryPlaceholderName  =  queryPlaceholderName;
		this.defaultValue = defaultValue;
	}
	
	private UserPayload(String webhookJsonKey, String queryPlaceholderName) {
		this.webhookJsonKey = webhookJsonKey;
		this.queryPlaceholderName  =  queryPlaceholderName;
	}

	public static UserPayload newInstance(String webhookJsonKey, String queryPlaceholderName) {
		return new UserPayload(webhookJsonKey, queryPlaceholderName);
	}
	
	public static UserPayload newInstance(String webhookJsonKey, String queryPlaceholderName, String defaultValue) {
		return new UserPayload(webhookJsonKey, queryPlaceholderName, defaultValue);
	}

}
