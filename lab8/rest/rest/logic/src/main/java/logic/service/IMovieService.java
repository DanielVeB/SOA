package logic.service;

import logic.dto.MovieDto;
import logic.dto.get.IdentifableMovieDto;

import java.util.List;

public interface IMovieService {


    IdentifableMovieDto getMovieById(String id);

    List<IdentifableMovieDto> getMovies(int offset, int limit, String title);

    List<String> getMoviesUriLinks(int offset, int limit, String title);

    IdentifableMovieDto createMovie(MovieDto movieDto);

    IdentifableMovieDto deleteMovie(String movieId);

    void updateMovie(String movieId, MovieDto updatedMovie);


}
