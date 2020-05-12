package controller;

import entity.Forum;
import entity.User;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.IForumService;
import service.ILoginService;
import service.IUserService;
import util.SessionUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
        } catch (ConstraintViolationException ex) {
            logger.error(ex.getMessage());
        }
    }

    public boolean hasSubscribed(Forum forum) {
        return user.getSubscribedForums().stream().anyMatch(f -> forum.getTitle().equals(f.getTitle()));
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
        forumService.sendMessage("user", "s", "xdd dddd");
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
}
