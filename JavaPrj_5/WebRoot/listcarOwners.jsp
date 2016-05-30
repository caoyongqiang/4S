<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.sanqing.po.CarOwners"%>
<%@ page import="com.sanqing.tool.*"%>
<%@ page import="com.sanqing.po.Users"%>
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
table tbody tr td {
	text-align: center;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>

<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
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
          <td>
            <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
             <tr>
               <TD width="100%">
				<fieldset style="height:100%;">
				<legend>查找车主</legend>
 				  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td nowrap align="right" width="">车主姓名：</td>
					    <td width=""><input name="name" type="text" class="input" id=ownerName></td>
					    <td width=""><div align="right">手机号：</div></td>
					    <td width="">
					      <input name="phoneNumber" type="text" class="input" id="phoneNumber"/>
					    </td>
					    <td nowrap align="right" width="">身份证号：</td>
					    <td><input name="idCard" type="text" class="input" id="idCard">
					    </td>
					    <td><div align="right">家庭住址：</div></td>
					    <td><input name="house" type="text" class="input" id="house"></td>
					  </tr>
					  <tr>
					    <td nowrap align="right">车型：</td>
					    <td><input name="car" type="text" class="input" id="car"></td>
					    <td><div align="right">车牌号：</div></td>
					    <td><input name="plateNumber" type="text" class="input" id="plateNumber"></td>
					    <td nowrap align="right">开始时间：</td>
					    <td><input name="purchaseTime" type="text" class="input" id="date_timepicker_start"></td>
					    <td nowrap align="right">结束时间：</td>
					    <td><input name="purchaseTime" type="text" class="input" id="date_timepicker_end"></td>
					  </tr>
					  <TR>
						<TD colspan="8" align="center" height="20px">
						<input id="searchCarOwners" type="button" class="button" value="查找"/>　
						</TD>
					  </TR>
					</table>
			    <br />
				</fieldset>			</TD>
          	 </tr>
          	 <tr>
               <td height="20"><span class="newfont07">车主信息列表</span></td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
				<%-- <table id="example" width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="11" align="center" style="font-size:16px">车主信息列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				        <td height="22" align="center" >车主姓名</td>
						<td height="22" align="center" >手机号</td>
						<td height="22" align="center" >身份证号</td>
						<td height="22" align="center" >家庭住址</td>
						<td height="22" align="center" >购买车型</td>
						<td height="22" align="center" >裸车价</td>
						<td height="22" align="center" >其他费用</td>
						<td height="22" align="center" >车牌号</td>
						<td height="22" align="center" >购车时间</td>
						<td height="22" align="center" >成交价格</td>
						<td height="22" align="center" >执行操作</td>
                  </tr>
				<% Users u = (Users)session.getAttribute("users");
				   List list=(List)request.getAttribute("list");
					 if(list!=null&&list.size()>0){
						Iterator it = list.iterator();
						   while (it.hasNext()) {
									CarOwners j = (CarOwners) it.next();
				
				  %>
				  <tr  bgcolor="#FFFFFF">
					<td height="22" align="center" ><%=j.getName()%></td>
					<td height="22" align="center" ><%=j.getPhoneNumber()%></td>
					<td height="22" align="center" ><%=j.getIdCard()%></td>
					<td height="22" align="center" ><%=j.getHouse()%></td>
					<td height="22" align="center" ><%=j.getCar()%></td>
					<td height="22" align="center" ><%=j.getCarPrice()%></td>
					<td height="22" align="center" ><%=j.getOther()%></td>
					<td height="22" align="center" ><%=j.getPlateNumber()%></td>
					<td height="22" align="center" ><%=StringUtil.notNull(DateUtil.parseToString(j.getPurchaseTime(),DateUtil.yyyyMMdd))%>&nbsp;</td>
					<td height="22" align="center" ><%=j.getTotalize()%></td>
					<td height="22" align="center" >
					  <%if(u.getRoleType() == 2 || u.getRoleType() == 3 ){%>
					  <a href="updatecarOwners.do?action=detailcarOwners&id=<%=j.getId()%>">修改</a>&nbsp;&nbsp;
					  <a href="modifycarOwners.do?action=deletecarOwners&id=<%=j.getId()%>">删除</a>
					  <%}%>
					</td>
				  </tr>
				  <%		}
				   }else{
				  %>
				  <tr  bgcolor="#FFFFFF">
					<td height="22" colspan="11" align="center" >对不起，没有添加车主信息！！！</td>
				  </tr>
				  <%}%>
            </table> --%>
            <table id="example" class="display" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		                <th>车主id</th>
		                <th>车主姓名</th>
		                <th>手机号</th>
		                <th>身份证号</th>
		                <th>家庭住址</th>
		                <th>购买车型</th>
		                <th>裸车价</th>
		                <th>其他费用</th>
		                <th>成交价格</th>
		                <th>车牌号</th>
		                <th>购买日期</th>
		                <th>执行操作</th>
		            </tr>
		        </thead>
		        <tbody>
		        </tbody>
		    </table>
          </td>
        </tr>
      </table>
          </td>
        </tr>
</table>
<script type="text/javascript" src="Js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="Js/jquery.datetimepicker.full.min.js"></script>
<script src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
  jQuery(function(){
	 jQuery.datetimepicker.setLocale('zh');
	 jQuery('#date_timepicker_start').datetimepicker({
	  format:'Y-m-d',
	  maxDate:'+1970/01/02',
	  onShow:function( ct ){
	    this.setOptions({
	    maxDate:jQuery('#date_timepicker_end').val()?jQuery('#date_timepicker_end').val():false
	   })
	  },
	  timepicker:false
	 });
	 jQuery('#date_timepicker_end').datetimepicker({
	  format:'Y-m-d',
	  maxDate:'+1970/01/02',
	  onShow:function( ct ){
	   this.setOptions({
	    minDate:jQuery('#date_timepicker_start').val()?jQuery('#date_timepicker_start').val():false
	   })
	  },
	  timepicker:false
	 });
	});

  $.ajax({
      url: 'carOwners.do?action=searchCarOwners',
      method: 'post',
      data: {
	          ownerName: $('#ownerName').val() || '',
	          phoneNumber: $('#phoneNumber').val() || '',
	          idCard: $('#idCard').val() || '',
	          house: $('#house').val() || '',
	          car: $('#car').val() || '',
	          plateNumber: $('#plateNumber').val() || '',
	          startDate: $('#date_timepicker_start').val() || '',
	          endDate: $('#date_timepicker_end').val() || '',
	  },
	  success: function(result) {
	    var dataSet = $.parseJSON(result).owners;
	      $('#example').DataTable({
          data: dataSet,
          oLanguage: {
			sLengthMenu: '每页显示 _MENU_ 条记录',
			sZeroRecords: '抱歉， 没有找到',
			sInfo: '从 _START_ 到 _END_ /共 _TOTAL_ 条数据',
			sInfoEmpty: '没有数据',
			sInfoFiltered: '(从 _MAX_ 条数据中检索)',
			oPaginate: {
			  sFirst: '首页',
			  sPrevious: '前一页',
			  sNext: '后一页',
			  sLast: '尾页'
		    },
		  },
          columnDefs: [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }
          ]
        });
	  }
    })

  $('#searchCarOwners').click(function(){
    $.ajax({
      url: 'carOwners.do?action=searchCarOwners',
      method: 'post',
      type: 'json',
      data: {
	          ownerName: $('#ownerName').val() || '',
	          phoneNumber: $('#phoneNumber').val() || '',
	          idCard: $('#idCard').val() || '',
	          house: $('#house').val() || '',
	          car: $('#car').val() || '',
	          plateNumber: $('#plateNumber').val() || '',
	          startDate: $('#date_timepicker_start').val() || '',
	          endDate: $('#date_timepicker_end').val() || '',
	  },
	  success: function(result) {
	    var dataSet = $.parseJSON(result).owners;
        $('#example').dataTable().fnClearTable();
        if(dataSet.length){
          $('#example').dataTable().fnAddData(dataSet,true);
        }
	  }
    })
  })
  
</script>
</body>
</html>

