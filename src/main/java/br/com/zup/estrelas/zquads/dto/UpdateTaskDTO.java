package br.com.zup.estrelas.zquads.dto;

public class UpdateTaskDTO {

    private Long idUser;
    
    private String name;

    private String content;

    
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
    
}
