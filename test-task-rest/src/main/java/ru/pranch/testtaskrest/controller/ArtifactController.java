package ru.pranch.testtaskrest.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.repository.ArtifactRepos;
import ru.pranch.testtaskrest.service.ArtifactService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public Page<Artifact> listBy(@RequestParam Optional<String> category,
                                 @RequestParam Optional<String> userId,
                                 @RequestParam Optional<String> description,
                                 @RequestParam Optional<Integer> page,
                                 @RequestParam Optional<String> sortBy) {
        if (category.isPresent()) {
            return artifactRepos.findAllByCategory(category.orElse("_"),
                    PageRequest.of(page.orElse(0), 10,
                            Sort.Direction.ASC, sortBy.orElse("id")));
        }
        if (userId.isPresent()) {
            return artifactRepos.findAllByUserId(userId.orElse("_"),
                    PageRequest.of(page.orElse(0), 10,
                            Sort.Direction.ASC, sortBy.orElse("id")));
        }
        if (description.isPresent()) {
            return artifactRepos.findAllByDescription(description.orElse("_"),
                    PageRequest.of(page.orElse(0), 10,
                            Sort.Direction.ASC, sortBy.orElse("id")));
        }
        return artifactRepos.findAll(PageRequest.of(page.orElse(0), 10,
                Sort.Direction.ASC, sortBy.orElse("id")));
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
