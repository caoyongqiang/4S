package com.sanqing.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.CarOwners;

/**
 * @author caoyongqiang
 *
 */
public class CarOwnersDao {

    /**
     *	���㹤������
     **/
    private CarOwners getCountTotalize(CarOwners e){
        float count=0;
        count=count+e.getOther();
        //count=count-e.getPlateNumber().longValue();
        count=count+e.getCarPrice();
        e.setTotalize((new Float(count)));
        return e;
    }

    public void addCarOwners(CarOwners e) throws HibernateException{
        e=getCountTotalize(e);
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.save(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

    public void deleteCarOwners(CarOwners e) throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

    public CarOwners loadCarOwners(long id) throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        CarOwners e=(CarOwners)session.get(CarOwners.class,new Long(id));
        tx.commit();
        HibernateSessionFactory.closeSession();
        return e;
    }

    public List listCarOwners() throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select e from CarOwners as e order by e.purchaseTime");
        List list = query.list();
        tx.commit();
        HibernateSessionFactory.closeSession();
        return list;
    }

    public void updateCarOwners(CarOwners carOwners) throws HibernateException{
        CarOwners e =this.loadCarOwners(carOwners.getId().longValue());
        if (carOwners.getPhoneNumber()!=null){
            e.setPhoneNumber(carOwners.getPhoneNumber());
        }
        if(carOwners.getCar()!=null){
            e.setCar(carOwners.getCar());
        }
        if (carOwners.getIdCard()!= null) {
            e.setIdCard(carOwners.getIdCard());
        }
        if (carOwners.getHouse()!= null) {
            e.setHouse(carOwners.getHouse());
        }
        if(carOwners.getPurchaseTime()!=null){
            e.setPurchaseTime(carOwners.getPurchaseTime());
        }
        if(carOwners.getName()!=null){
            e.setName(carOwners.getName());
        }
        if(carOwners.getOther()!=null){
            e.setOther(carOwners.getOther());
        }
        if(carOwners.getPlateNumber()!=null){
            e.setPlateNumber(carOwners.getPlateNumber());
        }
        if(carOwners.getCarPrice()!=null){
            e.setCarPrice(carOwners.getCarPrice());
        }
        e=getCountTotalize(e);
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }


}
