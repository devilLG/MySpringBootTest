<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品优惠</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出商品优惠数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/product/productFavourable/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/product/productFavourable/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	    
	    function checkEdit(cur_state,href){ 
	 	  if(cur_state == '0'){
	 	 	 window.location .href=href;
	 	  }else{
	 	    toastr.warning("只能修改待执行状态的商品优惠");
			return false;
	 	  }
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/productFavourable/list">商品优惠列表</a></li>
		<li><a href="${ctx}/product/productFavourable/form?favourable_type=02">按商品优惠</a></li>
		<li><a href="${ctx}/product/productFavourable/form?favourable_type=01">按网点优惠</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="productFavourable" action="${ctx}/product/productFavourable/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>商品名称：</label><form:input path="product_name" htmlEscape="false" maxlength="50" class="input-large" style="width:580px"/></li>
			<li><label>商品条码：</label><form:input path="bar_code" htmlEscape="false" maxlength="13" class="input-large"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>优惠对象</th><th>优惠类型</th><th>商品编码</th><th>商品名称</th><th>商品条码</th><th>优惠金额（元）</th><th>恢复金额（元）</th><th>开始日期</th><th>结束日期</th><th>状态</th><th>创建时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="productFavourable">
			<tr>
				<td>${productFavourable.site_id}</td>
				<td>${fns:getDictLabel(productFavourable.favourable_type, 'favourable_type', '未知')}</td>
				<td>${productFavourable.product_id}</td>
				<td title="${productFavourable.product_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(productFavourable.product_name)>12}">
				  	      ${fn:substring(productFavourable.product_name,0,12)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${productFavourable.product_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${productFavourable.bar_code}</td>
				<td>${productFavourable.favourable_price}</td>
				<td>${productFavourable.nomarl_price}</td>
				<td>${productFavourable.favourable_stime}</td>
				<td>${productFavourable.favourable_etime}</td>
				<td>${fns:getDictLabel(productFavourable.cur_state, 'product_favourable_state', '未知')}</td>
				<td>${productFavourable.create_time}</td>
		        <td>
		        	<a href="${ctx}/product/productFavourable/form?id=${productFavourable.logid}" onclick="return checkEdit('${productFavourable.cur_state}', this.href)">修改</a>
					<a href="${ctx}/product/productFavourable/delete?id=${productFavourable.logid}" onclick="return confirmx('确认要删除该商品优惠吗？', this.href)">删除</a>
					<a href="${ctx}/product/productFavourable/detail?id=${productFavourable.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>