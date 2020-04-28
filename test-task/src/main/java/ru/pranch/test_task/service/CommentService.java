package ru.pranch.test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.test_task.model.Comment;
import ru.pranch.test_task.repos.CommentRepos;

import java.util.List;

@Service
public class CommentService  {
    @Autowired
    private CommentRepos commentRepos;

    public Comment findById(Long id) {
        return commentRepos.getOne(id);
    }

    public List<Comment> findByArtifactId(Long id) {
        return commentRepos.findAllByArtifactId(id);
    }

    public Comment saveComment(Comment comment) {
        return commentRepos.save(comment);
    }

    public void deleteById(Long id) {
        commentRepos.deleteById(id);
    }
}
