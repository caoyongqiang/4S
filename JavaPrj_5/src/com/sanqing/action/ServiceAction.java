package com.sanqing.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;

import com.sanqing.dao.ServiceDao;
import com.sanqing.po.Clue;
import com.sanqing.po.Service;
import com.sanqing.tool.DateUtil;
import com.sanqing.tool.StringUtil;
/**
 * @author BWeiMing
 *
 */
public class ServiceAction extends Action {
    private ServiceDao dao=new ServiceDao();
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String action =request.getParameter("action");
        System.out.println("\nServiceAction*********************action="+action);
        if(action==null||"".equals(action)){
            return mapping.findForward("error");
        }else if("listservice".equals(action)){
            return listService(mapping,form,request,response);
        }else if("addservice".equals(action)){
            return addService(mapping,form,request,response);
        }else if("deleteservice".equals(action)){
            return deleteService(mapping,form,request,response);
        }else if("detailservice".equals(action)){
            return detailService(mapping,form,request,response);
        }else if("updateservice".equals(action)){
            return updateService(mapping,form,request,response);
        }else if("serviceAnalysis".equals(action)){
            return serviceAnalysis(mapping,form,request,response);
        }else if("searchService".equals(action)){
            return searchService(mapping,form,request,response);
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
    private ActionForward updateService(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        ServiceForm serviceForm=(ServiceForm)form;
        Service e=serviceForm.populate();
        dao.updateService(e);
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
    private ActionForward detailService(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        Long id=new Long(request.getParameter("id"));
        Service e = dao.loadService(id.longValue());
        System.out.println(e.getName());
        request.setAttribute("service",e);
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
    private ActionForward deleteService(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        Long id=new Long(request.getParameter("id").toString());
        Service e=new Service();
        e.setId(id);
        dao.deleteService(e);
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
    private ActionForward addService(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        ServiceForm serviceForm=(ServiceForm)form;
        Service e=serviceForm.populate();
        dao.addService(e);
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
    private ActionForward serviceAnalysis(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
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
        hashMap.put("serviceData", dao.getServiceData(startDate, endDate));
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
    private ActionForward listService(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        String service=request.getParameter("service");
        if(service==null||"".equals(service)||"0".equals(service)){
            request.setAttribute("list",dao.listService(new Byte("0").byteValue()));
        }else{
            request.setAttribute("list",dao.listService(new Byte("1").byteValue()));
        }
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
    private ActionForward searchService(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
    	try{
    	  request.setCharacterEncoding("UTF-8");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	String ownerName = request.getParameter("ownerName");
    	String phoneNumber = request.getParameter("phoneNumber");
    	String car = request.getParameter("car");
    	String plateNumber = request.getParameter("plateNumber");
    	String service = request.getParameter("service");
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	Map<String, String> owner=new HashMap<String, String>();
        owner.put("ownerName", ownerName);
        owner.put("phoneNumber", phoneNumber);
        owner.put("plateNumber", plateNumber);
        owner.put("car", car);
        owner.put("startDate", startDate);
        owner.put("endDate", endDate);
        owner.put("service", "(" + service + ")");
    	response.setCharacterEncoding("UTF-8");
    	Map<String, Object> hashMap=new HashMap<String, Object>();
    	List<Service> ownersList = dao.searchService(owner);
    	int length = ownersList.size();
    	String[][] ownersArr;
    	ownersArr = new String[length][];
    	for(int i=0; i<length; i++) {
    		ownersArr[i] = new String[7];
    		ownersArr[i][0] = String.valueOf(ownersList.get(i).getId());
    		ownersArr[i][1] = ownersList.get(i).getName();
    		ownersArr[i][2] = ownersList.get(i).getPhoneNumber();
    		ownersArr[i][3] = ownersList.get(i).getPlateNumber();
    		ownersArr[i][4] = ownersList.get(i).getCar();
    		ownersArr[i][5] = StringUtil.notNull(DateUtil.parseToString(ownersList.get(i).getCreatetime(),DateUtil.yyyyMMdd));
    		ownersArr[i][6] = "<a href='detailservice.do?service=<%=service%>&action=detailservice&id=" +ownersArr[i][0]+ "'>详情</a>&nbsp;&nbsp;" +
					          "<a href='modifyservice.do?action=deleteservice&id=" +ownersArr[i][0]+ "'>删除</a>&nbsp;&nbsp;";
    		if(ownersList.get(i).getService().equals(new Byte("0"))) {
    			ownersArr[i][6] += "<a href='updateservice.do?service=1&action=detailservice&id=" +ownersArr[i][0]+ "'>需求完成</a>";
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
        return mapping.findForward("success");
    }
}
