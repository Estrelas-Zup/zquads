package br.com.zup.estrelas.zquads.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import br.com.zup.estrelas.zquads.domain.Address;
import br.com.zup.estrelas.zquads.enums.Role;

public class UserDTO {

    @NotBlank(message = "NAME IS MANDATORY")
    private String name;

    @NotBlank(message = "E-MAIL IS MANDATORY")
    @Email(message = "ENTER A VALID E-MAIL")
    private String email;

    @NotBlank(message = "NICKNAME IS MANDATORY")
    private String nickname;

    @NotBlank(message = "PASSWORD IS MANDATORY")
    private String password;

    private Address address;

    private String gitHub;

    private String instagram;

    private String race;

    private String gender;

    private String sexualOrientation;

    private Role role;

    // Getters and Setters

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(String sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
