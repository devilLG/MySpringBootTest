<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点库存</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出站点库存数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/inventory/inventoryProduct/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/inventory/inventoryProduct/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/inventoryProduct/list">站点库存列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="inventoryProduct" action="${ctx}/inventory/inventoryProduct/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${inventoryProduct.site_id}" labelName="site_name" labelValue="${inventoryProduct.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<li><label>商品条码：</label><form:input path="bar_code" htmlEscape="false" maxlength="13" class="input-large"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
			<li><label>商品名称：</label><form:input path="product_name" htmlEscape="false" maxlength="50" class="input-large" style="width:580px"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>站点编号</th><th>站点简称</th><th>商品编码</th><th>商品名称</th><th>商品条码</th><th>商品分类</th><th>库存总数</th><th>正常库存数</th><th>在途库存数</th><th>回收库存数</th><th>过期库存数</th><th>创建时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="inventoryProduct">
			<tr>
				<td>${inventoryProduct.site_id}</td>
				<td title="${inventoryProduct.site_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(inventoryProduct.site_name)>6}">
				  	      ${fn:substring(inventoryProduct.site_name,0,6)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${inventoryProduct.site_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${inventoryProduct.product_id}</td>
				<td title="${inventoryProduct.product_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(inventoryProduct.product_name)>12}">
				  	      ${fn:substring(inventoryProduct.product_name,0,12)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${inventoryProduct.product_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${inventoryProduct.bar_code}</td>
				<td>${inventoryProduct.productType_name}</td>
				<td>${inventoryProduct.inventory_total}</td>
				<td>${inventoryProduct.inventory_num}</td>
				<td>${inventoryProduct.inventory_transit}</td>
				<td>${inventoryProduct.inventory_renum}</td>
				<td>${inventoryProduct.overdue_num}</td>
				<td>${inventoryProduct.create_time}</td>
		        <td>
					<a href="${ctx}/inventory/inventoryProduct/destroy?id=${inventoryProduct.logid}" onclick="return confirmx('确认要销毁已回收库存吗？', this.href)">销毁</a>
					<a href="${ctx}/inventory/inventoryProduct/detail?id=${inventoryProduct.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>