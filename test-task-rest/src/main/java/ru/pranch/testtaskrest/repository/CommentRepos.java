package ru.pranch.testtaskrest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.model.Comment;

import java.util.List;

@Repository
public interface CommentRepos extends PagingAndSortingRepository<Comment, Long> {
    List<Comment> findAllByArtifactId(Artifact artifactId);

    @Query("select c from Comment c where c.content like %?1%")
    Page<Comment> findAllByContent(String content, Pageable pageable);
}
