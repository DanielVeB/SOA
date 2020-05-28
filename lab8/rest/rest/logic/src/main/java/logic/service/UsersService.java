package logic.service;

import logic.config.exception.WebException;
import logic.dto.UserDto;
import logic.dto.get.IdentifableUserDto;
import logic.exception.InvalidIdException;
import logic.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import repo.UserRepo;
import repo.entity.User;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.transaction.RollbackException;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

public class UsersService implements IUserService {

    @EJB
    private UserRepo userRepo;

    @Inject
    private ModelMapper mapper;
    @Inject
    private Logger logger;


    @Override
    public IdentifableUserDto createUser(UserDto userDto) {
        try {
            logger.info("Create user {}", userDto);
            User createdUser = userRepo.create(mapper.map(userDto, User.class));
            return mapper.map(createdUser, IdentifableUserDto.class);
        } catch (RollbackException e) {
            throw new WebException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "RollbackException", "");
        }
    }

    @Override
    public IdentifableUserDto getUser(String userId) {
        logger.info("Get user by id: {}", userId);
        UUID id = parseId(userId);
        User user = userRepo.getById(id);
        if (user != null) {
            return mapper.map(user, IdentifableUserDto.class);
        } else {
            throw new NotFoundException(userId);
        }
    }

    @Override
    public void removeUser(String userId) {
        try {
            userRepo.deleteById(parseId(userId));
        } catch (EJBException ex) {
            logger.error("User with id {} not found", userId);
            throw new NotFoundException(userId);
        }
    }

    @Override
    public IdentifableUserDto updateUser(UserDto userDto) {
        return null;
    }

    public List<IdentifableUserDto> getUsers(int offset, int limit) {
        List<User> users = userRepo.getUsers(offset, limit);
        Type listType = new TypeToken<List<IdentifableUserDto>>() {
        }.getType();
        return mapper.map(users, listType);
    }


    @Override
    public byte[] getUserAvatar(String userId) {
        User user = userRepo.getById(parseId(userId));
        if (user != null) {
            return user.getAvatar();
        } else {
            throw new NotFoundException(userId);
        }
    }

    @Override
    public void updateUserAvatar(String userId, byte[] avatar) {

        User user = userRepo.getById(parseId(userId));
        if (user != null) {
            user.setAvatar(avatar);
            userRepo.update(user);
        } else {
            throw new NotFoundException(userId);
        }
    }

    @Override
    public void removeUserAvatar(String userId) {
        User user = userRepo.getById(parseId(userId));
        if (user != null) {
            user.setAvatar(null);
            userRepo.update(user);
        } else {
            throw new NotFoundException(userId);
        }
    }

    private UUID parseId(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            throw new InvalidIdException(id);
        }
    }

}
