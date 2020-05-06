package ru.pranch.testtaskrest.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.model.Comment;
import ru.pranch.testtaskrest.repository.CommentRepos;
import ru.pranch.testtaskrest.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("artifact")
public class CommentController {

    private final CommentRepos commentRepos;
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentRepos commentRepos, CommentService commentService) {
        this.commentRepos = commentRepos;
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public Page<Comment> list(String content, Pageable pageable) {
        if (content == null){
            return commentRepos.findAll(pageable);
        }
        return commentRepos.findAllByContent(content, pageable);
    }

    @GetMapping("{id}/comments")
    public List<Comment> list(@PathVariable("id") Artifact artifact) {
        return commentService.findByArtifactId(artifact);
    }

    @GetMapping("/comments/{id}")
    public Comment getOne(@PathVariable("id") Comment comment) {
        return comment;
    }

    @PostMapping("{id}/comments")
    public Comment create(@PathVariable("id") Artifact artifact,
                          @RequestBody Comment comment) {
        comment.setArtifactId(artifact);
        return commentRepos.save(comment);
    }

    @PutMapping("/comments/{id}")
    public Comment update(@PathVariable("id") Comment commentFromDb,
                          @RequestBody Comment comment) {
        if (comment.getArtifactId() != null && comment.getUserId() != null) {
            BeanUtils.copyProperties(comment, commentFromDb, "id");
        } else {
            BeanUtils.copyProperties(comment, commentFromDb, "id", "artifactId");
        }
        return commentRepos.save(commentFromDb);
    }

    @DeleteMapping("/comments/{id}")
    public void delete(@PathVariable("id") Comment comment) {
        commentRepos.delete(comment);
    }
}


