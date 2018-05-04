 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出货道类型信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/standard/boxType/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
	        });
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/standard/boxType/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/standard/boxType/">货道规格列表</a></li>
		<shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/standard/boxType/form">货道规格添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="boxType" action="${ctx}/standard/boxType/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
	
			<li><label>类型编号：</label><form:input path="type_id" htmlEscape="false" maxlength="20" class="input-medium"/></li>
			<li><label>类型简称：</label><form:input path="type_name" htmlEscape="false" maxlength="40" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>类型编号</th>
		<th>类型简称</th>
		<th>类型全称</th>
		<th>长度(cm)</th>
		<th>宽度(cm)</th>
		<th>高度(cm)</th>
		<th>容积(cm³)</th>
		<th>大号规格</th>
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="boxType">
			<tr>
				<td><a href="${ctx}/standard/boxType/form?type_id=${boxType.type_id}" title="${boxType.type_id}">${fns:abbr(boxType.type_id,40)}</a></td>
				<td>${boxType.type_name}</td>
				<td>${boxType.fullname}</td>
				<td>${boxType.size_length}</td>
				<td>${boxType.size_width}</td>
				<td>${boxType.size_height}</td>
				<td>${boxType.size_level}</td>
				<td>${boxType.exceed_type}</td>
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/standard/boxType/form?type_id=${boxType.type_id}">修改</a>
					<a href="${ctx}/standard/boxType/delete?type_id=${boxType.type_id}${boxType.delFlag ne 0?'&isRe=true':''}" onclick="return confirmx('确认要${boxType.delFlag ne 0?'恢复':''}删除该类型吗？', this.href)" >${boxType.delFlag ne 0?'恢复':''}删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>