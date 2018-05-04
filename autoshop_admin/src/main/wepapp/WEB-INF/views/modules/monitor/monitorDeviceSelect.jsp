<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>选择设备信息</title>
		<meta name="decorator" content="default" />
		<style>
			#monitorDevice_table li {line-height:25px;margin:2px 5px;cursor:pointer;list-style:none;}
			#monitorDevice_table i {margin:1px 5px;font-size:16px;}
			#monitorDevice_table li:hover {background-color:#DCDCDC;}
	        #monitorDevice_table li.active {background-color:#0088CC;color:#ffffff;}
		</style>
	</head>
	<body>
		<input type="hidden" id="monitorDevice" />
		<table id="monitorDevice_table" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th style="text-align: center;">设备名称</th>
					<th style="text-align: center;">设备品牌</th>
					<th style="text-align: center;">设备型号</th>
					<th style="text-align: center;">设备尺寸</th>
					<th style="text-align: center;">设备生产商</th>
					<th style="text-align: center; width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${deviceInfoList}" var="deviceInfo">
					<tr id="${deviceInfo.deviceId}" style="">
						<td style="text-align: center;">${deviceInfoMap[deviceInfo.deviceName]}</td>
						<td style="text-align: center;">${deviceBardMap[deviceInfo.deviceBard]}</td>
						<td style="text-align: center;">${deviceModelMap[deviceInfo.deviceModel]}</td>
						<td style="text-align: center;">${deviceInfo.deviceSize}</td>
						<td style="text-align: center;">${deviceMfrsMap[deviceInfo.deviceMfrs]}</td>
						<td style="text-align: center;">
							<li>
								<i class="icon-ok magnifying"></i>
								<input type="hidden" value="${deviceInfo.deviceName},${deviceInfoMap[deviceInfo.deviceName]},${deviceInfo.deviceBard},${deviceBardMap[deviceInfo.deviceBard]},${deviceInfo.deviceModel},${deviceModelMap[deviceInfo.deviceModel]},${deviceInfo.deviceMfrs},${deviceMfrsMap[deviceInfo.deviceMfrs]},${deviceInfo.deviceId}">
							</li>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
				$(document).ready(function(){
			    	$("#monitorDevice_table li").click(function(){
			    		$("#monitorDevice_table li").removeClass("active");
	    				$("#monitorDevice_table li i").removeClass("icon-white");
			    		$(this).addClass("active");
	    				$(this).children("i").addClass("icon-white");
			    		$("#monitorDevice").val($(this).children("input").val());
			    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
			    	});
			    });
		</script>
	</body>
</html>