package controller;

import exception.InvalidPasswordException;
import exception.UserNotFoundException;
import service.ILoginService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "login")
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
            loginService.loginUser(user,pwd);

        }catch (InvalidPasswordException ex){
            FacesContext.getCurrentInstance().addMessage("loginForm:login",
                    new FacesMessage("Invalid password, Please try again"));
        }catch (UserNotFoundException ex){
            FacesContext.getCurrentInstance().addMessage("loginForm:login",
                    new FacesMessage("User with this name not found"));
        }
        return "";
    }


}
