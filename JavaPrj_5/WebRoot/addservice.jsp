<%@ page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>4S店客户管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="css/style.css " type="text/css" media="all" />
<link href="css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>
<body class="ContentBody">
<form name="serviceForm" method="post" action="modifyservice.do?action=addservice" onsubmit="return serviceValidate();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >客户需求录入</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
		<tr><td align="left">
		<input type="submit"value="保存" class="button"/>　
			
			<input type="reset" value="返回" class="button"/>
		</td></tr>
		
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>客户需求</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td nowrap align="right">客户名称：</td>
					    <td width=""><input name="name" type="text" class="input"/>
					    <span class="red">*</span></td>
					    <td width="">手机号：</td>
					    <td width=""><input type="text" name="phoneNumber" class="input"/>
					    <span class="red">*</span></td>
					  </tr>
					  <tr>
					    <td nowrap align="right">车型：</td>
					    <td><input type="text" name="car" class="input"/>
					    <span class="red">*</span></td>
					    <td>预约时间：</td>
					    <td><input type="text" name="createtime" class="input" id="appointment"/>
					    <span class="red">*</span>
						</td>
					  </tr>
					  
					  <tr>
					    <td nowrap align="right">车牌号：</td>
					    <td><input type="text" name="plateNumber" class="input"/>
					    </td>
						</td>
					  </tr>
					    
					  <tr>
					    <td width="11%" nowrap align="right">需求详细：</td>
					    <td colspan="3"><textarea name="requireDetail" cols="100" rows="6" class="input"></textarea></td>
					  </tr>
					  </table>
				</fieldset>			
			</TD>
		</TR>
		</TABLE>
	 </td>
  </tr>
		<TR>
			<TD colspan="2" align="center" height="50px">
			<input name="提交" type="submit" class="button" value="保存"/>　
			<input name="重置" type="reset" class="button" value="重置"/></TD>
		</TR>
	</TABLE>	
</div>
</form>
<script type="text/javascript" src="Js/typem.js"></script>
<script type="text/javascript" src="Js/js.js"></script>
<script type="text/javascript" src="Js/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="Js/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript">
  jQuery.datetimepicker.setLocale('zh');
  jQuery('#appointment').datetimepicker({
  timepicker:false,
  format:'Y-m-d'
});
</script>
</body>
</html>

