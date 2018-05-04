<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选择商品</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#selectInventoryProductForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<form:form id="selectInventoryProductForm" modelAttribute="inventoryProduct" action="${ctx}/inventory/inventoryProduct/selectInventoryProduct?site_id=${inventoryProduct.site_id}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>商品名称：</label><form:input path="product_name" htmlEscape="false" class="input-medium" maxlength="50" />
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
		<input class="btn btn-warning" type="button" value="重置" onclick="myResult('selectInventoryProductForm');"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="selectInventoryProductTable" class="table table-striped table-bordered table-condensed" style="cellspacing:0,width:100%">
		<thead><tr>
		<th></th>
		<th>商品编码</th>
		<th>商品名称</th>
		<th>商品分类</th>
		<th>商品品牌</th>
		<th>标准价（元）</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="inventoryProduct">
			<tr>
				<td style="width: 20px; text-align: center;">
					<input name="logids" type="checkbox" value="${inventoryProduct.product_id};${inventoryProduct.product_name};${inventoryProduct.productType_name};${inventoryProduct.brand_name};${inventoryProduct.normal_price}">
				</td>
				<td>${inventoryProduct.product_id}</td>
				<td>${inventoryProduct.product_name}</td>
				<td>${inventoryProduct.productType_name}</td>
				<td>${inventoryProduct.brand_name}</td>
				<td>${inventoryProduct.normal_price}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>