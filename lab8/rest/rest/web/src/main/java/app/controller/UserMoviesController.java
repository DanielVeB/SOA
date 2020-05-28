package app.controller;


import app.controller.api.IUserMoviesApi;
import logic.dto.MovieDto;
import logic.dto.get.IdentifableMovieDto;
import logic.service.IUserMoviesService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

public class UserMoviesController implements IUserMoviesApi {

    @Inject
    private IUserMoviesService userMoviesService;

    @Inject
    private Logger logger;


    @Override
    public Response getUserMovies(String userId) {
        logger.info("Get movies for user with id {}", userId);
        List<IdentifableMovieDto> movies = userMoviesService.getUserMovies(userId);
        return Response.ok(movies).build();
    }


    @Override
    public Response addMovieToUserCollection(String userId, String movieId) {
        logger.info("Add movie(id : {} for user with id {}", movieId, userId);
        MovieDto movie = userMoviesService.addMovieToUserCollection(userId, movieId);
        return Response.status(Response.Status.CREATED).entity(movie).build();
    }


    @Override
    public Response removeMovieFromUserCollection(String userId, String movieId) {
        logger.info("Remove movie with id {} for user with id {}", movieId, userId);
        MovieDto movieDto = userMoviesService.removeMovieFromUserCollection(userId, movieId);
        return Response.ok(movieDto).build();
    }
}
