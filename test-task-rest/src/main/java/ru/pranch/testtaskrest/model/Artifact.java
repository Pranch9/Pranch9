package ru.pranch.testtaskrest.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "artifacts")
public class Artifact {

    @Id
    @GeneratedValue(generator = "artifact")
    @GenericGenerator(
            name = "artifact",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ARTIFACT"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    private Long id;
    private String userId;
    private String category;
    private String description;

    @OneToMany(targetEntity = Comment.class, mappedBy = "artifactId", cascade={CascadeType.ALL},orphanRemoval=true)
    private final Set<Comment> commentsSet = new HashSet<Comment>();

    @Column(updatable = false)
    private LocalDateTime creationDate;

    public Artifact() {
    }

    public Artifact(String userId, String category, String description) {
        this.userId = userId;
        this.category = category;
        this.description = description;
    }

    public Artifact(Long id, String userId, String category, String description) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artifact artifact = (Artifact) o;
        return id.equals(artifact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
