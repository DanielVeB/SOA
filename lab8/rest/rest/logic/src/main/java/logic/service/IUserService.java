package logic.service;

import logic.dto.UserDto;
import logic.dto.get.IdentifableUserDto;

import java.util.List;
import java.util.Map;

public interface IUserService {

    IdentifableUserDto createUser(UserDto userDto);

    IdentifableUserDto getUser(String userId);

    void removeUser(String userId);

    IdentifableUserDto updateUser(String userId ,UserDto userDto);

    IdentifableUserDto patchUser(String userId, Map<String, Object> updates);

    List<IdentifableUserDto> getUsers(int offset, int limit);

    byte[] getUserAvatar(String userId);

    void updateUserAvatar(String userId, byte[] avatar);

    void removeUserAvatar(String userId);

}
