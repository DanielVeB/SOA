package app.controller.api;

import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/users")
@Api(value = "/users", tags = {"users","movies"})
public interface IUserMoviesApi {

    @GET
    @Path("/{userId}/movies")
    @Produces({"application/json"})
    Response getUserMovies(@PathParam("userId") String userId);

    @POST
    @Path("/{userId}/movies/{movieId}")
    @Produces({"application/json"})
    Response addMovieToUserCollection(@PathParam("userId") String userId, String movieId);

    @DELETE
    @Path("/{userId}/movies/{movieId}")
    @Produces({"application/json"})
    Response removeMovieFromUserCollection(@PathParam("userId") String userId,
                                           @PathParam("movieId") String movieId);
}
