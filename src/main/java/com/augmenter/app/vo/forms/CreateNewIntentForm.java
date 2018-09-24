package com.augmenter.app.vo.forms;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateNewIntentForm {
	private String intentName;
	@NotNull
	private String intentDisplayName;
}
