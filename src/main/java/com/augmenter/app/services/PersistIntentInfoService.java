package com.augmenter.app.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augmenter.app.dao.IntentDataMutator;
import com.augmenter.app.vo.IntentInfo;
import com.augmenter.app.vo.IntentParameter;
import com.augmenter.app.vo.forms.CreateNewIntentForm;
import com.augmenter.app.vo.forms.CreateNewParameterForm;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class PersistIntentInfoService {

	@Autowired
	private IntentDataMutator intentDataMutator;
	
	public boolean saveIntent(IntentInfo intentInfo) {
		Integer lastInsertId = intentDataMutator.persistIntentInfo(
				intentInfo.getName(),
				intentInfo.getDisplayName()
				);
		intentDataMutator.persistIntentParameters(lastInsertId, intentInfo.getParameters());
		intentDataMutator.persistIntentAction(lastInsertId, intentInfo.getAction());
		return lastInsertId  >  0;
	}
	
	public boolean createNewIntent(CreateNewIntentForm newIntentForm)
	{
		Integer lastInsertId = intentDataMutator.persistIntentInfo(
				newIntentForm.getIntentName(), 
				newIntentForm.getIntentDisplayName());
		
		return lastInsertId > 0;
	}

	public boolean createNewParameter(@Valid CreateNewParameterForm newParameterForm) {
		
		IntentParameter intentParam = IntentParameter.newInstance()
					.dfInputName(newParameterForm.getInputName())
					.dbColumnName(newParameterForm.getDbColumnName())
					.regexValidationPattern(newParameterForm.getRegexValidationPattern())
					.isRequired(newParameterForm.getIsRequired())
					.paramType(newParameterForm.getParamType())
					.queryPlaceholderName(newParameterForm.getQueryPlaceholderName())
					.intentId(newParameterForm.getIntentId())
				.build();
		Integer lastInsertId = intentDataMutator.persistIntentParameter(newParameterForm.getIntentId(), intentParam );
		log.info("Create New Parameter.\nNew Parameter ID: "  + lastInsertId);
		return lastInsertId > 0;
	}

	
}
