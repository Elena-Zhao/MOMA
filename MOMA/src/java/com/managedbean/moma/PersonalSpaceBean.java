/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.Brochure;
import com.entity.moma.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class PersonalSpaceBean {

    private User user;
    private Brochure brochure;
    private boolean skip;
    private ArrayList<Brochure> brochures = new ArrayList();
//    private ArrayList<ArrayList<User>> brochureMember = new ArrayList();
    private static Logger logger = Logger.getLogger(PersonalSpaceBean.class.getName());

    public Brochure getBrochure() {
        return brochure;
    }

    public void setBrochure(Brochure brochure) {
        this.brochure = brochure;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PersonalSpaceBean.logger = logger;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Brochure> getBrochures() {
        return brochures;
    }

    public void setBrochures(ArrayList<Brochure> brochures) {
        this.brochures = brochures;
    }

//    public ArrayList<ArrayList<User>> getBrochureMember() {
//        return brochureMember;
//    }
//
//    public void setBrochureMember(ArrayList<ArrayList<User>> brochureMember) {
//        this.brochureMember = brochureMember;
//    }
    

    
    /**
     * Creates a new instance of PersonalPageBean
     */
    public PersonalSpaceBean() {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userofPersonalSpace");
        if (userName == null) {
            userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        }
        System.out.println(userName + "PersonalSpace Bean");
        user = UserDao.findby_userName(userName);
        brochures = new ArrayList(BrochureDao.findby_userId(user.getUserId()));
        for (Brochure temp_brochure: brochures) {
            System.out.println("brochureId is " + temp_brochure.getBrochureId());
        }
    }
    
    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());
        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void modifyPersonalInfo() {
        System.out.println("InDoModify");
        System.out.println(user.getUserRealName());
        UserDao.modify_user(user);
    }

    public boolean isHost() {
        String currentUserName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        String userName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userofPersonalSpace");
        if (userName == null) {
            return true;
        }
        else if (userName.equals(currentUserName))
            return true;
        else
            return false;
    }
}
