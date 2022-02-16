package org.readingisgood;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class IsAliveTest {

    @Test
    public void testIsAliveEndpoint() {
        given()
          .when().get("/isAlive")
          .then()
             .statusCode(200)
             .body(is("isAlive"));
    }

}