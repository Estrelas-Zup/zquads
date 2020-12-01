package br.com.zup.estrelas.zquads.domain;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "squad")
public class Squad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_squad")
    private Long idSquad;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    private String bio;

    @Column(name = "starting_date", nullable = false)
    private LocalDate startingDate = LocalDate.now();

    @Column(name = "finishing_date")
    private LocalDate finishingDate = null;

    @Column(name = "is_finished")
    private boolean isFinished = false;

    private String repository;

    @JsonBackReference
    @ManyToMany
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "FK_ID_SQUAD_USER_ADMIN"),
            referencedColumnName = "idUser")
    @JsonIgnore
    private List<User> admins;

    @Column(name = "id_user")
    private Long idUser;

    @JsonBackReference
    @ManyToMany
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "FK_ID_SQUAD_USER_MEMBER"),
            referencedColumnName = "idUser")
    @JsonIgnore
    private List<User> members;

    @OneToMany
    @JoinColumn(name = "id_squad", foreignKey = @ForeignKey(name = "FK_ID_SQUAD_TASK"))
    private List<Task> tasks;

    @Column(name = "feed_elements")
    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "id_squad")
    private List<FeedElement> feedElements;

    // Getters and Setters

    public Long getIdSquad() {
        return idSquad;
    }

    public void setIdSquad(Long idSquad) {
        this.idSquad = idSquad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getFinishingDate() {
        return finishingDate;
    }

    public void setFinishingDate(LocalDate finishingDate) {
        this.finishingDate = finishingDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
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
