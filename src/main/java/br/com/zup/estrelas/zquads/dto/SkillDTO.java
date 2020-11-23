package br.com.zup.estrelas.zquads.dto;

import br.com.zup.estrelas.zquads.enums.SkillType;

public class SkillDTO {

    private String name;

    private SkillType type;

    private Long thumbsUp;

    // Getters and Setters
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public Long getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(Long thumbsUp) {
        this.thumbsUp = thumbsUp;
    }
    
}
