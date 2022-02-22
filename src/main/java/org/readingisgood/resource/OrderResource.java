package org.readingisgood.resource;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.readingisgood.model.Book;
import org.readingisgood.model.BookOrder;
import org.readingisgood.model.User;

@Path("/order")
public class OrderResource {

  @POST
  @Transactional
  public Response create(BookOrder order) {
    Optional<User> user = User.findByIdOptional(order.user.id);
    if(user.isEmpty()) {
        throw new WebApplicationException(
        "User with id of " + order.user.id + " does not exist.",
        404
      );
    }
    Optional<Book> book = Book.findByIdOptional(order.book.id);
    if(book.isEmpty()) {
        throw new WebApplicationException(
        "Book with id of " + order.book.id + " does not exist.",
        404
      );
    }
    order.persist();
    return Response.status(Status.CREATED).entity(order).build();
  }
}
