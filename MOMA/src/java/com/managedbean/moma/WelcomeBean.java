/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.GroupDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.GroupOwner;
import com.entity.moma.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ASUS
 */
@ManagedBean
@RequestScoped
public class WelcomeBean {

    private String loginUserName;
    private String loginUserPassword;
    private String searchName;
    private User user;

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginUserPassword() {
        return loginUserPassword;
    }

    public void setLoginUserPassword(String loginUserPassword) {
        this.loginUserPassword = loginUserPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * Creates a new instance of RegisterBean
     */
    public WelcomeBean() {
        user = new User();
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName") != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userHomePage.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void doRegistration() {
        if ((UserDao.findby_userName(user.getUserName()) == null)
                && UserDao.findby_userEmail(user.getUserEmail()) == null) {
            user.setUserPortraitUrl("./img/portrait.png");
            UserDao.add_user(user);
            addGroupToUser(user);
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("registerUserName", user.getUserName());
            System.out.println(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("registerUserName"));
            System.out.println("InDoRegistration");
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userInfoCompletion.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
//            return "userInfoCompletion";
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
                //        UserDao.add_user_friend("shitVincent","shit");
                //        for (User user : UserDao.findby_userName("shit").getUsersForFirstUserId()) {
                //            System.out.println(user.getUserName());

            } catch (IOException ex) {
                Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addGroupToUser(User user) {
        for (int i = 0; i < 3; ++i) {
            GroupOwner groupOwner = new GroupOwner();
            groupOwner.setOwnerId(user.getUserId());
            switch (i) {
                case 0:
                    groupOwner.setGroupName("Family");
                    break;
                case 1:
                    groupOwner.setGroupName("Friends");
                    break;
                case 2:
                    groupOwner.setGroupName("Classmates");
                    break;
            }
            GroupDao.add_group(groupOwner);
        }
    }

    public String doLogin() {
        System.out.println(loginUserName + " and " + loginUserPassword);
        if (UserDao.loginby_userName_pw(loginUserName, loginUserPassword) == null) {
            System.out.println("LoginFail");
            return "welcome";
        } else {
            System.out.println("LoginSuccessfully");
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userName", loginUserName);

            return "userHomePage";
//            return "PersonalSpace";
//            return "PersonalSpace";
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            ExternalContext extContext = facesContext.getExternalContext();
//            HttpSession session = (HttpSession) extContext.getSession(true);
//            session.setAttribute("name", loginUserName);

//            Map <String,Object>   sessionParameterMap   =   FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//            sessionParameterMap.put("name", loginUserName);

//            System.out.println(session.getAttribute("name").toString());

            // return "test";
        }
    }

    public void doSearch() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchName", searchName);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("searchList.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}