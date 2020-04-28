package ru.pranch.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pranch.test_task.model.Comment;
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
        List<Comment> comments = commentService.findByArtifactId(id);
        model.addAttribute("comments", comments);
        return "comment-list";
    }
}
