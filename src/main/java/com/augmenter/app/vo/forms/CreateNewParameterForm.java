package com.augmenter.app.vo.forms;

import javax.validation.constraints.NotNull;

import com.augmenter.app.vo.ParameterType;

import lombok.Data;

@Data
public class CreateNewParameterForm {
	@NotNull
	private Integer intentId;
	@NotNull
	private String inputName;
	@NotNull
	private String dbColumnName;
	@NotNull
	private String queryPlaceholderName;
	private String regexValidationPattern = ".*";
	@NotNull
	private Boolean isRequired;
	@NotNull
	private ParameterType paramType;
}
