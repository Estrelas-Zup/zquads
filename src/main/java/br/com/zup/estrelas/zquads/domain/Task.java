package br.com.zup.estrelas.zquads.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "task")
public class Task implements Serializable{

    private static final long serialVersionUID = -6533086328480540626L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Long idTask;
    
    @ManyToOne
    @JoinColumn(name = "id_user", foreignKey=@ForeignKey(name="FK_ID_TASK_USER"), referencedColumnName = "id_user")
    @JsonBackReference
    private User author;
    
    @Column(name = "id_user", insertable = false, updatable = false)
    private Long idUser;
    
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "id_squad", foreignKey=@ForeignKey(name="FK_ID_TASK_SQUAD"), referencedColumnName = "id_squad")
    @JsonBackReference
    @JsonIgnore
    private Squad squad;
    
    @Column(name = "id_squad", insertable = false, updatable = false)
    private Long idSquad;

    @Column(name = "starting_date", nullable = false)
    private LocalDateTime startingDate = LocalDateTime.now();

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
    
    public User getAuthor() {
        return author;
    }
    
    public void setAuthor(User author) {
        this.author = author;
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

    public Squad getSquad() {
        return squad;
    }

    public void setSquad(Squad squad) {
        this.squad = squad;
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