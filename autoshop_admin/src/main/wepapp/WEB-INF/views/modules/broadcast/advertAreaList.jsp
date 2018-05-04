<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/broadcast/advertArea/">区域配置列表</a></li>
		<li><a href="${ctx}/broadcast/advertArea/form">区域配置添加</a></li>
	</ul>
	<form:form id="advertArea" modelAttribute="advertArea" action="${ctx}/broadcast/advertArea/list" method="post" class="breadcrumb form-search">
		<input id="advertAreaPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="advertAreaPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>配置名称：</label>
				<form:input path="configName" htmlEscape="false" maxlength="60" class="input-large" style="width:250px"/>
			</li>
			<li><label>区域名称：</label>
				<sys:treeselect id="tree" name="areaId" value="${param.areaId}" labelName="areaName" labelValue="${param.areaName}"
					title="区域名称" url="/sys/area/treeData" extId="${area.name}"  allowClear="true"/>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('advertArea');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="advertAreaList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>配置名称</th>
					<th>区域名称</th>
					<th>排列顺序</th>
					<th>屏幕显示像素x轴</th>
					<th>屏幕显示像素y轴</th>
					<th>媒体类型</th>
					<th>媒体格式</th>
					<th>配置描述</th>
					<th>创建时间</th>
					<th style="width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="advertArea">
					<tr id="${advertArea.logid}" >
						<td><a href="${ctx}/broadcast/advertArea/form?logid=${advertArea.logid}">${advertArea.configName}</a></td>
						<td>${advertArea.areaName}</td>
						<td>${advertArea.seqId}</td>
						<td>${advertArea.resolutionX}</td>
						<td>${advertArea.resolutionY}</td>
						<td>${mediaTypeMap[advertArea.mediaType]}</td>
						<td>${mimeTypeMap[advertArea.mimeType]}</td>
						<td title="${advertArea.description}">${fn:length(advertArea.description) > 20 ? (fn:substring(advertArea.description,0,19)).concat("...") : advertArea.description }</td>
						<td>${advertArea.createTime}</td>
						<td nowrap>
							<a href="${ctx}/broadcast/advertArea/delete?logid=${advertArea.logid}" onclick="return confirmx('要删除该区域配置吗？', this.href)">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#advertAreaPageNo").val(n);
		}
		if(s) {
			$("#advertAreaPageSize").val(s);
		}
		$("#advertArea").attr("action","${ctx}/broadcast/advertArea/list");
		$("#advertArea").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出区域配置数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#advertArea").attr("action","${ctx}/broadcast/advertArea/export");
					$("#advertArea").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



