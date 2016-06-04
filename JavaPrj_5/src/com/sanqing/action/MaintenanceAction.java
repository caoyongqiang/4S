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
        }else if("deleteclue".equals(action)){
            return deleteClue(mapping,form,request,response);
        }else if("detailMaintenance".equals(action)){
            return detailMaintenance(mapping,form,request,response);
        }else if("clueDist".equals(action)){
            return clueDist(mapping,form,request,response);
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
    private ActionForward detailMaintenance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        Long id=new Long(request.getParameter("id"));
        Maintenance i=dao.loadMaintenance(id.longValue());
        request.setAttribute("maintenance",i);
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
    private ActionForward deleteClue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        Long id=new Long(request.getParameter("id"));
        Clue i=new Clue();
        i.setId(id);
        dao.deleteClue(i);
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
    	e.setName(request.getParameter("name"));
    	e.setPhoneNumber(request.getParameter("phoneNumber"));
    	e.setCar(request.getParameter("car"));
    	e.setPlateNumber(request.getParameter("plateNumber"));
    	e.setContent(request.getParameter("content"));
    	e.setPreTime(DateUtil.parseToDate(request.getParameter("preTime"),DateUtil.yyyyMMdd));
    	e.setNextTime(DateUtil.parseToDate(request.getParameter("nextTime"),DateUtil.yyyyMMdd));
    	if(request.getParameter("id") != ""){
    		e.setId(new Long(request.getParameter("id")));
    		dao.updateMaintenance(e);
    	}else{
            dao.addMaintenance(e);
    	}
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
    private ActionForward listClue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        request.setAttribute("list",dao.listClue());
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
    	response.setCharacterEncoding("UTF-8");
    	Map<String, Object> hashMap=new HashMap<String, Object>();
    	List<Maintenance> ownersList = dao.searchMaintenance(owner);
    	int length = ownersList.size();
    	String[][] ownersArr;
    	ownersArr = new String[length][];
    	for(int i=0; i<length; i++) {
    		ownersArr[i] = new String[9];
    		ownersArr[i][0] = String.valueOf(ownersList.get(i).getId());
    		ownersArr[i][1] = ownersList.get(i).getName();
    		ownersArr[i][2] = ownersList.get(i).getPhoneNumber();
    		ownersArr[i][3] = ownersList.get(i).getCar();
    		ownersArr[i][4] = ownersList.get(i).getPlateNumber();
    		ownersArr[i][5] = ownersList.get(i).getContent();
    		ownersArr[i][6] = StringUtil.notNull(DateUtil.parseToString(ownersList.get(i).getPreTime(),DateUtil.yyyyMMdd));
    		ownersArr[i][7] = StringUtil.notNull(DateUtil.parseToString(ownersList.get(i).getNextTime(),DateUtil.yyyyMMdd));
    		ownersArr[i][8] = "<a href='updatemaintenance.do?action=detailMaintenance&id=" +ownersArr[i][0]+ "'>修改</a>&nbsp;&nbsp;" +
					          "<a href='modifyclue.do?action=deleteclue&id=" +ownersArr[i][0]+ "'>删除</a>&nbsp;&nbsp;" + 
					          "<a href='buyclue.do?action=detailclue&id=" +ownersArr[i][0]+ "'>购车</a>";
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
}
