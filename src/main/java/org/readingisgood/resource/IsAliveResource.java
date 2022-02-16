package org.readingisgood.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/isAlive")
public class IsAliveResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String isAlive() {
        return "isAlive";
    }
}