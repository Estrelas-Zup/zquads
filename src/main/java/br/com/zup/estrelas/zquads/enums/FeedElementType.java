package br.com.zup.estrelas.zquads.enums;

public enum FeedElementType {

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
