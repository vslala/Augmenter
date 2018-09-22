package com.augmenter.app.services;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

import com.augmenter.app.dao.IntentDataMutator;

@RunWith(MockitoJUnitRunner.class)
public class PersistIntentInfoServiceTest {

	@Mock
	IntentDataMutator intentInfoMutator;
	
	@Mock
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@InjectMocks
	PersistIntentInfoService  persistIntentService;
	
	@Before
	public void setup() {
		Mockito.when(jdbcTemplate.update(Matchers.anyString(), Matchers.any(SqlParameterSource.class), Matchers.any(KeyHolder.class)))
				.thenReturn(1);
	}
	
	
}
