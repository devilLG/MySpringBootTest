 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报文日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/log/pkgLog/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/pkgLog/">日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pkgLog" action="${ctx}/log/pkgLog/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
	
		<li><label>交易代码：</label><form:input path="trade_code" htmlEscape="false" maxlength="10" class="input-medium"/></li>
		<li><label>授权用户：</label><form:input path="auth_name" htmlEscape="false" maxlength="20" class="input-medium"/></li>	
			
	<li><label>交易时间：</label><input id="trade_time_pkgLogList" style="width:110px" name="trade_time" value="${pkgLog.trade_time}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								-
								<input id="trade_time_end_pkgLogList" style="width:110px" name="trade_time_end" value="${pkgLog.trade_time_end}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'trade_time_pkgLogList\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
		</div>
		
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><%--<th>日志编号</th>--%>
		<th>授权用户</th>
		<th>平台名称</th>
		<th>交易代码</th>
		<th>交易时间</th>
		<th>请求报文</th>
		<th>回应报文</th>
		<th>协议</th>
		<th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="pkgLog">
			<tr>
				<%-- <td><a href="${ctx}/log/pkgLog/form?log_id=${pkgLog.log_id}" title="${pkgLog.log_id}">${fns:abbr(pkgLog.log_id,40)}</a></td>--%>
				<td>${pkgLog.auth_name}</td>
				<td>${fns:getDictLabel(pkgLog.plat_code, 'plat_code', '未知')}</td>
				<%-- <td>${pkgLog.plat_code}</td> --%>
				<td>${pkgLog.trade_code}</td>
				<td>${pkgLog.trade_time}</td>
				<td>${fns:abbr(pkgLog.pkg_up,20)}</td>
				<td>${fns:abbr(pkgLog.pkg_down,20)}</td>
				<%-- <td>${pkgLog.protocol}</td> --%>
				<td>${fns:getDictLabel(pkgLog.protocol, 'commtype', '未知')}</td>
				<td>
    				<a href="${ctx}/log/pkgLog/detail?log_id=${pkgLog.log_id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>