<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点补货</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出站点补货数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/inventory/replenishmentOrder/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/inventory/replenishmentOrder/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/replenishmentOrder/list">站点补货列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="replenishmentOrder" action="${ctx}/inventory/replenishmentOrder/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${replenishmentOrder.site_id}" labelName="site_name" labelValue="${replenishmentOrder.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/></li>
			 <li><label>补货日期：</label><input id="beginTime_replenishmentOrderList" style="width:110px" name="beginTime" value="${replenishmentOrder.beginTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime_replenishmentOrderList\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								-
								<input id="endTime_replenishmentOrderList" style="width:110px" name="endTime" value="${replenishmentOrder.endTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime_replenishmentOrderList\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li><label>补货人名称：</label><form:input path="replener_name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>补货编号</th><th>站点编号</th><th>站点简称</th><th>缺货商品数量</th><th>缺货种类数量</th><th>缺货货道数量</th><th>补货商品数量</th><th>补货种类数量</th><th>补货货道数量</th><th>补货日期</th><th>补货人名称</th>
		<!-- <th>单据类型</th> -->
		<th>状态</th><th>状态时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="replenishmentOrder">
			<tr>
				<td>${replenishmentOrder.order_id}</td>
				<td>${replenishmentOrder.site_id}</td>
				<td title="${replenishmentOrder.site_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(replenishmentOrder.site_name)>6}">
				  	      ${fn:substring(replenishmentOrder.site_name,0,6)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${replenishmentOrder.site_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${replenishmentOrder.lack_num}</td>
				<td>${replenishmentOrder.lack_product}</td>
				<td>${replenishmentOrder.lack_box}</td>
				<td>${replenishmentOrder.replen_num}</td>
				<td>${replenishmentOrder.replen_product}</td>
				<td>${replenishmentOrder.replen_box}</td>
				<td>${replenishmentOrder.replen_date}</td>
				<td>${replenishmentOrder.replener_name}</td>
				<%-- <td>${fns:getDictLabel(replenishmentOrder.order_type, 'replenishment_order_type', '未知')}</td> --%>
				<td>${fns:getDictLabel(replenishmentOrder.cur_state, 'replenishment_cur_state', '未知')}</td>
				<td>${replenishmentOrder.state_time}</td>
		        <td>
					<a href="${ctx}/inventory/replenishmentOrder/detail?id=${replenishmentOrder.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>