package org.readingisgood.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.readingisgood.model.User;
import org.readingisgood.repository.UserRepository;

@Path("/users")
public class UserResource {

    @Inject
    UserRepository repository;

    @GET
    public List<User> users() {
        return User.listAll();
    }

    @POST
    @Transactional
    public Response create(User user) {
        user.persist();
        return Response.status(Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, User user) {
        User entity = User.findById(id);
        repository.findAll();
        if (entity == null) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
        }
        entity.name = user.name;
        return Response.status(Status.OK).entity(user).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        User entity = User.findById(id);
        if (entity == null) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }
}
