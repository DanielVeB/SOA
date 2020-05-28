package logic.service;

import logic.dto.MovieDto;
import logic.dto.get.IdentifableMovieDto;

import java.util.List;

public interface IMovieService {

    IdentifableMovieDto getMovieByTitle(String title);

    IdentifableMovieDto getMovieById(String id);

    List<IdentifableMovieDto> getMovies(int offset, int limit);

    List<String> getMoviesUriLinks(int offset, int limit);

    IdentifableMovieDto createMovie(MovieDto movieDto);

    IdentifableMovieDto deleteMovie(String movieId);

    void updateMovie(String movieId, MovieDto updatedMovie);


}
