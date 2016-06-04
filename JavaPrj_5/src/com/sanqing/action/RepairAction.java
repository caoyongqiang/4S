package com.sanqing.action;

import java.io.IOException;
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

import com.sanqing.dao.RepairDao;
import com.sanqing.po.Clue;
import com.sanqing.po.Repair;
import com.sanqing.po.Users;
import com.sanqing.tool.DateUtil;
import com.sanqing.tool.StringUtil;

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
        }else if("searchRepair".equals(action)){
            return searchRepair(mapping,form,request,response);
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
	    //request.setAttribute("list", dao.listRepair());
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
    private ActionForward searchRepair(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
    	try{
    	  request.setCharacterEncoding("UTF-8");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	Users u = (Users)request.getSession().getAttribute("users");
    	String ownerName = request.getParameter("ownerName");
    	String phoneNumber = request.getParameter("phoneNumber");
    	String car = request.getParameter("car");
    	String plateNumber = request.getParameter("plateNumber");
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	Map<String, String> owner=new HashMap<String, String>();
        owner.put("ownerName", ownerName);
        owner.put("phoneNumber", phoneNumber);
        owner.put("car", car);
        owner.put("plateNumber", plateNumber);
        owner.put("startDate", startDate);
        owner.put("endDate", endDate);
    	response.setCharacterEncoding("UTF-8");
    	Map<String, Object> hashMap=new HashMap<String, Object>();
    	List<Repair> ownersList = dao.searchRepair(owner);
    	int length = ownersList.size();
    	String[][] ownersArr;
    	ownersArr = new String[length][];
    	for(int i=0; i<length; i++) {
    		ownersArr[i] = new String[7];
    		ownersArr[i][0] = String.valueOf(ownersList.get(i).getId());
    		ownersArr[i][1] = ownersList.get(i).getName();
    		ownersArr[i][2] = ownersList.get(i).getTel();
    		ownersArr[i][3] = ownersList.get(i).getCar();
    		ownersArr[i][4] = ownersList.get(i).getPlateNumber();
    		ownersArr[i][5] = StringUtil.notNull(DateUtil.parseToString(ownersList.get(i).getCreatetime(),DateUtil.yyyyMMdd));
    		if(u.getRoleType() != 0) {
    		  ownersArr[i][6] = "<a href='updaterepair.do?action=detailrepair&id=" +ownersArr[i][0]+ "'>修改</a>&nbsp;&nbsp;" +
					            "<a href='modifyrepair.do?action=deleterepair&id=" +ownersArr[i][0]+ "'>删除</a>";
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
