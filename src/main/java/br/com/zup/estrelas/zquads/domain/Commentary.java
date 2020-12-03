package br.com.zup.estrelas.zquads.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zup.estrelas.zquads.enums.FeedElementType;

public class Commentary {

    @NotNull(message="Id user is mandatory")
    private Long idUser;

    @NotBlank(message="Content is mandatory")
    private String content;

    private FeedElementType type = FeedElementType.COMMENTARY;

    // Getters and Setters

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FeedElementType getType() {
        return type;
    }

    public void setType(FeedElementType type) {
        this.type = type;
    }

}
