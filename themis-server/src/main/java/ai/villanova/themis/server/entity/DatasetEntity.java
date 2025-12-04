package ai.villanova.themis.server.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "datasets")
public class DatasetEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String name;
    @Column(columnDefinition = "text") private String description;

    @Column(name = "start_timestamp", nullable = false)
    private Instant startTimestamp;

    @Column(name = "end_timestamp", nullable = false)
    private Instant endTimestamp;

    public DatasetEntity() {}

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
        this.description = description;
    }

    public Instant getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Instant startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Instant getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Instant endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
