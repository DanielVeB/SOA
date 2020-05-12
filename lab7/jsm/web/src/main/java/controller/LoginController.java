package controller;

import entity.User;
import exception.InvalidPasswordException;
import exception.UserNotFoundException;
import service.ILoginService;
import util.SessionUtils;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private ILoginService loginService;

    private String user;
    private String pwd;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String loginUser() {
        try {
            User user = loginService.loginUser(this.user, this.pwd);
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            return "app/forum";
        } catch (UserNotFoundException | InvalidPasswordException e) {
            FacesContext.getCurrentInstance().addMessage("loginForm:login",
                    new FacesMessage("Invalid user or password, Please try again"));
            return "login";
        }
    }

}
