/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ASUS
 */
@ManagedBean
@RequestScoped
public class NewPasswordBean {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Creates a new instance of NewPasswordBean
     */
    public NewPasswordBean() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String forgetUserName =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("forgetUserName");
        System.out.println(forgetUserName + " in forget user name");
        user = UserDao.findby_userName(forgetUserName);

    }

    public String doModifyPwd() {
        System.out.println("IN modify");
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String forgetUserName =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("forgetUserName");
        System.out.println(forgetUserName + " in forget user name in modifypwd");
        UserDao.modify_user(user);
        return "modifySuccess";
    }
}
