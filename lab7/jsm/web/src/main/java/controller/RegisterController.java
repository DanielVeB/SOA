package controller;


import service.ILoginService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "register")
@SessionScoped
public class RegisterController {

    @EJB
    private ILoginService loginService;
    
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

    public String registerUser(){
        return "";
    }
}
