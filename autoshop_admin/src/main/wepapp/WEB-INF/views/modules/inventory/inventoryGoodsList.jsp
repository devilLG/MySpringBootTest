<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>货道库存</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出货道库存数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/inventory/inventoryGoods/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/inventory/inventoryGoods/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/inventoryGoods/list">货道库存列表</a></li>
		<li><a href="${ctx}/inventory/inventoryGoods/form">货道库存添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="inventoryGoods" action="${ctx}/inventory/inventoryGoods/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${inventoryGoods.site_id}" labelName="site_name" labelValue="${inventoryGoods.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/></li>
			<li>&nbsp;&nbsp;&nbsp;<label>货道号：</label><form:input path="box_id" htmlEscape="false" maxlength="30" class="input-small"/></li>
			<li><label>库存状态：</label><form:select path="inventory_state" class="input-small">
								<form:option value="" label="请选择"/>
								<form:options items="${fns:getDictList('inventory_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
			<li><label>商品条码：</label><form:input path="bar_code" htmlEscape="false" maxlength="13" class="input-large"/></li>
			<li><label>商品名称：</label><form:input path="product_name" htmlEscape="false" maxlength="50" class="input-large" style="width:580px"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>站点编号</th><th>站点简称</th>
		<!-- <th>柜号</th><th>列数</th><th>层数</th> -->
		<th>货道号</th><th>条码</th><th>商品名称</th><th>温度环境</th><th>库存总数</th><th>可售库存数</th><th>在途数量</th><th>过期数量</th><th>回收数量</th><th>库存状态</th><th>缺货临界值</th><th>有过期商品</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="inventoryGoods">
			<tr>
				<td>${inventoryGoods.site_id}</td>
				<td>${inventoryGoods.site_name}</td>
				<%-- <td>${inventoryGoods.cabinet_id}</td>
				<td>${inventoryGoods.cloumn_id}</td>
				<td>${inventoryGoods.layer_num}</td> --%>
				<td>${inventoryGoods.box_id}</td>
				<td>${inventoryGoods.bar_code}</td>
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
			  	<td>${fns:getDictLabel(inventoryGoods.temper_type, 'temper_type', '未知')}</td>
				<td>${inventoryGoods.inventory_total}</td>
				<td>${inventoryGoods.inventory_num}</td>
				<td>${inventoryGoods.inventory_transit}</td>
				<td>${inventoryGoods.overdue_num}</td>
				<td>${inventoryGoods.inventory_recovery}</td>
				<td>${fns:getDictLabel(inventoryGoods.inventory_state, 'inventory_state', '未知')}</td>
				<td>${inventoryGoods.warn_num}</td>
				<td>${fns:getDictLabel(inventoryGoods.is_overdue, 'is_overdue', '未知')}</td>
		        <td>
					<a href="${ctx}/inventory/inventoryGoods/form?id=${inventoryGoods.logid}">修改</a>
					<%-- <a href="${ctx}/inventory/inventoryGoods/delete?id=${inventoryGoods.logid}" onclick="return confirmx('确认要删除该商品信息吗？', this.href)">删除</a> --%>
					<a href="${ctx}/inventory/inventoryGoods/detail?id=${inventoryGoods.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>