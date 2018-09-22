package com.augmenter.app.vo;

import lombok.Getter;
import lombok.Setter;

public class Query {

	@Getter @Setter private String sql;
	
	public Query() {}
	
	public Query(String sql){
		this.sql =  sql;
	}
	
	public static Query newInstance(String sql) {
		return new Query(sql);
	}

}
