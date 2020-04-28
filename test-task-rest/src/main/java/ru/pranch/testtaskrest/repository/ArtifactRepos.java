package ru.pranch.testtaskrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pranch.testtaskrest.model.Artifact;

import java.util.List;

@Repository
public interface ArtifactRepos extends JpaRepository<Artifact, Long> {
    List<Artifact> findAllByCategory(String category);

    List<Artifact> findAllByCategoryAndAndUserId(String category, String userID);

    List<Artifact> findAllByUserId(String userId);

    List<Artifact> findAllByDescription(String description);

}
