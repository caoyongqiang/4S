package com.sanqing.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.Clue;

/**
 * @author BWeiMing
 *
 */
public class ClueDao {

    public void addClue(Clue e) throws HibernateException{
//        e.setCreatetime(new java.util.Date());
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.save(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

    public void deleteClue(Clue e) throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

    public Clue loadClue(long id) throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Clue e=(Clue)session.get(Clue.class,new Long(id));
        tx.commit();
        HibernateSessionFactory.closeSession();
        return e;
    }

    public List listClue() throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select e from Clue as e order by visitTime");
        List list = query.list();
        tx.commit();
        HibernateSessionFactory.closeSession();
        return list;
    }

    public void updateClue(Clue ins) throws HibernateException{
        Clue e =this.loadClue(ins.getId().longValue());
        if (ins.getIdCard()!=null){
            e.setIdCard(ins.getIdCard());
        }
        if (ins.getHouse()!=null){
            e.setHouse(ins.getHouse());
        }
        if (ins.getDesireCar()!=null){
            e.setDesireCar(ins.getDesireCar());
        }
        if(ins.getName()!=null){
            e.setName(ins.getName());
        }
        if (ins.getPhoneNumber()!= null) {
            e.setPhoneNumber(ins.getPhoneNumber());
        }
        if (ins.getVisitTime()!= null) {
            e.setVisitTime(ins.getVisitTime());
        }
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }


}
