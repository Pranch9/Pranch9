package ru.pranch.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pranch.test_task.model.Artifact;
import ru.pranch.test_task.repos.ArtifactRepos;
import ru.pranch.test_task.service.ArtifactService;

import java.util.List;

@Controller
@RequestMapping("/artifact")
public class ArtifactController {
    @Autowired
    private ArtifactRepos artifactRepos;
    @Autowired
    private ArtifactService artifactService;

    @GetMapping
    public String findAllArtifacts(Model model) {
        List<Artifact> artifacts = artifactService.findAll();
        model.addAttribute("artifacts", artifacts);
        return "artifact-list";
    }
}
