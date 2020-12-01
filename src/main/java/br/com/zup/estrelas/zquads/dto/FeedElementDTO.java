package br.com.zup.estrelas.zquads.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zup.estrelas.zquads.enums.FeedElementType;

public class FeedElementDTO {

    @NotNull(message = "Id user is mandatory")
    private Long idUser;

    private String name;

    @NotBlank(message = "Content is mandatory")
    private String content;

    @NotNull(message = "Date is mandatory")
    private LocalDateTime date = LocalDateTime.now();

    @NotNull(message = "Id Squad is mandatory")
    private Long idSquad;

    private FeedElementType type;

    // Getters and Setters

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
