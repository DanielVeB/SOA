package logic.service;

import repo.entity.Movie;

import java.util.List;
import java.util.UUID;

public interface IUserMoviesService {

    List<Movie> getUserMovies(UUID userId);
}
