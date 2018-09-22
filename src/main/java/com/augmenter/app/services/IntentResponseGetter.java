package com.augmenter.app.services;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.augmenter.app.vo.IntentInfo;

public interface IntentResponseGetter {

	ResultSet getIntentResponseData(IntentInfo intentInfo, Map<String, String> transformedParams);

}
