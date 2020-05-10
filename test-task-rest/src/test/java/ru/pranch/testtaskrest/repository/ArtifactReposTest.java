package ru.pranch.testtaskrest.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pranch.testtaskrest.model.Artifact;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class ArtifactReposTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArtifactRepos artifactRepos;


    @Test
    public void findByUserId() {
        // given
        Artifact artifact1 = new Artifact("Spring1", "Boot1", "Test1");
        Artifact artifact2 = new Artifact("Spring2", "Boot2", "Test2");
        Artifact artifact3 = new Artifact("Spring3", "Boot3", "Test3");

        entityManager.persist(artifact1);
        entityManager.persist(artifact2);
        entityManager.persist(artifact3);
        entityManager.flush();

        // when
        Optional<Artifact> found = artifactRepos.findById(artifact1.getId());

        // then
        assertThat(found.get().getId()).isEqualTo(artifact1.getId());
    }

}