<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div id="chart">
	<!-- <div class="box box-success">
		<div class="box-header">
			<h3 class="box-title">Bar Chart</h3>
		</div>
		<div class="box-body chart-responsive">
			<div class="chart" id="bar-chart" style="height: 300px;"></div>
		</div>
	</div> -->

	<!-- <div class="box box-info">
		<div class="box-header">
			<h3 class="box-title">Line Chart</h3>
		</div>
		<div class="box-body chart-responsive">
			<div class="chart" id="line-chart" style="height: 300px;"></div>
		</div>
	</div> -->

	<!-- <div class="box box-danger">
		<div class="box-header">
			<h3 class="box-title">Donut Chart</h3>
		</div>
		<div class="box-body chart-responsive">
			<div class="chart" id="sales-chart"
				style="height: 300px; position: relative;"></div>
		</div>
	</div> -->
	<div class="bg-light-blue-gradient col-lg-5">
		<div id="world-map" style="height: 250px; width: 100%;"></div>
	</div>
</div>

<script>
$(function() {
	"use strict";
	//BAR CHART  
	/* var bar = new Morris.Bar({
		element : 'bar-chart',
		resize : true,
		data : [ {
			year : '1111',
			a : 15,
			b : 5
		}, {
			year : '2222',
			a : 5,
			b : 25
		}, {
			year : '3333',
			a : 35,
			b : 5
		}, {
			year : '4444',
			a : 5,
			b : 45
		}, {
			year : '5555',
			a : 55,
			b : 5
		}, {
			year : '6666',
			a : 5,
			b : 65
		}, {
			year : '7777',
			a : 71,
			b : 2
		} ],
		barColors : [ '#00a65a', '#f56954' ],
		xkey : 'year',
		ykeys : [ 'a', 'b' ],
		ymax : 'auto 100',
		units : '',
		labels : [ 'CPU', 'DISK' ],
		hideHover : 'true',
		hoverFillColor : '#00a65a'
	}); */

	// LINE CHART  
	/* var area = new Morris.Area({
		element : 'line-chart',
		resize : true,
		data : [ 
			{ year : '2011 Q1', data1 : 1000, data2 : 300 }, 
			{ year : '2011 Q2', data1 : 8000, data2 : 5000 }, 
			{ year : '2011 Q3', data1 : 6000, data2 : 3000 }, 
			{ year : '2011 Q4', data1 : 4000, data2 : 2000 }, 
			{ year : '2012 Q1', data1 : 5000, data2 : 1000 }, 
			{ year : '2012 Q2', data1 : 6000, data2 : 5000 }, 
			{ year : '2012 Q3', data1 : 7000, data2 : 5000 }, 
			{ year : '2012 Q4', data1 : 6000, data2 : 5500 }, 
			{ year : '2013 Q1', data1 : 8000, data2 : 5980 }, 
			{ year : '2013 Q2', data1 : 10000, data2 : 8000 }, 
			{ year : '2013 Q3', data1 : 8000, data2 : 3000 }, 
			{ year : '2013 Q4', data1 : 10000, data2 : 4000 } 
		],
		xkey : 'year',
		ykeys : [ 'data1', 'data2' ],
		labels : [ '数据1', '数据2' ],
		lineColors: ['#a0d0e0', '#3c8dbc'],
		hideHover: 'auto'
	}); */

	//DONUT CHART  
	/* var donut = new Morris.Donut({
		element : 'sales-chart',
		resize : true,
		colors : [ "#3c8dbc", "#f56954", "#00a65a" ],
		data : [ {
			label : "Download Sales",
			value : 50
		}, {
			label : "In-Store Sales",
			value : 30
		}, {
			label : "Mail-Order Sales",
			value : 20
		} ],
		hideHover : 'true'
	}); */
	
	//jvectormap data

	/* var visitorsData = {
		"US" : 398, //USA
		"SA" : 400, //Saudi Arabia
		"CA" : 1000, //Canada
		"DE" : 500, //Germany
		"FR" : 760, //France
		"CN" : 300, //China
		"AU" : 700, //Australia
		"BR" : 600, //Brazil
		"IN" : 800, //India
		"GB" : 320, //Great Britain
		"RU" : 3000 //Russia
	};
	$('#world-map').vectorMap({
		map : 'world_mill_en',
		backgroundColor : "transparent",
		regionStyle : {
			initial : {
				fill : '#e4e4e4',
				"fill-opacity" : 1,
				stroke : 'none',
				"stroke-width" : 0,
				"stroke-opacity" : 1
			}
		},
		series : {
			regions : [ {
				values : visitorsData,
				scale : [ "#92c1dc", "#ebf4f9" ],
				normalizeFunction : 'polynomial'
			} ]
		},
		onRegionLabelShow : function(e, el, code) {
			if (typeof visitorsData[code] != "undefined")
				el.html(el.html() + ': ' + visitorsData[code] + ' new visitors');
		}
	});  */
});
</script>