<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单推送</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/person/siteControlLog/orderPushList");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/person/siteControlLog/orderPushList">订单推送列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="siteControlLog" action="${ctx}/person/siteControlLog/orderPushList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${siteControlLog.site_id}" labelName="site_name" labelValue="${siteControlLog.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<li><label>订单编号：</label><form:input path="issued_key" htmlEscape="false" maxlength="30" class="input-medium" style="width:250px"/></li>
			<li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
		<th>站点编号</th>
		<th>站点简称</th>
		<th>订单编号</th>
		<th>状态</th>
		<th>创建时间</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="siteControlLog">
			<tr>
				<td>${siteControlLog.site_id}</a></td>
				<td>${siteControlLog.site_name}</td>
				<td>${siteControlLog.issued_key}</td>
				<td>${fns:getDictLabel(siteControlLog.cur_state, 'siteControlLog_state', '无')}</td>
				<td>${siteControlLog.create_time}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>