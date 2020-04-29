package ru.pranch.testtaskrest.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.model.Comment;
import ru.pranch.testtaskrest.repository.ArtifactRepos;
import ru.pranch.testtaskrest.repository.CommentRepos;
import ru.pranch.testtaskrest.service.ArtifactService;
import ru.pranch.testtaskrest.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("artifact")
public class ArtifactController {
    private final ArtifactRepos artifactRepos;
    private final ArtifactService artifactService;

    @Autowired
    public ArtifactController(ArtifactRepos artifactRepos, ArtifactService artifactService) {
        this.artifactRepos = artifactRepos;
        this.artifactService = artifactService;
    }

    @GetMapping
    public List<Artifact> list() {
        return artifactRepos.findAll();
    }

    @GetMapping("{id}")
    public Artifact getOne(@PathVariable("id") Artifact artifact) {
        return artifact;
    }

    @RequestMapping("/multiParams")
    public List<Artifact> getAllByMultiParams(@RequestParam(value = "category") String category,
                                              @RequestParam(value = "userId") String userId) {
        return artifactService.findAllByMultipleParams(category, userId);
    }


    @GetMapping("/category")
    public List<Artifact> getByCategory(String category) {
        return artifactService.findAllByCategory(category);
    }

    @GetMapping("/userId")
    public List<Artifact> getByUserId(String userId) {
        return artifactService.findAllByUserId(userId);
    }

    @GetMapping("/description")
    public List<Artifact> getByDescription(String description) {
        return artifactService.findAllByDescription(description);
    }

    @PostMapping
    public Artifact create(@RequestBody Artifact artifact) {
        artifact.setCreationDate(LocalDateTime.now());
        return artifactRepos.save(artifact);
    }

    @PutMapping("{id}")
    public Artifact update(@PathVariable("id") Artifact artifactFromDb,
                           @RequestBody Artifact artifact) {
        BeanUtils.copyProperties(artifact, artifactFromDb, "id");
        return artifactRepos.save(artifactFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Artifact artifact) {
        artifactRepos.delete(artifact);
    }

}
