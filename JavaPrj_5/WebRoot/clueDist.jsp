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

    <script type="text/javascript" src="Js/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="Js/jquery.datetimepicker.full.min.js"></script>
    <script type="text/javascript">
         var getxAxisData = function() {
            if(!$('#date_timepicker_start').val() && !$('#date_timepicker_end').val()) {
                var today = new Date();
                var year = today.getFullYear();
                var month = today.getMonth()+1;
                var endDate = new Date(year + '/' + month);
                today.setMonth( month - 12 );
                var startDate = today;
                $('#date_timepicker_start').val(startDate.getFullYear()+'-'+(startDate.getMonth()+1));
                $('#date_timepicker_end').val(endDate.getFullYear()+'-'+(endDate.getMonth()+1));
            }else{
	            var startDate=new Date($('#date_timepicker_start').val().replace("-", "/"));
	        	var endDate=new Date($('#date_timepicker_end').val().replace("-", "/"));
	        }
		    var number = 0;      
		    var yearToMonth = (endDate.getFullYear() - startDate.getFullYear()) * 12;      
		    number += yearToMonth;      
	        monthToMonth = endDate.getMonth() - startDate.getMonth();      
	        number += monthToMonth;
            var xAxisData = [];
            var year = startDate.getFullYear();
            var month = startDate.getMonth()+1;
            console.log(number);
            for(var i = parseInt(number); i >= 0; i--){
                if(month >= 13) {
                    year++;
                    month = 1;
                }
                xAxisData.push('' + year + '-' + (month++) );
            }
            return xAxisData;
         };
    
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
		        url: "clue.do?action=clueDist",
		        data: {
		          startDate: $('#date_timepicker_start').val(),
		          endDate: $('#date_timepicker_end').val()
		        },
		        success: function(result) {
		          var ownersArr = $.parseJSON(result).clues;
		          var xAxisData = getxAxisData();
		          option.xAxis[0].data = xAxisData;
		          option.series[0].data = ownersArr;
		          myChart.clear();
		          myChart.setOption(option);
		          myChart.setTheme('macarons');
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
		        url: "clue.do?action=clueDist",
		        data: {
		          startDate: $('#date_timepicker_start').val(),
		          endDate: $('#date_timepicker_end').val()
		        },
		        success: function(result) {
		          var ownersArr = $.parseJSON(result).clues;
		          var xAxisData = getxAxisData();
		          option.xAxis[0].data = xAxisData;
		          option.series[0].data = ownersArr;
		          myChart.clear();
		          myChart.setOption(option);
		          myChart.setTheme('macarons');
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
                'echarts/chart/line',
                'echarts/chart/bar'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                myChart = ec.init(document.getElementById('main')); 
                var ownersArr = '';
                var today = new Date();
                var year = today.getFullYear();
                var month = today.getMonth()+2;
                var endDate = year + '-' + month;
                today.setMonth( month - 12 );
                var startDate = today.getFullYear() + '-' + (today.getMonth()+1);
                $.ajax({
			        url: "clue.do?action=clueDist",
			        data: {
			          startDate: startDate,
			          endDate: endDate
			        },
			        async: false,
			        success: function(result) {
			          ownersArr = $.parseJSON(result).clues;
			        }
		        });
                var xAxisData = getxAxisData();
                option = {
				    title : {
				        text: '来店线索分布',
				        subtext: '按月计数'
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['来店线索数']
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
				            data : xAxisData
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: ''
				            }
				        }
				    ],
				    series : [
				        {
				            name:'来店线索数',
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
				        }
				    ]
				};
        
                // 为echarts对象加载数据 
                myChart.setOption(option);
                myChart.setTheme('macarons');
            }
        );
    </script>
</body>
</html>
