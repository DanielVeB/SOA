package logic.service;

import logic.dto.UserDto;
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
    public UserDto createUser(UserDto userDto) {
        try {
            logger.info("Create user {}", userDto);
            User createdUser = userRepo.create(mapper.map(userDto, User.class));
            return mapper.map(createdUser, UserDto.class);
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDto getUser(String userId) {
        logger.info("Get user by id: {}", userId);
        User user = userRepo.getById(getUserId(userId));
        return mapper.map(user, UserDto.class);

    }

    @Override
    public void removeUser(String userId) {
        try {
            userRepo.deleteById(getUserId(userId));
        } catch (EJBException ex) {
            logger.error("User with id {} not found", userId);
            throw new NotFoundException(userId);
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }

    public List<UserDto> getUsers(int offset, int limit) {
        List<User> users = userRepo.getUsers(offset, limit);
        Type listType = new TypeToken<List<UserDto>>() {
        }.getType();
        return mapper.map(users, listType);
    }


    @Override
    public byte[] getUserAvatar(String userId) {
        User user = userRepo.getById(getUserId(userId));
        if (user != null) {
            return user.getAvatar();
        } else {
            throw new NotFoundException(userId);
        }
    }

    @Override
    public void updateUserAvatar(String userId, byte[] avatar) {

        User user = userRepo.getById(getUserId(userId));
        if (user != null) {
            user.setAvatar(avatar);
            userRepo.update(user);
        } else {
            throw new NotFoundException(userId);
        }
    }

    @Override
    public void removeUserAvatar(String userId) {
        User user = userRepo.getById(getUserId(userId));
        if (user != null) {
            user.setAvatar(null);
            userRepo.update(user);
        } else {
            throw new NotFoundException(userId);
        }
    }


    private UUID getUserId(String userId) {
        try {
            return UUID.fromString(userId);
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid id {} provided", userId);
            throw new InvalidIdException(userId);
        }
    }

}
