package logic.service;

import logic.dto.get.IdentifableMovieDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import repo.UserRepo;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.List;

public class UserMoviesService implements IUserMoviesService {


    @Inject
    private ModelMapper mapper;

    @Inject
    private Logger logger;

    @EJB
    private UserRepo userRepo;

    @Override
    public List<IdentifableMovieDto> getUserMovies(String userId) {
        return null;
    }

    @Override
    public IdentifableMovieDto addMovieToUserCollection(String userId, String movieId) {
        return null;
    }


    @Override
    public IdentifableMovieDto removeMovieFromUserCollection(String userId, String movieId) {
        return null;
    }
}
