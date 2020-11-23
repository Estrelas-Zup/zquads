package br.com.zup.estrelas.zquads.enums;

public enum Role {

	BACKEND("backend"),
	FRONTEND("frontend"),
	FULLSTACK("fullstack"),
	MOBILE("mobile"),
	DESIGNER("designer");
	
	private String value;
	
	Role(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
