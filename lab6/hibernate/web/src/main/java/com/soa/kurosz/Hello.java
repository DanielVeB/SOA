package com.soa.kurosz;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "hello")
@SessionScoped
public class Hello implements Serializable {

    public String hello(){
        return "Hello world";
    }
}
