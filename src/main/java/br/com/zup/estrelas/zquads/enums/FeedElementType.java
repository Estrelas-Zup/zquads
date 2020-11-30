package br.com.zup.estrelas.zquads.enums;

import java.io.Serializable;

public enum FeedElementType implements Serializable {

	COMMENTARY("commentary"),
	TASK("task");
	
	private String value;
	
	FeedElementType(String value) {
		this.value = value;		
	}
	
	public String getValue() {
		return this.value;
	}
	
}
