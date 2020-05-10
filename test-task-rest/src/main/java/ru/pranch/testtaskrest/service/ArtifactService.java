package ru.pranch.testtaskrest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.repository.ArtifactRepos;

import java.util.Optional;

@Service
public class ArtifactService {

    private final ArtifactRepos artifactRepos;

    public ArtifactService(ArtifactRepos artifactRepos) {
        this.artifactRepos = artifactRepos;
    }

    public Optional<Artifact> findById(Long id) {
        return artifactRepos.findById(id);
    }

    public Iterable<Artifact> findAll(Pageable pageable) {
        return artifactRepos.findAll(pageable);
    }

    public Page<Artifact> findAllByCategoryAndAndUserId(String category, String userId, Pageable pageable) {
        return artifactRepos.findAllByCategoryAndAndUserId(category, userId, pageable);
    }

    public Page<Artifact> findAllByCategory(String category, Pageable pageable) {
        return artifactRepos.findAllByCategory(category, pageable);
    }

    public Page<Artifact> findAllByUserId(String userId, Pageable pageable) {
        return artifactRepos.findAllByUserId(userId, pageable);
    }

    public Page<Artifact> findAllByDescription(String description, Pageable pageable) {
        return artifactRepos.findAllByDescription(description, pageable);
    }

    public void delete(Long id) {
        artifactRepos.deleteById(id);
        artifactRepos.flush();
    }

    public Artifact save(Artifact artifact) {
        return artifactRepos.saveAndFlush(artifact);
    }
}
