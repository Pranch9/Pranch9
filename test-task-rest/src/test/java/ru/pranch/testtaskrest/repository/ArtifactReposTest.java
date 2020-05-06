package ru.pranch.testtaskrest.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pranch.testtaskrest.model.Artifact;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


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
        Artifact artifact = new Artifact("alex", "balex", "galex");
        Artifact artifact2 = new Artifact("alex", "balex", "galex");
        Artifact artifact3 = new Artifact("alex", "balex", "galex");

        entityManager.persist(artifact);
        entityManager.persist(artifact2);
        entityManager.persist(artifact3);
        entityManager.flush();

        // when
        Optional<Artifact> found = artifactRepos.findById(artifact.getId());

        // then
        assertThat(found.get().getId())
                .isEqualTo(artifact.getId());
    }

}