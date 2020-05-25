package logic.service;

import logic.dto.MovieDto;
import logic.exception.InvalidIdException;
import logic.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import repo.MovieRepo;
import repo.entity.Movie;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.UUID;

public class MovieService implements IMovieService {


    @EJB
    private MovieRepo movieRepo;

    @Inject
    private ModelMapper mapper;

    @Inject
    private Logger logger;


    @Override
    public MovieDto getMovieByTitle(String title) {
        return null;
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        return null;
    }

    @Override
    public MovieDto deleteMovie(String movieId) {
        return null;
    }

    @Override
    public MovieDto updateMovie(String movieId, MovieDto updatedMovie) {

        UUID movieUUID = getMovieId(movieId);
        Movie movie = movieRepo.getById(movieUUID);
        if (movie != null) {
            movie.setTitle(updatedMovie.getTitle());
            movie.setUrl(updatedMovie.getUrl());
            Movie updated = movieRepo.update(movie);
            return mapper.map(updated, MovieDto.class);
        } else {
            throw new NotFoundException(movieId);
        }
    }

    private UUID getMovieId(String movieId) {
        try {
            return UUID.fromString(movieId);
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid id {} provided", movieId);
            throw new InvalidIdException(movieId);
        }
    }
}
