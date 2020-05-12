package service;

import entity.Forum;
import entity.User;
import exception.InvalidPasswordException;
import exception.UserNotFoundException;
import jms.Subscriber;
import repo.UserRepository;
import util.BCryptCipher;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.JMSException;

@Stateless
@Remote(ILoginService.class)
public class LoginService implements ILoginService {

    @EJB
    private UserRepository loginRepository;

    @EJB
    private BCryptCipher cipher;

    @EJB
    private Subscriber subscriber;

    @Override
    public User registerUser(String name, String password) {
        String hashedPassword = cipher.encryptPassword(password);
        User user = new User();
        user.setName(name);
        user.setPassword(hashedPassword);
        return loginRepository.create(user);
    }

    @Override
    public User loginUser(String name, String password) throws UserNotFoundException, InvalidPasswordException {
        User savedUser = getUser(name);
        boolean valid = cipher.checkPassword(password, savedUser.getPassword());
        if (valid) {
            subscribeUser(savedUser);
            return savedUser;
        } else {
            throw new InvalidPasswordException();
        }
    }

    @Override
    public User removeUser(User user) {
        loginRepository.delete(user);
        return user;
    }

    private User getUser(String name) throws UserNotFoundException {
        try {
            return loginRepository.getUserByName(name);
        } catch (Exception ex) {
            throw new UserNotFoundException();
        }
    }

    private void subscribeUser(User user) {
        try {
            subscriber.registerSubscriber(user.getName());
            for (Forum forum : user.getSubscribedForums()){
                subscriber.registerSubscriberToForum(forum.getTitle(), user.getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
