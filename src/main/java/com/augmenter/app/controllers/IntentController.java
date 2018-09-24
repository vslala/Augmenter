package com.augmenter.app.controllers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.augmenter.app.services.FetchIntentInformationService;
import com.augmenter.app.services.PersistIntentInfoService;
import com.augmenter.app.vo.IntentInfo;
import com.augmenter.app.vo.forms.CreateNewIntentForm;
import com.augmenter.app.vo.forms.CreateNewParameterForm;
import com.dialogflow.utils.ChatbotUtil;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/intent")
public class IntentController {

	private static final Logger logger = Logger.getLogger(IntentController.class);
	
	@Autowired
	private PersistIntentInfoService persistIntentInfo;
	
	@Autowired
	private FetchIntentInformationService fetchIntentInfo;
	
	@PostMapping("/persist.json")
	public ResponseEntity<IntentInfo> persistIntentInfoIntoDatabase(@RequestBody String intentInfoJson) {
		logger.info(intentInfoJson);
		IntentInfo intentInfo = ChatbotUtil.convertJsonToObject(intentInfoJson, IntentInfo.class);
		boolean flag = persistIntentInfo.saveIntent(intentInfo);
		if (flag)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<IntentInfo>(HttpStatus.EXPECTATION_FAILED);
	}
	
	@GetMapping
	public ModelAndView loadWelcomePage() {
		ModelAndView mav = new ModelAndView("welcome");
		mav.addObject("metaTitle", "Welcome");
		return mav;
	}
	
	@GetMapping("/new")
	public ModelAndView loadNewIntentFormPage()
	{
		ModelAndView mav  = new ModelAndView("new_intent");
		mav.addObject("intentName", "intentName"); //  form input field value 
		mav.addObject("intentDisplayName", "intentDisplayName"); // form input value
		mav.addObject("metaTitle", "Create New Intent");
		return mav;
	}
	
	/**
	 * Creates a new intent into the database
	 * @param newIntentForm
	 * @param binding
	 * @return
	 */
	@PostMapping("/new")
	public ModelAndView submitNewIntent(@Valid CreateNewIntentForm newIntentForm, BindingResult binding)
	{
		ModelAndView mav = new ModelAndView();
		if (binding.hasErrors())
		{
			log.warn("There are errors in the form submission.");
			mav.setViewName("new_intent");
			mav.addObject("metaTitle", "Create New Intent");
			mav.addObject("errors", binding.getFieldErrors());
			return mav;
		}
		
		if (persistIntentInfo.createNewIntent(newIntentForm))
			mav.addObject("message", "Intent Created Successfully!");
		
		mav.setViewName("new_intent-success");
		mav.addObject("metaTitle", "Intent Created!");
		return mav;
	}
	
	@GetMapping("/parameter")
	public ModelAndView loadNewParameterFormPage() 
	{
		List<IntentInfo> intents  =  fetchIntentInfo.getAllIntents();
		ModelAndView mav = new ModelAndView("new_parameter");
		mav.addObject("metaTitle", "Add New Intent Parameter");
		mav.addObject("intents", intents);
		return mav;
	}

	private <T> void addFormFields(ModelAndView mav, Class<T> classType) {
		List<String> inputNames  = new ArrayList<>();
		Arrays.asList(classType.getDeclaredFields())
			.forEach(field -> {
				field.setAccessible(true);
				inputNames.add(field.getName());
			});
		log.debug("Input Fields Size: "  +  inputNames.size());
		mav.addObject("inputFields", inputNames);
	}
	
	@PostMapping("/parameter")
	public ModelAndView submitNewParameterForm(@Valid CreateNewParameterForm newParameterForm,
			BindingResult binding)
	{
		ModelAndView mav = new ModelAndView("new_parameter-success");
		if (binding.hasErrors())
		{
			mav.setViewName("new_parameter");
			mav.addObject("errors", binding.getFieldErrors());
			mav.addObject("metaTitle", "Create New Parameter Error!");
			return mav;
		}
		
		if (persistIntentInfo.createNewParameter(newParameterForm))
			mav.addObject("message",  "New Intent Parameter Added!");
		
		mav.addObject("metaTitle", "Create New Parameter!");
		return mav;
	}
}
