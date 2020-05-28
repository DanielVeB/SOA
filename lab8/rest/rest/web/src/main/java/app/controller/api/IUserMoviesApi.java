package app.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/users/{userId}/movies")
@Api(value = "/users/{userId}", tags = {"users","movies"})
public interface IUserMoviesApi {

    @GET
    @Produces({"application/json"})
    Response getUserMovies(@PathParam("userId") String userId);

    @POST
    @Path("/{movieId}")
    @Produces({"application/json"})
    Response addMovieToUserCollection(@ApiParam(value = "user id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                                      @PathParam("userId") String userId,
                                      @ApiParam(value = "movie id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                                      @PathParam("movieId") String movieId);

    @DELETE
    @Path("/{movieId}")
    @Produces({"application/json"})
    Response removeMovieFromUserCollection(@ApiParam(value = "user id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                                           @PathParam("userId") String userId,
                                           @ApiParam(value = "movie id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                                           @PathParam("movieId") String movieId);
}
