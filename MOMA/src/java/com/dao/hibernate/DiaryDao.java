/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.Brochure;
import com.entity.moma.Diary;
import com.entity.moma.User;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author bianshujun
 */
public class DiaryDao {

    public static Diary findby_diaryId(int diaryId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Diary where diary_id=";
        List diaryList = session.createSQLQuery(sql + diaryId + ";")
                .addEntity(Diary.class).list();
        transaction.commit();
//        session.close();

        Iterator it = diaryList.iterator();
        if (it.hasNext()) {
            Diary diary = (Diary) it.next();
            return diary;
        } else {
            return null;
        }
    }

    public static Diary findby_diaryurl(String diaryurl) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Diary where diary_url=:diaryurl";
        Query query = session.createQuery(hql);
        query.setString("diaryurl", diaryurl);
        List diaryList = (List<Diary>) query.list();
        transaction.commit();
//        session.close();

        Iterator it = diaryList.iterator();
        if (it.hasNext()) {
            Diary diary = (Diary) it.next();
            return diary;
        } else {
            return null;
        }
    }

    public static Diary findby_userId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Diary where diary_user_fk=";
        List diaryList = session.createSQLQuery(sql + userId + ";")
                .addEntity(Diary.class).list();
        
        transaction.commit();
        Iterator it = diaryList.iterator();
        if (it.hasNext()) {
            Diary diary = (Diary) it.next();
            return diary;
        } else {
            return null;
        }
    }

    public static List<Diary> findby_brochureId(int brochureId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Diary where diary_brochure_fk=";
        List diaryList = session.createSQLQuery(sql + brochureId + ";")
                .addEntity(Diary.class).list();
        transaction.commit();
        return diaryList;
    }

    public static void add_diary(Diary diary) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(diary);
        session.flush();

        transaction.commit();
    }

    public static void modify_diary(Diary diary) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

//        session.merge(diary);
        session.update(diary);
        session.flush();

        transaction.commit();
    }
    
    public static void add_diary_brochure(int diaryId, int  brochureId) {
        Diary diary = DiaryDao.findby_diaryId(diaryId);
        Brochure brochure = BrochureDao.findby_brochureId(brochureId);

        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
        brochure.getDiaries().add(diary);
        diary.setBrochure(brochure);
        session.merge(diary);

        transaction.commit();
        session.close();
        sessionfactory.close();
    }
    
    
    public static int getMaxDiaryId() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        int maxUserId = (Integer) session.createQuery(
                "select max(diary.diaryId) from Diary diary").uniqueResult();
        transaction.commit();
        return maxUserId;
    }
    
}
