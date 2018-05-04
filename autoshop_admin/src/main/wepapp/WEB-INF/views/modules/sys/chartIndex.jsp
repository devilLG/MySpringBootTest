<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/sys/chartLTE/taglibs.jsp"%>
<script src="${ctx}/static/zzw/js/btp_jquery.js"></script>
<script src="${ctx}/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ZhiLai | Dashboard</title><!-- AdminLTE | Dashboard -->
<script type="text/javascript">
	$(function(){
		var groupId="${groupId}";
		if(groupId==""){
			$(".clas*").css("background-color","#fff");
			$(".clas*").css("color","#555");
			$(".clasAll").css("background-color","#1c7db3");
			$(".clasAll").css("color","#ffffff");
		}else{
			$(".clas*").css("background-color","#fff");
			$(".clas*").css("color","#555");
			$(".clasAll").css("background-color","#fff");
			$(".clasAll").css("color","#555");
			$(".clas"+groupId).css("background-color","#1c7db3");
			$(".clas"+groupId).css("color","#ffffff");
		}
	});
</script>
<style type="text/css">
.nav-tabs > li {
    float: left;
    margin-bottom: 0px;
}
.mainid-footer{
	background: #fff;
    padding: 15px;
    color: #444;
    border-top: 1px solid #d2d6de
}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper11111">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<h1>
			运营图表<small>Control panel</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a>
			</li>
			<li class="active">图表展示 Dashboard</li>
		</ol>
		</section>

		<!-- Main content -->
		<section class="content"> <!-- Small boxes (Stat box) -->
		<div class="row">
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-aqua">
					<div class="inner"><h3>${saleMoneyDay}</h3><p>本日销售额</p></div>
					<div class="icon"><i class="ion ion-bag"></i></div>
					<a href="${ctx}/a/order/orderApply?pay_state=-1" class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-green">
					<div class="inner"><h3>${orderNumDay}</h3><p>本日订单数</p></div>
					<div class="icon"> <i class="ion ion-stats-bars"></i></div>
					<a href="${ctx}/a/order/orderApply" class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-yellow">
					<div class="inner"><h3>${productNumDay}</h3><p>本日销售商品数</p></div>
					<div class="icon"><i class="ion ion-person-add"></i></div>
					<a href="${ctx}/a/order/orderApply?cur_state=05" class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-red">
					<div class="inner"><h3>${warnNum}</h3><p>告警信息</p></div>
					<div class="icon"><i class="ion ion-person-add"></i></div>
					<a href="${ctx}/a/site/assite/list?warn_type=01" class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-aqua">
					<div class="inner"><h3>${saleMoneyMonth}</h3><p>本月销售额</p></div>
					<div class="icon"><i class="ion ion-bag"></i></div>
					<a href="${ctx}/a/order/orderApply?beginTime=${beginTime}&endTime=${endTime}&pay_state=-1" class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-green">
					<div class="inner"><h3>${orderNumMonth}</h3><p>本月订单数</p></div>
					<div class="icon"><i class="ion ion-stats-bars"></i></div>
					<a href="${ctx}/a/order/orderApply?beginTime=${beginTime}&endTime=${endTime}" class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-yellow">
					<div class="inner"><h3>${refundNumMonth}</h3><p>本月退款数</p></div>
					<div class="icon"><i class="ion ion-pie-graph"></i></div>
					<a href="${ctx}/a/order/orderApply?beginTime=${beginTime}&endTime=${endTime}&pay_state=04" class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-red" style="height:125px">
					<div class="inner">
					<a href="${ctx }/a/order/orderApply">
				    <div style="text-align: center;cursor: pointer;color: white;">
					<span style="position: relative;top: 15px;">
						<i style="font-size: x-large;" class=" ion-search"></i>
						<p>订单查询</p>
					</span>
					</div>
					</a>
				</div>
			</div>
			<!-- ./col -->
		</div>
		<!-- /.row --> <!-- Main row -->
		<div class="row">
			<!-- Left col -->
			<section class="col-lg-7 connectedSortable"> <!-- Custom tabs (Charts with tabs)-->
			<div class="nav-tabs-custom" style="margin-top: 20px;">
				<!-- Tabs within a box -->
				<ul class="nav nav-tabs pull-right">
					<li class="pull-left header"><i class="fa fa-line-chart"></i> 每月销售额趋势</li>
				</ul>
				<div class="tab-content no-padding">
					<!-- Morris chart - Sales -->
					<div class="chart tab-pane active" id="revenue-chart" style="position: relative; height: 300px;"></div>
				</div>
			</div>
			<div id="barNet" style="min-width:400px;height:400px;margin-top: 20px;"></div>
			<!-- /.box --> </section>
			<!-- /.Left col -->
			<!-- right col (We are only adding the ID to make the widgets sortable)-->
			<section class="col-lg-5 connectedSortable"> <!-- Map box -->
			<div class="nav-tabs-custom" style="margin-top: 20px;height:335px">
				<!-- Tabs within a box -->
				<ul class="nav nav-tabs pull-right">
					<li class="pull-left header"><i class="fa fa-inbox"></i>告警记录</li>
				</ul>
				<div class="tab-content no-padding">
				<table id="contentTable" class="table"  style="width:685px;">
						<tbody>
						<c:forEach items="${siteList}" var="site">
							<tr style="background-color:rgba(0,0,0,0.05)">
								<td style="width:635px"><font color="666666" size="2">时间 ：</font><font color="000000" size="2" style="font-weight:bold;">${site.warn_time}</font>&nbsp;&nbsp;<font color="666666" size="2">告警类型 ：</font><font color="FF0000" size="2" style="font-weight:bold;">${site.warn_type}</font>&nbsp;&nbsp;<font color="666666" size="2">站点编号 ：</font><font color="000000" size="2" style="font-weight:bold;">${site.site_id}</font>&nbsp;&nbsp;<font color="666666" size="2">站点：</font><font color="000000" size="2" style="font-weight:bold;"><c:choose>
							  	   <c:when test="${fn:length(site.site_name)>9}">
							  	      ${fn:substring(site.site_name,0,9)}...
							  	   </c:when>
							  	   <c:otherwise>
							  	      ${site.site_name}
							  	   </c:otherwise>
							  	 </c:choose></font> </td>
							  	<td><a href="${ctx}/a/site/assite/detail?site_id=${site.site_id}"><font color="0099FF" size="2">详情</font></a></td>
							</tr>
						</c:forEach>
							<tr style="background-color:rgba(0,0,0,0.1)">
							  	<td colspan="2" align=center><a href="${ctx}/a/site/assite/list?warn_type=01"><font color="0099FF" style="font-weight:bold;" size="2.5">查看更多</font></a></td>
							  	
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="percenterNet" style="min-width:400px;height:400px;margin-top: 20px;"></div>
			
			<!-- /.box --> </section>
			<!-- right col -->
		</div>
		<!-- /.row (main row) -->

		</section>
		<!-- /.content -->
	</div>

	<!-- /.control-sidebar -->
	<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>

	<!-- ./wrapper -->

	<!-- jQuery 3 -->
	<%-- <script src="${ctx}/static/adminTLE/bower_components/jquery/dist/jquery.js"></script> --%>
	<script src="${ctx}/static/zzw/js/jquery.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<%-- <script src="${ctx}/static/adminTLE/bower_components/jquery-ui/jquery-ui.min.js"></script> --%>
	<script src="${ctx}/static/zzw/js/jquery-ui.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>$.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.7 -->
	<%-- <script src="${ctx}/static/adminTLE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script> --%>
	<script src="${ctx}/static/zzw/js/bootstrap.js"></script>
	<!-- Morris.js charts -->
	<%-- <script src="${ctx}/static/adminTLE/bower_components/raphael/raphael.min.js"></script> --%>
	<script src="${ctx}/static/zzw/js/raphael.js"></script>
	<%-- <script src="${ctx}/static/adminTLE/bower_components/morris.js/morris.min.js"></script> --%>
	<script src="${ctx}/static/zzw/js/morris.js"></script>
	<!-- Sparkline -->
	<%-- <script src="${ctx}/static/adminTLE/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script> --%>
	<script src="${ctx}/static/zzw/js/jquery.sparkline.js"></script>
	<!-- jvectormap -->
	<%-- <script src="${ctx}/static/adminTLE/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="${ctx}/static/adminTLE/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script> --%>
	<script src="${ctx}/static/zzw/js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="${ctx}/static/zzw/js/jquery-jvectormap-world-mill-en.js"></script>
	<!-- jQuery Knob Chart -->
	<%-- <script src="${ctx}/static/adminTLE/bower_components/jquery-knob/dist/jquery.knob.min.js"></script> --%>
	<script src="${ctx}/static/zzw/js/jquery.knob.min.js"></script>
	<!-- daterangepicker -->
	<%-- <script src="${ctx}/static/adminTLE/bower_components/moment/min/moment.min.js"></script>
	<script src="${ctx}/static/adminTLE/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script> --%>
	<script src="${ctx}/static/zzw/js/moment.min.js"></script>
	<script src="${ctx}/static/zzw/js/daterangepicker.js"></script>
	<!-- datepicker -->
	<%-- <script src="${ctx}/static/adminTLE/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script> --%>
	<script src="${ctx}/static/zzw/js/bootstrap-datepicker.js"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<%-- <script src="${ctx}/static/adminTLE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script> --%>
	<script src="${ctx}/static/zzw/js/bootstrap3-wysihtml5.all.js"></script>
	<!-- Slimscroll -->
	<%-- <script src="${ctx}/static/adminTLE/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script> --%>
	<script src="${ctx}/static/zzw/js/jquery.slimscroll.js"></script>
	<!-- FastClick -->
	<%-- <script src="${ctx}/static/adminTLE/bower_components/fastclick/lib/fastclick.js"></script> --%>
	<script src="${ctx}/static/zzw/js/fastclick.js"></script>
	<!-- AdminLTE App -->
	<%-- <script src="${ctx}/static/adminTLE/dist/js/adminlte.min.js"></script> --%>
	<script src="${ctx}/static/zzw/js/adminlte.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) <script src="${ctx}/static/adminTLE/dist/js/pages/dashboard.js"></script>-->
	<script type="text/javascript">
	$("#donutRC").click(function(){
		//debugger;
		var mydiv=$("#donutR1")[0];  
		if(mydiv.hidden){
   			mydiv.hidden=false;
   			$("#donutR2").hide();
 		}else{
 			mydiv.hidden=true;
 			$("#donutR2").show();
 		}
	});
	
