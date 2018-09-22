package com.augmenter.app.vo;

import java.util.ArrayList;
import java.util.List;

import com.dialogflow.utils.ChatbotUtil;

import lombok.Data;

@Data
public class IntentInfo {
	private Integer id;
	private String name;
	private String displayName;
	private List<IntentParameter> parameters;
	private Action action;
	
	public static IntentInfo newInstance() {
		return new IntentInfo();
	}
	
	public IntentInfo  name(String name) {
		setName(name);
		return this;
	}
	
	public IntentInfo displayName(String displayName) {
		setDisplayName(displayName);
		return this;
	}
	
	public IntentInfo addIntentParameter(String dfInputName, String dbColumnName, String queryPlaceholderName, String validationPattern) {
		if (! ChatbotUtil.isSet(parameters))
			parameters  = new ArrayList<>();
		
//		parameters.add(IntentParameter.newInstance(dfInputName, dbColumnName, queryPlaceholderName, validationPattern));
		return this;
	}
}
