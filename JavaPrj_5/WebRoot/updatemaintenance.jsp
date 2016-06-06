<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="com.sanqing.po.Maintenance"%>
<%@ page import="com.sanqing.po.CarOwners"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.sanqing.tool.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>4S店客户管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="css/style.css " type="text/css" media="all" />
<link href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="Js/typem.js"></script>
<script type="text/javascript" src="Js/js.js"></script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>
<body class="ContentBody">
<%
	  HashMap<String , Object> map=(HashMap<String , Object>)request.getAttribute("info");
	  Maintenance e = null;
	  CarOwners c = null;
	  if(map != null){
	    e = (Maintenance)map.get("maintenance");
	    c = (CarOwners)map.get("carOwner");
	  }
	  Date date=new Date();
	  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	  String time=format.format(date);
  %>
<form name="clueForm" method="post" action="modifyclue.do?action=addclue" onSubmit="return clueValidate()">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >线索信息录入</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
		<tr><td align="left">
		</td></tr>
		
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>线索信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <input name="name" type="text" style="display:none;" id="maintenanceId" <%if(e != null) {%> value="<%=e.getId()%>" <%} %>>
					    <td nowrap align="right" width="9%">车主姓名：</td>
					    <td width="36%"><input disabled name="name" type="text" class="input" id="name" <%if(e != null) {%> value="<%=c.getName()%>" <%} %>>
						<span class="red">*</span></td>
					    <td width="12%"><div align="right">手机号：</div></td>
					    <td width="43%">
					      <input disabled name="phoneNumber" type="text" class="input" id="phoneNumber" <%if(e != null) {%> value="<%=c.getPhoneNumber()%>" <%} %>/>
					      <span class="red">*</span></td></tr>
					  <tr>
					    <td nowrap align="right" width="9%">车型：</td>
					    <td><input disabled name="car" type="text" class="input" id="car" <%if(e != null) {%> value="<%=c.getCar()%>" <%} %>>
					    </td>
					    <td><div align="right">车牌号：</div></td>
					    <td><input disabled name="plateNumber" type="text" class="input" id="plateNumber" <%if(e != null) {%> value="<%=c.getPlateNumber()%>" <%} %>></td>
					  </tr>
					  <tr>
					    <td nowrap align="right">当前保养时间：</td>
					    <td><input name="preTime" type="text" class="input" id="preTime" <%if(e != null) {%> value="<%=time%>" <%} %>></td>
					    <td nowrap align="right">下次时间：</td>
					    <td><input name="nextTime" type="text" class="input" id="nextTime" <%if(e != null) {%> value="<%=StringUtil.notNull(DateUtil.parseToString(e.getNextTime(),DateUtil.yyyyMMdd))%>" <%} %>>
					    <span class="red">*</span>
					    </td>
					  </tr>
					  <tr>
						<td nowrap align="right" width="9%">保养周期：</td>
						<td id="period">
						  <input name="period" type="radio" value="0" checked>3个月
						  <input name="period" type="radio" value="1">半年
						  <input name="period" type="radio" value="2">一年
						  <input name="period" type="radio" value="3">两年
						  <input name="period" type="radio" value="4">其他
						  <input hidden id="otherPeriod">
						</td>
					  </tr>
					  <tr>
						<td nowrap align="right">保养项目：</td>
						<td id="content" width="">
						  <input name="engineOil" type="radio" value="机油" checked>机油
						  <input name="oilFilter" type="radio" value="机油滤清器" checked>机油滤清器
						</td>
					 </tr>
					  </table>
			  <br />
				</fieldset>			</TD>
			
		</TR>
		</TABLE>
	 </td>
  </tr>
		<TR>
			<TD colspan="2" align="center" height="50px">
			<input name="提交" type="button" class="button" value="保存" id="save"/>　
			<input name="重置" type="reset" class="button" value="重置"/></TD>
		</TR>
		</TABLE>	
