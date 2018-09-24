package com.augmenter.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.augmenter.app.utils.ReaderUtil;
import com.augmenter.app.vo.Action;
import com.augmenter.app.vo.IntentInfo;
import com.augmenter.app.vo.IntentParameter;
import com.augmenter.app.vo.ParameterType;
import com.augmenter.app.vo.Query;
import com.augmenter.app.vo.UserPayload;

import lombok.extern.log4j.Log4j;

@Log4j
@Repository
public class IntentDataAccessor {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	/**
	 * Fetch list of parameters stored agaist the intent id
	 * @param intentId
	 * @return
	 */
	public List<IntentParameter> fetchIntentParams(Integer intentId) {
		return jdbcTemplate.queryForList(
				ReaderUtil.readSQLFile("fetch_intent_params"), 
				new MapSqlParameterSource().addValue("intentId", intentId), 
				IntentParameter.class);
		
	}
	
	/**
	 * Fetch action corresponding to intent id
	 * @param intentId
	 * @return
	 */
	public Action fetchIntentAction(Integer intentId) {
		return  jdbcTemplate.queryForObject(
				ReaderUtil.readSQLFile("fetch_intent_action"), 
				new MapSqlParameterSource()
					.addValue("intentId", intentId), 
				Action.class);
	}

	/**
	 * fetch intent info from the database based on intentDisplayName
	 * @param intentDisplayName
	 * @return
	 */
	public IntentInfo fetchIntentInfo(String intentDisplayName) {
		return jdbcTemplate.queryForObject(
				ReaderUtil.readSQLFile("fetch_intent_info"), 
				new MapSqlParameterSource()
					.addValue("intentDisplayName", intentDisplayName), 
				new RowMapper<IntentInfo>() {

					@Override
					public IntentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
						IntentInfo info  = new IntentInfo();
						info.setDisplayName(rs.getString("intent_display_name"));
						info.setName(rs.getString("intent_name"));
						info.setId(rs.getInt("intent_id"));
						return info;
					}
					
				});
	}

	/**
	 * Fetch the list of all intents from database
	 * @return
	 */
	public List<IntentInfo> fetchAllIntents() {
		
		List<IntentInfo> intents  =  jdbcTemplate.query(
				ReaderUtil.readSQLFile("fetch_all_intents"), 
				new RowMapper<IntentInfo>() {
					@Override
					public IntentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
						IntentInfo  intent =  new IntentInfo();
						intent.setId(rs.getInt("intent_id"));
						intent.setDisplayName(rs.getString("intent_display_name"));
						intent.setName(rs.getString("intent_name"));
						return intent;
					}
				});
		log.info("Total Intents: " + intents.size());
		return intents;
	}
	
	
}
