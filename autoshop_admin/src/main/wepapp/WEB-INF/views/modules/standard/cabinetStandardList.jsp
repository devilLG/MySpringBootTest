<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规格管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出规格信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/standard/cabinetStandard/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
	        });
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/standard/cabinetStandard/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/standard/cabinetStandard/">规格配置列表</a></li>
		<shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/standard/cabinetStandard/form">规格配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cabinetStandard" action="${ctx}/standard/cabinetStandard/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>配置标识：</label><form:input path="cabconfig_id" htmlEscape="false" maxlength="30" class="input-medium"/></li>
			<li><label>配置名称：</label><form:input path="cabconfig_name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>编号</th>
		<th>柜子配置标识</th>
		<th>柜子配置名称</th>
		<th>柜子类型编号</th>
		<th>柜子类型名称</th>
		<th>排列列数</th>
		<th>创建时间</th>
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="cabinetStandard">
			<tr>
				<td><a href="${ctx}/standard/cabinetStandard/form?logid=${cabinetStandard.logid}" title="${cabinetStandard.logid}">${fns:abbr(cabinetStandard.logid,20)}</a></td>
				<td>${cabinetStandard.cabconfig_id}</td>
				<td>${cabinetStandard.cabconfig_name}</td>
				<td>${cabinetStandard.cabinettype_id}</td>
				<td>${cabinetStandard.cabinettype_name}</td>
				<td>${cabinetStandard.column_location}</td>
				<td>${cabinetStandard.create_time}</td>
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/standard/cabinetStandard/form?logid=${cabinetStandard.logid}">修改</a>
					<a href="${ctx}/standard/cabinetStandard/delete?logid=${cabinetStandard.logid}${cabinetStandard.delFlag ne 0?'&isRe=true':''}" onclick="return confirmx('确认要${cabinetStandard.delFlag ne 0?'恢复':''}删除该配置吗？', this.href)" >${cabinetStandard.delFlag ne 0?'恢复':''}删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>