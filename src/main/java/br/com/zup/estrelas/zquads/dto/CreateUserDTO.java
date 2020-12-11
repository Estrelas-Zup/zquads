package br.com.zup.estrelas.zquads.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CreateUserDTO {
    @NotBlank(message = "NAME IS MANDATORY")
    private String name;

    @NotBlank(message = "E-MAIL IS MANDATORY")
    @Email(message = "ENTER A VALID E-MAIL")
    private String email;

    @NotBlank(message = "NICKNAME IS MANDATORY")
    private String nickname;

    @NotBlank(message = "PASSWORD IS MANDATORY")
    private String password;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
