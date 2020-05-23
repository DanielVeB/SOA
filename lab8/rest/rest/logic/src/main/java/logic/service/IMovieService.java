package logic.service;

import logic.dto.MovieDto;

import java.util.UUID;

public interface IMovieService {

    MovieDto getMovieByTitle(String title);

    MovieDto createMovie(MovieDto movieDto);

    MovieDto deleteMovie(UUID movieId);

    MovieDto updateMovie(UUID movieId, MovieDto updatedMovie);


}
