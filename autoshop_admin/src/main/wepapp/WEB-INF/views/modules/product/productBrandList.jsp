<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品品牌</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出商品品牌数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/product/productBrand/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/product/productBrand/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/productBrand/list">商品品牌列表</a></li>
		<li><a href="${ctx}/product/productBrand/form">商品品牌添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="productBrand" action="${ctx}/product/productBrand/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>品牌名称：</label><form:input path="brand_name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>品牌公司：</label><form:input path="brandCorp_name" htmlEscape="false" maxlength="100" class="input-medium"/></li>
			<li><label>所属行业：</label><form:input path="industry" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>品牌名称</th><th>品牌公司</th><th>成立时间</th><th>所属行业</th><th>创建时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="productBrand">
			<tr>
				<td>${productBrand.brand_name}</td>
				<td>${productBrand.brandCorp_name}</td>
				<td>${productBrand.establish_time}</td>
				<td>${productBrand.industry}</td>
				<td>${productBrand.create_time}</td>
		        <td>
		        	<a href="${ctx}/product/productBrand/form?id=${productBrand.logid}">修改</a>
					<a href="${ctx}/product/productBrand/delete?id=${productBrand.logid}" onclick="return confirmx('确认要删除该商品品牌吗？', this.href)">删除</a>
					<a href="${ctx}/product/productBrand/detail?id=${productBrand.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>