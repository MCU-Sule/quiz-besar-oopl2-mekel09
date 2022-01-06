package com.example.quiz02_1872005.dao;

import com.example.quiz02_1872005.entitas.BukuEntity;
import com.example.quiz02_1872005.util.DaoService;
import com.example.quiz02_1872005.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
/**Michael Sebastian Gunadi-1872005*/
public class BukuDaoImpl implements DaoService<BukuEntity> {
    @Override
    public List<BukuEntity> fetchall() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BukuEntity> criteriaQuery = criteriaBuilder.createQuery(BukuEntity.class);
        criteriaQuery.from(BukuEntity.class);
        List<BukuEntity> buku = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return buku;
    }

    @Override
    public int addData(BukuEntity object){
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(object);
            transaction.commit();
            result = 1;
        }catch (HibernateException ex){
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int updateData(BukuEntity object){
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(object);
            transaction.commit();
            result = 1;
        }catch (HibernateException ex){
            transaction.rollback();
        }
        session.close();
        return result;
    }



    @Override
    public int deleteData(BukuEntity object){
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(object);
            transaction.commit();
            result = 1;
        }catch (HibernateException ex){
            transaction.rollback();
        }
        session.close();
        return result;
    }
}
