package ru.pranch.testtaskrest.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.model.Comment;
import ru.pranch.testtaskrest.service.CommentService;

import java.util.Optional;

@RestController
@RequestMapping("artifact")
public class CommentController implements ICommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public Iterable<Comment> list(String content, Pageable pageable) {
        if (content != null) {
            return commentService.findAllByContent(content, pageable);
        }
        return commentService.findAll(pageable);
    }

    public Iterable<Comment> list(Artifact artifact, Pageable pageable) {
        return commentService.findAllByArtifactId(artifact, pageable);
    }

    public Optional<Comment> getOne(Long id) {
        return commentService.findById(id);
    }

    public Comment create(Artifact artifact, Comment comment) {
        comment.setArtifactId(artifact);
        return commentService.save(comment);
    }

    public Comment update(Comment commentFromDb, Comment comment) {
        if (comment.getArtifactId() != null && comment.getUserId() != null) {
            BeanUtils.copyProperties(comment, commentFromDb, "id");
        } else {
            BeanUtils.copyProperties(comment, commentFromDb, "id", "artifactId");
        }
        return commentService.save(commentFromDb);
    }

    public void delete(Long id) {
        commentService.delete(id);
    }
}


