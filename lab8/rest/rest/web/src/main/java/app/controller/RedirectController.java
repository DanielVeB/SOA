package app.controller;

import app.controller.api.IRedirectApi;

import javax.ws.rs.core.Response;
import java.net.URI;


public class RedirectController implements IRedirectApi {

    @Override
    public Response getUsers(int offset, int limit) {
        return Response.status(Response.Status.SEE_OTHER).location(URI.create("/users?offset=" + offset + "&limit=" + limit)).build();
    }
}
