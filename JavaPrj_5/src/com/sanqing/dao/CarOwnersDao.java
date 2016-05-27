package com.sanqing.dao;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import net.sf.json.JSONObject;

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
    
    public Integer[] carSaleDist(String startDate, String endDate) throws HibernateException{
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        String startArr[] = startDate.split("-");
        String endArr[] = endDate.split("-");
        SimpleDateFormat sdf =  new SimpleDateFormat( "yyyy-MM" );
        int months = 0;
        try{
        	Date dateStart = sdf.parse(startDate);
        	Date dateEnd = sdf.parse(endDate);
        	months = calculateMonthIn(dateStart,dateEnd);
        }catch(Exception e){
        	e.printStackTrace();
        }
        List list = new ArrayList<Integer>(); 
        for(int i = 0; i < months; i++) {
          Query query = session.createQuery("select count(*) from CarOwners as e where e.purchaseTime like'%" 
                                           + startArr[0] + "-0" + (startArr[1]+i) + "%'");
          list.add(query.list().get(0));
        }
        int size = list.size();
        Integer[] arr=new Integer[list.size()];
        for(int i=0; i<size; i++) {
        	//arr[i] = ((Number)list.get(i)).intValue();
        	arr[i] = i+1;
        }
        tx.commit();
        HibernateSessionFactory.closeSession();
        return arr;
    }
    
    public static int calculateMonthIn(Date date1, Date date2) {
    	Calendar cal1 = new GregorianCalendar();
    	cal1.setTime(date1);
    	Calendar cal2 = new GregorianCalendar();
    	cal2.setTime(date2);
    	int c =
    	(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
    	- cal2.get(Calendar.MONTH);
    	return c;
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
