package ru.pranch.testtaskrest.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.repository.ArtifactRepos;
import ru.pranch.testtaskrest.service.ArtifactService;

import java.time.LocalDateTime;

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
    public Page<Artifact> listBy(String category,
                                 String userId,
                                 String description,
                                 Pageable pageable) {
        if (category != null && userId != null) {
            return artifactRepos.findAllByCategoryAndAndUserId(category, userId, pageable);
        }
        if (category != null) {
            return artifactRepos.findAllByCategory(category, pageable);
        }
        if (userId != null) {
            return artifactRepos.findAllByUserId(userId, pageable);
        }
        if (description != null) {
            return artifactRepos.findAllByDescription(description, pageable);
        }
        return artifactRepos.findAll(pageable);
    }

    @GetMapping("{id}")
    public Artifact getOne(@PathVariable("id") Artifact artifact) {
        return artifact;
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
