package ru.pranch.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.pranch.test_task.repos.ArtifactRepos;
import ru.pranch.test_task.repos.UserRepos;

@Controller
public class MainController {
    @Autowired
    private ArtifactRepos artifactRepos;
    @Autowired
    private UserRepos userRepos;

    @GetMapping(value = "/")
    public String login(Model model) {
        return "redirect:/main";
    }

    @GetMapping(value = "/main")
    public String main(Model model) {
        Long userCounter = userRepos.count();
        Long bookCounter = artifactRepos.count();
        model.addAttribute("userCounter", userCounter);
        model.addAttribute("bookCounter", bookCounter);
        return "main";
    }
}
