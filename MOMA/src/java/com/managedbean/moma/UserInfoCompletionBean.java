/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class UserInfoCompletionBean {

    private User user;
    private Integer userId;
    private String userName;
    private String userRealName;
    private String userPassword;
    private String userEmail;
    private String phoneNumber;
    private Integer qqNumber;
    private String adressFirstLine;
    private String adressSecondLine;
    private Integer adressCountry;
    private Integer adressProvince;
    private String userPortraitUrl;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        user.setUserName(userName);
    }

    public String getUserRealName() {
        System.out.println("IngetMethod");
        if ("RealName".equals(userRealName)) {
            return "请输入您的真实姓名";
        } else {
            return userRealName;
        }
    }

    public void setUserRealName(String userRealName) {
        System.out.println("InsetMethod");
        this.userRealName = userRealName;
        user.setUserRealName(userRealName);
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        user.setUserPassword(userPassword);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        user.setUserEmail(userEmail);
    }

    public String getPhoneNumber() {
        if (phoneNumber == null) {
            return "请输入您的电话号码";
        }
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        user.setPhoneNumber(phoneNumber);
    }

    public String getQqNumber() {
        if (qqNumber == null) {
            return "请输入您的qq号码";
        } else {
            return qqNumber.toString();
        }
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = Integer.parseInt(qqNumber);
        user.setQqNumber(this.qqNumber);
    }

    public String getAdressFirstLine() {
        if (adressFirstLine == null) {
            return "请输入您的地址";
        }
        return adressFirstLine;
    }

    public void setAdressFirstLine(String adressFirstLine) {
        this.adressFirstLine = adressFirstLine;
        user.setAdressFirstLine(adressFirstLine);
    }

    public String getAdressSecondLine() {
        if (adressSecondLine == null) {
            return "请输入您的地址";
        }
        return adressSecondLine;
    }

    public void setAdressSecondLine(String adressSecondLine) {
        this.adressSecondLine = adressSecondLine;
        user.setAdressSecondLine(adressSecondLine);
    }

    public String getAdressCountry() {
        if (adressCountry == null) {
            return "请输入您的国家";
        }
        return adressCountry.toString();
    }

    public void setAdressCountry(String adressCountry) {
        this.adressCountry = Integer.parseInt(adressCountry);
        user.setAdressCountry(this.adressCountry);
    }

    public String getAdressProvince() {
        if (adressProvince == null) {
            return "请输入您的省份";
        }
        return adressProvince.toString();
    }

    public void setAdressProvince(String adressProvince) {
        this.adressProvince = Integer.parseInt(adressProvince);
        user.setAdressProvince(this.adressProvince);
    }

    public String getUserPortraitUrl() {
        if (userPortraitUrl == null) {
            return "请输入您的PortraitURL";
        }
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
        user.setUserPortraitUrl(userPortraitUrl);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Creates a new instance of userInfoCompletionBean
     */
    public UserInfoCompletionBean() {
        String personalUserName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("registerUserName").toString();
        user = UserDao.findby_userName(personalUserName);
        userId = user.getUserId();
        userName = user.getUserName();
        userRealName = user.getUserRealName();
        userPassword = user.getUserPassword();
        userEmail = user.getUserEmail();
        phoneNumber = user.getPhoneNumber();
        qqNumber = user.getQqNumber();
        adressFirstLine = user.getAdressFirstLine();
        adressSecondLine = user.getAdressSecondLine();
        adressCountry = user.getAdressCountry();
        adressProvince = user.getAdressProvince();
        userPortraitUrl = user.getUserPortraitUrl();
    }
    
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            File file = new File("/Users/bianshujun/Downloads/MOMA/MOMA/web/Data/User/" + user.getUserId()+ "/");
            if (!file.exists()) {
                file.mkdirs();
            }
            File targetFolder = new File("/Users/bianshujun/Downloads/MOMA/MOMA/web/Data/User/" + user.getUserId()+ "/");
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    event.getFile().getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();

            String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
            User user = UserDao.findby_userName(userName);
            user.setUserPortraitUrl("./Data/User/" + user.getUserId()+ "/" + event.getFile().getFileName());
            UserDao.modify_user(user);
            System.out.println("In handleFileUpload");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doCompletion() {
        System.out.println("InDoCompletion");
        System.out.println(user.getUserRealName());
        UserDao.modify_user(user);
                    try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
