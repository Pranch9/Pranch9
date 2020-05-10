package ru.pranch.testtaskrest.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.model.Comment;
import ru.pranch.testtaskrest.service.CommentService;

import java.util.Optional;

@RestController
@RequestMapping("artifact")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public Iterable<Comment> list(String content, Pageable pageable) {
        if (content != null) {
            return commentService.findAllByContent(content, pageable);
        }
        return commentService.findAll(pageable);
    }

    @GetMapping("{id}/comments")
    public Iterable<Comment> list(@PathVariable("id") Artifact artifact, Pageable pageable) {
        return commentService.findAllByArtifactId(artifact, pageable);
    }

    @GetMapping("/comments/{id}")
    public Optional<Comment> getOne(@PathVariable("id") Long id) {
        return commentService.findById(id);
    }

    @PostMapping("{id}/comments")
    public Comment create(@PathVariable("id") Artifact artifact,
                          @RequestBody Comment comment) {
        comment.setArtifactId(artifact);
        return commentService.save(comment);
    }

    @PutMapping("/comments/{id}")
    public Comment update(@PathVariable("id") Comment commentFromDb,
                          @RequestBody Comment comment) {
        if (comment.getArtifactId() != null && comment.getUserId() != null) {
            BeanUtils.copyProperties(comment, commentFromDb, "id");
        } else {
            BeanUtils.copyProperties(comment, commentFromDb, "id", "artifactId");
        }
        return commentService.save(commentFromDb);
    }

    @DeleteMapping("/comments/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentService.delete(id);
    }
}


