/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.Brochure;
import com.entity.moma.Photo;
import com.entity.moma.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bianshujun
 */
public class FriendsDao {
    
    public static List<User> findFriendsby_userName(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Friends where second_user_id =";
        List photoList = session.createSQLQuery(sql + userId + ";")
                .addEntity(Photo.class).list();
        transaction.commit();
        return photoList;
    }
}
