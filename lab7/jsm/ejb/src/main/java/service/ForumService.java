package service;

import entity.Forum;
import entity.User;
import jms.JMSPublisher;
import jms.Subscriber;
import repo.ForumRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Remote(IForumService.class)
public class ForumService implements IForumService {

    @EJB
    private ForumRepository forumRepository;

    @Inject
    private JMSPublisher publisher;

    @EJB
    private Subscriber subscriber;

    @Override
    public void sendMessage(String userName, String subject, String text) {
        publisher.sendMessage(userName, text);
    }

    @Override
    public void broadcastMessageOnForum(String forumName, String message) {
        publisher.broadcastForumMessage(forumName, message);
    }

    @Override
    public Forum createForum(String name, String description) {
        Forum forum = new Forum();
        forum.setTitle(name);
        forum.setDescription(description);
        return forumRepository.create(forum);
    }

    @Override
    public List<Forum> getAllForums() {
        return forumRepository.read();
    }

    @Override
    public Forum registerNewSubcriber(User user, Forum forum) {
        forum.subscribeNewUser(user);
        forumRepository.updateForum(forum);
        return forum;
    }
}
