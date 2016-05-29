package com.sanqing.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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

import com.sanqing.dao.ClueDao;
import com.sanqing.po.Clue;

/**
 * @author BWeiMing
 *
 */
public class ClueAction extends Action {
    private ClueDao dao=new ClueDao();

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String action =request.getParameter("action");
        System.out.println("\nClueAction*********************action="+action);
        if(action==null||"".equals(action)){
            return mapping.findForward("error");
        }else if("listclue".equals(action)){
            return listClue(mapping,form,request,response);
        }else if("addclue".equals(action)){
            return addClue(mapping,form,request,response);
        }else if("updateclue".equals(action)){
            return updateClue(mapping,form,request,response);
        }else if("deleteclue".equals(action)){
            return deleteClue(mapping,form,request,response);
        }else if("detailclue".equals(action)){
            return detailClue(mapping,form,request,response);
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
    private ActionForward detailClue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        Long id=new Long(request.getParameter("id"));
        Clue i=dao.loadClue(id.longValue());
        request.setAttribute("clue",i);
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
    private ActionForward updateClue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        ClueForm clueForm=(ClueForm)form;
        Clue i=clueForm.populate();
        dao.updateClue(i);
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
    private ActionForward addClue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        ClueForm clueForm=(ClueForm)form;
        Clue i=clueForm.populate();
        dao.addClue(i);
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
}
