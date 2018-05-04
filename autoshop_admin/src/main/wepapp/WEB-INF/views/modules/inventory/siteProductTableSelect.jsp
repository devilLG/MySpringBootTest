<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>商品信息</title>
		<meta name="decorator" content="default" />
		<style>
			#inventoryProduct_table li {line-height:25px;margin:2px 5px;cursor:pointer;list-style:none;}
			#inventoryProduct_table i {margin:1px 5px;font-size:16px;}
			#inventoryProduct_table li:hover {background-color:#DCDCDC;}
	        #inventoryProduct_table li.active {background-color:#0088CC;color:#ffffff;}
		</style>
	</head>
	<body>
		<form:form id="searchForm" modelAttribute="inventoryProduct" action="${ctx}/inventory/inventoryProduct/showInventoryProductInfo" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
		<form:hidden path="productType_id"/>
			<li><label>商品名称：</label><form:input path="product_name" htmlEscape="false" maxlength="50" class="input-large" style="width:350px"/></li>
			<li><label>商品条码：</label><form:input path="bar_code" htmlEscape="false" maxlength="13" class="input-large"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
		</ul>
		</form:form>
		<input type="hidden" id="inventoryProduct_parms" />
		<table id="inventoryProduct_table" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<tr>
					<th>商品编码</th>
					<th>商品名称</th>
					<th>商品型号</th>
					<th>商品条码</th>
					<th>商品分类</th>
					<th>温度环境</th>
					<th>标准价（元）</th>
					<th style="text-align: center; width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="inventoryProduct">
					<tr>
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
			  	<td title="${inventoryProduct.product_model}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(inventoryProduct.product_model)>12}">
				  	      ${fn:substring(inventoryProduct.product_model,0,12)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${inventoryProduct.product_model}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${inventoryProduct.bar_code}</td>
				<td>${inventoryProduct.productType_name}</td>
				<td>${fns:getDictLabel(inventoryProduct.temper_type, 'temper_type', '未知')}</td>
				<td>${inventoryProduct.normal_price}</td>
						<td style="text-align: center;">
							<li>
								<i class="icon-ok magnifying"></i>
								<input type="hidden" value="${inventoryProduct.product_id},${inventoryProduct.product_name},${inventoryProduct.bar_code},${inventoryProduct.product_title},${inventoryProduct.normal_price},${inventoryProduct.detail_url},${inventoryProduct.corp_id},${inventoryProduct.corp_name},${inventoryProduct.temper_type}">
							</li>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
				$(document).ready(function(){
			    	$("#inventoryProduct_table li").click(function(){
			    		debugger;
			    		$("#inventoryProduct_table li").removeClass("active");
	    				$("#inventoryProduct_table li i").removeClass("icon-white");
			    		$(this).addClass("active");
	    				$(this).children("i").addClass("icon-white");
			    		$("#inventoryProduct_parms").val($(this).children("input").val());
			    	});
			    	$("#inventoryProduct_table li").dblclick(function(){
			    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
			    	});
			    });
		</script>
	</body>
</html>