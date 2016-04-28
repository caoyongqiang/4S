package com.sanqing.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

}
