package org.readingisgood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.readingisgood.model.Book;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BookResourceTest {

  @BeforeEach
  @Transactional
  public void SetUp() {
    Book.deleteAll();
  }

  @Transactional
  public Book createBook(String title) {
    Book book = new Book();
    book.title = title;
    book.stock = 1;
    book.persistAndFlush();
    return book;
  }

  @Test
  public void getBooks() {
    createBook("Test Title 1");
    given()
      .when()
      .get("/book")
      .then()
      .body(
        "$.size()",
        is(1),
        "[0].title",
        is("Test Title 1"),
        "[0].stock",
        is(1)
      );
  }

  @Test
  public void createBook() {
    Book book = new Book();
    book.title = "Test Title Created";
    given()
      .contentType("application/json")
      .body(book)
      .when()
      .post("/book")
      .then()
      .statusCode(201);
  }

  @Test
  public void updateBookStock() {
    Book book = createBook("Test Title 2");
    book.stock = 0;
    given()
      .contentType("application/json")
      .body(book)
      .when()
      .put("/book/" + book.id)
      .then()
      .statusCode(200);
  }
}
