package logic.service;

import logic.config.exception.WebException;
import logic.dto.MovieDto;
import logic.dto.get.IdentifableMovieDto;
import logic.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import repo.MovieRepo;
import repo.entity.Movie;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.RollbackException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService implements IMovieService {


    @EJB
    private MovieRepo movieRepo;

    @Inject
    private ModelMapper mapper;

    @Inject
    private Logger logger;


    @Override
    public IdentifableMovieDto getMovieByTitle(String title) {
        Movie movie = movieRepo.getMovieByTitle(title);
        if (movie != null) {
            return mapper.map(movie, IdentifableMovieDto.class);
        } else {
            throw new NotFoundException(title);
        }
    }

    @Override
    public IdentifableMovieDto getMovieById(String id) {
//        Movie = movieRepo.getById()
        return null;
    }

    @Override
    public List<IdentifableMovieDto> getMovies(int offset, int limit) {
        List<Movie> movies = movieRepo.getMovies(offset, limit);
        Type listType = new TypeToken<List<IdentifableMovieDto>>() {
        }.getType();
        return mapper.map(movies, listType);
    }

    @Override
    public List<String> getMoviesUriLinks(int offset, int limit) {
        List<Movie> movies = movieRepo.getMovies(offset, limit);
        return movies.stream().map(
                Movie::getUrl
        ).collect(Collectors.toList());
    }

    @Override
    public IdentifableMovieDto createMovie(MovieDto movieDto) {
        Movie movie = mapper.map(movieDto,Movie.class);
        try {
            Movie createdMove = movieRepo.create(movie);
            return mapper.map(createdMove, IdentifableMovieDto.class);
        } catch (RollbackException e) {
            throw new WebException(500,"","");
        }
    }

    @Override
    public IdentifableMovieDto deleteMovie(String movieId) {
        return null;
    }

    @Override
    public void updateMovie(String movieId, MovieDto updatedMovie) {

    }
}
