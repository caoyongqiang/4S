package com.sanqing.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.Repair;

public class RepairDao {

	public void addRepair(Repair repair) throws HibernateException{
	    repair.setCreatetime(new java.util.Date());//设置当前时间
	    Session session = HibernateSessionFactory.getSession();//获得Session对象
	    Transaction tx = session.beginTransaction();//开启事务处理
	    session.save(repair);//保存维修记录信息
	    tx.commit();//提交事务
	    HibernateSessionFactory.closeSession();//关闭Session对象
	}

	public List listRepair() throws HibernateException{
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("select j from Repair " +
			"as j order by createtime");//查询所有维修信息
		List list = query.list();//获得维修信息列表
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}

	public Repair loadRepair(long id){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Repair j = (Repair) session.get(Repair.class,id);//加载指定ID的维修信息
		HibernateSessionFactory.closeSession();//关闭Session对象
		return j;//返回查询对象
	}

	public void deleteRepair(Repair repair) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction tx = session.beginTransaction();//开启事务处理
		session.delete(repair);//删除该repair对象
		tx.commit();//提交事务
		HibernateSessionFactory.closeSession();//关闭Session对象
	}

    public void updateRepair(Repair repair){
        Repair j = loadRepair(repair.getId().longValue());
        if(repair.getRepairDetail()!=null){
            j.setRepairDetail(repair.getRepairDetail());
        }
        if (repair.getPlateNumber() != null) {
            j.setPlateNumber(repair.getPlateNumber());
        }
        if(repair.getName()!=null){
            j.setName(repair.getName());
        }
        if(repair.getCar()!=null){
            j.setCar(repair.getCar());
        }
        if(repair.getTel()!=null){
            j.setTel(repair.getTel());
        }
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.update(j);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }
    
    public List searchRepair(Map<String, String> owner) throws HibernateException{
    	String timeSqlStr = "";
    	if(owner.get("startDate")=="" && owner.get("endDate")!="") {
    		timeSqlStr = "and e.createtime < '" + owner.get("endDate") + "'";
    	}else if(owner.get("startDate")!="" && owner.get("endDate")=="") {
    		timeSqlStr = "and e.createtime > '" + owner.get("startDate") + "'";
    	}else if(owner.get("startDate")!="" && owner.get("endDate")!="") {
    		timeSqlStr = "and e.createtime between '" +owner.get("startDate")+ "' and '" + owner.get("endDate") + "'";
    	}
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select e from Repair as e " +
        		"where e.name like '%" +owner.get("ownerName")+ "%' " +
        		"and e.tel like '%" +owner.get("phoneNumber")+ "%'" +
        		"and e.plateNumber like '%" +owner.get("plateNumber")+ "%'" +
        		"and e.car like '%" +owner.get("car")+ "%'" + timeSqlStr );
        List list = query.list();
        tx.commit();
        HibernateSessionFactory.closeSession();
        return list;
    }
}
