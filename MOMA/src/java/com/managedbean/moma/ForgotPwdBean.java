/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import com.helperClass.moma.GenerateLinkUtils;
import java.util.Properties;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author ASUS
 */
@ManagedBean
@RequestScoped
public class ForgotPwdBean {

    private User user;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String send() throws MessagingException {
        user = UserDao.findby_userName(name);
        if (user == null) {
            return "forgotPwd";
        } else {
            String host = "smtp.163.com";   //发件人使用发邮件的电子信箱服务器
            String from = "j2ee_moma@163.com";    //发邮件的出发地（发件人的信箱）
            String to = user.getUserEmail();   //发邮件的目的地（收件人信箱）

            // Get system properties
            Properties props = System.getProperties();

            // Setup mail server
            props.put("mail.smtp.host", host);

            // Get session
            props.put("mail.smtp.auth", "true"); //这样才能通过验证

            MyAuthenticator myauth = new MyAuthenticator("j2ee_moma@163.com", "abc111");
            Session session = Session.getInstance(props, myauth);

//    session.setDebug(true);

            // Define message
            MimeMessage message = new MimeMessage(session);


            // Set the from address
            message.setFrom(new InternetAddress(from));

            // Set the to address
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set the subject
            message.setSubject("MOMA Find Your Password");

            // Set the content
            message.setContent("尊敬的用户: 要使用新的密码, 请使用以下链接启用密码:<br/><a href='" + GenerateLinkUtils.generateResetPwdLink(user) + "'>点击重新设置密码</a>", "text/html;charset=utf-8");

            message.saveChanges();

            Transport.send(message);
            return "VerifySuccess";
        }



    }

    /**
     * Creates a new instance of ForgotPwdBean
     */
    public ForgotPwdBean() {
    }
}

class MyAuthenticator extends javax.mail.Authenticator {

    private String strUser;
    private String strPwd;

    public MyAuthenticator(String user, String password) {
        this.strUser = user;
        this.strPwd = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(strUser, strPwd);
    }
}