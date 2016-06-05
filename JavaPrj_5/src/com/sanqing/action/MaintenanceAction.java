package com.sanqing.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
import com.sanqing.dao.ClueDao;
import com.sanqing.dao.MaintenanceDao;
import com.sanqing.po.CarOwners;
import com.sanqing.po.Clue;
import com.sanqing.po.Maintenance;
import com.sanqing.po.Service;
import com.sanqing.tool.DateUtil;
import com.sanqing.tool.StringUtil;

/**
 * @author BWeiMing
 *
 */
public class MaintenanceAction extends Action {
    private MaintenanceDao dao=new MaintenanceDao();

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String action =request.getParameter("action");
        System.out.println("\nMaintenanceAction*********************action="+action);
        if(action==null||"".equals(action)){
            return mapping.findForward(null);
        }else if("searchMaintenance".equals(action)){
            return searchMaintenance(mapping,form,request,response);
        }else if("addMaintenance".equals(action)){
            return addMaintenance(mapping,form,request,response);
        }else if("updateclue".equals(action)){
            return updateMaintenance(mapping,form,request,response);
        }else if("deleteMaintenance".equals(action)){
            return deleteMaintenance(mapping,form,request,response);
        }else if("doneMaintenance".equals(action)){
            return doneMaintenance(mapping,form,request,response);
        }else if("clueDist".equals(action)){
            return clueDist(mapping,form,request,response);
        }else if("detailMaintenance".equals(action)){
            return detailMaintenance(mapping,form,request,response);
        }else if("addOtherMaintenance".equals(action)){
            return addOtherMaintenance(mapping,form,request,response);
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
    private ActionForward doneMaintenance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        Long id=new Long(request.getParameter("id"));
        Maintenance i=dao.loadMaintenance(id.longValue());
        /*i.setIsDone(new Long(1));
        dao.updateMaintenance(i);*/
        CarOwnersDao ownerDao = new CarOwnersDao();
        CarOwners owner = ownerDao.loadCarOwners(i.getOwnerId());
        HashMap<String , Object> map = new HashMap<String , Object>();
        map.put("carOwner", owner);
        map.put("maintenance", i);
        request.setAttribute("info",map);
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
    private ActionForward deleteMaintenance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        Long id=new Long(request.getParameter("id"));
        Maintenance i=new Maintenance();
        i.setId(id);
        dao.deleteMaintenance(i);
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
    private ActionForward updateMaintenance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        /*ClueForm clueForm=(ClueForm)form;
        Clue i=clueForm.populate();*/
    	Maintenance m = new Maintenance();
    	m.setIsDone(new Long(1));
        dao.updateMaintenance(m);
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     * @throws IOException 
     */
    private ActionForward addMaintenance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException, IOException {
    	Maintenance e = new Maintenance();
    	/*e.setName(request.getParameter("name"));
    	e.setPhoneNumber(request.getParameter("phoneNumber"));
    	e.setCar(request.getParameter("car"));
    	e.setPlateNumber(request.getParameter("plateNumber"));*/
    	e.setContent(request.getParameter("content"));
    	e.setPreTime(DateUtil.parseToDate(request.getParameter("preTime"),DateUtil.yyyyMMdd));
    	e.setNextTime(DateUtil.parseToDate(request.getParameter("nextTime"),DateUtil.yyyyMMdd));
    	e.setIsDone(new Long(0));
    	if(request.getParameter("id") != ""){
    		Long id = new Long(request.getParameter("id"));
    		Maintenance i=dao.loadMaintenance(id.longValue());
    		i.setIsDone(new Long(1));
    		dao.updateMaintenance(i);
    		e.setOwnerId(i.getOwnerId());
    	}
        dao.addMaintenance(e);
        return mapping.findForward(null);
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     * @throws IOException 
     */
    private ActionForward addOtherMaintenance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException, IOException {
    	Maintenance e = new Maintenance();
    	CarOwners c = new CarOwners();
    	CarOwnersDao ownersDao=new CarOwnersDao();
    	c.setName(request.getParameter("name"));
    	c.setPhoneNumber(request.getParameter("phoneNumber"));
    	c.setCar(request.getParameter("car"));
    	c.setPlateNumber(request.getParameter("plateNumber"));
    	ownersDao.addCarOwners(c);
    	e.setContent(request.getParameter("content"));
    	e.setPreTime(DateUtil.parseToDate(request.getParameter("preTime"),DateUtil.yyyyMMdd));
    	e.setNextTime(DateUtil.parseToDate(request.getParameter("nextTime"),DateUtil.yyyyMMdd));
    	e.setOwnerId(c.getId());
    	e.setIsDone(new Long(0));
        dao.addMaintenance(e);
        return mapping.findForward(null);
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     */
    private ActionForward clueDist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
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
        hashMap.put("clues", dao.getClueDist(startDate, endDate));
    	try {
    		JSONObject result=JSONObject.fromObject(hashMap);
    		response.getWriter().write(result.toString());
            response.getWriter().close();
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
    private ActionForward searchMaintenance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
    	try{
    	  request.setCharacterEncoding("UTF-8");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	String name = request.getParameter("name");
    	String phoneNumber = request.getParameter("phoneNumber");
    	String car = request.getParameter("car");
    	String plateNumber = request.getParameter("plateNumber");
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	String status = request.getParameter("status");
    	/*if(startDate == "" && endDate == "") {
    		Calendar calendar = new GregorianCalendar(); 
            Calendar cal  = Calendar.getInstance();
            SimpleDateFormat formatter_shuzi = new SimpleDateFormat("yyyy-MM-dd");
            endDate = formatter_shuzi.format(cal.getTime());
            cal.add(Calendar.WEEK_OF_YEAR, -1);
            startDate = formatter_shuzi.format(cal.getTime());
    	}*/
    	Map<String, String> owner=new HashMap<String, String>();
        owner.put("name", name);
        owner.put("phoneNumber", phoneNumber);
        owner.put("car", car);
        owner.put("plateNumber", plateNumber);
        owner.put("startDate", startDate);
        owner.put("endDate", endDate);
        owner.put("status", "(" + status + ")");
    	response.setCharacterEncoding("UTF-8");
    	Map<String, Object> hashMap=new HashMap<String, Object>();
    	List<Object[]> ownersList = (List<Object[]>)dao.searchMaintenance(owner);
    	int length = ownersList.size();
    	String[][] ownersArr;
    	ownersArr = new String[length][];
    	for(int i=0; i<length; i++) {
    		ownersArr[i] = new String[9];
    		ownersArr[i][0] = String.valueOf(ownersList.get(i)[0]);
    		ownersArr[i][1] = String.valueOf(ownersList.get(i)[1]);
    		ownersArr[i][2] = String.valueOf(ownersList.get(i)[2]);
    		ownersArr[i][3] = String.valueOf(ownersList.get(i)[3]);
    		ownersArr[i][4] = String.valueOf(ownersList.get(i)[4]);
    		ownersArr[i][5] = String.valueOf(ownersList.get(i)[5]);
    		ownersArr[i][6] = StringUtil.notNull(DateUtil.parseToString(String.valueOf(ownersList.get(i)[6]),DateUtil.yyyyMMdd));
    		ownersArr[i][7] = StringUtil.notNull(DateUtil.parseToString(String.valueOf(ownersList.get(i)[7]),DateUtil.yyyyMMdd));
    		if((Long)ownersList.get(i)[8] != 1){
    		    ownersArr[i][8] = "<a href='updatemaintenance.do?action=doneMaintenance&id=" +ownersArr[i][0]+ "'>完成</a>&nbsp;&nbsp;" +
					              "<a href='maintenance.do?action=deleteMaintenance&id=" +ownersArr[i][0]+ "'>删除</a>&nbsp;&nbsp;" + 
					              "<a href='updatemaintenance.do?action=detailMaintenance&id=" +ownersArr[i][0]+ "'>详情</a>";
    		}else{
    			ownersArr[i][8] = "<a href='donemaintenance.do?action=detailMaintenance&id=" +ownersArr[i][0]+ "'>已完成</a>";
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
        return mapping.findForward(null);
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     */
    private ActionForward detailMaintenance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
    	Long id=new Long(request.getParameter("id"));
        Maintenance i=dao.loadMaintenance(id.longValue());
        CarOwnersDao ownerDao = new CarOwnersDao();
        CarOwners owner = ownerDao.loadCarOwners(i.getOwnerId());
        HashMap<String , Object> map = new HashMap<String , Object>();
        map.put("carOwner", owner);
        map.put("maintenance", i);
        request.setAttribute("info",map);
        return mapping.findForward("success");
    }
}
