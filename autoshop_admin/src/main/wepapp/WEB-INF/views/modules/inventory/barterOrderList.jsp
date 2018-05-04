<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点换货</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出站点换货数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/inventory/barterOrder/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/inventory/barterOrder/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/barterOrder/list">站点换货列表</a></li>
		<li><a href="${ctx}/inventory/barterOrder/form">站点换货添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="barterOrder" action="${ctx}/inventory/barterOrder/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${barterOrder.site_id}" labelName="site_name" labelValue="${barterOrder.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/></li>
			 <li><label>换货日期：</label><input id="beginTime_barterOrderList" style="width:110px" name="beginTime" value="${barterOrder.beginTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime_barterOrderList\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								-
								<input id="endTime_barterOrderList" style="width:110px" name="endTime" value="${barterOrder.endTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime_barterOrderList\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li><label>换货人名称：</label><form:input path="barter_name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>换货编号</th><th>站点编号</th><th>站点简称</th><th>换货商品数量</th><th>换货种类数量</th><th>换货货道数量</th><th>换货日期</th><th>换货人名称</th><th>下发状态</th><th>状态</th><th>状态时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="barterOrder">
			<tr>
				<td>${barterOrder.order_id}</td>
				<td>${barterOrder.site_id}</td>
				<td title="${barterOrder.site_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(barterOrder.site_name)>6}">
				  	      ${fn:substring(barterOrder.site_name,0,6)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${barterOrder.site_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${barterOrder.barter_num}</td>
				<td>${barterOrder.barter_product}</td>
				<td>${barterOrder.barter_box}</td>
				<td>${barterOrder.barter_date}</td>
				<td>${barterOrder.barter_name}</td>
				<td>${fns:getDictLabel(barterOrder.down_state, 'down_state', '未知')}</td>
				<td>${fns:getDictLabel(barterOrder.barter_state, 'barter_state', '未知')}</td>
				<td>${barterOrder.state_time}</td>
		        <td>
		        	<a href="${ctx}/inventory/barterOrder/form?id=${barterOrder.logid}">修改</a>
					<a href="${ctx}/inventory/barterOrder/delete?id=${barterOrder.logid}" onclick="return confirmx('确认要删除该站点换货吗？', this.href)">删除</a>
					<a href="${ctx}/inventory/barterOrder/detail?id=${barterOrder.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>