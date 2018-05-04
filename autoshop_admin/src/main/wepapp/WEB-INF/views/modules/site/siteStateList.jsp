<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网络状态</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出网络状态数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/site/siteState/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/site/siteState/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/site/siteState/list">网络状态列表</a></li>
		<%-- <li><a href="${ctx}/site/siteState/form">网络状态添加</a></li>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="siteState" action="${ctx}/site/siteState/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${siteState.site_id}" labelName="site_name" labelValue="${siteState.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<label>网络状态：</label><form:select path="refresh_state" class="input-medium">
			        <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('refresh_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead><tr><th>站点编号</th><th>站点简称</th><th>网络状态</th><th>刷新时间</th><th>刷新次数</th><%-- <th>操作</th>--%></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="siteState">
			<c:if test="${siteState.refresh_state=='1'}">
				<tr style="background-color:#FF9999">
				<td>${siteState.site_id}</td>
				<td>${siteState.site_name}</td>
				<td>${fns:getDictLabel(siteState.refresh_state, 'refresh_state', '无')}</td>
				<td>${siteState.refresh_time}</td>
				<td>${siteState.refresh_num}</td>
				</tr>
			</c:if>
			<c:if test="${siteState.refresh_state!='1'}">
				<tr>
				<td>${siteState.site_id}</td>
				<td>${siteState.site_name}</td>
				<td>${fns:getDictLabel(siteState.refresh_state, 'refresh_state', '无')}</td>
				<td>${siteState.refresh_time}</td>
				<td>${siteState.refresh_num}</td>
				</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>