 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>性能日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/log/tradeLog/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/tradeLog/">日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tradeLog" action="${ctx}/log/tradeLog/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
		<li><label>交易代码：</label><form:input path="trade_code" htmlEscape="false" maxlength="10" class="input-medium"/></li>
		<li><label>授权用户：</label><form:input path="auth_name" htmlEscape="false" maxlength="20" class="input-medium"/></li>	
			
			<li><label>交易时间：</label><input id="begin_time_tradeLogList" style="width:110px" name="begin_time" value="${tradeLog.begin_time}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								-
								<input id="end_time_tradeLogList" style="width:110px" name="end_time" value="${tradeLog.end_time}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'begin_time_tradeLogList\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><%--<th>日志编号</th>--%>
		<th>授权用户</th>
		<th>平台名称</th>
		<th>交易代码</th>
		<th>交易名称</th>
		<th>开始时间</th>
		<th>结束时间</th>
		<th>返回码</th>
		<th>执行时间（ms）</th>
		<th>交易协议</th>
		<tbody>
		<c:forEach items="${page.list}" var="tradeLog">
			<tr>
				<%-- <td><a href="${ctx}/log/tradeLog/form?log_id=${tradeLog.log_id}" title="${tradeLog.log_id}">${fns:abbr(tradeLog.log_id,40)}</a></td>--%>
				<td>${tradeLog.auth_name}</td>
				<td>${tradeLog.plat_code}</td>
				<td>${tradeLog.trade_code}</td>
				<td>${tradeLog.trade_name}</td>
				<td>${tradeLog.begin_time}</td>
				<td>${tradeLog.end_time}</td>
				<td>${tradeLog.ret_code}</td>
				<td>${tradeLog.exec_time}</td>
				<td>${tradeLog.protocol}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>