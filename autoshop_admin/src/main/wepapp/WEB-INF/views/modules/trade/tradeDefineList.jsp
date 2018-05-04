 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/trade/tradeDefine/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/trade/tradeDefine/">交易列表</a></li>
		<shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/trade/tradeDefine/form">交易添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tradeDefine" action="${ctx}/trade/tradeDefine/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>平台代码：</label><form:input path="plat_code" htmlEscape="false" maxlength="2" class="input-medium"/></li>
			<li><label>交易码：</label><form:input path="trade_code" htmlEscape="false" maxlength="10" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<li class="clearfix"></li>
		
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>交易编号</th>
		<th>平台代码</th>
		<th>交易名称</th>
		<th>交易描述</th>
		<th>交易状态</th>
		<th>交易日志</th>
		<th>报文日志</th>
		<th>使用方式</th>
		
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="tradeDefine">
			<tr>
				<td><a href="${ctx}/trade/tradeDefine/form?trade_code=${tradeDefine.trade_code}" title="${tradeDefine.trade_code}">${fns:abbr(tradeDefine.trade_code,40)}</a></td>
				<td>${tradeDefine.plat_code}</td>
				<td>${tradeDefine.trade_name}</td>
				<td>${tradeDefine.trade_desc}</td>
				<td>${tradeDefine.trade_state}</td>
				<td>${tradeDefine.trade_log}</td>
				<td>${tradeDefine.pkg_log}</td>
				<td>${tradeDefine.use_type}</td>
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/trade/tradeDefine/form?trade_code=${tradeDefine.trade_code}">修改</a>
					<a href="${ctx}/trade/tradeDefine/delete?trade_code=${tradeDefine.trade_code}${tradeDefine.delFlag ne 0?'&isRe=true':''}" onclick="return confirmx('确认要${tradeDefine.delFlag ne 0?'恢复':''}删除该交易吗？', this.href)" >${tradeDefine.delFlag ne 0?'恢复':''}删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>