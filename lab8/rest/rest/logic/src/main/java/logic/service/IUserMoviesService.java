package logic.service;

import logic.dto.get.IdentifableMovieDto;

import java.util.List;

public interface IUserMoviesService {

    List<IdentifableMovieDto> getUserMovies(String userId);

    IdentifableMovieDto addMovieToUserCollection(String userId, String movieId);

    IdentifableMovieDto removeMovieFromUserCollection(String userId, String  movieId);
}
