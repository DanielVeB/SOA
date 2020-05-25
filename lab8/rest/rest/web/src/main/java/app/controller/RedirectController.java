package app.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/osoby")
public class RedirectController {

    @GET
    @Produces({"application/json"})
    public Response getUsers(@DefaultValue("0") @QueryParam("offset") int offset,
                             @DefaultValue("10") @QueryParam("limit") int limit) {

        return Response.status(Response.Status.SEE_OTHER).location(URI.create("/users?offset=" + offset + "&limit=" + limit)).build();
    }
}
