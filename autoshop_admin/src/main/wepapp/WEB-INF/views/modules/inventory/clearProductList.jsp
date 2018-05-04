<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>货道清除</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出货道清除数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/inventory/clearProduct/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/inventory/clearProduct/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/clearProduct/list">货道清除列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="clearProduct" action="${ctx}/inventory/clearProduct/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${clearProduct.site_id}" labelName="site_name" labelValue="${clearProduct.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/></li>
			 <li><label>清除日期：</label><input id="beginTime_clearProductList" style="width:110px" name="beginTime" value="${clearProduct.beginTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime_clearProductList\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							-
							<input id="endTime_clearProductList" style="width:110px" name="endTime" value="${clearProduct.endTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime_clearProductList\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li><label>清除人名称：</label><form:input path="clear_name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>清除编号</th><th>站点编号</th><th>站点简称</th><th>货道号</th><th>商品编码</th><th>商品名称</th><th>条码</th><th>清除数量</th><th>终端库存</th><th>清除日期</th><th>清除人名称</th><th>创建时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="clearProduct">
			<tr>
				<td>${clearProduct.order_id}</td>
				<td>${clearProduct.site_id}</td>
				<td title="${clearProduct.site_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(clearProduct.site_name)>6}">
				  	      ${fn:substring(clearProduct.site_name,0,6)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${clearProduct.site_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${clearProduct.box_id}</td>
				<td>${clearProduct.product_id}</td>
				<td title="${clearProduct.product_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(clearProduct.product_name)>10}">
				  	      ${fn:substring(clearProduct.product_name,0,10)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${clearProduct.product_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${clearProduct.bar_code}</td>
				<td>${clearProduct.clear_num}</td>
				<td>${clearProduct.inventory_num}</td>
				<td>${clearProduct.clear_date}</td>
				<td>${clearProduct.clear_name}</td>
				<td>${clearProduct.create_time}</td>
		        <td>
					<a href="${ctx}/inventory/clearProduct/detail?id=${clearProduct.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>