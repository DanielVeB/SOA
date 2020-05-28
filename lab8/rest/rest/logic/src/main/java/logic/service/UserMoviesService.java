package logic.service;

import logic.dto.get.IdentifableMovieDto;
import logic.exception.InvalidIdException;
import logic.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import repo.MovieRepo;
import repo.UserRepo;
import repo.entity.Movie;
import repo.entity.User;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

public class UserMoviesService implements IUserMoviesService {


    @Inject
    private ModelMapper mapper;

    @Inject
    private Logger logger;

    @EJB
    private UserRepo userRepo;

    @EJB
    private MovieRepo movieRepo;

    @Override
    public List<IdentifableMovieDto> getUserMovies(String userId) {
        logger.info("Get movies for use with id {}", userId);
        User user = getUser(userId);
        List<Movie> movies = user.getMovies();
        Type listType = new TypeToken<List<IdentifableMovieDto>>() {
        }.getType();
        return mapper.map(movies, listType);
    }

    @Override
    public IdentifableMovieDto addMovieToUserCollection(String userId, String movieId) {
        logger.info("Add movie with id {} to user with id {}", movieId, userId);
        User user = getUser(userId);
        Movie movie = getMovie(movieId);
        if (user.getMovies().stream().anyMatch(m -> m.getId().equals(movie.getId()))) {
            return mapper.map(movie, IdentifableMovieDto.class);
        }
        user.addMovie(movie);
        userRepo.update(user);
        return mapper.map(movie, IdentifableMovieDto.class);
    }


    @Override
    public IdentifableMovieDto removeMovieFromUserCollection(String userId, String movieId) {
        UUID movieUUID = parseId(movieId);
        User user = getUser(userId);
        Movie movie = user.getMovies().stream().filter(m -> m.getId().equals(movieUUID))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(movieId));
        user.removeMovie(movie);
        userRepo.update(user);
        return mapper.map(movie, IdentifableMovieDto.class);
    }


    private User getUser(String id) {
        User user = userRepo.getById(parseId(id));
        if (user != null) {
            return user;
        } else {
            logger.error("User with id {} not found", id);
            throw new NotFoundException(id);
        }
    }

    private Movie getMovie(String id) {
        Movie movie = movieRepo.getById(parseId(id));
        if (movie != null) {
            return movie;
        } else {
            logger.error("Movie with id {} not found", id);
            throw new NotFoundException(id);
        }
    }

    private UUID parseId(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid id {}", id);
            throw new InvalidIdException(id);
        }
    }
}
