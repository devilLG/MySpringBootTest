<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<script src="${ctxStatic}/zzw/js/echarts.min.js"></script>

<section class="col-lg-5 col-md-5 connectedSortable"> 
	<div class="box box-solid">
		<div class="box-header">
			<h3 class="box-title"><i class="fa fa-pie-chart"></i> 当月实时业务类型统计</h3>
		</div>
		<div class="box-body chart-responsive">
			<div id="businessType_pie" style="height:260px"></div>
		</div>
	</div>
</section>

<script>
$(function() {
	var theme1 = {
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
	//	-------------------------------------------------------------
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
			//	本月业务总数
			var currentMonthSum = businessMonths.outstorages[currentMonth - 1] + businessMonths.instorages[currentMonth - 1]
				+	businessMonths.allocations[currentMonth - 1] + businessMonths.inventorys[currentMonth - 1];
			$("#current-month-businees-sum-h3").text(currentMonthSum);
			//	
			var echartDonut = echarts.init(document.getElementById('businessType_pie'), theme1);
			//	legend左边距
			var legendLeft = 0;
			var canvasW = $("#businessType_pie").width();
			var canvasH = $("#businessType_pie").height();
			legendLeft = (canvasW - canvasH) / 2 + canvasH + (canvasW - canvasH) / 8;
			
			echartDonut.setOption({
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				calculable : true,
				legend : {
					orient : 'vertical',
					x : legendLeft + '',
					y : 'center',
					data : dataLabel
				},
				series : [ {
					name : '当月业务类型统计',
					type : 'pie',
					radius : [ '35%', '55%' ],
					itemStyle : {
						normal : {
							label : {
								show : true
							},
							labelLine : {
								show : true
							}
						},
						emphasis : {
							label : {
								show : true,
								position : 'center',
								textStyle : {
									fontSize : '14',
									fontWeight : 'normal'
								}
							}
						}
					},
					data : seriesData
				} ]
			});
			//	
			$("#businessType_pie").find("canvas").css({
				top : -20
			});
		}
	});
});
</script>