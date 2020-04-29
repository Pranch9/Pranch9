package ru.pranch.testtaskrest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pranch.testtaskrest.model.Artifact;

import java.util.List;

@Repository
public interface ArtifactRepos extends JpaRepository<Artifact, Long> {

//    List<Artifact> findAllByCreationDate(LocalDateTime date);

    @Query("select a from Artifact a where a.category like ?1")
    Page<Artifact> findAllByCategory(String category, Pageable pageable);

    @Query("select a from Artifact a where a.userId like ?1")
    Page<Artifact> findAllByUserId(String userId, Pageable pageable);

    @Query("select a from Artifact a where a.description like %?1%")
    Page<Artifact> findAllByDescription(String description, Pageable pageable);

    List<Artifact> findAllByCategoryAndAndUserId(String category, String userID);

    @Query("select a from Artifact a")
    Page<Artifact> findAll(Pageable pageable);
}
