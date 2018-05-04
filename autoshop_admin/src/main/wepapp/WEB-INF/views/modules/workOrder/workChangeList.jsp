<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/workOrder/workChange/">广告日志列表</a></li>
	</ul>
	<form:form id="workChange" modelAttribute="workChange" action="${ctx}/workOrder/workChange/list" method="post" class="breadcrumb form-search">
		<input id="workChangePageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="workChangePageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>工单编号：</label>
				<form:input path="workId" htmlEscape="false" maxlength="30" class="input-large" style="width:250px"/>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('workChange');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="scrapMaterielListForm" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>工单标题</th>
					<th>工单编号</th>
					<th>公司编号</th>
					<th>公司名称</th>
					<th>操作动作 </th>
					<th>工单状态时间</th>
					<th>操作人名称</th>
					<th>操作人内容</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="workChange">
					<tr id="${workChange.logid}" >
						<td>${workChange.workTitle}</td>
						<td>${workChange.workId}</td>
						<td>${workChange.corpId}</td>
						<td>${workChange.corpName}</td>
						<td>${operActionMap[workChange.operAction]}</td>
						<td>${workChange.operTime}</td>
						<td>${workChange.operName}</td>
						<td title="${workChange.perCont}">${fn:length(workChange.perCont) > 20 ? (fn:substring(workChange.perCont,0,19)).concat("...") : workChange.perCont }</td>
						<td>${workChange.createTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#workChangePageNo").val(n);
		}
		if(s) {
			$("#workChangePageSize").val(s);
		}
		$("#workChange").attr("action","${ctx}/workOrder/workChange/list");
		$("#workChange").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出广告日志数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#workChange").attr("action","${ctx}/workOrder/workChange/export");
					$("#workChange").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



