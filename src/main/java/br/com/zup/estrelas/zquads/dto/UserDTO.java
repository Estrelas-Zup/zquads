package br.com.zup.estrelas.zquads.dto;

// import java.util.List;
import br.com.zup.estrelas.zquads.domain.Address;
// import br.com.zup.estrelas.zquads.domain.Skill;
// import br.com.zup.estrelas.zquads.domain.Squad;
// import br.com.zup.estrelas.zquads.domain.Task;
// import br.com.zup.estrelas.zquads.domain.User;
// import br.com.zup.estrelas.zquads.enums.Role;

public class UserDTO {

    private String name;

    private String email;

    private String nickname;

    private Address address;

    private String gitHub;

    private String instagram;

    private String race;

    private String gender;

    private String sexualOrientation;

    // private List<Role> role;
    //
    // private List<Squad> squads;
    //
    // private List<Skill> skills;
    //
    // private List<User> friends;
    //
    // private List<Task> tasks;

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

    // public List<Role> getRole() {
    // return role;
    // }
    //
    // public void setRole(List<Role> role) {
    // this.role = role;
    // }
    //
    // public List<Squad> getSquads() {
    // return squads;
    // }
    //
    // public void setSquads(List<Squad> squads) {
    // this.squads = squads;
    // }
    //
    // public List<Skill> getSkills() {
    // return skills;
    // }
    //
    // public void setSkills(List<Skill> skills) {
    // this.skills = skills;
    // }
    //
    // public List<User> getFriends() {
    // return friends;
    // }
    //
    // public void setFriends(List<User> friends) {
    // this.friends = friends;
    // }
    //
    // public List<Task> getTasks() {
    // return tasks;
    // }
    //
    // public void setTasks(List<Task> tasks) {
    // this.tasks = tasks;
    // }

}