$(function () {
  'use strict';
  // Make the dashboard widgets sortable Using jquery UI
  $('.connectedSortable').sortable({
    placeholder         : 'sort-highlight',
    connectWith         : '.connectedSortable',
    handle              : '.box-header, .nav-tabs',
    forcePlaceholderSize: true,
    zIndex              : 999999
  });
  $('.connectedSortable .box-header, .connectedSortable .nav-tabs-custom').css('cursor', 'move');

  // jQuery UI sortable for the todo list
  $('.todo-list').sortable({
    placeholder         : 'sort-highlight',
    handle              : '.handle',
    forcePlaceholderSize: true,
    zIndex              : 999999
  });

  // bootstrap WYSIHTML5 - text editor
  $('.textarea').wysihtml5();

  $('.daterange').daterangepicker({
    ranges   : {
      'Today'       : [moment(), moment()],
      'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
      'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
      'Last 30 Days': [moment().subtract(29, 'days'), moment()],
      'This Month'  : [moment().startOf('month'), moment().endOf('month')],
      'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    },
    startDate: moment().subtract(29, 'days'),
    endDate  : moment()
  }, function (start, end) {
    window.alert('You chose: ' + start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
  });

  /* jQueryKnob */
  $('.knob').knob();

  // jvectormap data
  var visitorsData = {
    US: 398, // USA
    SA: 400, // Saudi Arabia
    CA: 1000, // Canada
    DE: 500, // Germany
    FR: 760, // France
    CN: 300, // China
    AU: 700, // Australia
    BR: 600, // Brazil
    IN: 800, // India
    GB: 320, // Great Britain
    RU: 3000 // Russia
  };
  // World map by jvectormap
  $('#world-map').vectorMap({
    map              : 'world_mill_en',
    backgroundColor  : 'transparent',
    regionStyle      : {
      initial: {
        fill            : '#e4e4e4',
        'fill-opacity'  : 1,
        stroke          : 'none',
        'stroke-width'  : 0,
        'stroke-opacity': 1
      }
    },
    series           : {
      regions: [
        {
          values           : visitorsData,
          scale            : ['#92c1dc', '#ebf4f9'],
          normalizeFunction: 'polynomial'
        }
      ]
    },
    onRegionLabelShow: function (e, el, code) {
      if (typeof visitorsData[code] != 'undefined')
        el.html(el.html() + ': ' + visitorsData[code] + ' new visitors');
    }
  });

  // Sparkline charts var myvalues = [1000, 1200, 920, 927, 931, 1027, 819, 930, 1021];
  var myvalues =${ChartTest.myV1Jsons};
  $('#sparkline-1').sparkline(myvalues[0], {
    type     : 'line',
    lineColor: '#92c1dc',
    fillColor: '#ebf4f9',
    height   : '50',
    width    : '180'
  });
  //myvalues = [515, 519, 520, 522, 652, 810, 370, 627, 319, 630, 921];
  $('#sparkline-2').sparkline(myvalues[1], {
    type     : 'line',
    lineColor: '#92c1dc',
    fillColor: '#ebf4f9',
    height   : '50',
    width    : '180'
  });
  //myvalues = [15, 19, 20, 22, 33, 27, 31, 27, 19, 30, 21];
  $('#sparkline-3').sparkline(myvalues[2], {
    type     : 'line',
    lineColor: '#92c1dc',
    fillColor: '#ebf4f9',
    height   : '50',
    width    : '180'
  });
  
  // The Calender
  $('#calendar').datepicker();

  // SLIMSCROLL FOR CHAT WIDGET
  $('#chat-box').slimScroll({
    height: '250px'
  });

 //debugger;
  /* Morris.js Charts */
  // Sales chart
  var dataArea=${ChartTest.areaDatasJson};
  var area = new Morris.Area({
    element   : 'revenue-chart',
    resize    : true,
    data : dataArea,
    xkey      : 'y',
    ykeys     : ['item1'],
    labels    : ['销售额'],
    lineColors: ['#a0d0e0'],
    hideHover : 'auto'
  });
  var lineData=${ChartTest.lineDatasJson};
  var line = new Morris.Line({
    element          : 'line-chart',
    resize           : true,
    data :lineData,
    xkey             : 'y',
    ykeys            : ['item1'],
    labels           : ['Item 1'],
    lineColors       : ['#efefef'],
    lineWidth        : 2,
    hideHover        : 'auto',
    gridTextColor    : '#fff',
    gridStrokeWidth  : 0.4,
    pointSize        : 4,
    pointStrokeColors: ['#efefef'],
    gridLineColor    : '#efefef',
    gridTextFamily   : 'Open Sans',
    gridTextSize     : 10
  });

  // Donut Chart ['#3c8dbc', '#f56954', '#00a65a']
  var donutData=${ChartTest.donutDatasJson};
  var colorCode=${colorCode};
  var donut = new Morris.Donut({
    element  : 'sales-chart',
    resize   : true,
    colors   : colorCode,
    data     : donutData,
    hideHover: 'auto'
  });

  // Fix for charts under tabs
  $('.box ul.nav a').on('shown.bs.tab', function () {
    area.redraw();
    donut.redraw();
    line.redraw();
  });

  /* The todo list plugin */
  $('.todo-list').todoList({
    onCheck  : function () {
      window.console.log($(this), 'The element has been checked');
    },
    onUnCheck: function () {
      window.console.log($(this), 'The element has been unchecked');
    }
  });

});


</script>
	<!-- AdminLTE for demo purposes -->
<%-- 	<script src="${ctx}/static/adminTLE/dist/js/demo.js"></script> --%>
	<script src="${ctx}/static/zzw/js/demo.js"></script>
	<script>

$(function () {
	var seriesData=${ChartTest.seriesDatasJson};
	/* ['2011', '2012', '2013', '2014', '2015', '2016', '2017'] */
	var categories=${categories};
    $('#container').highcharts({
        chart: {
        	/* borderWidth: 1,
        	borderColor: 'black', */
            type: 'area'
        },
        title: {
            text: '<i class=\"fa fa-inbox\"></i>  在线趋势',
            align: 'left',
            useHTML: true
        },
        /* subtitle: {
            text: '数据来源: www.szzhilai.com',
            align: 'left'
        }, */
        xAxis: {
            categories: categories,
            tickmarkPlacement: 'on',
            title: {
                enabled: false
            }
        },
        yAxis: {
            title: {
                text: '百分比'
            }
        },
        tooltip: {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b> ({point.y:,.0f})<br/>',
            shared: true
        },
        plotOptions: {
            area: {
                stacking: 'percent',
                lineColor: '#ffffff',
                lineWidth: 1,
                marker: {
                    lineWidth: 1,
                    lineColor: '#ffffff'
                }
            }
        },
        series: seriesData
    });
});

        </script>
       

        <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>

        <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>

        <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
        <script>
$(function () {
	var dataNet=${ChartTest2.perJsons};
    $('#percenterNet').highcharts({
        chart: {
        	/* borderWidth: 1,
        	borderColor: 'black', */
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '<i class=\"fa fa-pie-chart\"></i>  当月各渠道销售情况统计',
            align: 'left',
            useHTML: true
        },
        tooltip: {
            headerFormat: '{series.name}<br>',
            pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '当月各渠道销售情况统计',
            data: dataNet
        }]
    });
    debugger;
    var data=${ChartTest.perJsons};
    $('#barNet').highcharts({
        chart: {
        	/* borderWidth: 1,
        	borderColor: 'black', */
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '<i class=\"fa fa-bar-chart-o\"></i> 每月订单总数统计 ',
            align: 'left',
            useHTML: true
        },
        tooltip: {
            headerFormat: '{data.name}<br>',
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
         xAxis: [
	        {
	            type: 'category',
	            show: true,     //显示横轴false-不显示，true-显示
	            data: data,
	        }
	    ],
	    // 纵轴属性
	    yAxis: [
	        {
	            type: 'value',
	            show: true,    //显示纵轴false-不显示，true-显示
	        }
	    ],
        series: [{
            type: 'bar',
            name: '订单总数统计',
            data: data,
           
        }]
    });
    
});
        </script>
        
</body>
</html>