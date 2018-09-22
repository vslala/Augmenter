package com.augmenter.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augmenter.app.dao.IntentDataMutator;
import com.augmenter.app.vo.IntentInfo;

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

	
}
