package org.financier.v1.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello World";
    }

    @POST
    @Produces("text/plain")
    @Consumes({MediaType.APPLICATION_JSON})
    public String hello(@QueryParam("name") String name) {
        return "Hello " + name;
    }

}
