<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/monitor/noticeQueueLog/">通知发送日志列表</a></li>
	</ul>
	<form:form id="noticeQueueLog" modelAttribute="noticeQueueLog" action="${ctx}/monitor/noticeQueueLog/list" method="post" class="breadcrumb form-search">
		<input id="noticeQueueLogPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="noticeQueueLogPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>业务单号：</label>
				<form:input path="queue_id" htmlEscape="false" maxlength="30" class="input" style="width:250px"/>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('noticeQueueLog');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="noticeQueueLogList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>业务单号</th>
					<th>业务关联编号</th>
					<th>公司名称</th>
					<th>通知人员名称</th>
					<th>操作内容</th>
					<th>操作状态</th>
					<th>操作时间</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="noticeQueueLog">
					<tr>
						<td>${noticeQueueLog.queue_id}</td>
						<td>${noticeQueueLog.order_id}</td>
						<td>${noticeQueueLog.corp_name}</td>
						<td>${noticeQueueLog.login_name}</td>
						<td>${noticeQueueLog.cont}</td>
						<td>${noticeOperActionMap[noticeQueueLog.oper_action]}</td>
						<td>${noticeQueueLog.oper_time}</td>
						<td>${noticeQueueLog.create_time}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#noticeQueueLogPageNo").val(n);
		}
		if(s) {
			$("#noticeQueueLogPageSize").val(s);
		}
		$("#noticeQueueLog").attr("action","${ctx}/monitor/noticeQueueLog/list");
		$("#noticeQueueLog").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出设备信息数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#noticeQueueLog").attr("action","${ctx}/monitor/noticeQueueLog/export");
					$("#noticeQueueLog").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	
	</script>
</body>
</html>



