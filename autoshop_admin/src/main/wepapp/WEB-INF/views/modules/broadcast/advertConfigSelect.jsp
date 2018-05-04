<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>选择广告配置</title>
		<meta name="decorator" content="default" />
		<style>
			#advertConfig_table li {line-height:25px;margin:2px 5px;cursor:pointer;list-style:none;}
			#advertConfig_table i {margin:1px 5px;font-size:16px;}
			#advertConfig_table li:hover {background-color:#DCDCDC;}
	        #advertConfig_table li.active {background-color:#0088CC;color:#ffffff;}
		</style>
	</head>
	<body>
		<input type="hidden" id="advertConfig_parms" />
		<table id="advertConfig_table" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th style="text-align: center;">配置编号</th>
					<th style="text-align: center;">配置名称</th>
					<th style="text-align: center;">计划开始播放日期</th>
					<th style="text-align: center;">计划停止播放日期</th>
					<th style="text-align: center; width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${advertConfigList}" var="advertConfig">
					<tr id="${advertConfig.configId}" style="">
						<td style="text-align: center;" nowrap>${advertConfig.configId}</td>
						<td style="text-align: center;">${advertConfig.configName}</td>
						<td style="text-align: center;">${advertConfig.planStime}</td>
						<td style="text-align: center;">${advertConfig.planEtime}</td>
						<td style="text-align: center;">
							<li>
								<i class="icon-ok magnifying"></i>
								<input type="hidden" value="${advertConfig.configId},${advertConfig.configName},${advertConfig.planStime},${advertConfig.planEtime}">
							</li>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
				$(document).ready(function(){
			    	$("#advertConfig_table li").click(function(){
			    		debugger;
			    		$("#advertConfig_table li").removeClass("active");
	    				$("#advertConfig_table li i").removeClass("icon-white");
			    		$(this).addClass("active");
	    				$(this).children("i").addClass("icon-white");
			    		$("#advertConfig_parms").val($(this).children("input").val());
			    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
			    	});
			    });
		</script>
	</body>
</html>