<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/notice/noticeQueue/">通知发送队列列表</a></li>
	</ul>
	<form:form id="noticeQueue" modelAttribute="noticeQueue" action="${ctx}/notice/noticeQueue/list" method="post" class="breadcrumb form-search">
		<input id="noticeQueuePageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="noticeQueuePageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>业务单号：</label>
				<form:input path="queue_id" htmlEscape="false" maxlength="30" class="input" style="width:250px"/>
			</li>
			<li><label>业务大类：</label>
				<select name="main_type" style="width:135px">
					<option value="">-- ALL --</option>
					<c:forEach items="${mainTypeList}" var="mainType">
						<option value="${mainType.value}"
							${mainType.value==param.main_type?"selected='selected'":""}>${mainType.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('noticeQueue');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="noticeQueueList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>业务单号</th>
					<th>模板名称</th>
					<th>业务大类</th>
					<th>通知类型</th>
					<th>通知渠道</th>
					<th>通知地址</th>
					<th>通知人员名称</th>
					<th>发送失败次数</th>
					<th>发送状态</th>
					<th>状态时间</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="noticeQueue">
					<tr id="${noticeQueue.logid}" >
						<td><a href="${ctx}/notice/noticeQueue/form?queueId=${noticeQueue.queue_id}">${noticeQueue.queue_id}</a></td>
						<td>${noticeQueue.temp_name}</td>
						<td>${mainTypeMap[noticeQueue.main_type]}</td>
						<td>${noticeTypeMap[noticeQueue.notice_type]}</td>
						<td>${noticeChannelMap[noticeQueue.notice_channel]}</td>
						<td>${noticeQueue.notice_address}</td>
						<td>${noticeQueue.login_name}</td>
						<td>${noticeQueue.fail_num}</td>
						<td>${sendStateMap[noticeQueue.send_state]}</td>
						<td>${noticeQueue.state_time}</td>
						<td>${noticeQueue.create_time}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#noticeQueuePageNo").val(n);
		}
		if(s) {
			$("#noticeQueuePageSize").val(s);
		}
		$("#noticeQueue").attr("action","${ctx}/notice/noticeQueue/list");
		$("#noticeQueue").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出通知发送队列数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#noticeQueue").attr("action","${ctx}/notice/noticeQueue/export");
					$("#noticeQueue").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



