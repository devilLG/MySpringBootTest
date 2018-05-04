<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单状态</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出订单状态数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/order/orderChange/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/order/orderChange/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/orderChange/list">订单状态列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderChange" action="${ctx}/order/orderChange/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>订单编号：</label><form:input path="order_id" htmlEscape="false" maxlength="30" class="input-large" style="width:250px"/></li>
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${orderChange.site_id}" labelName="site_name" labelValue="${orderChange.site_name}" title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<li class="clearfix"></li>
			<li><label>状态：</label><form:select path="oper_action" class="input-small">
								<form:option value="" label="请选择"/>
								<form:options items="${fns:getDictList('order_change_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select></li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <label>状态时间：</label><input id="beginTime_orderChangeList" style="width:110px" name="beginTime" value="${orderChange.beginTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								-
								<input id="endTime_orderChangeList" style="width:110px" name="endTime" value="${orderChange.endTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime_orderChangeList\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>订单编号</th><th>站点编号</th><th>站点简称</th><th>操作状态</th><th>操作人名称</th><th>操作时间</th><th>推送状态</th><th>推送次数</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderChange">
			<tr>
				<td>${orderChange.order_id}</td>
				<td>${orderChange.site_id}</td>
				<td title="${orderChange.site_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(orderChange.site_name)>12}">
				  	      ${fn:substring(orderChange.site_name,0,12)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${orderChange.site_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<%-- <td>${orderChange.bar_code}</td> --%>
				<td>${fns:getDictLabel(orderChange.oper_action, 'order_change_state', '未知')}</td>
				<td>${orderChange.oper_name}</td>
				<td>${orderChange.oper_time}</td>
				<td>${fns:getDictLabel(orderChange.poc_state, 'orderChange_push_state', '未知')}</td>
				<td>${orderChange.poc_times}</td>
		        <td>
					<a href="${ctx}/order/orderChange/detail?id=${orderChange.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>