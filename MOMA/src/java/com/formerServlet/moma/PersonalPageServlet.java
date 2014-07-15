/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.formerServlet.moma;

import com.entity.moma.Brochure;
import com.entity.moma.User;
import java.io.IOException;
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
@WebServlet(name = "PersonalPageServlet", urlPatterns = {"/PersonalPageServlet"})
public class PersonalPageServlet extends HttpServlet {

    private User userofPage = null;

    public PersonalPageServlet(User user) {
        if (userofPage == null) {
            userofPage = user;
        }
    }

    public int getPersonalPageUserId() {
        return this.userofPage.getUserId();
    }

    public User getUserofPage() {
        return this.userofPage;
    }

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
    }

    public void addFriend(User friendUser) {
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
//        userofPage.addFriend(friendUser);
        session.merge(userofPage);
        transaction.commit();
    }

    public void removeFriend(User friendUser) {
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
//        userofPage.removeFriend(friendUser);
        session.merge(userofPage);
        transaction.commit();
}

    public void addBrochure(Brochure userBrochure) {
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
//        userofPage.addBrochure(userBrochure);
        session.merge(userofPage);
        transaction.commit();
    }

    public void removeBrochure(Brochure userBrochure) {
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
//        userofPage.removeBrochure(userBrochure);
        session.merge(userofPage);
        transaction.commit();
    }
    
    public void clearAllFriends(){
        for(User friendtoDelete :userofPage.getUsersForSecondUserId()){
            this.removeFriend(friendtoDelete);
        }
    }
}
