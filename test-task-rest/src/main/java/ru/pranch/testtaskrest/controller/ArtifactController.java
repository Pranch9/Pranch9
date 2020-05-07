package ru.pranch.testtaskrest.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.service.ArtifactService;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ArtifactController {

    private final ArtifactService artifactService;

    @Autowired
    public ArtifactController(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    @GetMapping
    public Iterable<Artifact> findAll(Pageable pageable) {
        return artifactService.findAll(pageable);
    }

    @GetMapping("/artifact")
    public Iterable<Artifact> listBy(String category,
                                 String userId,
                                 String description,
                                 Pageable pageable) {
        if (category != null && userId != null) {
            return artifactService.findAllByCategoryAndAndUserId(category, userId, pageable);
        }
        if (category != null) {
            return artifactService.findAllByCategory(category, pageable);
        }
        if (userId != null) {
            return artifactService.findAllByUserId(userId, pageable);
        }
        if (description != null) {
            return artifactService.findAllByDescription(description, pageable);
        }
        return artifactService.findAll(pageable);
    }

    @GetMapping("/artifact/{id}")
    public Optional<Artifact> getOne(@PathVariable("id") Long id) {
        return artifactService.findById(id);
    }

    @PostMapping("/artifact")
    public Artifact create(@RequestBody Artifact artifact) {
        artifact.setCreationDate(LocalDateTime.now());
        return artifactService.save(artifact);
    }

    @PutMapping("/artifact/{id}")
    public Artifact update(@PathVariable("id") Artifact artifactFromDb,
                           @RequestBody Artifact artifact) {
        BeanUtils.copyProperties(artifact, artifactFromDb, "id");
        return artifactService.save(artifactFromDb);
    }

    @DeleteMapping("/artifact/{id}")
    public void delete(@PathVariable("id") Long id) {
        artifactService.delete(id);
    }

}
