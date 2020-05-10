package ru.pranch.testtaskrest.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pranch.testtaskrest.repository.ArtifactRepos;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArtifactRepos artifactRepos;


    @Test
    public void findByUserId() {
        // given
        Comment comment1 = new Comment("Spring1", "Boot1");
        Comment comment2 = new Comment("Spring2", "Boot2");
        Comment comment3 = new Comment("Spring3", "Boot3");

        entityManager.persist(comment1);
        entityManager.persist(comment2);
        entityManager.persist(comment3);
        entityManager.flush();

        // when
        Optional<Artifact> found = artifactRepos.findById(comment1.getId());

        // then
        assertThat(found.get().getId()).isEqualTo(comment1.getId());
    }
}