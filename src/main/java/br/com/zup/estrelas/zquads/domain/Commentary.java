package br.com.zup.estrelas.zquads.domain;

public class Commentary {

    private User author;
        
    private String content;

    private FeedElement feedElement;

    // Getters and Setters
    
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FeedElement getFeedElement() {
        return feedElement;
    }

    public void setFeedElement(FeedElement feedElement) {
        this.feedElement = feedElement;
    }
    
    
}
