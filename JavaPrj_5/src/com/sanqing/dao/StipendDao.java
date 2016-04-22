package com.sanqing.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.Stipend;

/**
 * @author caoyongqiang
 *
 */
public class StipendDao {

    /**
     *	���㹤������
     **/
    private Stipend getCountTotalize(Stipend e){
        float count=0;
        count=count+e.getOther();
        //count=count-e.getPlateNumber().longValue();
        count=count+e.getCarPrice();
        e.setTotalize((new Float(count)));
        return e;
    }

    public void addStipend(Stipend e) throws HibernateException{
        e=getCountTotalize(e);
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.save(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

    public void deleteStipend(Stipend e) throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

    public Stipend loadStipend(long id) throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Stipend e=(Stipend)session.get(Stipend.class,new Long(id));
        tx.commit();
        HibernateSessionFactory.closeSession();
        return e;
    }

    public List listStipend() throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select e from Stipend as e order by e.purchaseTime");
        List list = query.list();
        tx.commit();
        HibernateSessionFactory.closeSession();
        return list;
    }

    public void updateStipend(Stipend stipend) throws HibernateException{
        Stipend e =this.loadStipend(stipend.getId().longValue());
        if (stipend.getPhoneNumber()!=null){
            e.setPhoneNumber(stipend.getPhoneNumber());
        }
        if(stipend.getCar()!=null){
            e.setCar(stipend.getCar());
        }
        if (stipend.getIdCard()!= null) {
            e.setIdCard(stipend.getIdCard());
        }
        if (stipend.getHouse()!= null) {
            e.setHouse(stipend.getHouse());
        }
        if(stipend.getPurchaseTime()!=null){
            e.setPurchaseTime(stipend.getPurchaseTime());
        }
        if(stipend.getName()!=null){
            e.setName(stipend.getName());
        }
        if(stipend.getOther()!=null){
            e.setOther(stipend.getOther());
        }
        if(stipend.getPlateNumber()!=null){
            e.setPlateNumber(stipend.getPlateNumber());
        }
        if(stipend.getCarPrice()!=null){
            e.setCarPrice(stipend.getCarPrice());
        }
        e=getCountTotalize(e);
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }


}