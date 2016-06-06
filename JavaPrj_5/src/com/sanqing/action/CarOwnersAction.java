package com.sanqing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;

import com.sanqing.dao.CarOwnersDao;
import com.sanqing.dao.MaintenanceDao;
import com.sanqing.po.CarOwners;
import com.sanqing.po.Maintenance;
import com.sanqing.po.Users;
import com.sanqing.dao.ClueDao;
import com.sanqing.po.Clue;
import com.sanqing.tool.DateUtil;
import com.sanqing.tool.StringUtil;

public class CarOwnersAction extends Action {
    private CarOwnersDao dao=new CarOwnersDao();

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String action =request.getParameter("action").trim();
        System.out.println("\nCarOwnersAction*********************action="+action);
        if(action==null||"".equals(action)){
            return mapping.findForward("error");
        }else if("listcarOwners".equals(action)){
            return listCarOwners(mapping,form,request,response);
        }else if("addcarOwners".equals(action)){
            return addCarOwners(mapping,form,request,response);
        }else if("deletecarOwners".equals(action)){
            return deleteCarOwners(mapping,form,request,response);
        }else if("updatecarOwners".equals(action)){
            return updateCarOwners(mapping,form,request,response);
        }else if("detailcarOwners".equals(action)){
            return detailCarOwners(mapping,form,request,response);
        }else if("carOwnersChart".equals(action)){
            return carOwnersChart(mapping,form,request,response);
        }else if("searchCarOwners".equals(action)){
            return searchCarOwners(mapping,form,request,response);
        }
        return mapping.findForward("error");
    }

	/**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     */
    private ActionForward detailCarOwners(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        Long id=new Long(request.getParameter("id"));
        CarOwners s=dao.loadCarOwners(id.longValue());
        request.setAttribute("carOwners",s);
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     */
    private ActionForward updateCarOwners(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        CarOwnersForm carOwnersForm=(CarOwnersForm)form;
        CarOwners s=carOwnersForm.populate();
        dao.updateCarOwners(s);
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     */
    private ActionForward deleteCarOwners(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
    	Users u = (Users)request.getSession().getAttribute("users");
    	if (u.getRoleType() != 2 && u.getRoleType() != 3) {
    		return mapping.findForward("success");
    	}
    	Long id=new Long(request.getParameter("id"));
        CarOwners s=new CarOwners();
        s.setId(id);
        dao.deleteCarOwners(s);
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     */
    private ActionForward addCarOwners(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
    	String id=request.getParameter("id"); // id用来判断是否删除线索信息中的记录
    	if (id != null) {
    		Long lid = new Long(id);
    		ClueDao dao=new ClueDao();
            Clue i=new Clue();
            i.setId(lid);
            dao.deleteClue(i);
    	}
    	CarOwnersForm carOwnersForm=(CarOwnersForm)form;
        CarOwners s=carOwnersForm.populate();
        dao.addCarOwners(s);
        //车主买车后3个月保养
        MaintenanceDao mainDao=new MaintenanceDao();
        Maintenance main = new Maintenance();
        Calendar c=Calendar.getInstance();
        c.setTime(s.getPurchaseTime());
        c.add(Calendar.MONTH,3);
        main.setOwnerId(s.getId());
        main.setPreTime(s.getPurchaseTime());
        main.setNextTime(c.getTime());
        main.setContent("首保");
        main.setIsDone(new Long(0));
        mainDao.addMaintenance(main);
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     */
    private ActionForward listCarOwners(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        //request.setAttribute("list",dao.listCarOwners());
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     */
    private ActionForward carOwnersChart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	if(startDate == null && endDate == null) {
    		Calendar calendar = new GregorianCalendar(); 
            Calendar cal  = Calendar.getInstance();
            SimpleDateFormat formatter_shuzi = new SimpleDateFormat("yyyy-MM");
            endDate = formatter_shuzi.format(cal.getTime());
            cal.add(Calendar.MONTH, -11);
            startDate = formatter_shuzi.format(cal.getTime());
    	}
    	response.setCharacterEncoding("UTF-8");
    	Map<String, Object> hashMap=new HashMap<String, Object>();
        hashMap.put("owners", dao.carSaleDist(startDate, endDate));
    	try {
    		JSONArray result=JSONArray.fromObject(hashMap);
            response.getWriter().write(result.toString());
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        return mapping.findForward("chart");
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     */
    private ActionForward searchCarOwners(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
    	try{
    	  request.setCharacterEncoding("UTF-8");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	Users u = (Users)request.getSession().getAttribute("users");
    	String ownerName = request.getParameter("ownerName");
    	String phoneNumber = request.getParameter("phoneNumber");
    	String idCard = request.getParameter("idCard");
    	String house = request.getParameter("house");
    	String car = request.getParameter("car");
    	String plateNumber = request.getParameter("plateNumber");
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	Map<String, String> owner=new HashMap<String, String>();
        owner.put("ownerName", ownerName);
        owner.put("phoneNumber", phoneNumber);
        owner.put("idCard", idCard);
        owner.put("house", house);
        owner.put("car", car);
        owner.put("plateNumber", plateNumber);
        owner.put("startDate", startDate);
        owner.put("endDate", endDate);
    	response.setCharacterEncoding("UTF-8");
    	Map<String, Object> hashMap=new HashMap<String, Object>();
    	List<CarOwners> ownersList = dao.searchCarOwners(owner);
    	int length = ownersList.size();
    	String[][] ownersArr;
    	ownersArr = new String[length][];
    	for(int i=0; i<length; i++) {
    		ownersArr[i] = new String[13];
    		ownersArr[i][0] = String.valueOf(ownersList.get(i).getId());
    		ownersArr[i][1] = ownersList.get(i).getName();
    		ownersArr[i][2] = ownersList.get(i).getPhoneNumber();
    		ownersArr[i][3] = ownersList.get(i).getIdCard();
    		ownersArr[i][4] = ownersList.get(i).getHouse();
    		ownersArr[i][5] = ownersList.get(i).getCar();
    		ownersArr[i][6] = String.valueOf(ownersList.get(i).getCarPrice());
    		ownersArr[i][7] = String.valueOf(ownersList.get(i).getOther());
    		ownersArr[i][8] = String.valueOf(ownersList.get(i).getTotalize());
    		ownersArr[i][9] = ownersList.get(i).getPlateNumber();
    		ownersArr[i][10] = StringUtil.notNull(DateUtil.parseToString(ownersList.get(i).getPurchaseTime(),DateUtil.yyyyMMdd));
    		ownersArr[i][11] = ownersList.get(i).getSeller();
    		if(u.getRoleType() == 2 || u.getRoleType()==3) {
    		  ownersArr[i][12] = "<a href='updatecarOwners.do?action=detailcarOwners&id=" +ownersArr[i][0]+ "'>修改</a>&nbsp;&nbsp;" +
				   	             "<a href='modifycarOwners.do?action=deletecarOwners&id=" +ownersArr[i][0]+ "'>删除</a>";
    		}
    	}
        hashMap.put("owners", ownersArr);
    	try {
    		JSONObject result=JSONObject.fromObject(hashMap);
            response.getWriter().write(result.toString());
            response.getWriter().close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	// request.setAttribute("list",dao.listCarOwners());
        return mapping.findForward("success");
    }
}
