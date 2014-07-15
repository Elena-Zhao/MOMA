/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.Video;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bianshujun
 */
public class VideoDao {
        public static Video findby_videoId(int videoId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Video where video_id=";
        List videoList = session.createSQLQuery(sql + videoId + ";")
                .addEntity(Video.class).list();

        transaction.commit();
//        session.close();

        Iterator it = videoList.iterator();
        if (it.hasNext()) {
            Video video = (Video) it.next();
            return video;
        } else {
            return null;
        }
    }

    public static Video findby_videourl(String videourl) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Video where video_url=:videourl";
        Query query = session.createQuery(hql);
        query.setString("videourl", videourl);
        List videoList = (List<Video>) query.list();
        transaction.commit();
//        session.close();

        Iterator it = videoList.iterator();
        if (it.hasNext()) {
            Video video = (Video) it.next();
            return video;
        } else {
            return null;
        }
    }

    public static Video findby_userId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Video where video_user_fk=";
        List videoList = session.createSQLQuery(sql + userId + ";")
                .addEntity(Video.class).list();

        transaction.commit();
        Iterator it = videoList.iterator();
        if (it.hasNext()) {
            Video video = (Video) it.next();
            return video;
        } else {
            return null;
        }
    }

    public static List<Video> findby_brochureId(int brochureId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Video where video_brochure_fk=";
        List videoList = session.createSQLQuery(sql + brochureId + ";")
                .addEntity(Video.class).list();
        transaction.commit();
        return videoList;
    }

    public static void add_video(Video video) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(video);
        session.flush();

        transaction.commit();
    }

    public static void modify_video(Video video) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

//        session.merge(video);
        session.update(video);
        session.flush();

        transaction.commit();
    }
}
