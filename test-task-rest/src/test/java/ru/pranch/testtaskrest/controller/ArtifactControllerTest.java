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
import ru.pranch.testtaskrest.repository.ArtifactRepos;
import ru.pranch.testtaskrest.service.ArtifactService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtifactControllerTest {

    private final String username = "admin";
    private final String password = "admin";

    @LocalServerPort
    private int port;

    @Autowired
    private ArtifactService service;

    @Autowired
    private ArtifactRepos repos;

    @Before
    public void setup() {
        RestAssured.port = port;
        Artifact testData;
        testData = new Artifact("Spring1", "Boot1", "Test1");
        repos.saveAndFlush(testData);
        testData = new Artifact("Spring2", "Boot2", "Test2");
        repos.saveAndFlush(testData);
        testData = new Artifact("Spring3", "Boot3", "Test3");
        repos.saveAndFlush(testData);
        testData = new Artifact("Spring4", "Boot4", "Test4");
        repos.saveAndFlush(testData);
        testData = new Artifact("Spring5", "Boot5", "Test5");
        repos.saveAndFlush(testData);
    }

    @After
    public void resetDb() {
        repos.deleteAll();
        repos.flush();
    }

    @Test
    public void addArtifactTest() throws Exception {
        Artifact artifact = new Artifact("Spring", "Boot", "Test");

        given()
                .auth().preemptive().basic(username, password)
                .log().body()
                .contentType("application/json").body(artifact)

                .when().post("/artifact")

                .then().log().body()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void updateArtifactTest() {
        long id = repos.findAll().get(0).getId();
        Artifact artifact = new Artifact("UpdSpring", "UpdBoot", "UpdTest");

        given()
                .pathParam("id", id)
                .auth().preemptive().basic(username, password)
                .log().body()
                .contentType("application/json").body(artifact)

                .when().put("/artifact/{id}")

                .then().log().body().statusCode(HttpStatus.OK.value())
                .and().body("userId", equalTo("UpdSpring"));
    }

    @Test
    public void deleteArtifactTest() {
        long id = repos.findAll().get(0).getId();

        given()
                .pathParam("id", id)
                .auth().preemptive().basic(username, password)
                .log().body()
                .contentType("application/json")

                .when().delete("/artifact/{id}")

                .then().log().body().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findByIdTest() {
        long id = repos.findAll().get(0).getId();
        given()
                .pathParams("id", id)
                .auth().preemptive().basic(username, password)
                .log().body()

                .when().get("artifact/{id}")

                .then().log().body().statusCode(HttpStatus.OK.value())
                .and().body("userId", equalTo("Spring"));
    }

    @Test
    public void findAllTest() {
        given()
                .auth().preemptive().basic(username, password)

                .when().get("/")

                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("content.get(0).userId", equalTo("Spring1"))
                .and().body("content.get(1).userId", equalTo("Spring2"))
                .and().body("totalElements", equalTo(5))
                .and().body("content", hasSize(5));
    }
}