</div>
</form>
<script type="text/javascript" src="Js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="Js/jquery.datetimepicker.full.min.js"></script>
<script src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
  jQuery.datetimepicker.setLocale('zh');
  jQuery('#preTime').datetimepicker({
    timepicker:false,
    format:'Y-m-d'
  });
  jQuery('#nextTime').datetimepicker({
    timepicker:false,
    format:'Y-m-d'
  });
  $('#save').click(function(){
    if($('#period input:radio:checked').val() == '4' && !$('#otherPeriod').val()){
      alert('请填写保养周期');
      return;
    }
    var period = '';
    switch ($('#period input:radio:checked').val()) {
      case '0':
        period = '3个月';
        break;
      case '1':
        period = '半年';
        break;
      case '2':
        period = '一年';
        break;
      case '3':
        period = '两年';
        break;
      case '4':
        period = $('#otherPeriod').val();
        break;
    }
    var content = '';
    $('#content input:radio:checked').each(function(index, html) {
      content += ($(html).val() + ',');
    })
    $.ajax({
      url: 'maintenance.do?action=addMaintenance',
      type: 'post',
      data: {
          id: $('#maintenanceId').val() || '',
          name: $('#name').val() || '',
	      phoneNumber: $('#phoneNumber').val() || '',
	      car: $('#car').val() || '',
	      plateNumber: $('#plateNumber').val() || '',
	      preTime: $('#preTime').val() || '',
	      nextTime: $('#nextTime').val() || '',
	      content: content,
	      period: period
      },
      success: function(result){
        alert('添加维修信息成功！');
      } 
    })
  })
  $('#period').click(function(e){
      var engineOil = "<input name='engineOil' type='radio' value='机油' checked>机油";
	  var oilFilter = "<input name='oilFilter' type='radio' value='机油滤清器' checked>机油滤清器";
	  var airFilter = "<input name='airFilter' type='radio' value='空气滤清器' checked>空气滤清器";
	  var fuelFilter = "<input name='fuelFilter' type='radio' value='汽油滤清器' checked>汽油滤清器";
	  var coolantTank = "<input name='coolantTank' type='radio' value='水箱冷却液' checked>水箱冷却液";
	  var autoTransmission = "<input name='autoTransmission' type='radio' value='自动变速箱' checked>自动变速箱";
	  var brakeFluid = "<input name='brakeFluid' type='radio' value='煞车油' checked>煞车油";
	  var gearOil = "<input name='gearOil' type='radio' value='齿轮油' checked>齿轮油";
	  var powerOil = "<input name='powerOil' type='radio' value='动力油' checked>动力油";
	  var storageBattery = "<input name='storageBattery' type='radio' value='电瓶' checked>电瓶";
	  var sparkplug = "<input name='sparkplug' type='radio' value='火星塞' checked>火星塞";
	  var other = "<input name='other' type='radio' value=''>其他";
	  switch (e.target.value) {
	    case '0':
	      $('#otherPeriod').hide();
	      $('#content').html(engineOil + oilFilter);
	      break;
	    case '1':
	      $('#otherPeriod').hide();
	      $('#content').html(engineOil + oilFilter + airFilter);
	      break;
	    case '2': 
	      $('#otherPeriod').hide();
	      $('#content').html(engineOil + oilFilter + airFilter + fuelFilter + 
	                         coolantTank +'<br/>'+ autoTransmission + brakeFluid);
	      break;
	    case '3':
	      $('#otherPeriod').hide();
	      $('#content').html(engineOil + oilFilter + airFilter + fuelFilter + coolantTank +
	          autoTransmission +'<br/>'+ brakeFluid + gearOil + powerOil + storageBattery + sparkplug);
	      break;
	    case '4':
	      /* if($('#otherPeriod').length == 0) {
	        $('#period').append("<input id='otherPeriod'/>");
	      } */
	      $('#otherPeriod').show();
	      $('#content').html("<textarea name='content' cols='60' rows='6' class='input'></textarea></td>");
	      break;
	  }
  })
</script>
</body>
</html>

