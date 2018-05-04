<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参数管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/parmter/parameter/">参数列表</a></li>
		<shiro:hasPermission name="site:assite:view">
		<li><a href="${ctx}/parmter/parameter/form">参数添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="parameter" action="${ctx}/parmter/parameter/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label>参数名 ：</label>
		<form:input path="name" htmlEscape="false" maxlength="40" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th>参数名</th>
		<th>参数值</th>
		<th>参数类型</th>
		<th>参数描述</th>
		
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="parameter">
			<tr>
				<td>${parameter.name}</td>
				<td>${parameter.value}</td>
				<td>${parameter.para_type}</td>
				<td>${parameter.description}</td>
				
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/parmter/parameter/form?logid=${parameter.logid}">修改</a>
					<a href="${ctx}/parmter/parameter/delete?logid=${parameter.logid}" onclick="return confirmx('确认要删除该参数吗？', this.href)">删除</a>
    				
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>