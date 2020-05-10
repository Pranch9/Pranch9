package ru.pranch.testtaskrest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.model.Comment;
import ru.pranch.testtaskrest.repository.CommentRepos;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepos commentRepos;

    public CommentService(CommentRepos commentRepos) {
        this.commentRepos = commentRepos;
    }

    public Iterable<Comment> findAll(Pageable pageable) {
        return commentRepos.findAll(pageable);
    }

    public Page<Comment> findAllByContent(String content, Pageable pageable) {
        return commentRepos.findAllByContent(content, pageable);
    }

    public Page<Comment> findAllByArtifactId(Artifact artifactId, Pageable pageable) {
        return commentRepos.findAllByArtifactId(artifactId, pageable);
    }

    public void delete(Long id) {
        commentRepos.deleteById(id);
        commentRepos.flush();
    }

    public Comment save(Comment comment) {
        return commentRepos.saveAndFlush(comment);
    }

    public Optional<Comment> findById(Long id) {
        return commentRepos.findById(id);
    }
}
