<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>错误码管理</title>
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
		<li class="active"><a href="${ctx}/parmter/errorCode/">错误码列表</a></li>
		<shiro:hasPermission name="site:assite:view">
		<li><a href="${ctx}/parmter/errorCode/form">错误码添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="errorCode" action="${ctx}/parmter/errorCode/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label>错误序号 ：</label>
		<form:input path="err_id" htmlEscape="false" maxlength="10" class="input-medium"/>
		<label>错误代码：</label>
		<form:input path="err_code" htmlEscape="false" maxlength="10" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th>错误序号</th>
		<th>错误类型</th>
		<th>错误代码</th>
		<th>错误描述</th>
		<th>严重程度</th>
		<th>减除标识</th>
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="errorCode">
			<tr>
				<td>${errorCode.err_id}</td>
				<td>${errorCode.err_type}</td>
				<td>${errorCode.err_code}</td>
				<td>${errorCode.err_desc}</td>
				<td>${fns:getDictLabel(errorCode.err_level, 'err_level', '未知')}</td>
				<td>${fns:getDictLabel(errorCode.err_box, 'is_need', '未知')}</td>
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/parmter/errorCode/form?logid=${errorCode.logid}">修改</a>
					<a href="${ctx}/parmter/errorCode/delete?logid=${errorCode.logid}" onclick="return confirmx('确认要删除该错误码吗？', this.href)">删除</a>
    				
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>