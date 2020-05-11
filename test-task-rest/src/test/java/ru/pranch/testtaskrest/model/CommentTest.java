package ru.pranch.testtaskrest.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pranch.testtaskrest.repository.ArtifactRepos;
import ru.pranch.testtaskrest.repository.CommentRepos;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepos repos;

    @Before
    public void setup() {
        Comment testData;
        testData = new Comment("Spring1", "Boot1");
        repos.save(testData);
        testData = new Comment("Spring2", "Boot2");
        repos.save(testData);
        testData = new Comment("Spring3", "Boot3");
        repos.save(testData);
        testData = new Comment("Spring4", "Boot4");
        repos.save(testData);
        testData = new Comment("Spring5", "Boot5");
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
        Comment comment1 = new Comment("Spring", "Boot");
        entityManager.persist(comment1);
        entityManager.flush();

        // when
        Optional<Comment> found = repos.findById(comment1.getId());

        // then
        assertThat(found.get().getUserId()).isEqualTo("Spring");
    }

    @Test
    public void addInDb() {
        Comment comment = new Comment();
        comment.setContent("test");
        repos.save(comment);
        assertThat(repos.getOne(6L).getContent()).isEqualTo(comment.getContent());
    }

    @Test
    public void updateInDb() {
        repos.findAll().get(0).setContent("UpdCategory");
        assertThat( repos.findAll().get(0).getContent()).isEqualTo("UpdCategory");
    }

    @Test
    public void deleteFromDb() {
        repos.deleteById(repos.findAll().get(0).getId());
        assertThat(repos.count()).isEqualTo(4);
    }
}