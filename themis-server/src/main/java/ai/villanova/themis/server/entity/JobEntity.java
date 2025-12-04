package ai.villanova.themis.server.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "jobs", indexes = @Index(name = "idx_jobs_type_status", columnList = "type,status"))
public class JobEntity extends Auditable {
    public enum JobType {CRAWL, ENCODE, CURATE, TRAIN, TEST}

    public enum JobStatus {PENDING, RUNNING, COMPLETED, FAILED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status = JobStatus.PENDING;

    @Column(name = "started_at")
    private Instant startedAt;
    @Column(name = "completed_at")
    private Instant completedAt;
    @Column(name = "progress")
    private Float progress;

    public JobEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

    public Float getProgress() {
        return progress;
    }

    public void setProgress(Float progress) {
        this.progress = progress;
    }
}
