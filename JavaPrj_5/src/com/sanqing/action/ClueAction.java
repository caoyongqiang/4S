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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;

import com.sanqing.dao.ClueDao;
import com.sanqing.po.CarOwners;
import com.sanqing.po.Clue;
import com.sanqing.tool.DateUtil;
import com.sanqing.tool.StringUtil;

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
        }else if("searchClues".equals(action)){
            return searchClues(mapping,form,request,response);
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
        //request.setAttribute("list",dao.listClue());
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
    private ActionForward searchClues(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
    	try{
    	  request.setCharacterEncoding("UTF-8");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	String clueName = request.getParameter("clueName");
    	String phoneNumber = request.getParameter("phoneNumber");
    	String idCard = request.getParameter("idCard");
    	String house = request.getParameter("house");
    	String car = request.getParameter("car");
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	Map<String, String> owner=new HashMap<String, String>();
        owner.put("clueName", clueName);
        owner.put("phoneNumber", phoneNumber);
        owner.put("idCard", idCard);
        owner.put("house", house);
        owner.put("car", car);
        owner.put("startDate", startDate);
        owner.put("endDate", endDate);
    	response.setCharacterEncoding("UTF-8");
    	Map<String, Object> hashMap=new HashMap<String, Object>();
    	List<Clue> ownersList = dao.searchClues(owner);
    	int length = ownersList.size();
    	String[][] ownersArr;
    	ownersArr = new String[length][];
    	for(int i=0; i<length; i++) {
    		ownersArr[i] = new String[8];
    		ownersArr[i][0] = String.valueOf(ownersList.get(i).getId());
    		ownersArr[i][1] = ownersList.get(i).getName();
    		ownersArr[i][2] = ownersList.get(i).getPhoneNumber();
    		ownersArr[i][3] = ownersList.get(i).getIdCard();
    		ownersArr[i][4] = ownersList.get(i).getHouse();
    		ownersArr[i][5] = ownersList.get(i).getDesireCar();
    		ownersArr[i][6] = StringUtil.notNull(DateUtil.parseToString(ownersList.get(i).getVisitTime(),DateUtil.yyyyMMdd));
    		ownersArr[i][7] = "<a href='updateclue.do?action=detailclue&id=" +ownersArr[i][0]+ "'>修改</a>&nbsp;&nbsp;" +
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
        return mapping.findForward("success");
    }
}
