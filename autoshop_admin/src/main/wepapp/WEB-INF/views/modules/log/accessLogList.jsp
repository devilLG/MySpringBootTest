 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>访问日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/log/accessLog/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/accessLog/">日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="accessLog" action="${ctx}/log/accessLog/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>授权编号：</label><form:input path="auth_id" htmlEscape="false" maxlength="64" class="input-medium"/></li>
			
			<li><label>访问时间：</label><input id="access_time_accessLogList" style="width:110px" name="access_time" value="${accessLog.access_time}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								-
								<input id="access_time_end_accessLogList" style="width:110px" name="access_time_end" value="${accessLog.access_time_end}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'access_time_accessLogList\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<li class="clearfix"></li>
		
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><%--<th>日志编号</th>--%>
		<th>授权用户</th>
		<th>授权编号</th>
		<th>访问时间</th>
		<th>iP地址</th>
		<th>主机名</th>
		<th>状态</th>
		<th>协议</th>
		<tbody>
		<c:forEach items="${page.list}" var="accessLog">
			<tr>
				<%-- <td><a href="${ctx}/log/accessLog/form?log_id=${accessLog.log_id}" title="${accessLog.log_id}">${fns:abbr(accessLog.log_id,40)}</a></td>--%>
				<td>${accessLog.auth_name}</td>
				<td>${accessLog.auth_id}</td>
				<td>${accessLog.access_time}</td>
				<td>${accessLog.ip_addr}</td>
				<td>${accessLog.hostname}</td>
				<td>${accessLog.state}</td>
				<td>${accessLog.protocol}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>