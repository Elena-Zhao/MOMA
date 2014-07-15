/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.formerServlet.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author bianshujun
 */
@WebServlet(name = "ResigterServlet", urlPatterns = {"/ResigterServlet"})
public class ResigterServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        if (!isSameEmail(request.getParameter("newUserEmail")) && !isSameUserName(request.getParameter("newUserName")) && !isEmptyInput(request)) {
//            if (isTwicePasswordSame(request.getParameter("newUserPassword"), request.getParameter("newUserPasswordConfirm"))) {
//        setNewUserInformation(request);
                User user = new User();
                user.setUserRealName("RealName");
                user.setUserId(getNewUserId());
                user.setUserEmail(request.getParameter("newUserEmail"));
                user.setUserName(request.getParameter("newUserName"));
                user.setUserPassword(request.getParameter("newUserPassword"));
                UserDao.add_user(user);
                response.getWriter().println("Database Updated Successfully");
//            } else {
//                //修改第二行密码输入栏为两次密码不一致
//            }
//        }
        
    }
    
    private void setNewUserInformation(HttpServletRequest request) {
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setUserRealName("RealName");
        user.setUserId(getNewUserId());
        user.setUserEmail(request.getParameter("newUserEmail"));
        user.setUserName(request.getParameter("newUserName"));
        user.setUserPassword(request.getParameter("newUserPassword"));
        session.save(user);
        transaction.commit();
    }
    
    private boolean isEmptyInput(HttpServletRequest request) {
        if (request.getParameter("newUserEmail") == null
                && request.getParameter("newUserName") == null
                && request.getParameter("newUserPassword") == null) {
            return true;
        }
        return false;
    }
    
    private boolean isSameEmail(String registerEmail) {
        if (UserDao.findby_userEmail(registerEmail) == null) {
            return false;
        }
        return true;
    }
    
    private boolean isSameUserName(String registerUserName) {
        if (UserDao.findby_userName(registerUserName) == null) {
            return false;
        }
        return true;
    }
    
    private int getNewUserId() {
        int maxUserId = UserDao.getMaxUserId();
        return maxUserId + 1;
    }
    
    private boolean isTwicePasswordSame(String firstPassword, String secondPassword) {
        if (firstPassword.equals(secondPassword)) {
            return true;
        }
        return false;
    }
}
