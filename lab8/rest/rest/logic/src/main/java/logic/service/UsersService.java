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
import java.util.Map;
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
        User user = getUser(id);
        return mapper.map(user, IdentifableUserDto.class);
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
    public IdentifableUserDto updateUser(String userId, UserDto userDto) {
        User user = getUser(parseId(userId));
        User newUser = mapper.map(userDto, User.class);
        newUser.setId(user.getId());
        return mapper.map(userRepo.update(newUser), IdentifableUserDto.class);
    }

    @Override
    public IdentifableUserDto patchUser(String userId, Map<String, String> updates) {
        User user = getUser(parseId(userId));
        if (updates.containsKey("name")) {
            user.setName(updates.get("name"));
        }
        if (updates.containsKey("age")) {
            user.setAge(parseAge(updates.get("age")));
        }
        User updatedUser = userRepo.update(user);
        return mapper.map(updatedUser, IdentifableUserDto.class);
    }

    public List<IdentifableUserDto> getUsers(int offset, int limit) {
        List<User> users = userRepo.getUsers(offset, limit);
        Type listType = new TypeToken<List<IdentifableUserDto>>() {
        }.getType();
        return mapper.map(users, listType);
    }

    @Override
    public byte[] getUserAvatar(String userId) {
        User user = getUser(parseId(userId));
        return user.getAvatar();
    }

    @Override
    public void updateUserAvatar(String userId, byte[] avatar) {

        User user = getUser(parseId(userId));
        user.setAvatar(avatar);
        userRepo.update(user);
    }

    @Override
    public void removeUserAvatar(String userId) {
        User user = getUser(parseId(userId));
        user.setAvatar(null);
        userRepo.update(user);
    }

    private int parseAge(String stringAge) {
        try {
            return Integer.parseInt(stringAge);
        } catch (NumberFormatException ex) {
            throw new WebException(Response.Status.BAD_REQUEST.getStatusCode(),
                    "BAD_REQUEST", "Invalid age format");
        }
    }

    private User getUser(UUID userId) {
        User user = userRepo.getById(userId);
        if (user != null) {
            return user;
        } else {
            throw new NotFoundException(userId.toString());
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
