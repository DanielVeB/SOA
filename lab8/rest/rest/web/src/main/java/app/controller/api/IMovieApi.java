package app.controller.api;

import io.swagger.annotations.Api;
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
    Response getMovieById(@PathParam("id") String id);

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    Response createMovie(MovieDto movieDto);

    @PUT
    @Path("/{id}")
    Response updateMovie(@PathParam("id") String id, MovieDto movieDto);

    @DELETE
    @Path("/{id}")
    Response deleteMovie(@PathParam("id") String id);
}
