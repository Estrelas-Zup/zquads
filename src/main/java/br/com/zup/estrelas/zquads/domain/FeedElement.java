package br.com.zup.estrelas.zquads.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.zup.estrelas.zquads.enums.FeedElementType;

@Entity
@Table(name = "feed_element")
public class FeedElement implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feed_element")
    private Long idFeedElement;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_user", foreignKey=@ForeignKey(name="FK_ID_FEED_ELEMENT_USER"), referencedColumnName="id_user")
    @JsonIgnore
    private User author;

    @Column(name="id_user", insertable=false, updatable=false)
    @NotNull(message="Id user is mandatory")
    private Long idUser;
    
    @Column(nullable = false)
    @NotBlank(message="Content is mandatory")
    private String content;

    @Column(nullable = false) 
    @NotNull(message="Date is mandatory")
    private LocalDateTime date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_squad", foreignKey=@ForeignKey(name="FK_ID_FEED_ELEMENT_SQUAD"), referencedColumnName="id_squad")
    @JsonIgnore
    private Squad squad;

    @Column(name="id_squad", insertable=false, updatable=false)
    @NotNull(message="Id Squad is mandatory")
    private Long idSquad;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FeedElementType type;
 
    // Getters and Setters

    public Long getIdFeedElement() {
        return idFeedElement;
    }

    public void setIdFeedElement(Long idFeedElement) {
        this.idFeedElement = idFeedElement;
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
    
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getIdSquad() {
        return idSquad;
    }

    public void setIdSquad(Long idSquad) {
        this.idSquad = idSquad;
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

    public FeedElementType getType() {
        return type;
    }

    public void setType(FeedElementType type) {
        this.type = type;
    }

}
