package br.com.zup.estrelas.zquads.domain;

import br.com.zup.estrelas.zquads.enums.FeedElementType;

public class Commentary {

    private Long idUser;

    private Long idSquad;

    private String content;

    private FeedElementType type = FeedElementType.COMMENTARY;

    // Getters and Setters

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdSquad() {
        return idSquad;
    }

    public void setIdSquad(Long idSquad) {
        this.idSquad = idSquad;
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
