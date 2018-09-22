package com.augmenter.app.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.augmenter.app.services.PersistIntentInfoService;
import com.augmenter.app.vo.IntentInfo;
import com.dialogflow.utils.ChatbotUtil;

@Controller
@RequestMapping("/intent")
public class IntentController {

	private static final Logger logger = Logger.getLogger(IntentController.class);
	
	@Autowired
	private PersistIntentInfoService persistIntentInfo;
	
	@PostMapping("/persist.json")
	public ResponseEntity<IntentInfo> persistIntentInfoIntoDatabase(@RequestBody String intentInfoJson) {
		logger.info(intentInfoJson);
		IntentInfo intentInfo = ChatbotUtil.convertJsonToObject(intentInfoJson, IntentInfo.class);
		boolean flag = persistIntentInfo.saveIntent(intentInfo);
		if (flag)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<IntentInfo>(HttpStatus.EXPECTATION_FAILED);
	}
}
