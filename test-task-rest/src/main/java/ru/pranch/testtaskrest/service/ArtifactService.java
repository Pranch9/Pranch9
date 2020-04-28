package ru.pranch.testtaskrest.service;

import org.springframework.stereotype.Service;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.repository.ArtifactRepos;

import java.util.List;

@Service
public class ArtifactService {

    private ArtifactRepos artifactRepos;

    public ArtifactService(ArtifactRepos artifactRepos) {
        this.artifactRepos = artifactRepos;
    }

    public List<Artifact> findAllByCategory(String category) {
        return artifactRepos.findAllByCategory(category);
    }

    public List<Artifact> findAllByMultipleParams(String category, String userID) {
        return artifactRepos.findAllByCategoryAndAndUserId(category, userID);
    }

    public List<Artifact> findAllByDescription(String description) {
        return artifactRepos.findAllByDescription(description);
    }

    public List<Artifact> findAllByUserId(String userId) {
        return artifactRepos.findAllByUserId(userId);
    }

}
