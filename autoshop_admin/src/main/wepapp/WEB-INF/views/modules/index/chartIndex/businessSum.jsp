<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<script src="${ctxStatic}/zzw/js/echarts.min.js"></script>

<section class="col-lg-7 col-md-7 connectedSortable">
	<div class="box box-solid">
		<div class="box-header">
			<h3 class="box-title"><i class="fa fa-bar-chart-o"></i> 每月业务总数统计</h3>
		</div>
		<div class="box-body chart-responsive">
			<div id="echart_bar_horizontal" style="height:260px"></div>
		</div>
	</div>
</section>

<script>
$(function() {
	var theme = {
		color : [ '#3498DB', '#34495E', '#BDC3C7', '#9B59B6', '#26B99A', '#8abb6f', '#759c6a', '#bfd3b7' ],
		categoryAxis : {
			axisLine : {
				lineStyle : {
					color : '#408888'
				}
			},
			splitLine : {
				lineStyle : {
					color : [ '#eee' ]
				}
			}
		},
		valueAxis : {
			axisLine : {
				lineStyle : {
					color : '#408888'
				}
			},
			splitArea : {
				show : true,
				areaStyle : {
					color : [ 'rgba(250,250,250,0.1)', 'rgba(200,200,200,0.1)' ]
				}
			},
			splitLine : {
				lineStyle : {
					color : [ '#eee' ]
				}
			}
		},
		textStyle : {
			fontFamily : 'Arial, Verdana, sans-serif'
		}
	};
	//	-------------------------------------------------------------
	$.ajax({
		type : "POST",
		url : "${ctx}/sys/index/getMonthsBusiness",
		dataType : "JSON",
		success : function(ret) {
			var businessMonths = ret;
			//	Y
			var yAxis = [];
			// Data
			var series = [{
				name : '业务总数',
				type : 'bar'
			}];
			var serieData = [];
			for(var i = 0; i < 12; i++) {
				var monthData = businessMonths.allocations[i] + businessMonths.inventorys[i]
						 + businessMonths.instorages[i] + businessMonths.outstorages[i];
				yAxis.push(((i < 9 ? "0" : "") + (i + 1)) + "月");
				serieData.push(monthData);
			}
			series[0].data = serieData;
			
			var echartBar = echarts.init(document.getElementById('echart_bar_horizontal'), theme);
			
			echartBar.setOption({
				tooltip : {
					trigger : 'axis'
				},
				calculable : true,
				xAxis : [ {
					type : 'value',
					boundaryGap : [ 0, 0.01 ]
				} ],
				yAxis : [ {
					type : 'category',
					data : yAxis
				} ],
				series : series
			});
			//
			$("#echart_bar_horizontal").find("canvas").css({
				top : -15,
				left : -25
			});
		}
	});
});
</script>