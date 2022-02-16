package org.readingisgood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class IsAliveTest {

  @Test
  public void testIsAliveEndpoint() {
    given().when().get("/isAlive").then().statusCode(200).body(is("isAlive"));
  }
}
