package service;

import entity.User;
import exception.InvalidPasswordException;
import exception.UserNotFoundException;

public interface ILoginService {

    User registerUser(String name, String password);

    User loginUser(String name, String password) throws UserNotFoundException, InvalidPasswordException;

    User removeUser(User user);

}
