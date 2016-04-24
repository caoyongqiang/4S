<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.sanqing.po.Institution"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>4S店客户管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.tabfont01 {	
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}
.font051 {font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>

<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">

</script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    <td height="30">      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="62" background="images/nav04.gif">&nbsp;</td>
        </tr>
    </table></td></tr>
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          	 <tr>
               <td height="20"><span class="newfont07">线索信息查看</span></td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
				<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="8" align="center" style="font-size:16px">线索信息列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				        <td height="22" align="center" >线索姓名</td>
						<td height="22" align="center" >手机号</td>
						<td height="22" align="center" >身份证号</td>
						<td height="22" align="center" >家庭住址</td>
						<td height="22" align="center" >欲购车型</td>
						<td height="22" align="center" >来电时间</td>
						<td height="22" align="center" >执行操作</td>
                  </tr>
				<% List list=(List)request.getAttribute("list");
				 if(list!=null&&list.size()>0){
					Iterator it = list.iterator();
					   while (it.hasNext()) {
								Institution j = (Institution) it.next();
			
			  %>
			  <tr  bgcolor="#FFFFFF">
				<td height="22" align="center" ><%=j.getName()%></td>
				<td height="22" align="center" ><%=j.getPhoneNumber()%></td>
				<td height="22" align="center" ><%=j.getIdCard()%></td>
				<td height="22" align="center" ><%=j.getHouse()%></td>
				<td height="22" align="center" ><%=j.getDesireCar()%></td>
				<td height="22" align="center" ><%=j.getVisitTime()%></td>
				<td height="22" align="center" >
				  <a href="updateinstitution.do?action=detailinstitution&id=<%=j.getId()%>">修改</a>&nbsp;&nbsp;
				  <a href="modifyinstitution.do?action=deleteinstitution&id=<%=j.getId()%>">删除</a>
				</td>
			  </tr>
			  <%		}
			   }else{
			  %>
			  <tr  bgcolor="#FFFFFF">
				<td height="22" colspan="3" align="center" >对不起，没有添加线索信息！！！</td>
			  </tr>
			  <%}%>
            </table></td>
        </tr>
      </table>
          </td>
        </tr>
</table>
</body>
</html>

