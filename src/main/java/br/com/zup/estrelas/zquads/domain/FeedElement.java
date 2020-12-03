package br.com.zup.estrelas.zquads.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zup.estrelas.zquads.enums.FeedElementType;

@Entity
@Table(name = "feed_element")
public class FeedElement implements Serializable {

    private static final long serialVersionUID = -364272089595690154L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_feed_element")
    private Long idFeedElement;

    @Column(name="id_user")
    @NotNull(message="Id user is mandatory")
    private Long idUser;

    @Column(nullable=false)
    @NotBlank(message="Content is mandatory")
    private String content;

    @Column(nullable = false)
    @NotNull(message="Date is mandatory")
    private LocalDateTime date;

    @Column(name = "id_squad")
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

    public FeedElementType getType() {
        return type;
    }

    public void setType(FeedElementType type) {
        this.type = type;
    }

}
