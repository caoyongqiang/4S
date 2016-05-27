<%@ page contentType="text/html; charset=utf-8" language="java" %>
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
-->
</style>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<style type="text/css">
<!--
.STYLE1 {
	font-size: 36px;
	color: #009999;
	font-family: "方正舒体";
}
-->
</style>
</head>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>车主信息图表</title>
</head>
<body>
    <div>
      <p>
                     开始时间：<input id="date_timepicker_start" type="text" >
                     结束时间：<input id="date_timepicker_end" type="text" >
      </p>
    </div>
    <%Integer[] arr=(Integer[])request.getAttribute("arr");
      int length = arr.length;
	  String str = "";
	  for(int i = 0; i < length-1; i++) {
	    str = str + arr[i] + ',';
	  }
	  str += arr[length-1];
    %>
    <input id="ownersArr" type='hidden' value='<%=str%>'/>
    <script type="text/javascript" src="Js/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="Js/jquery.datetimepicker.full.min.js"></script>
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
		  onSelectDate:function(ct,$i){
		    if( !$('#date_timepicker_start').val() || !$('#date_timepicker_end').val() ) return;
		    $.ajax({
		        url: "carOwnersChart.do?action=carOwnersChart",
		        data: {
		          startDate: $('#date_timepicker_start').val(),
		          endDate: $('#date_timepicker_end').val()
		        }
	        });
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
		  onSelectDate:function(ct,$i){
		    if( !$('#date_timepicker_start').val() || !$('#date_timepicker_end').val() ) return;
		    $.ajax({
		        url: "carOwnersChart.do?action=carOwnersChart",
		        data: {
		          startDate: $('#date_timepicker_start').val(),
		          endDate: $('#date_timepicker_end').val()
		        }
	        });
		  },
		  timepicker:false
		 });
		});
    </script>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:400px"></div>
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
    <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/line'            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                var ownersArr = $('#ownersArr').val().split(',');
                var startDate = $('#date_timepicker_start').val();
                
                var option = {
				    title : {
				        text: '未来一周气温变化',
				        subtext: '纯属虚构'
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['最高气温','最低气温']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : ['周一','周二','周三','周四','周五','周六','周日']
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value} °C'
				            }
				        }
				    ],
				    series : [
				        {
				            name:'最高气温',
				            type:'line',
				            data:ownersArr,
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name: '平均值'}
				                ]
				            }
				        },
				        {
				            name:'最低气温',
				            type:'line',
				            data:[1, -2, 2, 5, 3, 2, 0],
				            markPoint : {
				                data : [
				                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name : '平均值'}
				                ]
				            }
				        }
				    ]
				};
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>
</body>
</html>
