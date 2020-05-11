package ru.pranch.testtaskrest.config;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSecurityConfigTest {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void authOkTest() {
        given()
                .auth().preemptive().basic("admin", "admin")
                .when().get("/")
                .then().log().status()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void authNoLoginTest() {
        given()
                .auth().preemptive().basic("", "admin")
                .when().get("/")
                .then().log().status()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void authVoidTest() {
        given()
                .auth().preemptive().basic("", "")
                .when().get("/")
                .then().log().status()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
    @Test
    public void authInvalidLoginTest() {
        given()
                .auth().preemptive().basic("test", "test")
                .when().get("/")
                .then().log().status()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void authInvalidPasswordTest() {
        given()
                .auth().preemptive().basic("admin", "test")
                .when().get("/")
                .then().log().status()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
}