<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点盘点</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出站点盘点数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/inventory/checkOrder/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/inventory/checkOrder/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/checkOrder/list">站点盘点列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="checkOrder" action="${ctx}/inventory/checkOrder/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${checkOrder.site_id}" labelName="site_name" labelValue="${checkOrder.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/></li>
			 <li><label>盘点日期：</label><input id="beginTime_checkOrderList" style="width:110px" name="beginTime" value="${checkOrder.beginTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime_checkOrderList\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								-
								<input id="endTime_checkOrderList" style="width:110px" name="endTime" value="${checkOrder.endTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime_checkOrderList\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li><label>盘点人名称：</label><form:input path="check_name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>盘点编号</th><th>站点编号</th><th>站点简称</th><th>盘点商品数量</th><th>盘点种类数量</th><th>盘点货道数量</th><th>盘点日期</th><th>盘点人名称</th><th>状态</th><th>状态时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="checkOrder">
			<tr>
				<td>${checkOrder.order_id}</td>
				<td>${checkOrder.site_id}</td>
				<td title="${checkOrder.site_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(checkOrder.site_name)>6}">
				  	      ${fn:substring(checkOrder.site_name,0,6)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${checkOrder.site_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${checkOrder.check_num}</td>
				<td>${checkOrder.check_product}</td>
				<td>${checkOrder.check_box}</td>
				<td>${checkOrder.check_date}</td>
				<td>${checkOrder.check_name}</td>
				<td>${fns:getDictLabel(checkOrder.check_state, 'check_state', '未知')}</td>
				<td>${checkOrder.state_time}</td>
		        <td>
					<a href="${ctx}/inventory/checkOrder/detail?id=${checkOrder.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>