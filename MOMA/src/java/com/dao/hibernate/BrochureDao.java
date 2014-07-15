/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.Brochure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bianshujun
 */
public class BrochureDao {

    public static Brochure findby_brochureId(int brochureId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from Brochure where brochure_id=";
        List brochureList = session.createSQLQuery(sql + brochureId + ";")
                .addEntity(Brochure.class).list();
        transaction.commit();
//        session.close();

        Iterator it = brochureList.iterator();
        if (it.hasNext()) {
            Brochure brochure = (Brochure) it.next();
            return brochure;
        } else {
            return null;
        }
    }

    public static List<Brochure> findby_brochureName(String brochureName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Brochure where brochure_name=:brochureName";
        Query query = session.createQuery(hql);
        query.setString("brochureName", brochureName);
        List brochureList = (List<Brochure>) query.list();
        transaction.commit();
//        session.close();

        return brochureList;
    }

    public static List<Brochure> findby_maxBrochureVisit(int brochureVisit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Brochure where brochure_visit=:brochureVisit";
        Query query = session.createQuery(hql);
        query.setInteger("brochureVisit", brochureVisit);
        List brochureList = (List<Brochure>) query.list();
        transaction.commit();
//        session.close();

        return brochureList;
    }
        
    //这个数据库查询语句有待改进。。不能使用。。
    public static ArrayList<Brochure> findby_userId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
//        String hql= "select brochure from Brochure brochure inner join brochure.users user where user.userName=:userName";
        String hql = "select distinct b from Brochure b,User u where b in elements(u.brochures) and u.userId=:userId";
        Query query = session.createQuery(hql);
        query.setInteger("userId", userId);
        ArrayList brochureList = (ArrayList) query.list();
        transaction.commit();
//        session.close();
//        ArrayList brochureList = new ArrayList();
//        Iterator it = list.iterator();
//        while (it.hasNext()) {
//
//            Object[] obj = (Object[]) it.next();
//            Brochure brochure = (Brochure)obj[1];
//            brochureList.add(brochure);
//            System.out.println(brochure.getBrochureName());
//
//        }
//        session.close();

        return brochureList;
    }

    public static void add_brochure(Brochure brochure) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(brochure);
        session.flush();

        transaction.commit();
    }

    public static void modify_brochure(Brochure brochure) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

//        session.merge(brochure);
        session.update(brochure);
        session.flush();

        transaction.commit();
    }

    public static int getMaxBrochureId() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        int maxUserId = (Integer) session.createQuery(
                "select max(brochure.brochureId) from Brochure brochure").uniqueResult();
        transaction.commit();
        return maxUserId;
    }
    
    public static int getMaxBrochureVisited() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        int brochureVisit = (Integer) session.createQuery(
                "select max(brochure.brochureVisit) from Brochure brochure").uniqueResult();
        transaction.commit();
        return brochureVisit;
    }
    
    
}
