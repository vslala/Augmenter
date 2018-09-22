package com.augmenter.app.vo;

import lombok.Getter;
import lombok.Setter;

public class Action {
	@Getter @Setter private Integer actionId;
	@Getter @Setter private Integer intentId;
	@Getter @Setter private String name;
	@Getter @Setter private Query query; 
	
	public Action(){}
	
	public Action (Integer intentId, String name, Query query) {
		this.intentId = intentId;
		this.query  =  query;
		this.name  = name;
	}
}
