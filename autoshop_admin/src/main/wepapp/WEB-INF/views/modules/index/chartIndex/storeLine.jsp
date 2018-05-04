<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section class="col-lg-7 connectedSortable ui-sortable">
	<div class="nav-tabs-custom">
		<ul class="nav nav-tabs pull-right ui-sortable-handle">
			<li class="active">
				<a href="#line-chart" data-toggle="tab">月计</a>
			</li>
			<li>
				<a href="#donut-chart" data-toggle="tab">年计</a>
			</li>
			<li class="pull-left header"><!-- <i class="fa fa-inbox"></i> -->
				<i class="fa fa-line-chart"></i> 每月出入库趋势</li>
		</ul>
		<div class="tab-content no-padding">
			<div class="chart tab-pane active" id="line-chart" style="height: 260px"></div>
			<div class="chart tab-pane" id="donut-chart" style="height: 260px"></div>
		</div>
	</div>
</section>

<script>
$(function() {
	$.ajax({
		type : "POST",
		url : "${ctx}/sys/index/getStoreMonth",
		dataType : "JSON",
		success : function(ret) {
			var storeMap = ret;
			//	生成折线图对象
			var lineData = getLineData(storeMap);
			var storeLineLine = new Morris.Line({
				element : 'line-chart',
				resize : true,
				data : lineData,
				xkey : 'month',
				ykeys : [ 'instore', 'outstore' ],
				labels : [ '入库', '出库' ],
				lineColors: ['#a0d0e0', '#3c8dbc'],
				hideHover: 'auto'
			});
			//	生成饼图对象
			var donutData = getDonutData(storeMap);
			var donut = new Morris.Donut({
				element : 'donut-chart',
				resize : true,
				colors : [ "#3c8dbc", "#f56954", "#00a65a" ],
				data : donutData,
				hideHover : 'true'
			});
		}
	});
});

var getLineData = function(storeMap) {
	//	获取当前年份
	var now = new Date();
	var yyyy = now.getFullYear();
	var data = [], instores = storeMap.instores, outstores = storeMap.outstores;
	for(var i = 0; i < instores.length; i++) {
		data.push({
			month : yyyy + "-" + ((i < 9 ? "0" : "") + (i + 1)),
			instore : instores[i],
			outstore : outstores[i]
		});
	}
	return data;
};

var getDonutData = function(storeMap) {
	var data = [], instores = storeMap.instores, outstores = storeMap.outstores;
	var insum = 0, outsum = 0;
	for(var i = 0; i < instores.length; i++) {
		insum += instores[i];
		outsum += outstores[i];
	}
	data.push({
		label : "入库总数",
		value : insum
	});
	data.push({
		label : "出库总数",
		value : outsum
	});
	return data;
};
</script>
