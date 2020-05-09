package service;

import entity.User;

public interface ILoginService {

    User registerUser(User user);

    User loginUser(User user);

    User removeUser(User user);

}
