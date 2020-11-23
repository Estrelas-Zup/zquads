package br.com.zup.estrelas.zquads.domain;

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
import com.fasterxml.jackson.annotation.JsonBackReference;
import br.com.zup.estrelas.zquads.enums.FeedElementType;

@Entity
@Table(name = "feed_element")
public class FeedElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feed_element")
    private Long idFeedElement;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_user", foreignKey=@ForeignKey(name="FK_ID_FEED_ELEMENT_USER"))
    private User author;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_squad", foreignKey=@ForeignKey(name="FK_ID_FEED_ELEMENT_SQUAD"))
    private Squad squad;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
