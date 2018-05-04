<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出商品信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/product/productInfo/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/product/productInfo/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/productInfo/list">商品信息列表</a></li>
		<li><a href="${ctx}/product/productInfo/form">商品信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="productInfo" action="${ctx}/product/productInfo/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
		<form:hidden path="productType_id"/>
			<li><label>商品条码：</label><form:input path="bar_code" htmlEscape="false" maxlength="13" class="input-large"/></li>
			<li><label>标准价(元)：</label><form:input path="product_beginPrice" htmlEscape="false" maxlength="11" class="input-small"/>
			-
			<form:input path="product_endPrice" htmlEscape="false" maxlength="11" class="input-small"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
			<li><label>商品名称：</label><form:input path="product_name" htmlEscape="false" maxlength="50" class="input-large" style="width:580px"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>商品编码</th><th>商品名称</th><th>商品型号</th><th>商品条码</th><th>商品分类</th><th>标准价(元)</th><th>长(cm)</th><th>宽(cm)</th><th>高(cm)</th><th>创建时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="productInfo">
			<tr>
				<td>${productInfo.product_id}</td>
				<td title="${productInfo.product_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(productInfo.product_name)>12}">
				  	      ${fn:substring(productInfo.product_name,0,12)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${productInfo.product_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
			  	<td title="${productInfo.product_model}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(productInfo.product_model)>12}">
				  	      ${fn:substring(productInfo.product_model,0,12)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${productInfo.product_model}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${productInfo.bar_code}</td>
				<td>${productInfo.productType_name}</td>
				<td>${productInfo.normal_price}</td>
				<td>${productInfo.pro_length}</td>
				<td>${productInfo.pro_width}</td>
				<td>${productInfo.pro_height}</td>
				<td>${productInfo.create_time}</td>
		        <td>
    				<a href="${ctx}/product/productInfo/form?id=${productInfo.logid}">修改</a>
					<a href="${ctx}/product/productInfo/delete?id=${productInfo.logid}" onclick="return confirmx('确认要删除该商品信息吗？', this.href)">删除</a>
					<a href="${ctx}/product/productInfo/detail?id=${productInfo.logid}">详情</a>
					<a href="${ctx}/product/productInfo/image?id=${productInfo.logid}">图片管理</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>