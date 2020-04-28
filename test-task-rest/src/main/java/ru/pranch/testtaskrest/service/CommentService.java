package ru.pranch.testtaskrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.model.Comment;
import ru.pranch.testtaskrest.repository.CommentRepos;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepos commentRepos;

    public List<Comment> findByArtifactId(Artifact artifactId) {
        return commentRepos.findAllByArtifactId(artifactId);
    }

}
