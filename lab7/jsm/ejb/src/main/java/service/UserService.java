package service;

import entity.User;
import repo.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote(IUserService.class)
public class UserService implements IUserService {

    @EJB
    private UserRepository userRepository;

    @Override
    public User getUser(String username) {
        return userRepository.getUserByName(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.read();
    }
}
