package service;

import entity.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ILoginService.class)
public class LoginService implements ILoginService {

    @Override
    public User registerUser(User user) {
        return null;
    }

    @Override
    public User loginUser(User user) {
        return null;
    }

    @Override
    public User removeUser(User user) {
        return null;
    }
}
