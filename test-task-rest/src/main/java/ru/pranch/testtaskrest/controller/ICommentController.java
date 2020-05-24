package ru.pranch.testtaskrest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.model.Comment;

import java.util.Optional;

@Api(tags = "Comment")
@RequestMapping("/default")
public interface ICommentController {

    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "GET",
            tags = "Comment")
    @GetMapping("/comments")
    Iterable<Comment> list(String content, Pageable pageable);

    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "GET",
            tags = "Comment")
    @GetMapping("{id}/comments")
    Iterable<Comment> list(@PathVariable("id") Artifact artifact, Pageable pageable);

    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "GET",
            tags = "Comment")
    @GetMapping("/comments/{id}")
    Optional<Comment> getOne(@PathVariable("id") Long id);

    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "POST",
            tags = "Comment")
    @PostMapping("{id}/comments")
    Comment create(@PathVariable("id") Artifact artifact,
                   @RequestBody Comment comment);

    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "PUT",
            tags = "Comment")
    @PutMapping("/comments/{id}")
    Comment update(@PathVariable("id") Comment commentFromDb,
                   @RequestBody Comment comment);

    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "DELETE",
            tags = "Comment")
    @DeleteMapping("/comments/{id}")
    void delete(@PathVariable("id") Long id);

}
