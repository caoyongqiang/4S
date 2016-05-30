package com.sanqing.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.CarOwners;
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
    
    public Integer[] getClueDist(String startDate, String endDate) throws HibernateException{
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
          Query query = session.createQuery("select count(*) from Clue as e where e.visitTime like'%" 
                                           + str + "%'");
          list.add(query.list().get(0));
        }
        int size = list.size();
        Integer[] arr=new Integer[list.size()];
        for(int i=0; i<size; i++) {
        	arr[i] = ((Number)list.get(i)).intValue();
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

	public List searchClues(Map<String, String> owner) throws HibernateException{
    	String timeSqlStr = "";
    	if(owner.get("startDate")=="" && owner.get("endDate")!="") {
    		timeSqlStr = "and e.visitTime < '" + owner.get("endDate") + "'";
    	}else if(owner.get("startDate")!="" && owner.get("endDate")=="") {
    		timeSqlStr = "and e.visitTime > '" + owner.get("startDate") + "'";
    	}else if(owner.get("startDate")!="" && owner.get("endDate")!="") {
    		timeSqlStr = "and e.visitTime between '" +owner.get("startDate")+ "' and '" + owner.get("endDate") + "'";
    	}
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select e from Clue as e " +
        		"where e.name like '%" +owner.get("clueName")+ "%' " +
        		"and e.phoneNumber like '%" +owner.get("phoneNumber")+ "%'" +
        		"and e.idCard like '%" +owner.get("idCard")+ "%'" +
        		"and e.house like '%" +owner.get("house")+ "%'" +
        		"and e.desireCar like '%" +owner.get("car")+ "%'" + timeSqlStr );
        List list = query.list();
        tx.commit();
        HibernateSessionFactory.closeSession();
        return list;
    }


}
