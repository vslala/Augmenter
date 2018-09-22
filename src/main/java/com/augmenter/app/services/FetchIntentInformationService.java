package com.augmenter.app.services;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.augmenter.app.dao.IntentDataAccessor;
import com.augmenter.app.vo.IntentInfo;
import com.augmenter.app.vo.Query;

@Service
public class FetchIntentInformationService {

	@Autowired
	private IntentDataAccessor intentDataAccessor;
	
	@Autowired
	@Qualifier("bigQueryResponseGetter")
	private IntentResponseGetter intentResponseGetter;
	
	/**
	 * Fetch intent info, intent parameters and intent action
	 * from the database and return intentInfo object.
	 * 
	 * @param intentDisplayName
	 * @return
	 */
	public IntentInfo getIntentSchemaInfo(String  intentDisplayName) {
		IntentInfo intentInfo = intentDataAccessor.fetchIntentInfo(intentDisplayName);
		intentInfo.setParameters(intentDataAccessor.fetchIntentParams(intentInfo.getId()));
		intentInfo.setAction(intentDataAccessor.fetchIntentAction(intentInfo.getId()));
		return intentInfo;
	}

	public void fetchIntentResponseData(IntentInfo intentInfo, Map<String, String> transformedParams) {
		ResultSet rs = intentResponseGetter.getIntentResponseData(intentInfo, transformedParams);
	}

}
