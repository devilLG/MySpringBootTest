<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点控制管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出站点控制信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/site/siteCmd/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			});
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/site/siteCmd/list");
			$("#searchForm").submit();
	    	return false;
	    }  
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/site/siteCmd/">站点控制列表</a></li>
		<shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/site/siteCmd/form">站点控制添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="siteCmd" action="${ctx}/site/siteCmd/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${siteCmd.site_id}" labelName="site_name" labelValue="${siteCmd.site_name}"
			 title="站点简称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<label>控制类型：</label><form:select path="cmd_type" class="input-medium">
			        <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('cmd_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>命令编号</th>
		<th>站点编号</th>
		<th>站点简称</th>
		<th>控制对象编号</th>
		<th>控制对象</th>
		<th>控制类型</th>
		<th>下发状态</th>
		<th>下发时间</th>
		<th>创建时间</th>
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="siteCmd">
			<tr>
				<td><a href="${ctx}/site/siteCmd/form?cmd_id=${siteCmd.cmd_id}" title="${siteCmd.cmd_id}">${fns:abbr(siteCmd.cmd_id,40)}</a></td>
				<td>${siteCmd.site_id}</td>
				<td>${siteCmd.site_name}</td>
				<td>${siteCmd.cmd_val}</td>
				<td>${fns:getDictLabel(siteCmd.cmd_obj, 'cmd_obj', '无')}</td>
				<td>${fns:getDictLabel(siteCmd.cmd_type, 'cmd_type', '无')}</td>
				<td>${fns:getDictLabel(siteCmd.down_state, 'down_state', '无')}</td>
				<td>${siteCmd.down_time}</td>
				<td>${siteCmd.create_time}</td>
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/site/siteCmd/form?cmd_id=${siteCmd.cmd_id}">修改</a>
					<a href="${ctx}/site/siteCmd/delete?cmd_id=${siteCmd.cmd_id}${siteCmd.delFlag ne 0?'&isRe=true':''}" onclick="return confirmx('确认要${siteCmd.delFlag ne 0?'恢复':''}删除该站点控制吗？', this.href)" >${siteCmd.delFlag ne 0?'恢复':''}删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>