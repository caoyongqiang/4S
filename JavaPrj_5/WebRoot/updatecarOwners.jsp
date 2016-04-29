<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.sanqing.po.CarOwners"%>
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
<form name="carOwnersForm" method="post" action="modifycarOwners.do?action=updatecarOwners" onSubmit="return carOwnersValidate();" >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >薪金数据更新</th>
  </tr>
    <%
  CarOwners e=(CarOwners)request.getAttribute("carOwners");
  if(e!=null){
  %>
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
		<tr><td align="left">
			<input type="submit"value="更新" class="button"/>　
			<input type="reset" value="返回" class="button"/>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<fieldset style="height:100%;">
				<legend>薪金数据</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <input type="hidden" name="id" value="<%=e.getId()%>"/>
					  <tr>
					    <td nowrap align="right" width="9%">车主姓名：</td>
					    <td width="36%"><input name="name" type="text" class="input" value="<%=e.getName()%>"/>
						<span class="red">*</span></td>
					    <td width="12%"><div align="right">手机号：</div></td>
					    <td width="43%">
					      <input name="phoneNumber" type="text" class="input" id="phoneNumber" value="<%=e.getPhoneNumber()%>" />
					      <span class="red">*</span></td></tr>
					  <tr>
					    <td nowrap align="right" width="9%">身份证号：</td>
					    <td><input name="idCard" type="text" class="input" id="idCard" value="<%=e.getIdCard()%>"/>
					        <span class="red">*</span>
					    </td>
					    <td><div align="right">家庭住址：</div></td>
					    <td><input name="house" type="text" class="input" id="house"  value="<%=e.getHouse()%>"/></td>
					  </tr>
					  <tr>
					    <td nowrap align="right">购买车型：</td>
					    <td><input name="car" type="text" class="input" id="car" value="<%=e.getCar()%>"></td>
					    <td><div align="right">裸车价：</div></td>
					    <td><input name="carPrice" type="text" class="input" id="carPrice"  value="<%=e.getCarPrice()%>"/></td>
					    </tr>
					  <tr>
					    <td nowrap align="right">其他费用：</td>
					    <td><input name="other" type="text" class="input" id="other"  value="<%=e.getOther()%>"/></td>
					    <td><div align="right">车牌号：</div></td>
					    <td><input name="plateNumber" type="text" class="input" id="plateNumber"  value="<%=e.getPlateNumber()%>"></td>
					    </tr>
					  <tr>
					    <td nowrap align="right">购车时间：</td>
					    <td><input name="purchaseTime" type="text" class="input" id="purchaseTime" value="<%=StringUtil.notNull(DateUtil.parseToString(e.getPurchaseTime(),DateUtil.yyyyMMdd))%>">
				        <span class="red">*</span></td>
					    <td colspan="2"><div align="left">注：金钱单位（元/RMB）</div></td>
					    </tr>
					  </table>
				</fieldset>		
			</td>
		</tr>
	<tr>
		<td colspan="2" align="center" height="50px">
		<input name="更新" type="submit" class="button" value="更新"/>　
		<input name="重置" type="reset" class="button" value="重置"/>
		</td>
	</tr>
	  <%}else{ %>
	  <tr>
	    <td height="22" colspan="4" align="center" >该信息不存在！！！</td>
	  </tr>
	  <%}%>
	</tabel>
</table>
</body>
</html>

