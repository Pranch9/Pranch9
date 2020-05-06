package ru.pranch.testtaskrest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pranch.testtaskrest.model.Artifact;

@Repository
public interface ArtifactRepos extends JpaRepository<Artifact, Long> {

    @Query("select a from Artifact a where a.category like ?1")
    Page<Artifact> findAllByCategory(String category, Pageable pageable);

    @Query("select a from Artifact a where a.userId like ?1")
    Page<Artifact> findAllByUserId(String userId, Pageable pageable);

    @Query("select a from Artifact a where a.description like %?1%")
    Page<Artifact> findAllByDescription(String description, Pageable pageable);

    Page<Artifact> findAllByCategoryAndAndUserId(String category, String userID, Pageable pageable);

}
