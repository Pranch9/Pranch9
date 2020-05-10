package ru.pranch.testtaskrest.controller;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.model.Comment;
import ru.pranch.testtaskrest.repository.ArtifactRepos;
import ru.pranch.testtaskrest.repository.CommentRepos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {

    private final String username = "admin";
    private final String password = "admin";

    @LocalServerPort
    private int port;

    @Autowired
    private CommentRepos commentRepos;

    @Autowired
    private ArtifactRepos artifactRepos;

    @Before
    public void setup() {
        RestAssured.port = port;
        Artifact testDataArtifact;
        testDataArtifact = new Artifact("Creator1", "Boot1", "Test1");
        artifactRepos.saveAndFlush(testDataArtifact);
        Comment testDataComment;
        testDataComment = new Comment("Spring1", "ContentBoot1");
        testDataComment.setArtifactId(testDataArtifact);
        commentRepos.saveAndFlush(testDataComment);
        testDataComment = new Comment("Spring2", "ContentBoot2");
        testDataComment.setArtifactId(testDataArtifact);
        commentRepos.saveAndFlush(testDataComment);
    }

    @After
    public void resetDb() {
        commentRepos.deleteAll();
        commentRepos.flush();
        artifactRepos.deleteAll();
        artifactRepos.flush();
    }

    @Test
    public void addCommentTest() throws Exception {
        Comment comment = new Comment("SpringTest", "TestContent");
        long id = artifactRepos.findAll().get(0).getId();
        given()
                .pathParam("id", id)
                .auth().preemptive().basic(username, password)
                .log().body()
                .contentType("application/json").body(comment)

                .when().post("/artifact/{id}/comments")

                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("content", equalTo("TestContent"));
    }

    @Test
    public void deleteCommentTest() {
        long id = commentRepos.findAll().get(0).getId();

        given()
                .pathParam("id", id)
                .auth().preemptive().basic(username, password)
                .log().body()
                .contentType("application/json")

                .when().delete("/artifact/comments/{id}")

                .then().log().body().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findCommentByIdTest() {
        long id = commentRepos.findAll().get(0).getId();
        given()
                .pathParams("id", id)
                .auth().preemptive().basic(username, password)
                .log().body()

                .when().get("artifact/comments/{id}")

                .then().log().body().statusCode(HttpStatus.OK.value())
                .and().body("userId", equalTo("Spring1"));
    }

    @Test
    public void findAllCommentsTest() {
        given()
                .auth().preemptive().basic(username, password)

                .when().get("/artifact/comments")

                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("content.get(0).userId", equalTo("Spring1"))
                .and().body("content.get(1).userId", equalTo("Spring2"))
                .and().body("totalElements", equalTo(2))
                .and().body("content", hasSize(2));
    }

    @Test
    public void findAllByContentTest() {
        String content = commentRepos.findAll().get(0).getContent();
        given()
                .pathParams("content", content)
                .auth().preemptive().basic(username, password)

                .when().get("/artifact/comments?content={content}")

                .then().log().body().statusCode(HttpStatus.OK.value())
                .and().body("content.get(0).content", equalTo("ContentBoot1"));
    }

    @Test
    public void findCommentsByArtifactIdTest() {
        long id = commentRepos.findAll().get(0).getArtifactId().getId();
        given()
                .pathParams("id", id)
                .auth().preemptive().basic(username, password)

                .when().get("/artifact/{id}/comments")

                .then().log().body().statusCode(HttpStatus.OK.value())
                .and().body("content.get(0).userId", equalTo("Spring1"));
    }

    @Test
    public void updateCommentTest() {
        Comment comment = new Comment("UpdSpringTest", "UpdTestContent");
        long id = commentRepos.findAll().get(0).getId();

        given()
                .pathParam("id", id)
                .auth().preemptive().basic(username, password)
                .log().body()
                .contentType("application/json").body(comment)

                .when().put("/artifact/comments/{id}")

                .then().log().body().statusCode(HttpStatus.OK.value())
                .and().body("content", equalTo("UpdTestContent"));
    }
}