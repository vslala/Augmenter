package com.augmenter.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augmenter.app.services.ResponseGeneratorService;
import com.dialogflow.response.vo.WebhookRequestVO;
import com.dialogflow.response.vo.WebhookResponseVO;
import com.dialogflow.utils.ChatbotUtil;

@RestController
@RequestMapping("/v1/webhook")
public class WebhookController {
	
	@Autowired
	private ResponseGeneratorService responseGenerator;
	
	@PostMapping
	public WebhookResponseVO generateAugmentedResponse(@RequestBody String webhookFulfillmentRequest) {
		WebhookRequestVO webhookRequest = ChatbotUtil.convertJsonToObject(webhookFulfillmentRequest, WebhookRequestVO.class);
		return responseGenerator.generateResponse(webhookRequest);
	}
}
