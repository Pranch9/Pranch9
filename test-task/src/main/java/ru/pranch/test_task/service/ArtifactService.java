package ru.pranch.test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.test_task.model.Artifact;
import ru.pranch.test_task.repos.ArtifactRepos;

import java.util.List;

@Service
public class ArtifactService {

    @Autowired
    private ArtifactRepos artifactRepos;

    public Artifact findById(Long id) {
        return artifactRepos.getOne(id);
    }

    public List<Artifact> findAll() {
        return artifactRepos.findAll();
    }

    public Artifact saveArtifact(Artifact artifact) {
        return artifactRepos.save(artifact);
    }

    public void deleteById(Long id) {
        artifactRepos.deleteById(id);
    }
}
