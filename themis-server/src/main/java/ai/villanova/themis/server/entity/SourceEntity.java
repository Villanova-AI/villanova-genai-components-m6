package ai.villanova.themis.server.entity;

import ai.villanova.themis.server.model.Source;
import jakarta.persistence.*;

@Entity
@Table(name = "sources")
public class SourceEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "text", nullable = false)
    private String description = "";

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Source.TypeEnum type;

    @Column(nullable = false, unique = true)
    private String url;

    public SourceEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = (description == null) ? "" : description;
    }

    public Source.TypeEnum getType() {
        return type;
    }

    public void setType(Source.TypeEnum type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
