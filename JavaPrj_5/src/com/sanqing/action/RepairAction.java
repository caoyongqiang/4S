package com.sanqing.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;

import com.sanqing.dao.RepairDao;
import com.sanqing.po.Repair;

public class RepairAction extends Action {
    private RepairDao dao=new RepairDao();
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String action =request.getParameter("action");
        System.out.println("\nRepairAction*********************action="+action);
        if(action==null||"".equals(action)){
            return mapping.findForward("error");
        }else if("listrepair".equals(action)){
            return listRepair(mapping,form,request,response);
        }else if("addrepair".equals(action)){
            return addRepair(mapping,form,request,response);
        }else if("deleterepair".equals(action)){
            return deleteRepair(mapping,form,request,response);
        }else if("updaterepair".equals(action)){
            return updateRepair(mapping,form,request,response);
        }else if("detailrepair".equals(action)){
            return detailrepair(mapping,form,request,response);
        }
        return mapping.findForward("error");
    }

	private ActionForward detailrepair(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		Long id=new Long(request.getParameter("id"));//获得参数id
		Repair j=dao.loadRepair(id.longValue());//加载该ID对应的维修信息
		request.setAttribute("repair",j);//将维修信息对象设置到request范围
		return mapping.findForward("success");
	}

    private ActionForward updateRepair(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    // 更新维修信息时需要先取出id对应的维修详细，然后与新的维修信息拼接在一起存数据库
        RepairForm repairform=(RepairForm)form;
        Repair repair=repairform.populate();
        Long id=repair.getId();//获得参数id
		Repair repairOld=dao.loadRepair(id.longValue());//加载该ID对应的维修信息
		String repairDetail = repairOld.getRepairDetail().trim()+ "--" +repair.getRepairDetail().trim();
		repair.setRepairDetail(repairDetail);
        dao.updateRepair(repair);
        return mapping.findForward("success");
    }

	private ActionForward deleteRepair(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
	    Long id=new Long(request.getParameter("id"));//接受id参数
	    Repair j=new Repair();//新建一个Repair对象
	    j.setId(id);//设置其id属性
	    dao.deleteRepair(j);//调用dao对象完成Repair对象的删除
	    return mapping.findForward("success");
	}

	private ActionForward addRepair(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
	    RepairForm repairform=(RepairForm)form;//获得RepairForm
	    Repair repair=repairform.populate();//获得表单提交的维修信息
	    dao.addRepair(repair);//调用DAO完成维修信息的保存
	    return mapping.findForward("success");//跳转到success逻辑视图
	}

	private ActionForward listRepair(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
	    String isstock=request.getParameter("isstock");//获得isstock参数
	    if(isstock==null||"".equals(isstock)){//如果isstock为null或者为空
	        request.setAttribute("list",
	        		dao.listRepair(new Byte("0").byteValue()));//查询所有没入库的维修信息
	    }else{
	        request.setAttribute("list",
	        		dao.listRepair(new Byte("1").byteValue()));//查询所有入库的维修信息
	    }
	    return mapping.findForward("success");
	}
}
