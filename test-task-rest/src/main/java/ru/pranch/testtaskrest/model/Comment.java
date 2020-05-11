package ru.pranch.testtaskrest.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(generator = "comment")
    @GenericGenerator(
            name = "comment",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "COMMENT"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    private Long id;

    @ManyToOne(targetEntity = Artifact.class)
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
