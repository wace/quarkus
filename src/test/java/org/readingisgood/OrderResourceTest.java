package org.readingisgood;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.readingisgood.model.Book;
import org.readingisgood.model.BookOrder;
import org.readingisgood.model.User;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class OrderResourceTest {

  @BeforeEach
  @Transactional
  public void SetUp() {
    BookOrder.deleteAll();
    Book.deleteAll();
    User.deleteAll();
  }

  @Transactional
  public Book createBook(String title) {
    Book book = new Book();
    book.title = title;
    book.stock = 1;
    book.persistAndFlush();
    assertTrue(book.isPersistent());
    return book;
  }

  @Transactional
  public User createUser(String name) {
    User user = new User();
    user.name = name;
    user.persistAndFlush();
    assertTrue(user.isPersistent());
    return user;
  }

  @Test
  public void createOrder() {
    Book book = createBook("Test Title");
    User user = createUser("Test Name");
    BookOrder order = new BookOrder();
    order.book = book;
    order.user = user;
    given()
      .contentType("application/json")
      .body(order)
      .when()
      .post("/order")
      .then()
      .statusCode(201);
  }
}
