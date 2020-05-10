package ru.pranch.testtaskrest.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "artifact_id")
    private Artifact artifactId;
    private String userId;
    private String content;

    public Comment() {
    }

    public Comment(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public Comment(Artifact artifactId, String userId, String content) {
        this.artifactId = artifactId;
        this.userId = userId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artifact getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Artifact artifactId) {
        this.artifactId = artifactId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", artifactId=" + artifactId +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
