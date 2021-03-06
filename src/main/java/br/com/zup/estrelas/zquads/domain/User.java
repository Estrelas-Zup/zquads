package br.com.zup.estrelas.zquads.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import br.com.zup.estrelas.zquads.enums.Gender;
import br.com.zup.estrelas.zquads.enums.Race;
import br.com.zup.estrelas.zquads.enums.Role;
import br.com.zup.estrelas.zquads.enums.SexualOrientation;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Embedded
    private Address address;

    @Column(name = "git_hub")
    private String gitHub;

    private String instagram;

    @Column(name = "image_url")
    private String imageUrl;
    
    @Enumerated(EnumType.STRING)
    private Race race = Race.UNDEFIND;

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.UNDEFIND;

    @Column(name = "sexual_orientation")
    @Enumerated(EnumType.STRING)
    private SexualOrientation sexualOrientation = SexualOrientation.UNDEFIND;

    @Enumerated(EnumType.STRING)
    private Role role = Role.UNDEFIND;

    @ManyToMany(mappedBy = "members")
    @JsonIgnore
    private List<Squad> squads;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "id_user")
    private List<Skill> skills;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_friend"))
    private List<User> friends;

    @OneToMany
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "FK_ID_TASK_USER"))
    private List<Task> tasks;

    @OneToMany
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "FK_ID_FEED_ELEMENT_USER"))
    private List<FeedElement> feedElements;

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

    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public SexualOrientation getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(SexualOrientation sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Squad> getSquads() {
        return squads;
    }

    public void setSquads(List<Squad> squads) {
        this.squads = squads;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<FeedElement> getFeedElements() {
        return feedElements;
    }

    public void setFeedElements(List<FeedElement> feedElements) {
        this.feedElements = feedElements;
    }
}
