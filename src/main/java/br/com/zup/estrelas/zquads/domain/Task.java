package br.com.zup.estrelas.zquads.domain;

import static java.time.LocalDateTime.now;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Long idTask;
    
    @Column(name = "id_user")
    private Long idUser;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(name = "id_squad")
    private Long idSquad;

    @Column(name = "starting_date", nullable = false)
    private LocalDateTime startingDate = now();

    @Column(name = "finishing_date")
    private LocalDateTime finishingDate = null;
    
    @Column(name = "is_finished")
    private boolean isFinished = false;

    // Getters and Setters

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }
        
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

    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDateTime getFinishingDate() {
        return finishingDate;
    }

    public void setFinishingDate(LocalDateTime finishingDate) {
        this.finishingDate = finishingDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

}