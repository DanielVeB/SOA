package service;


import entity.Forum;
import entity.User;

import java.util.List;

public interface IForumService {

    void sendMessage(String userName, String subject, String text);

    void broadcastMessageOnForum(String forumName, String message);

    Forum createForum(String name, String description);

    List<Forum> getAllForums();

    Forum registerNewSubcriber(User user, Forum forum);
}
