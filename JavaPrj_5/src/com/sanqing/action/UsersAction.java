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

import com.sanqing.dao.UsersDao;
import com.sanqing.po.Clue;
import com.sanqing.po.Users;
import com.sanqing.tool.DateUtil;
import com.sanqing.tool.StringUtil;

public class UsersAction extends Action {

    private UsersDao dao=new UsersDao();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action =request.getParameter("action");
		if(action==null||"".equals(action)){
			return mapping.findForward("error");
		}else if("listuser".equals(action)){
			return listUser(mapping,form,request,response);
		}else if("adduser".equals(action)){
			return addUsers(mapping,form,request,response);
		}else if("logon".equals(action)){
			return logon(mapping,form,request,response);
		}else if("updateuser".equals(action)){
			return updateUser(mapping,form,request,response);
		}else if("deleteuser".equals(action)){
			return deleteUser(mapping,form,request,response);
		}else if("selectuser".equals(action)){
			return selectUser(mapping,form,request,response);
		}else if("searchUsers".equals(action)){
			return searchUsers(mapping,form,request,response);
		}
		return mapping.findForward("error");
	}

	private ActionForward selectUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		Long id=new Long(request.getParameter("id"));//获得id参数
		Users u=dao.loadUsers(id);//加载该ID对应的人员信息
		request.setAttribute("user",u);//将人员信息设置到request范围
		return mapping.findForward("success");//跳转到success对应的逻辑视图
	}
	
	private ActionForward updateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		UsersForm usersform=(UsersForm)form;//获得UsersForm
		Users users=usersform.populate();//获得提交的人员信息
		dao.updateUsers(users);//更新人员信息
		return mapping.findForward("success");//跳转到success对应的逻辑视图
	}

	private ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		Long id=new Long(request.getParameter("id"));//获得id参数
		Users users=new Users();//新建一个users对象
		users.setId(id);//设置该users对象的id
		dao.deleteUsers(users);//删除该users对象
		return mapping.findForward("success");
	}
    private ActionForward logon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
        UsersForm usersform=(UsersForm)form;
        Users users=new Users();
        users.setUsername(usersform.getUsername());
        users.setPassword(usersform.getPassword());
        boolean flag=dao.logonUsers(users);
        if(flag){
            request.getSession().setAttribute("users",dao.getUser(users));
            return mapping.findForward("success");
        }else{
        	request.setAttribute("error", "登录失败");
            return mapping.findForward("failed");
       }
    }

	private ActionForward addUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
	    UsersForm usersform=(UsersForm)form;	//获得UsersForm
	    Users users=usersform.populate();		//获得提交的人员信息
	    boolean flag=dao.exitUsers(users);     //相同用户名不能添加
	    if(flag){
	    	request.setAttribute("error", "用户名已经存在");
            return mapping.findForward("failed");
        }else{
        	dao.addUsers(users);					//完成人员信息保存
    	    return mapping.findForward("success");	//跳转到成功页面
       }
	}

	private ActionForward listUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
	    //request.setAttribute("list",dao.listUser());//将人员信息列表保存到request范围
	    return mapping.findForward("success");		//跳转到成功页面
	}
	
	/**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws HibernateException
     */
    private ActionForward searchUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HibernateException {
    	try{
    	  request.setCharacterEncoding("UTF-8");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	String ownerName = request.getParameter("ownerName");
    	String phoneNumber = request.getParameter("phoneNumber");
    	String idCard = request.getParameter("idCard");
    	String role = request.getParameter("role");
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	Map<String, String> owner=new HashMap<String, String>();
        owner.put("ownerName", ownerName);
        owner.put("phoneNumber", phoneNumber);
        owner.put("idCard", idCard);
        owner.put("role", "(" + role + ")");
        owner.put("startDate", startDate);
        owner.put("endDate", endDate);
    	response.setCharacterEncoding("UTF-8");
    	Map<String, Object> hashMap=new HashMap<String, Object>();
    	List<Users> ownersList = dao.searchUsers(owner);
    	int length = ownersList.size();
    	String[][] ownersArr;
    	ownersArr = new String[length][];
    	for(int i=0; i<length; i++) {
    		ownersArr[i] = new String[8];
    		ownersArr[i][0] = String.valueOf(ownersList.get(i).getId());
    		ownersArr[i][1] = ownersList.get(i).getUsername();
    		ownersArr[i][2] = ownersList.get(i).getPhoneNumber();
    		ownersArr[i][3] = ownersList.get(i).getIdCard();
    		if(ownersList.get(i).getRoleType() == 0) {
    			ownersArr[i][4] = "普通员工";
    		}else if(ownersList.get(i).getRoleType() == 1) {
    			ownersArr[i][4] = "中层管理员";
    		}else if(ownersList.get(i).getRoleType() == 2) {
    			ownersArr[i][4] = "高层管理员";
    		}
    		ownersArr[i][5] = ownersList.get(i).getContent();
    		ownersArr[i][6] = StringUtil.notNull(DateUtil.parseToString(ownersList.get(i).getCreatetime(),DateUtil.yyyyMMdd));
    		ownersArr[i][7] = "<a href='selectuser.do?action=selectuser&id=" +ownersArr[i][0]+ "'>修改</a>&nbsp;&nbsp;" +
					          "<a href='modifyuser.do?action=deleteuser&id=" +ownersArr[i][0]+ "'>删除</a>&nbsp;&nbsp;";
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
