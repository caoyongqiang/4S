package com.sanqing.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        // e.setCreatetime(new java.util.Date());
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

    public Float[][] getServiceData(String startDate, String endDate) throws HibernateException{
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
        List listAll = new ArrayList<Integer>();
        List listUnDone = new ArrayList<Integer>();
        int year = Integer.parseInt(startArr[0]);
        int month = Integer.parseInt(startArr[1]);
        String str = "";
        for(int i = 0; i <= months; i++) {
          if(month >= 13) {
        	  month = 1;
        	  year++;
          }
          if(month < 10) {
        	  str = year + "-0" + (month++);
          } else{
        	  str = year + "-" + (month++);
          }
          Query queryAll = session.createQuery("select count(*) from Service as e where e.createtime like'%" 
                                           + str + "%'");
          Query queryUnDone = session.createQuery("select count(*) from Service as e where e.createtime like'%" 
                  + str + "%' and e.service =  0");
          listAll.add(queryAll.list().get(0));
          listUnDone.add(queryUnDone.list().get(0));
        }
        int size = listAll.size();
        Float[][] arr=new Float[4][];
        for(int i=0; i<4; i++) {
        	arr[i] = new Float[size];
        	switch(i) {
        	    case 0: 
        	    	for(int j=0; j<size; j++) {
            	        arr[i][j] = (float) ((Number)listAll.get(j)).intValue();
            	    }
        	    	break;
        	    case 1:
        	    	for(int j=0; j<size; j++) {
            	        arr[i][j] = (float) ((Number)listUnDone.get(j)).intValue();
            	    }
        	    	break;
        	    case 2:
        	    	for(int j=0; j<size; j++) {
            	        arr[i][j] = arr[0][j] - arr[1][j];
            	    }
        	    	break;
        	    case 3:
        	    	for(int j=0; j<size; j++) {
        	    		if(new Float(arr[0][j]).compareTo(new Float(0)) == 0) {
        	    			arr[i][j] = (float) 0;
        	    		} else {
            	            arr[i][j] = arr[2][j]/arr[0][j];
        	    		}
            	    }
        	    	break;
        	}
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
    	(cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12 + cal2.get(Calendar.MONTH)
    	- cal1.get(Calendar.MONTH);
    	return c;
    }
    
    public void updateService(Service service) throws HibernateException{
        Service e =this.loadService(service.getId().longValue());
        if(service.getRequireDetail()!=null){
            e.setRequireDetail(service.getRequireDetail());
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
