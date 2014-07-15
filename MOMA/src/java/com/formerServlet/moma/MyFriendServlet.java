/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.formerServlet.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bianshujun
 */

@WebServlet(name = "MyFriendServlet", urlPatterns = {"/MyFriendServlet"})
public class MyFriendServlet extends HttpServlet {

    private PersonalPageServlet personalPage ;
    private String userNametoSearch = null;
    private UserDao userhelper = new UserDao();

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
        personalPage = (PersonalPageServlet) request.getSession().getAttribute("currentPersonalPageforSearch");
        userNametoSearch = request.getParameter("SearchName");
//        List<User> allUsers =  userhelper.getUser();
//        for(User tempEntity: allUsers){
//            if (tempEntity.getUserName().equals(userNametoSearch)){
//                personalPage.addFriend(tempEntity);
//            }
//        }
//        response.getWriter().println(personalPage.getUserofPage().getUserName() + " add " + userNametoSearch + " as a friend successfully");
//        
        //request.setAttribute("searchUserList", personalPage);
        //request.getRequestDispatcher("SearchFriend.jsp").forward(request, response);
    }

}
