<%@page import="com.zhilai.master.modules.sys.utils.UserUtils"%>
<%@ page contentType="text/html;charset=UTF-8" 
		import="com.zhilai.master.modules.utils.GParameter"
		import="com.zhilai.master.modules.sys.utils.UserUtils"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class="col-lg-3 col-xs-6">
	<div class="small-box bg-aqua">
		<div class="inner"><h3>${boxDataMap["instore"] }&nbsp;</h3><p>本月入库数</p></div>
		<div class="icon"><i class="ion ion-bag"></i></div>
		<a href="${ctx }/stock/instorage/instoreDetail" class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i></a>
	</div>
</div>

<div class="col-lg-3 col-xs-6">
	<div class="small-box bg-green">
		<div class="inner"><h3>${boxDataMap["outstore"] }&nbsp;</h3><p>本月出库数</p></div>
		<div class="icon"><i class="ion ion-stats-bars"></i></div>
		<a href="${ctx }/stock/outstorage/outstoreDetail" class="small-box-footer">查看详情 <i class="fa fa-arrow-circle-right"></i> </a>
	</div>
</div>

<div class="col-lg-3 col-xs-6">
	<div class="small-box bg-yellow dropdown">
		<div class="inner">
			<h3 id="current-month-businees-sum-h3">&nbsp;</h3>
			<p>本月业务总数</p>
		</div>
		<div class="icon"><i class="ion ion-person-add"></i></div>
		<a href="#" class="small-box-footer" data-toggle="dropdown" title="初次加载需要数秒，请稍候！">
			查看详情 <i class="fa fa-arrow-circle-right"></i> 
		</a>
		<ul class="dropdown-menu" role="menu" aria-Labelledby="dLabel" style="border: 1px solid lightgray;">
			<li><div id="businessSumPie"></div></li>
		</ul>
	</div>
</div>

<div class="col-lg-3 col-xs-6">
	<div class="small-box bg-red">
		<div class="inner">
			<h3>0</h3>
			<p>告警信息(敬请期待)</p>
		</div>
		<div class="icon"><i class="ion ion-pie-graph"></i></div>
		<a href="#" class="small-box-footer">查看详情<i class="fa fa-arrow-circle-right"></i> </a>
	</div>
</div>

