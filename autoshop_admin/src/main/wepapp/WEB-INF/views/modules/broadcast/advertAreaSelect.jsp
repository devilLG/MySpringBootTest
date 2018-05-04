<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>选择区域配置信息</title>
		<meta name="decorator" content="default" />
		<style>
			#advertArea_table li {line-height:25px;margin:2px 5px;cursor:pointer;list-style:none;}
			#advertArea_table i {margin:1px 5px;font-size:16px;}
			#advertArea_table li:hover {background-color:#DCDCDC;}
	        #advertArea_table li.active {background-color:#0088CC;color:#ffffff;}
		</style>
	</head>
	<body>
		<input type="hidden" id="advertArea_parms" />
		<table id="advertArea_table" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th style="text-align: center;">配置名称</th>
					<th style="text-align: center;">区域名称</th>
					<th style="text-align: center;">排列顺序</th>
					<th style="text-align: center;">媒体类型</th>
					<th style="text-align: center; width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${advertAreaList }" var="advertArea">
					<tr id="${sotre.advertAreaId}" style="">
						<td style="text-align: center;" nowrap>${advertArea.configName}</td>
						<td style="text-align: center;">${advertArea.areaName}</td>
						<td style="text-align: center;">${advertArea.seqId}</td>
						<td style="text-align: center;">${mediaTypeMap[advertArea.mediaType]}</td>
						<td style="text-align: center;">
							<li>
								<i class="icon-ok magnifying"></i>
								<input type="hidden" value="${advertArea.configId},${advertArea.configName},${advertArea.areaId},${advertArea.areaName}">
							</li>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
				$(document).ready(function(){
			    	$("#advertArea_table li").click(function(){
			    		debugger;
			    		$("#advertArea_table li").removeClass("active");
	    				$("#advertArea_table li i").removeClass("icon-white");
			    		$(this).addClass("active");
	    				$(this).children("i").addClass("icon-white");
			    		$("#advertArea_parms").val($(this).children("input").val());
			    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
			    	});
			    });
		</script>
	</body>
</html>