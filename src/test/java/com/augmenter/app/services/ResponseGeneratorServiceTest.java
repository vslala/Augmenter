package com.augmenter.app.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.augmenter.app.fixtures.MockDataFixture;

@RunWith(MockitoJUnitRunner.class)
public class ResponseGeneratorServiceTest {

	@Mock
	FetchIntentInformationService intentInfo;
	
	@InjectMocks
	ResponseGeneratorService responseGenerator;
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void itShouldInitObject() {
		assertNotNull(responseGenerator);
	}
	
	@Test
	public void itShouldPrintIntentName(){
		responseGenerator.generateResponse(MockDataFixture.WEBHOOK_REQUEST_OBJECT);
	}
	
}
