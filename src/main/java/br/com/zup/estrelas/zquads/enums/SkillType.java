package br.com.zup.estrelas.zquads.enums;

public enum SkillType {

	HARD("hardskill"),
	SOFT("softskill");
	
	private String value;
	
	SkillType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
