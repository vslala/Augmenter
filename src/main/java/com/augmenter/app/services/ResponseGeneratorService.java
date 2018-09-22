package com.augmenter.app.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augmenter.app.dao.IntentDataAccessor;
import com.augmenter.app.utils.Validator;
import com.augmenter.app.vo.IntentInfo;
import com.augmenter.app.vo.IntentParameter;
import com.augmenter.app.vo.ParameterType;
import com.dialogflow.response.vo.WebhookRequestVO;
import com.dialogflow.response.vo.WebhookResponseVO;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Varun Shrivastava
 *
 */
@Log4j
@Service
public class ResponseGeneratorService {

	@Autowired
	private  FetchIntentInformationService intentInfoService;
	
	@Autowired
	private IntentResponseAccessor intentResponseService;
	
	@Autowired
	private IntentResponseGenerator intentResponseGeneratorService;
	
	/**
	 * Performs complex logic and returns response
	 * @param webhookRequest
	 * @return
	 */
	public WebhookResponseVO generateResponse(WebhookRequestVO webhookRequest) {
		log.info("Request is from Intent: " + webhookRequest.getQueryResult().getIntent().getDisplayName());
		IntentInfo intentInfo = intentInfoService.getIntentSchemaInfo(webhookRequest.getQueryResult().getIntent().getDisplayName());
		
		// validate parameters
		Map<String, String> transformedParams = validateAndTransformRequestParams(webhookRequest, intentInfo);
		
		// fire query into database to retrieve result
		intentInfoService.fetchIntentResponseData(intentInfo, transformedParams);
		
		// generate response
		
		
		return null;
	}

	private Map<String, String> validateAndTransformRequestParams(WebhookRequestVO webhookRequest,
			IntentInfo intentInfo) {
		Map<String, Object> dfParams = webhookRequest.getQueryResult().getParameters();
		Map<String, String> transformedParams = new LinkedHashMap<>();
		for (IntentParameter param: intentInfo.getParameters())
		{
			ParameterType paramType = param.getParamType();
			if (paramType.equals(ParameterType.DATE_PERIOD))
			{
				Map<String, Object> datePeriod = (Map<String, Object>) dfParams.get(param.getDfInputName()); // get("date-period');
				Validator.validateRegex(param.getRegexValidationPattern(), datePeriod.get("startDate").toString()); //  throws exception if regex do not match
				transformedParams.put("startDate", datePeriod.get("startDate").toString());
				transformedParams.put("endDate", datePeriod.get("endDate").toString());
			}
			
			else 
			{
				transformedParams.put(param.getDfInputName(), dfParams.get(param.getDfInputName()).toString()); // e.g. put('amount', get('amount'))
			}
		}
		return transformedParams;
	}

}
