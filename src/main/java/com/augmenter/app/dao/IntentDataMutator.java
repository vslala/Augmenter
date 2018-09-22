package com.augmenter.app.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.augmenter.app.utils.ReaderUtil;
import com.augmenter.app.vo.Action;
import com.augmenter.app.vo.IntentInfo;
import com.augmenter.app.vo.IntentParameter;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class IntentDataMutator {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	/**
	 * @param intentInfo
	 * @return
	 */
	public int persistIntentInfo(String intentName, String intentDisplayName) {
		KeyHolder holder  = new GeneratedKeyHolder();
		SqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("intentName", intentName)
				.addValue("intentDisplayName", intentDisplayName)
				.addValue("unixTimeStamp", System.currentTimeMillis());
		int lastInsertId = jdbcTemplate.update(ReaderUtil.readSQLFile("persist_intent_info"), paramMap, holder);
		log.debug("Last Insert Id: {}", lastInsertId);
		return lastInsertId;
	}


	/**
	 * Persist list of intent parameters into database
	 * @param intentInfo
	 * @return
	 */
	public int[] persistIntentParameters(Integer intentId, List<IntentParameter> intentParams) {
		List<SqlParameterSource> params =  new ArrayList<>();
		intentParams.forEach(item->
			params.add(new MapSqlParameterSource()
					.addValue("intentId", intentId)
					.addValue("dbColName", item.getDbColumnName())
					.addValue("queryPlaceholderName", item.getQueryPlaceholderName())
					.addValue("validRegex", item.getRegexValidationPattern())
					.addValue("isRequired", item.getIsRequired())
					.addValue("paramType", item.getParamType())
					)
		);
		
		return jdbcTemplate.batchUpdate(
				ReaderUtil.readSQLFile("persist_intent_params"), 
				params.toArray(new SqlParameterSource[params.size()]));
	}
	
	/**
	 * Persist single intent parameter into the database and returns last insert id
	 * @param intentId
	 * @param intentParam
	 * @return
	 */
	public Integer persistIntentParameter(Integer intentId, IntentParameter intentParam) {
		KeyHolder holder = new GeneratedKeyHolder();
		return jdbcTemplate.update(ReaderUtil.readSQLFile("persist_intent_params"), 
				new MapSqlParameterSource()
					.addValue("intentId", intentId)
					.addValue("dbColName", intentParam.getDbColumnName())
					.addValue("queryPlaceholderName", intentParam.getQueryPlaceholderName())
					.addValue("validRegex", intentParam.getRegexValidationPattern())
					.addValue("isRequired", intentParam.getIsRequired())
					.addValue("paramType", intentParam.getParamType()),
				holder);
	}


	/**
	 * Persist intent action info into the database and returns the last insert id
	 * @param intentId
	 * @param action
	 * @return
	 */
	public Integer persistIntentAction(Integer intentId, Action action) { 
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("intentId", intentId)
				.addValue("actionName", action.getName())
				.addValue("actionQuery", action.getQuery());
		return jdbcTemplate.update(ReaderUtil.readSQLFile("persist_intent_action"), params, holder);
		
	}

}
