package com.augmenter.app.services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.augmenter.app.vo.IntentInfo;
import com.augmenter.app.vo.IntentParameter;
import com.bma.bigquery.core.BigQueryConnect;
import com.bma.bigquery.utils.BigQueryHelper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service("bigQueryResponseGetter")
public class BigQueryResponseGetter implements IntentResponseGetter {

	@Override
	public ResultSet getIntentResponseData(IntentInfo intentInfo, Map<String, String> transformedParams) {
		String sql = intentInfo.getAction().getQuery().getSql();
		for (IntentParameter  param:  intentInfo.getParameters())
			sql = sql.replaceAll(param.getQueryPlaceholderName(), transformedParams.get(param.getDfInputName()));

		log.debug("Final Query\n" + sql);
		return BigQueryHelper.fetchDataByQuery(sql.replaceAll(":projectId", BigQueryConnect.getProjectId()));
		
	}

}
