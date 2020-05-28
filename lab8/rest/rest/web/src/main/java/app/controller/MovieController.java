package app.controller;

import app.controller.api.IMovieApi;
import logic.dto.MovieDto;
import logic.dto.get.IdentifableMovieDto;
import logic.service.IMovieService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;


public class MovieController implements IMovieApi {

    @Inject
    private IMovieService movieService;

    @Inject
    private Logger logger;


    @Override
    public Response getMovies(int offset, int limit) {
        logger.info("Get movies");
        List<IdentifableMovieDto> movies = movieService.getMovies(offset, limit);
        return Response.ok().entity(movies).build();
    }

    @Override
    public Response getMoviesAsUriList(int offset, int limit) {
        logger.info("Get movies as uri list");
        List<String> uriLinks = movieService.getMoviesUriLinks(offset, limit);
        return Response.ok().entity(uriLinks).build();
    }

    @Override
    public Response getMovieByTitle(String title) {
        logger.info("Get users");
        IdentifableMovieDto movieDto = movieService.getMovieByTitle(title);
        return Response.ok().entity(movieDto).build();
    }

    @Override
    public Response getMovieById(String id) {
        logger.info("Get users");
        MovieDto movieDto = movieService.getMovieById(id);
        return Response.ok().entity(movieDto).build();
    }

    @Override
    public Response createMovie(MovieDto movieDto) {
        logger.info("Create new movie");
        MovieDto movie = movieService.createMovie(movieDto);
        return Response.status(Response.Status.CREATED).entity(movie).build();
    }

    @Override
    public Response updateMovie(String id, MovieDto movieDto) {
        logger.info("Update movie");
        movieService.updateMovie(id, movieDto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Override
    public Response deleteMovie(String id) {
        logger.info("Update movie");
        movieService.deleteMovie(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

