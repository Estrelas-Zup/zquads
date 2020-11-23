package br.com.zup.estrelas.zquads.enums;

public enum SexualOrientation {

	HETEROSEXUAL("heterosexual"),
	HOMOSEXUAL("homosexual"),
	BISEXUAL("bisexual"),
	ASEXUAL("asexual"),
	OTHER("other");
	
	private String value;
	
	SexualOrientation(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
