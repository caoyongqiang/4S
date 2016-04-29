package com.sanqing.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.Service;

/**
 * @author BWeiMing
 *
 */
public class ServiceDao {

    public void addService(Service e) throws HibernateException{
        e.setCreatetime(new java.util.Date());
        e.setService(new Byte("0"));
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.save(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

    public void deleteService(Service e) throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

    public Service loadService(long id) throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Service e=(Service)session.get(Service.class,new Long(id));
        tx.commit();
        HibernateSessionFactory.closeSession();
        return e;
    }

    public List listService(byte service) throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select e from Service as e where e.service =:service order by createtime");
        query.setByte("service",service);
        List list = query.list();
        tx.commit();
        HibernateSessionFactory.closeSession();
        return list;
    }

    public void updateService(Service service) throws HibernateException{
        Service e =this.loadService(service.getId().longValue());
        if (service.getBegintime()!=null){
            e.setBegintime(service.getBegintime());
        }
        if(service.getRequireDetail()!=null){
            e.setRequireDetail(service.getRequireDetail());
        }
        if (service.getEffect()!= null) {
            e.setEffect(service.getEffect());
        }
        if (service.getEndtime()!= null) {
            e.setEndtime(service.getEndtime());
        }
        if(service.getName()!=null){
            e.setName(service.getName());
        }
        if(service.getPhoneNumber()!=null){
            e.setPhoneNumber(service.getPhoneNumber());
        }
        if(service.getPlateNumber()!=null){
            e.setPlateNumber(service.getPlateNumber());
        }
        if(service.getSummarize()!=null){
            e.setSummarize(service.getSummarize());
        }
        if(service.getService()!=null){
            e.setService(service.getService());
        }
        if(service.getCar()!=null){
            e.setCar(service.getCar());
        }
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

}
