package app.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import logic.dto.MovieDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/movies")
@Api(value = "/movies", tags = {"movies"})
public interface IMovieApi {

    @GET
    @Produces({"application/json"})
    Response getMovies(@DefaultValue("0") @QueryParam("offset") int offset,
                       @DefaultValue("10") @QueryParam("limit") int limit);

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    Response getMoviesAsUriList(@DefaultValue("0") @QueryParam("offset") int offset,
                                @DefaultValue("10") @QueryParam("limit") int limit);

    @GET()
    @Path("/filter")
    @Produces({"application/json"})
    Response getMovieByTitle(@DefaultValue("") @QueryParam("title") String title);

    @GET()
    @Path("/{id}")
    @Produces({"application/json"})
    Response getMovieById(@ApiParam(value = "movie id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                          @PathParam("id") String id);

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    Response createMovie(MovieDto movieDto);

    @PUT
    @Path("/{id}")
    Response updateMovie(@ApiParam(value = "movie id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                         @PathParam("id") String id, MovieDto movieDto);

    @DELETE
    @Path("/{id}")
    Response deleteMovie(@ApiParam(value = "movie id", example = "4d59f17f-2df3-4cd0-9f95-b01d51d98ce5", required = true)
                         @PathParam("id") String id);
}
