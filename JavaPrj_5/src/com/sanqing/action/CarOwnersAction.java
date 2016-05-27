package com.sanqing.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import com.sanqing.po.CarOwners;
import com.sanqing.po.Users;
import com.sanqing.dao.ClueDao;
import com.sanqing.po.Clue;

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
    	if (u.getRoleType() != 2 || u.getRoleType() != 3) {
    		return mapping.findForward("success");
    	}
    	Long id=new Long(request.getParameter("id").toString());
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
        request.setAttribute("list",dao.listCarOwners());
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
            startDate = formatter_shuzi.format(cal.getTime());
            cal.add(Calendar.MONTH, -7);
            endDate = formatter_shuzi.format(cal.getTime());
    	}
        request.setAttribute("arr",dao.carSaleDist(startDate, endDate));
        return mapping.findForward("chart");
    }
}
