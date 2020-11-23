package br.com.zup.estrelas.zquads.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import br.com.zup.estrelas.zquads.domain.FeedElement;
import br.com.zup.estrelas.zquads.domain.Task;
import br.com.zup.estrelas.zquads.domain.User;

public class SquadDTO {

    private String name;

    private String projectName;

    private String bio;

    private LocalDate startingDate;

    private LocalDate finishingDate;

    private boolean isFinished;

    private Optional<String> repository;

    private List<User> admins;

    private List<User> members;

    private List<Task> tasks;

    private List<FeedElement> feedElements;

    // Getters and Setters
    
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

    public Optional<String> getRepository() {
        return repository;
    }

    public void setRepository(Optional<String> repository) {
        this.repository = repository;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
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
