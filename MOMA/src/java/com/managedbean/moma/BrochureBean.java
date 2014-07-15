/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.Brochure;
import com.entity.moma.User;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.*;

/**
 *
 * @author ZZ
 */
@ManagedBean
@RequestScoped
public class BrochureBean {

    private int brochureId;
    private User addedmember;
    private ArrayList<User> userFriends = new ArrayList();
    @NotNull
    private String brochureName;
    private Date brochureDate;
    private int brochureType;
    @NotNull
    private int brochureRoot;
    @NotNull
    private String brochureDescription;
    private List<String> brochureRootString;
    Brochure brochure = new Brochure();

    public BrochureBean() {
        System.out.println("for test brochure");
    }

    public int getBrochureType() {
        return brochureType;
    }

    public void setBrochureType(int brochureType) {
        this.brochureType = brochureType;
    }

    public int getBrochureId() {
        return brochureId;
    }

    public void setBrochureId(int brochureId) {
        this.brochureId = brochureId;
    }

    public String getBrochureName() {
        return brochureName;
    }

    public void setBrochureName(String brochureName) {
        this.brochureName = brochureName;
    }

    public Date getBrochureDate() {
        return brochureDate;
    }

    public void setBrochureDate(Date brochureDate) {
        this.brochureDate = brochureDate;
    }

    public int getBrochureRoot() {
        return brochureRoot;
    }

    public void setBrochureRoot(int brochureRoot) {
        this.brochureRoot = brochureRoot;
    }

    public String getBrochureDescription() {
        return brochureDescription;
    }

    public void setBrochureDescription(String brochureDescription) {
        this.brochureDescription = brochureDescription;
    }

    public ArrayList<User> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(ArrayList<User> userFriends) {
        this.userFriends = userFriends;
    }

    public User getAddedmember() {
        return addedmember;
    }

    public void setAddedmember(User addedmember) {
        this.addedmember = addedmember;
    }
    
    

    public void initBrochureRootString() {
        brochureRootString = new ArrayList<String>();
        brochureRootString.add("只限纪念册所有者浏览");
        brochureRootString.add("只限纪念册所有者好友浏览");
        brochureRootString.add("公开所有人都可以浏览");

    }

    public void newBrochure() {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        User user = UserDao.findby_userName(userName);
        userFriends = new ArrayList(user.getUsersForFirstUserId());
        brochureDate = new Date();
//        SimpleDateFormat   dateFormat = new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//        try {
//            brochureDate = dateFormat.parse((new Date()).toString());
//        } catch (ParseException ex) {
//            Logger.getLogger(BrochureBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
        System.out.println(userName + " in brochure");
        brochure.setBrochureName(this.brochureName);
        brochure.setBrochureRoot(this.brochureRoot);
        brochure.setBrochureDescription(this.brochureDescription);
        brochure.setBrochureStartTime(brochureDate);
        brochure.setBrochureModifyTime(brochureDate);
        brochure.setBrochureImageUrl("./img/brochure.jpg");
        BrochureDao.add_brochure(brochure);
        System.out.println(brochure.getBrochureId());
        String brochureDir = "/Users/bianshujun/Downloads/MOMA/MOMA/web/Data/Brochure/" + brochure.getBrochureId();
        File file = new File(brochureDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        UserDao.add_user_brochure(userName, brochure.getBrochureId());


        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentBrochureId", brochure.getBrochureId());

        try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userHomePage.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public String addDiary() {
        return "writeDiary";
    }
    
    public String back () {
        return "PersonalSpace";
    }
}
