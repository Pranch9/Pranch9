package ru.pranch.testtaskrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.model.Comment;

import java.util.List;

@Repository
public interface CommentRepos extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArtifactId(Artifact artifactId);
    List<Comment> findAllByContent(String content);
}
