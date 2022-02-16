package org.readingisgood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.readingisgood.model.User;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class UserResourceTest {

  @BeforeEach
  @Transactional
  public void setUp() {
    User.deleteAll();
  }

  @Transactional
  public User createTestUser(String name) {
    User user = new User();
    user.name = name;
    user.persistAndFlush();
    return user;
  }

  @Test
  public void getAllUsers() {
    createTestUser("Test User 1");

    given()
        .when()
        .get("/users")
        .then()
        .body("$.size()", is(1), "[0].name", is("Test User 1"));
  }

  @Test
  public void createUser() {
    User user = new User();
    user.name = "Test User 2";
    given()
        .contentType("application/json")
        .body(user)
        .when()
        .post("/users")
        .then()
        .statusCode(201);

    given()
        .when()
        .get("/users")
        .then()
        .body("$.size()", is(1), "[0].name", is("Test User 2"));
  }

  @Test
  public void updateUser() {
    User user = createTestUser("Test User 3");
    user.name = "Test User updated";

    given()
        .contentType("application/json")
        .body(user)
        .when()
        .put("/users/" + user.id)
        .then()
        .statusCode(200);

    given()
        .when()
        .get("/users")
        .then()
        .body("$.size()", is(1), "[0].name", is("Test User updated"));
  }

  @Test
  public void deleteUser() {
    User user = createTestUser("Test User 4");
    given()
        .contentType("application/json")
        .body(user)
        .when()
        .delete("/users/" + user.id)
        .then()
        .statusCode(204);

  }
}
