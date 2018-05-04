 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/log/useLog/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/useLog/">终端日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="useLog" action="${ctx}/log/useLog/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<div>
			<li><label>站点信息：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${useLog.site_id}" labelName="site_name" labelValue="${useLog.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			
			<li><label>操作时间：</label><input id="oper_time_useLogList" style="width:110px" name="oper_time" value="${useLog.oper_time}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								-
								<input id="oper_time_end_useLogList" style="width:110px" name="oper_time_end" value="${useLog.oper_time_end}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'oper_time_useLogList\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
		</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><%--<th>日志编号</th>--%>
		<th>站点编号</th>
		<th>站点简称</th>
		<th>日志级别</th>
		<th>操作账户</th>
		<th>操作人名称</th>
		<th>操作码</th>
		<th>操作动作</th>
		<th>操作对象</th>
		<th>操作结果</th>
		<th>操作时间</th>
		<tbody>
		<c:forEach items="${page.list}" var="useLog">
			<tr>
				<%-- <td><a href="${ctx}/log/useLog/form?log_id=${useLog.log_id}" title="${useLog.log_id}">${fns:abbr(useLog.log_id,40)}</a></td>--%>
				<td>${useLog.site_id}</td>
				<td>${useLog.site_name}</td>
				<td>${useLog.oper_level}</td>
				<td>${useLog.oper_id}</td>
				<td>${useLog.oper_name}</td>
				<td>${useLog.oper_code}</td>
				<td>${useLog.oper_action}</td>
				<td>${useLog.oper_obj}</td>
				<td>${useLog.oper_ret}</td>
				<td>${useLog.oper_time}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>