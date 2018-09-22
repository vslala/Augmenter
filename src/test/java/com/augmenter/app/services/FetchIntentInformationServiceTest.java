package com.augmenter.app.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.augmenter.app.dao.IntentDataAccessor;
import com.augmenter.app.services.FetchIntentInformationService;
import com.augmenter.app.vo.IntentParameter;
import com.augmenter.app.vo.Query;
import com.augmenter.app.vo.UserPayload;

@RunWith(MockitoJUnitRunner.class)
public class FetchIntentInformationServiceTest {

	private static final String TEST_INTENT = "Test.Intent";
	
	@Mock
	IntentDataAccessor intentDataAccessor;
	
	@InjectMocks
	FetchIntentInformationService intentInfo;
	
	@Before
	public void  setup() {
	}
	
	@Test
	public void itShouldCreateObject()  {
		assertNotNull(intentInfo);
	}
	
}
