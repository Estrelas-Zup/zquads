package br.com.zup.estrelas.zquads.enums;

public enum Gender {

    UNDEFIND("undefind"),
	CISGENDER("cisgender"),
	TRANSGENDER("transgender"),
	OTHER("other");
	
	private String value;
	
	Gender(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
