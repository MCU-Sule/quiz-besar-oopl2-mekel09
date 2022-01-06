package com.example.quiz02_1872005.dao;

import com.example.quiz02_1872005.entitas.AnggotaEntity;
import com.example.quiz02_1872005.util.DaoService;
import com.example.quiz02_1872005.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
/**Michael Sebastian Gunadi-1872005*/
public class AnggotaDaoImpl implements DaoService<AnggotaEntity> {
    @Override
    public List<AnggotaEntity> fetchall() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<AnggotaEntity> criteriaQuery = criteriaBuilder.createQuery(AnggotaEntity.class);
        criteriaQuery.from(AnggotaEntity.class);
        List<AnggotaEntity> anggo = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return anggo;
    }

    @Override
    public int addData(AnggotaEntity object){
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
    public int updateData(AnggotaEntity object){
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
    public int deleteData(AnggotaEntity object){
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
