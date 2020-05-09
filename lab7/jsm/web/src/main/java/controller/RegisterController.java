package controller;


import entity.User;
import service.ILoginService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean(name = "register")
@SessionScoped
public class RegisterController {

    @EJB
    private ILoginService loginService;

    private UIComponent mybutton;

    private String user;
    private String pwd;
    private String rPwd;

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

    public String getrPwd() {
        return rPwd;
    }

    public void setrPwd(String rPwd) {
        this.rPwd = rPwd;
    }

    public String registerUser() {
        if (isCorrectRepeatPassword()) {
            loginService.registerUser(this.user, this.pwd);
        } else {
            FacesMessage message = new FacesMessage("Passwords not match");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(mybutton.getClientId(context), message);
        }

        return "";
    }

    private boolean isCorrectRepeatPassword() {
        return (this.pwd.equals(this.rPwd));
    }

    public UIComponent getMybutton() {
        return mybutton;
    }

    public void setMybutton(UIComponent mybutton) {
        this.mybutton = mybutton;
    }
}
