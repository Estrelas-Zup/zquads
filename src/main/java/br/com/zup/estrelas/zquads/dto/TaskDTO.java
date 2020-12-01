package br.com.zup.estrelas.zquads.dto;

import javax.validation.constraints.NotNull;

public class TaskDTO {
    
    @NotNull(message = "Id user is mandatory")
    private Long idUser;

    @NotNull(message = "Id user is mandatory")
    private String name;

    @NotNull(message = "Id user is mandatory")
    private String content;

    @NotNull(message = "Id user is mandatory")
    private Long idSquad;

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

    public Long getIdSquad() {
        return idSquad;
    }

    public void setIdSquad(Long idSquad) {
        this.idSquad = idSquad;
    }
    
}
