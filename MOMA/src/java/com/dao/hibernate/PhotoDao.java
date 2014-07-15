/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.Photo;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bianshujun
 */
public class PhotoDao {

    public static Photo findby_photoId(int photoId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Photo where photo_id=";
        List photoList = session.createSQLQuery(sql + photoId + ";")
                .addEntity(Photo.class).list();

        transaction.commit();
//        session.close();

        Iterator it = photoList.iterator();
        if (it.hasNext()) {
            Photo photo = (Photo) it.next();
            return photo;
        } else {
            return null;
        }
    }

    public static Photo findby_photourl(String photourl) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Photo where photo_url=:photourl";
        Query query = session.createQuery(hql);
        query.setString("photourl", photourl);
        List photoList = (List<Photo>) query.list();
        transaction.commit();
//        session.close();

        Iterator it = photoList.iterator();
        if (it.hasNext()) {
            Photo photo = (Photo) it.next();
            return photo;
        } else {
            return null;
        }
    }

    public static Photo findby_userId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Photo where photo_user_fk=";
        List photoList = session.createSQLQuery(sql + userId + ";")
                .addEntity(Photo.class).list();
        transaction.commit();
        Iterator it = photoList.iterator();
        if (it.hasNext()) {
            Photo photo = (Photo) it.next();
            return photo;
        } else {
            return null;
        }
    }

    public static List<Photo> findby_brochureId(int brochureId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Photo where photo_brochure_fk=";
        List photoList = session.createSQLQuery(sql + brochureId + ";")
                .addEntity(Photo.class).list();
        transaction.commit();
        return photoList;
    }

    public static void add_photo(Photo photo) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(photo);
        session.flush();

        transaction.commit();
    }

    public static void modify_photo(Photo photo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

//        session.merge(photo);
        session.update(photo);
        session.flush();

        transaction.commit();
    }
}
