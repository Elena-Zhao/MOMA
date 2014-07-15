/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helperClass.moma;

import com.entity.moma.User;
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
import javax.faces.context.FacesContext;
  
import javax.servlet.ServletRequest;  
/**
 *
 * @author ASUS
 */
public class GenerateLinkUtils {
    
 
    //generate the new password link
    public static String generateResetPwdLink(User user) {  
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("forgetUserName", user.getUserName());
        return "http://localhost:8080/MOMA/faces/ResetPasswordUI.xhtml?forgetUserName=" + user.getUserName();
    }  
}
