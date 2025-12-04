package ai.villanova.themis.server.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "models")
public class ModelEntity extends Auditable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String name;
    @Column(columnDefinition = "text") private String description;

    @Column(name = "split_timestamp", nullable = false)
    private Instant splitTimestamp;

    @ManyToOne
    @JoinColumn(name = "dataset_id")
    private DatasetEntity dataset;

    public ModelEntity() {}

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

    public Instant getSplitTimestamp() {
        return splitTimestamp;
    }

    public void setSplitTimestamp(Instant splitTimestamp) {
        this.splitTimestamp = splitTimestamp;
    }

    public DatasetEntity getDataset() {
        return dataset;
    }

    public void setDataset(DatasetEntity dataset) {
        this.dataset = dataset;
    }
}
