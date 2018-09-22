package com.augmenter.app.vo;

import lombok.Getter;
import lombok.Setter;

public class IntentParameter {

	@Getter @Setter private Integer intentId;
	@Getter @Setter private String dfInputName;
	@Getter @Setter private String dbColumnName;
	@Getter @Setter private String queryPlaceholderName;
	@Getter @Setter private String regexValidationPattern;
	@Getter @Setter private Boolean isRequired;
	@Getter @Setter private ParameterType paramType;
	
	public IntentParameter(Builder builder) {
		this.intentId  = builder.intentId;
		this.dfInputName = builder.dfInputName;
		this.dbColumnName  =  builder.dbColumnName;
		this.queryPlaceholderName =  builder.queryPlaceholderName;
		this.regexValidationPattern = builder.regexValidationPattern;
		this.isRequired = builder.isRequired;
		this.paramType  = builder.paramType;
	}

	public static class Builder  {
		@Getter @Setter private Integer intentId;
		@Getter @Setter private String dfInputName;
		@Getter @Setter private String dbColumnName;
		@Getter @Setter private String queryPlaceholderName;
		@Getter @Setter private String regexValidationPattern;
		@Getter @Setter private Boolean isRequired;
		@Getter @Setter private ParameterType paramType;
		
		public Builder intentId(Integer intentId) {
			this.intentId = intentId;
			return this;
		}
		
		public Builder dfInputName(String dfInputName) {
			this.dfInputName = dfInputName;
			return this;
		}
		
		public Builder dbColumnName(String dbColumnName) {
			this.dbColumnName = dbColumnName;
			return this;
		}
		
		public Builder queryPlaceholderName(String queryPlaceholderName) {
			this.queryPlaceholderName  = queryPlaceholderName;
			return this;
		}
		
		public Builder regexValidationPattern(String regexValidationPattern) {
			this.regexValidationPattern =  regexValidationPattern;
			return this;
		}
		
		public Builder isRequired(Boolean isRequired) {
			this.isRequired  =  isRequired;
			return this;
		}
		
		public Builder paramType(ParameterType paramType) {
			this.paramType  = paramType;
			return this;
		}
		
		public IntentParameter build() {
			return new IntentParameter(this);
		}
	}
	
	public static Builder newInstance() {
		return new IntentParameter.Builder();
	}

}
