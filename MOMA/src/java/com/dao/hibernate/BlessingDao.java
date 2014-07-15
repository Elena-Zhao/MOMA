/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.Blessing;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bianshujun
 */
public class BlessingDao {

    public static Blessing findby_blessingId(int blessingId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Blessing where blessing_id=";
        List blessingList = session.createSQLQuery(sql + blessingId + ";")
                .addEntity(Blessing.class).list();
        transaction.commit();
//        session.close();

        Iterator it = blessingList.iterator();
        if (it.hasNext()) {
            Blessing blessing = (Blessing) it.next();
            return blessing;
        } else {
            return null;
        }
    }

    public static Blessing findby_blessingurl(String blessingurl) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Blessing where blessing_url=:blessingurl";
        Query query = session.createQuery(hql);
        query.setString("blessingurl", blessingurl);
        List blessingList = (List<Blessing>) query.list();
        transaction.commit();
//        session.close();

        Iterator it = blessingList.iterator();
        if (it.hasNext()) {
            Blessing blessing = (Blessing) it.next();
            return blessing;
        } else {
            return null;
        }
    }

    public static Blessing findby_userId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Blessing where blessing_user_fk=";
        List blessingList = session.createSQLQuery(sql + userId + ";")
                .addEntity(Blessing.class).list();

        transaction.commit();
        Iterator it = blessingList.iterator();
        if (it.hasNext()) {
            Blessing blessing = (Blessing) it.next();
            return blessing;
        } else {
            return null;
        }
    }

    public static Blessing findby_brochureId(int brochureId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Blessing where blessing_brochure_fk=";
        List blessingList = session.createSQLQuery(sql + brochureId + ";")
                .addEntity(Blessing.class).list();
        transaction.commit();
        Iterator it = blessingList.iterator();
        if (it.hasNext()) {
            Blessing blessing = (Blessing) it.next();
            return blessing;
        } else {
            return null;
        }
    }

    public static void add_blessing(Blessing blessing) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(blessing);
        session.flush();

        transaction.commit();
    }

    public static void modify_blessing(Blessing blessing) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

//        session.merge(blessing);
        session.update(blessing);
        session.flush();

        transaction.commit();
    }
}
