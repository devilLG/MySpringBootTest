<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>版本升级管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出版本升级信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/version/ugradeLog/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
	      });
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/version/ugradeLog/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/version/ugradeLog/">版本升级列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ugradeLog" action="${ctx}/version/ugradeLog/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>平台代码：</label><form:input path="plat_code" htmlEscape="false" maxlength="30" class="input-medium"/></li>
			<li><label>站点编号：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${ugradeLog.site_id}" labelName="site_name" labelValue="${ugradeLog.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<li><label>平台版本：</label><form:input path="plat_version" htmlEscape="false" maxlength="30" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>编号</th>
		<th>区域编号</th>
		<th>区域名称</th>
		<th>站点编号</th>
		<th>站点名称</th>
		<th>平台代码</th>
		<th>平台名称</th>
		<th>平台版本</th>
		<th>升级版本</th>
		<th>升级状态</th>
		<th>状态时间</th>
		<th>异常代码</th>
		
		<tbody>
		<c:forEach items="${page.list}" var="ugradeLog">
			<tr>
				<td>${fns:abbr(ugradeLog.logid,10)}</a></td>
				<td>${ugradeLog.tree_id}</td>
				<td>${ugradeLog.tree_name}</td>
				<td>${ugradeLog.site_id}</td>
				<td>${ugradeLog.site_name}</td>
				<td>${ugradeLog.plat_code}</td>
				<td>${ugradeLog.plat_name}</td>
				<td>${ugradeLog.plat_version}</td>
				<td>${ugradeLog.newp_version}</td>
				<td>${fns:getDictLabel(ugradeLog.action_type, 'upgrade_state', '无')}</td>
				<td>${ugradeLog.create_time}</td>
				<td>${ugradeLog.ret_code}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>