package ru.pranch.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pranch.test_task.model.Comment;
import ru.pranch.test_task.model.User;
import ru.pranch.test_task.repos.CommentRepos;
import ru.pranch.test_task.service.CommentService;

import java.util.List;

@Controller
@RequestMapping(("/artifact"))
public class CommentController {
    @Autowired
    private CommentRepos commentRepos;
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}/comments")
    public String findAllComments(@PathVariable("id") Long id, Model model) {
        Iterable<Comment> comments = commentService.findByArtifactId(id);
        model.addAttribute("comments", comments);
        return "comment-list";
    }

    @PostMapping("/{id}/comments")
    public String add(@AuthenticationPrincipal User user,
                      @PathVariable("id") Long id,
                      @RequestParam String text, Model model) {
        Comment comment = new Comment(id, user, text);
        commentRepos.save(comment);
        Iterable<Comment> comments = commentService.findByArtifactId(id);
        model.addAttribute("comments", comments);
        return "comment-list";
    }
}
