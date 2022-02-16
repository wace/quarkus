package org.readingisgood.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.readingisgood.model.Book;

@Path("/book")
public class BookResource {

  @GET
  public List<Book> books() {
    return Book.listAll();
  }

  @POST
  @Transactional
  public Response create(Book book) {
    book.persist();
    return Response.status(Status.CREATED).entity(book).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateStock(@PathParam("id") Long id, Book book) {
    Book entity = Book.findById(id);
    if (entity == null) {
      throw new WebApplicationException(
        "Book with id of " + id + " does not exist.",
        404
      );
    }
    entity.stock = book.stock;
    return Response.status(Status.OK).entity(book).build();
  }
}
