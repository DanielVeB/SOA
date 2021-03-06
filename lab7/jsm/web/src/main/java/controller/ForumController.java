package controller;

import entity.Forum;
import entity.User;
import exception.ForumAlreadyExist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.IForumService;
import service.IUserService;
import util.SessionUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name = "forumController")
@SessionScoped
public class ForumController {

    private String forumName;
    private String forumDescription;

    private List<Forum> forums;

    private Forum selectedForum;

    private String message;

    private User user;

    private List<User> selectedUsers;

    @EJB
    private IForumService forumService;

    @EJB
    private IUserService userService;

    private Logger logger;

    @PostConstruct
    public void init() {
        logger = LoggerFactory.getLogger(ForumController.class);
        this.user = (User) SessionUtils.getSession().getAttribute("username");
        try {
            forums = forumService.getAllForums();
        } catch (Exception ex) {
        }
    }

    public void createNewForum() {
        try {
            Forum forum = forumService.createForum(this.forumName, this.forumDescription);
            forums.add(forum);
        } catch (ForumAlreadyExist ex) {
            FacesContext.getCurrentInstance().addMessage("form:forum",
                    new FacesMessage("Forum already exist"));
        }
    }

    public boolean hasSubscribed(Forum forum) {
        return user.getSubscribedForums().stream().anyMatch(f -> forum.getTitle().equals(f.getTitle()));
    }

    public List<User> getUsers() {
        return userService.getUsers();
    }

    public void unsubscribe() {
//        TODO Create unsubscribe method
    }

    public void registerToForum() {
        logger.info("Register to forum" + selectedForum.getTitle());
        forumService.registerNewSubcriber(user, selectedForum);
        user = userService.getUser(user.getName());
    }

    public void sendMessage() {
        for (User user : selectedUsers) {
            logger.info("Send message to user {}", user.getName());
            forumService.sendMessage(user.getName(), "", message);
        }
    }

    public void broadcastMessage() {
        forumService.broadcastMessageOnForum(selectedForum.getTitle(), this.message);
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getForumDescription() {
        return forumDescription;
    }

    public void setForumDescription(String forumDescription) {
        this.forumDescription = forumDescription;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

    public Forum getSelectedForum() {
        return selectedForum;
    }

    public void setSelectedForum(Forum selectedForum) {
        this.selectedForum = selectedForum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }
}
