/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.GroupOwner;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bianshujun
 */
public class GroupDao {

    public static List<GroupOwner> findby_ownerId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from GroupOwner where owner_id=:userId";
        Query query = session.createQuery(hql);
        query.setInteger("userId", userId);
        List groupList = (List<GroupOwner>) query.list();
        transaction.commit();
//        session.close();

        return groupList;
    }

    public static void add_group(GroupOwner groupOwner) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(groupOwner);
        session.flush();

        transaction.commit();
    }

    public static void modify_group(GroupOwner groupOwner) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

//        session.merge(groupOwner);
        session.update(groupOwner);
        session.flush();

        transaction.commit();
    }
}
