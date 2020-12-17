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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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

    @ManyToMany
    @JoinTable(name = "squad_user",
        joinColumns = {@JoinColumn(name = "id_squad")},
        inverseJoinColumns = {@JoinColumn(name = "id_user")})
    private List<User> members;

    @Column(name = "id_user")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Long idUser;

    @OneToMany
    @JoinColumn(name = "id_squad", foreignKey = @ForeignKey(name = "FK_ID_SQUAD_TASK"))
    private List<Task> tasks;

    @Column(name = "feed_elements")
    @OneToMany
    @JoinColumn(name = "id_squad", foreignKey = @ForeignKey(name = "FK_ID_SQUAD_FEED_ELEMENT"))
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
