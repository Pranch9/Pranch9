package ru.pranch.test_task.model;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long artifactId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    private String comment;

    public Comment() {
    }

    public Comment(Long artifactId, User user, String comment) {
        this.artifactId = artifactId;
        this.userId = user;
        this.comment = comment;
    }
    public String getUserId() {
        return userId != null ? userId.getUsername() : "<none>";
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public void setUserId(User userId) {
        this.userId = userId;
    }
}
