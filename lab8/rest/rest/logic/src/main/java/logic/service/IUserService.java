package logic.service;

import logic.dto.UserDto;

import java.util.List;

public interface IUserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(String userId);

    void removeUser(String userId);

    UserDto updateUser(UserDto userDto);

    List<UserDto> getUsers(int offset, int limit);

    byte[] getUserAvatar(String userId);

    void updateUserAvatar(String userId, byte[] avatar);

    void removeUserAvatar(String userId);

}
