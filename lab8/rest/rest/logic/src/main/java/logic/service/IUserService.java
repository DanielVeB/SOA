package logic.service;

import logic.dto.UserDto;

import java.io.File;
import java.util.List;

public interface IUserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(String userId);

    void removeUser(String userId);

    UserDto updateUser(UserDto userDto);

    List<UserDto> getUsers(int offset, int limit);

    File getUserAvatar(String userId);

    void updateUserAvatar(String userId, File avatar);

    void removeUserAvatar(String userId);

}
