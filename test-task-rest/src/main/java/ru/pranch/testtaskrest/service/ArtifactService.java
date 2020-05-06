package ru.pranch.testtaskrest.service;

import org.springframework.stereotype.Service;
import ru.pranch.testtaskrest.repository.ArtifactRepos;

@Service
public class ArtifactService {

    private ArtifactRepos artifactRepos;

    public ArtifactService(ArtifactRepos artifactRepos) {
        this.artifactRepos = artifactRepos;
    }


}
