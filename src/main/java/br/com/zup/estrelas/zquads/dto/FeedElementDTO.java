package br.com.zup.estrelas.zquads.dto;

import java.time.LocalDateTime;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.enums.FeedElementType;

public class FeedElementDTO {

    private Long idUser;

    private String content;

    private LocalDateTime date = LocalDateTime.now();

    private Long idSquad;

    private FeedElementType type;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getIdSquad() {
        return idSquad;
    }

    public void setIdSquad(Long idSquad) {
        this.idSquad = idSquad;
    }

    public FeedElementType getType() {
        return type;
    }

    public void setType(FeedElementType type) {
        this.type = type;
    }
    
}
