package br.com.zup.estrelas.zquads.enums;

public enum Race {

    UNDEFIND("undefind"),
    BLACK("black"),
    WHITE("white"),
    ASIAN("asian"),
    INDIAN("indian"),
    OTHER("other");
    
    private String value;
    
    Race(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
}
