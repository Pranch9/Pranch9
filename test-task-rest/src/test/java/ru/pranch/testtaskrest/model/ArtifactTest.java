package ru.pranch.testtaskrest.model;

import org.junit.After;
import org.junit.Before;
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
public class ArtifactTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArtifactRepos repos;

    @Before
    public void setup() {
        Artifact testData;
        testData = new Artifact("Spring1", "Boot1", "Test1");
        repos.save(testData);
        testData = new Artifact("Spring2", "Boot2", "Test2");
        repos.save(testData);
        testData = new Artifact("Spring3", "Boot3", "Test3");
        repos.save(testData);
        testData = new Artifact("Spring4", "Boot4", "Test4");
        repos.save(testData);
        testData = new Artifact("Spring5", "Boot5", "Test5");
        repos.save(testData);
    }

    @After
    public void resetDb() {
        repos.deleteAll();
        repos.flush();
    }

    @Test
    public void findById() {
        // given
        Artifact artifact1 = new Artifact("Spring1", "Boot1", "Test1");
        entityManager.persist(artifact1);
        entityManager.flush();

        // when
        Optional<Artifact> found = repos.findById(artifact1.getId());

        // then
        assertThat(found.get().getUserId()).isEqualTo("Spring1");
    }

    @Test
    public void addInDb() {
        Artifact artifact = new Artifact();
        artifact.setCategory("test");
        repos.save(artifact);
        assertThat(repos.getOne(6L).getCategory()).isEqualTo(artifact.getCategory());
    }

    @Test
    public void updateInDb() {
        repos.findAll().get(0).setCategory("UpdCategory");
        assertThat( repos.findAll().get(0).getCategory()).isEqualTo("UpdCategory");
    }

    @Test
    public void deleteFromDb() {
        repos.deleteById(repos.findAll().get(0).getId());
        assertThat(repos.count()).isEqualTo(4);
    }
}
