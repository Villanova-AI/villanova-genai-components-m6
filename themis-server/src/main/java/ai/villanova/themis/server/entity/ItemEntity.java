package ai.villanova.themis.server.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "items", indexes = {
        @Index(name = "idx_items_url", columnList = "url"),
        @Index(name = "idx_items_title", columnList = "title")
})
public class ItemEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)   // URI in DTO
    private String url;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column(name = "content_version", nullable = false)
    private Integer contentVersion = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
        this.contentVersion++;
    }

    public Integer getContentVersion() {
        return contentVersion;
    }
}
