<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.sanqing.po.Service"%>
<%@ page import="com.sanqing.tool.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>4S店客户管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="css/style.css " type="text/css" media="all" />
<script type="text/javascript" src="Js/typem.js"></script>
<script type="text/javascript" src="Js/js.js"></script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>
<body class="ContentBody">
<form name="serviceForm" method="post" action="modifyservice.do?action=updateservice" onSubmit="return serviceValidate();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >需求记录</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
		<tr><td align="left">
		<input type="submit"value="保存" class="button"/>　
			
			<input type="reset" value="返回" class="button"/>
		</td></tr>
		<%
			Service e=(Service)request.getAttribute("service");
				  if(e!=null){
		%>
	  	<input type="hidden" name="id" value="<%=e.getId()%>"/>
	  	<input type="hidden" name="service" value="<%=request.getParameter("service")%>"/>
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>客户需求</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td nowrap align="right" width="11%">客户名称：</td>
					    <td colspan="3"><input name="name" type="text" class="input" value="<%=e.getName()%>" disabled/>
					    <span class="red">*</span></td>
					    </tr>
					  <tr>
					    <td nowrap align="right" width="11%">手机号：</td>
					    <td colspan="3"><input type="text" name="phoneNumber" class="input" value="<%=e.getPhoneNumber()%>" disabled/>
					    <span class="red">*</span></td>
					    </tr>
					  <tr>
					    <td nowrap align="right" width="11%">创建时间：</td>
					    <td colspan="3"><input type="text" name="begintime" class="input" value="<%=StringUtil.notNull(DateUtil.parseToString(e.getCreatetime(),DateUtil.yyyyMMdd))%>" disabled/>
					    	<span class="red">*</span></td>
					  </tr>
					  <tr>
					    <td nowrap align="right">车型：</td>
					    <td><input type="text" name="car" class="input" value="<%=e.getCar()%>" disabled/>
					    <span class="red">*</span></td>
					    <td>车牌号：</td>
					    <td><input type="text" name="plateNumber" class="input" value="<%=e.getPlateNumber()%>" disabled/>
					    <span class="red">*</span>
						</td>
					  </tr>
					    
					  <tr>
					    <td width="11%" nowrap align="right">需求详细：</td>
					    <td colspan="3"><textarea name="requireDetail" cols="100" rows="6" class="input" disabled><%=e.getRequireDetail()%></textarea></td>
					  </tr>
					  
<%-- 					  <tr align="center">
					    <td width="11%" height="22" >培训效果：</td>
					    <td height="22" colspan="3" align="left" ><input name="effect" type="text"  class="input" value="<%=StringUtil.notNull(e.getEffect())%>" size="50"></td>
					  </tr> --%>
					  <tr align="center">
					    <td width="11%" height="22" >完成情况：</td>
					    <td height="22" colspan="3" align="left" ><textarea name="summarize" cols="100" rows="6"  class="input"><%=StringUtil.notNull(e.getSummarize())%></textarea></td>
					  </tr>
					  </table>
			  <br />
				</fieldset>			</TD>
			
		</TR>
		</TABLE>
	 </td>
  </tr>
   <%}else{ %>
  <tr>
    <td height="22" colspan="4" align="center" >该信息不存在！！！</td>
  </tr>
  <%}%>
	<TR>
		<TD colspan="2" align="center" height="50px">
			<input name="提交" type="submit" class="button" value="保存"/>　
			<input name="重置" type="reset" class="button" value="重置"/>
		</TD>
	</TR>
</TABLE>	
</div>
</form>
</body>
</html>

