/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.PhotoDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.Photo;
import com.entity.moma.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class ChangeUserInfoBean {
    
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    /**
     * Creates a new instance of ChangeUserInfoBean
     */
    public ChangeUserInfoBean() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        user = UserDao.findby_userName(userName);
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
    
    public String doCompletion() {
        System.out.println("InDoCompletion");
        System.out.println(user.getUserRealName());
        UserDao.modify_user(user);
        return "userHomePage";
    }
}
