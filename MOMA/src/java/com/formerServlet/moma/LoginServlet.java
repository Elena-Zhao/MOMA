/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.formerServlet.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bianshujun
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private User currentLoginUser = null;
    private PersonalPageServlet currentPersonalPage= null;
    private ArrayList<PersonalPageServlet> personalPageList = new ArrayList<PersonalPageServlet>();

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
        if (isRightPassword(request)) {
            response.getWriter().println("It is Successful");
//            if (!hasSamePersonPage()){
//                currentPersonalPage = new PersonalPageServlet(currentLoginUser);
//                personalPageList.add(currentPersonalPage);
//            }
//            request.setAttribute("currentPersonalPage", currentPersonalPage);
////            response.getWriter().println(currentPersonalPage.getuserofPage().getUserName());
//            
//            request.getRequestDispatcher("PersonalSpace.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    private boolean hasSamePersonPage() {
        for (PersonalPageServlet tempEntity : personalPageList) {
            if (tempEntity.getPersonalPageUserId() == currentLoginUser.getUserId()) {
                currentPersonalPage = tempEntity;
                return true;
            }
        }
        return false;
    }

    private boolean isRightPassword(HttpServletRequest request) {
        if (UserDao.loginby_userName_pw(request.getParameter("userName"), request.getParameter("userPassword")) == null){
            return false;
        }
        return true;
    }
}
