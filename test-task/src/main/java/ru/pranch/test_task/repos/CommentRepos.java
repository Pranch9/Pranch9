package ru.pranch.test_task.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pranch.test_task.model.Comment;

import java.util.List;

public interface CommentRepos extends JpaRepository<Comment, Long> {

    List<Comment> findAllByArtifactId(Long id);
}

