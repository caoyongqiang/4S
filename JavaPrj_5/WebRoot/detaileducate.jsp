<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.sanqing.po.Educate"%>
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
<form name="educateForm" method="post" action="modifyeducate.do?action=addeducate" onsubmit="return educateValidate();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >
      	<%
		String educate=null;
		try{
			educate=request.getParameter("educate").toString();
		}catch(Exception e){
			educate="0";
		}
		if("1".equals(educate)){
			out.print("完成需求详细");
		  }else{
			out.print("待完成需求详细");
		  }
		%>
      </th>
  </tr>
    <%
	  Educate e=(Educate)request.getAttribute("educate");
	  if(e!=null){
  	%>
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
					  <table border="0" cellpadding="8" cellspacing="1" style="width:100%">
					  <tr>
					    <td nowrap align="right" width="11%">客户名称：</td>
					    <td colspan="3"><%=e.getName()%>
					    </td>
					    </tr>
					  <tr>
					    <td nowrap align="right" width="11%">手机号：</td>
					    <td colspan="3"><%=e.getPhoneNumber()%>
					    </td>
					    </tr>
					  <tr>
					    <td nowrap align="right" width="11%">创建时间：</td>
					    <td colspan="3"><%=StringUtil.notNull(DateUtil.parseToString(e.getCreatetime(),DateUtil.yyyyMMdd))%></td>
					  </tr>
					  <tr>
					    <td nowrap align="right" width="11%">车型：</td>
					    <td colspan="3"><%=e.getCar()%>
					    </td>
					  </tr>
					 <tr>
					    <td nowrap align="right" width="11%">车牌号：</td>
					    <td colspan="3"><%=e.getPlateNumber()%></td>
					  </tr>
					  <tr>
					    <td width="11%" nowrap align="right">需求详细：</td>
					    <td colspan="3"><%=e.getRequireDetail()%></td>
					  </tr>
					   <%if("1".equals(educate)){%>
						  <%-- <tr>
						    <td width="11%" height="22" align="center" >培训效果：</td>
						    <td height="22" colspan="3" align="left" ><%=StringUtil.notNull(e.getEffect())%>&nbsp;</td>
						  </tr> --%>
						  <tr>
						    <td width="11%" height="22" align="right" >完成情况：</td>
						    <td height="22" colspan="3" align="left" ><%=StringUtil.notNull(e.getSummarize())%>&nbsp;</td>
						  </tr>
						 <%}%>
					  </table>
				</fieldset>			
				</TD>
		</TR>
    <%}else{ %>
  	<tr>
    <td height="22" colspan="4" align="center" >该信息不存在！！！</td>
  	</tr>
  	<%}%>
</TABLE>	
</div>
</form>
</body>
</html>

