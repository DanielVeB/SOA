package service;

import entity.User;

import java.util.List;

public interface IUserService {

    User getUser(String username);

    List<User> getUsers();
}