<script>
$(function() {
	//	业务总数dropdown相关
	var theme2 = {
		color: [ '#26B99A', '#34495E','#9B59B6', '#3498DB', '#8abb6f', '#BDC3C7', '#759c6a', '#bfd3b7' ],
		title : {
			itemGap : 8,
			textStyle : {
				fontWeight : 'normal',
				color : '#408829'
			}
		},
		dataRange : {
			color : [ '#1f610a', '#97b58d' ]
		},
		toolbox : {
			color : [ '#408829', '#408829', '#408829', '#408829' ]
		},
		tooltip : {
			backgroundColor : 'rgba(0,0,0,0.5)',
			axisPointer : {
				type : 'line',
				lineStyle : {
					color : '#408829',
					type : 'dashed'
				},
				crossStyle : {
					color : '#408829'
				},
				shadowStyle : {
					color : 'rgba(200,200,200,0.3)'
				}
			}
		},
		dataZoom : {
			dataBackgroundColor : '#eee',
			fillerColor : 'rgba(64,136,41,0.2)',
			handleColor : '#408829'
		},
		grid : {
			borderWidth : 0
		},
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
		timeline : {
			lineStyle : {
				color : '#408829'
			},
			controlStyle : {
				normal : {
					color : '#408829'
				},
				emphasis : {
					color : '#408829'
				}
			}
		},
		
		force : {
			itemStyle : {
				normal : {
					linkStyle : {
						strokeColor : '#408829'
					}
				}
			}
		},
		chord : {
			padding : 4,
			itemStyle : {
				normal : {
					lineStyle : {
						width : 1,
						color : 'rgba(128, 128, 128, 0.5)'
					},
					chordStyle : {
						lineStyle : {
							width : 1,
							color : 'rgba(128, 128, 128, 0.5)'
						}
					}
				},
				emphasis : {
					lineStyle : {
						width : 1,
						color : 'rgba(128, 128, 128, 0.5)'
					},
					chordStyle : {
						lineStyle : {
							width : 1,
							color : 'rgba(128, 128, 128, 0.5)'
						}
					}
				}
			}
		},
		gauge : {
			startAngle : 225,
			endAngle : -45,
			axisLine : {
				show : true,
				lineStyle : {
					color : [ [ 0.2, '#86b379' ], [ 0.8, '#68a54a' ],
							[ 1, '#408829' ] ],
					width : 8
				}
			},
			axisTick : {
				splitNumber : 10,
				length : 12,
				lineStyle : {
					color : 'auto'
				}
			},
			axisLabel : {
				textStyle : {
					color : 'auto'
				}
			},
			splitLine : {
				length : 18,
				lineStyle : {
					color : 'auto'
				}
			},
			pointer : {
				length : '90%',
				color : 'auto'
			},
			title : {
				textStyle : {
					color : '#333'
				}
			},
			detail : {
				textStyle : {
					color : 'auto'
				}
			}
		},
		textStyle : {
			fontFamily : 'Arial, Verdana, sans-serif'
		}
	};
	//	是否第一次点击
	var isFirstTimeClick = true;
	var aLabel = $("#current-month-businees-sum-h3").parent().parent().find("a[data-toggle]");
	aLabel.on("click", function(e) {
		if(isFirstTimeClick) {
			$("#businessSumPie").css({
				width : $("#businessSumPie").parent().parent().parent(".dropdown").width(),
				height : 300,
				marginTop : -8,
				marginLeft : -1
			});
			$.ajax({
				type : "POST",
				url : "${ctx}/sys/index/getMonthsBusiness",
				dataType : "JSON",
				success : function(ret) {
					var businessMonths = ret;
					var currentMonth = (new Date()).getMonth() + 1;
					var dataLabel = [ '出库', '入库', '调拨', '盘点' ]
					var seriesData = [];
					seriesData.push( {
						value : businessMonths.outstorages[currentMonth - 1],
						name : dataLabel[0]
					});
					seriesData.push( {
						value : businessMonths.instorages[currentMonth - 1],
						name : dataLabel[1]
					});
					seriesData.push( {
						value : businessMonths.allocations[currentMonth - 1],
						name : dataLabel[2]
					});
					seriesData.push( {
						value : businessMonths.inventorys[currentMonth - 1],
						name : dataLabel[3]
					});
					//	----------------------
					var echartPieCollapse = echarts.init(document.getElementById('businessSumPie'), theme2);
			  
					echartPieCollapse.setOption({
						tooltip : {
							trigger : 'item',
							formatter : "{a}</br>{b} : {c} ({d}%)"
						},
						calculable : true,
						series : [ {
							name : '点击查看详情',
							type : 'pie',
							radius : [ 25, 90 ],
							center : [ '50%', 170 ],
							roseType : 'area',
							x : '50%',
							max : 40,
							sort : 'ascending',
							data : seriesData
						} ]
					});
					$("#businessSumPie").find("canvas").css("top", "-20px");
					//	Click Event
					echartPieCollapse.on("click", function(param) {
						var type = param.name;
						var now = new Date();
						var yyyy = now.getFullYear(), MM = now.getMonth() + 1;
						MM = (MM < 10 ? "0" : "") + MM;
						var windowHref;
						<%-- if("调拨" == type) {
							windowHref = "${ctx}/allocation/allocation?createTime=" + yyyy + "-" + MM
								 + "&curStatus=<%=GParameter.purchaseOrder_status_finished%>"
								 + "&companyId=<%=UserUtils.getUser().getCompany().getId()%>";
						} else if ("盘点" == type) {
							windowHref = "${ctx}/stock/inventoryOverage?create_time=" + yyyy + "-" + MM
								 + "&cur_status=<%=GParameter.purchaseOrder_status_finished%>"
								 + "&company_id=<%=UserUtils.getUser().getCompany().getId()%>";
						} else if ("入库" == type) {
							windowHref = "${ctx}/stock/instorage?createTime=" + yyyy + "-" + MM
								 + "&curStatus=<%=GParameter.purchaseOrder_status_finished%>"
								 + "&companyId=<%=UserUtils.getUser().getCompany().getId()%>"; 
						} else if ("出库" == type) {
							windowHref = "${ctx}/stock/outstorage?createTime=" + yyyy + "-" + MM
								 + "&curStatus=<%=GParameter.purchaseOrder_status_finished%>"
								 + "&companyId=<%=UserUtils.getUser().getCompany().getId()%>";
						} --%>
						window.location.href = windowHref; 
					});
				}
			});
		}
		isFirstTimeClick = false;
	});
});
</script>
