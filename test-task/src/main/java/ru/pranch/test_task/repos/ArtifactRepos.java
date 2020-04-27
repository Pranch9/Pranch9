package ru.pranch.test_task.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pranch.test_task.model.Artifact;

public interface ArtifactRepos extends JpaRepository<Artifact, Long> {
    Artifact findByCategory(String category);
    Artifact findByUserId(String userId);
}
