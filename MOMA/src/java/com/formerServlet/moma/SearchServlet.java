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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private PersonalPageServlet personalPage;
    private String userNametoSearch = null;
    private UserDao userhelper = new UserDao();
    private String relatedFriend = null;

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

        if (isSameUserName(userNametoSearch)) {
            if (!isSameFriend(userNametoSearch)) {
                personalPage.addFriend(UserDao.findby_userName(userNametoSearch));
                response.getWriter().println(personalPage.getUserofPage().getUserName() + " add " + userNametoSearch + " as a friend successfully");
                response.getWriter().println(relatedFriend);
            } else {
                response.getWriter().println("He/She is already your friend");
            }
        } else {
            response.getWriter().println("There is no such user");
        }

        for (User tempUser : personalPage.getUserofPage().getUsersForSecondUserId()) {
            response.getWriter().println("Now you have friends:" + tempUser.getUserName());
        }

        for (User tempUser : personalPage.getUserofPage().getUsersForFirstUserId()) {
            response.getWriter().println("Now you have friends:" + tempUser.getUserName());
        }

        //request.setAttribute("searchUserList", personalPage);
        //request.getRequestDispatcher("SearchFriend.jsp").forward(request, response);
    }

    private boolean isSameFriend(String friendName) {
        for (User tempEntity : personalPage.getUserofPage().getUsersForSecondUserId()) {
            if (tempEntity.getUserName().equals(friendName)) {
                return true;
            }
        }

        for (User tempEntity : personalPage.getUserofPage().getUsersForFirstUserId()) {
            if (tempEntity.getUserName().equals(friendName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameUserName(String registerUserName) {
        if (UserDao.findby_userName(registerUserName) == null) {
            return false;
        }
        return true;
    }
}
