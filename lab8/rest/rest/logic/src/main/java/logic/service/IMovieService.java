package logic.service;

import logic.dto.MovieDto;

public interface IMovieService {

    MovieDto getMovieByTitle(String title);

    MovieDto createMovie(MovieDto movieDto);

    MovieDto deleteMovie(String movieId);

    MovieDto updateMovie(String movieId, MovieDto updatedMovie);


}
