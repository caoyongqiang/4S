package com.sanqing.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.Repair;

public class RepairDao {

	public void addRepair(Repair repair) throws HibernateException{
	    repair.setIsstock(new Byte("0"));//设置入库信息为0，表示暂时不入库
	    repair.setCreatetime(new java.util.Date());//设置当前时间
	    Session session = HibernateSessionFactory.getSession();//获得Session对象
	    Transaction tx = session.beginTransaction();//开启事务处理
	    session.save(repair);//保存应聘人员信息
	    tx.commit();//提交事务
	    HibernateSessionFactory.closeSession();//关闭Session对象
	}

	public List listRepair(byte isstock) throws HibernateException{
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("select j from Repair " +
			"as j where j.isstock = :isstock order by createtime");//查询所有维修信息
		query.setByte("isstock",isstock);//设置是否入库
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
        if (repair.getRepairTimes()!=null){
            j.setRepairTimes(repair.getRepairTimes());
        }
        if(repair.getRepairDetail()!=null){
            j.setRepairDetail(repair.getRepairDetail());
        }
        if (repair.getPlateNumber() != null) {
            j.setPlateNumber(repair.getPlateNumber());
        }
        if (repair.getIsstock()!= null) {
            j.setIsstock(repair.getIsstock());
        }
        if(repair.getRepair()!=null){
            j.setRepair(repair.getRepair());
        }
        if(repair.getName()!=null){
            j.setName(repair.getName());
        }
        if(repair.getRepairCost()!=null){
            j.setRepairCost(repair.getRepairCost());
        }
        if(repair.getCar()!=null){
            j.setCar(repair.getCar());
        }
        if(repair.getRepairType()!=null){
            j.setRepairType(repair.getRepairType());
        }
        if(repair.getTel()!=null){
            j.setTel(repair.getTel());
        }
        if(repair.getEmail()!=null){
            j.setEmail(repair.getEmail());
        }
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.update(j);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }
}
