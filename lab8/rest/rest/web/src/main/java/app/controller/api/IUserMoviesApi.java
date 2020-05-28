package app.controller.api;

import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/users/{userId}")
@Api(value = "/users/{userId}", tags = {"users","movies"})
public interface IUserMoviesApi {

    @GET
    @Path("/movies")
    @Produces({"application/json"})
    Response getUserMovies(@PathParam("userId") String userId);

    @POST
    @Path("/movies/{movieId}")
    @Produces({"application/json"})
    Response addMovieToUserCollection(@PathParam("userId") String userId, String movieId);

    @DELETE
    @Path("/movies/{movieId}")
    @Produces({"application/json"})
    Response removeMovieFromUserCollection(@PathParam("userId") String userId,
                                           @PathParam("movieId") String movieId);
}
