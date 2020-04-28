package ru.pranch.test_task.model;

import javax.persistence.*;

@Entity
@Table(name = "artifacts")
public class Artifact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;
    private String category;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    public Artifact() {
    }

    public Artifact(String date, String category, String description, User user) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.userId = user;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
