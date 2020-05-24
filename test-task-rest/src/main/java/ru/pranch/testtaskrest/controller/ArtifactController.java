package ru.pranch.testtaskrest.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.service.ArtifactService;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ArtifactController implements IArtifactController {

    private final ArtifactService artifactService;

    public ArtifactController(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    public Iterable<Artifact> findAll(Pageable pageable) {
        return artifactService.findAll(pageable);
    }

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

    public Optional<Artifact> getOne(Long id) {
        return artifactService.findById(id);
    }

    public Artifact create(Artifact artifact) {
        artifact.setCreationDate(LocalDateTime.now());
        return artifactService.save(artifact);
    }

    public Artifact update(Artifact artifactFromDb, Artifact artifact) {
        BeanUtils.copyProperties(artifact, artifactFromDb, "id");
        return artifactService.save(artifactFromDb);
    }

    public void delete(Long id) {
        artifactService.delete(id);
    }

}
