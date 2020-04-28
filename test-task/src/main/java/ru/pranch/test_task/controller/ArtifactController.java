package ru.pranch.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pranch.test_task.model.Artifact;
import ru.pranch.test_task.model.User;
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

    @GetMapping("/delete/{id}")
    public String deleteArtifact(@PathVariable("id") Long id) {
        artifactService.deleteById(id);
        return "redirect:/artifact";
    }

    @GetMapping("/edit/{id}")
    public String updateArtifactForm(@PathVariable("id") Long id, Model model) {
        Artifact artifact = artifactService.findById(id);
        model.addAttribute("artifact", artifact);
        return "artifact-update";
    }

    @PostMapping("/edit")
    public String updateArtifact(
            @RequestParam String date,
            @RequestParam String category,
            @RequestParam String description,
            Artifact artifact) {
        artifact.setCategory(category);
        artifact.setDate(date);
        artifact.setDescription(description);
        artifactService.saveArtifact(artifact);
        return "redirect:/artifact";
    }

    @GetMapping("/add")
    public String addArtifactForm(Artifact artifact) {
        return "artifact-add";
    }

    @PostMapping("/add")
    public String addArtifact(
            @AuthenticationPrincipal User user,
            @RequestParam String date,
            @RequestParam String category,
            @RequestParam String description) {
        Artifact artifact = new Artifact(date, description, category, user);
        artifactRepos.save(artifact);
        return "redirect:/artifact";
    }
}
