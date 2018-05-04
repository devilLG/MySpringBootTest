<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>商品信息</title>
		<meta name="decorator" content="default" />
		<style>
			#inventoryGoods_table li {line-height:25px;margin:2px 5px;cursor:pointer;list-style:none;}
			#inventoryGoods_table i {margin:1px 5px;font-size:16px;}
			#inventoryGoods_table li:hover {background-color:#DCDCDC;}
	        #inventoryGoods_table li.active {background-color:#0088CC;color:#ffffff;}
		</style>
	</head>
	<body>
		<form:form id="searchForm" modelAttribute="inventoryGoods" action="${ctx}/inventory/inventoryGoods/showInventoryGoodsInfo" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
		<form:hidden path="productType_id"/>
			<li><label>货道号：</label><form:input path="box_id" htmlEscape="false" maxlength="11" class="input-large"/></li>
			<%-- <li><label>商品名称：</label><form:input path="product_name" htmlEscape="false" maxlength="50" class="input-large" style="width:350px"/></li> --%>
			<li><label>商品条码：</label><form:input path="bar_code" htmlEscape="false" maxlength="13" class="input-large"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
		</ul>
		</form:form>
		<input type="hidden" id="inventoryGoods_parms" />
		<table id="inventoryGoods_table" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<tr>
					<th>货道号</th>
					<th>温度环境</th>
					<th>商品编码</th>
					<th>商品名称</th>
					<th>商品条码</th>
					<th style="text-align: center; width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="inventoryGoods">
					<tr>
						<td>${inventoryGoods.box_id}</td>
						<td>${fns:getDictLabel(inventoryGoods.temper_type, 'temper_type', '未知')}</td>
						<td>${inventoryGoods.product_id}</td>
						<td title="${inventoryGoods.product_name}">
					  	     <c:choose>
						  	   <c:when test="${fn:length(inventoryGoods.product_name)>12}">
						  	      ${fn:substring(inventoryGoods.product_name,0,12)}...
						  	   </c:when>
						  	   <c:otherwise>
						  	      ${inventoryGoods.product_name}
						  	   </c:otherwise>
						  	 </c:choose>
					  	</td>
						<td>${inventoryGoods.bar_code}</td>
						<td style="text-align: center;">
							<li>
								<i class="icon-ok magnifying"></i>
								<input type="hidden" value="${inventoryGoods.box_id},${inventoryGoods.product_id},${inventoryGoods.product_name},${inventoryGoods.bar_code},${inventoryGoods.corp_id},${inventoryGoods.corp_name},${inventoryGoods.temper_type}">
							</li>
						</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
				$(document).ready(function(){
			    	$("#inventoryGoods_table li").click(function(){
			    		debugger;
			    		$("#inventoryGoods_table li").removeClass("active");
	    				$("#inventoryGoods_table li i").removeClass("icon-white");
			    		$(this).addClass("active");
	    				$(this).children("i").addClass("icon-white");
			    		$("#inventoryGoods_parms").val($(this).children("input").val());
			    	});
			    	$("#inventoryGoods_table li").dblclick(function(){
			    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
			    	});
			    });
		</script>
	</body>
</html>