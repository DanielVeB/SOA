package app.controller.api;

import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/users/{userId}/movies")
@Api(value = "/users/{userId}", tags = {"users","movies"})
public interface IUserMoviesApi {

    @GET
//    @Path("/movies")
    @Produces({"application/json"})
    Response getUserMovies(@PathParam("userId") String userId);

    @POST
    @Path("/{movieId}")
    @Produces({"application/json"})
    Response addMovieToUserCollection(@PathParam("userId") String userId,
                                      @PathParam("movieId") String movieId);

    @DELETE
    @Path("/{movieId}")
    @Produces({"application/json"})
    Response removeMovieFromUserCollection(@PathParam("userId") String userId,
                                           @PathParam("movieId") String movieId);
}
