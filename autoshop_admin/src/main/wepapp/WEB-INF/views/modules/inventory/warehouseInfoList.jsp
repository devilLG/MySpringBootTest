<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>虚拟仓库</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出虚拟仓库数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/inventory/warehouseInfo/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/inventory/warehouseInfo/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/warehouseInfo/list">虚拟仓库列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="warehouseInfo" action="${ctx}/inventory/warehouseInfo/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>公司名称：</label><sys:treeselect id="company" name="corp_id" value="${warehouseInfo.corp_id}" labelName="corp_name" labelValue="${warehouseInfo.corp_name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>公司编号</th><th>公司名称</th><th>商品种类</th><th>库存总数</th><th>正常库存数</th><th>在途库存数</th><th>回收库存数</th><th>过期库存数</th><th>创建时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="warehouseInfo">
			<tr>
				<td>${warehouseInfo.corp_id}</td>
				<td>${warehouseInfo.corp_name}</td>
				<td>${warehouseInfo.classify_total}</td>
				<td>${warehouseInfo.inventory_total}</td>
				<td>${warehouseInfo.inventory_num}</td>
				<td>${warehouseInfo.inventory_transit}</td>
				<td>${warehouseInfo.inventory_renum}</td>
				<td>${warehouseInfo.overdue_num}</td>
				<td>${warehouseInfo.create_time}</td>
		        <td>
					<a href="${ctx}/inventory/warehouseInfo/detail?id=${warehouseInfo.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>