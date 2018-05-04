<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section class="col-lg-5 connectedSortable"> <!-- Map box -->
	<div class="box box-solid bg-light-blue-gradient">
		<div class="box-header">
			<div class="pull-right box-tools">
				<!-- <button type="button" class="btn btn-primary btn-sm daterange pull-right" data-toggle="tooltip" title="Date range">
					<i class="fa fa-calendar"></i>
				</button> -->
				<button type="button" class="btn btn-primary btn-sm pull-right" data-widget="collapse" data-toggle="tooltip" title="Collapse" style="margin-right: 5px;">
					<i class="fa fa-minus"></i>
				</button>
			</div>
			<i class="fa fa-map-marker"></i>
			<h3 class="box-title">仓库分布图</h3>
		</div>
		<div class="box-body">
			<div id="world-map" style="height: 220px; width: 100%;"></div>
		</div>
	</div>
</section>

<script>
$(function() {
	var visitorsData = {
		"US" : 398, 	//	USA
		"SA" : 400, 	//	Saudi Arabia
		"CA" : 1000, 	//	Canada
		"DE" : 500, 	//	Germany
		"FR" : 760, 	//	France
		"CN" : 100, 	//	China
		"AU" : 700, 	//	Australia
		"BR" : 600, 	//	Brazil
		"IN" : 800, 	//	India
		"GB" : 320, 	//	Great Britain
		"RU" : 3000 	//	Russia
	};
	$.ajax({
		type : "POST",
		url : "${ctx}/sys/index/getStoreInfos",
		dataType : "JSON",
		success : function(ret) {
			var markers = [];
			var storeList = ret;
			for(var i = 0; i < storeList.length; i++) {
				markers.push({
					latLng : [storeList[i].latitude, storeList[i].longitude],
					name : storeList[i].storeName + "[" + storeList[i].storeAddress + "]",
					style : {storeId: storeList[i].storeId}
				});
			}
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
				markers: markers,
				markerStyle: {
				  initial: {
				    fill: 'red',
				    stroke: '#ffffff',
				    "fill-opacity": 1,
				    "stroke-width": 1,
				    "stroke-opacity": 1,
				    r: 3
				  },
				  hover: {
				    stroke: '#ff3300',
				    "stroke-width": 1
				  },
				  selected: {
				    fill: 'blue'
				  },
				  selectedHover: {
				  }
				},
				onMarkerClick : function(e, code) {
					debugger;
					var storeId = $("circle[data-index='" + code + "']").attr("storeId");
					window.location.href = "${ctx}/storeManage/store/mapDataToList?storeId=" + storeId;
				}
			});
		}
	});
	
	
});
</script>