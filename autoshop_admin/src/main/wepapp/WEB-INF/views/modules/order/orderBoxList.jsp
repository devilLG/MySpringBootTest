<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单货道</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出订单货道数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/order/orderBox/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/order/orderBox/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/orderBox/list">订单货道列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderBox" action="${ctx}/order/orderBox/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>订单编号：</label><form:input path="order_id" htmlEscape="false" maxlength="30" class="input-large" style="width:250px"/></li>
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${orderBox.site_id}" labelName="site_name" labelValue="${orderBox.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<li><label>状态时间：</label><input id="beginTime_orderBoxList" style="width:110px" name="beginTime" value="${orderBox.beginTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								-
								<input id="endTime_orderBoxList" style="width:110px" name="endTime" value="${orderBox.endTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime_orderBoxList\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="clearfix"></li>
			<li><label>商品编码：</label><form:input path="product_id" htmlEscape="false" maxlength="30" class="input-medium" style="width:250px"/></li>
			<li><label>货道号：</label><form:input path="box_id" htmlEscape="false" maxlength="11" class="input-medium"/></li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>状态：</label><form:select path="cur_state" class="input">
								<form:option value="" label="请选择"/>
								<form:options items="${fns:getDictList('order_box_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>订单编号</th><th>站点编号</th><th>站点简称</th><th>商品编码</th><th>商品名称</th><th>取货数量</th><th>货道号</th><th>货道类型</th><th>提货数量</th><th>状态</th><th>状态时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderBox">
			<tr>
				<td>${orderBox.order_id}</td>
				<td>${orderBox.site_id}</td>
				<td title="${orderBox.site_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(orderBox.site_name)>12}">
				  	      ${fn:substring(orderBox.site_name,0,12)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${orderBox.site_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${orderBox.product_id}</td>
				<td title="${orderBox.product_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(orderBox.product_name)>12}">
				  	      ${fn:substring(orderBox.product_name,0,12)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${orderBox.product_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${orderBox.take_num}</td>
				<td>${orderBox.box_id}</td>
				<td>${orderBox.box_type}</td>
				<td>${orderBox.altake_num}</td>
				<td>${fns:getDictLabel(orderBox.cur_state, 'order_box_state', '未知')}</td>
				<td>${orderBox.state_time}</td>
		        <td>
					<a href="${ctx}/order/orderBox/detail?id=${orderBox.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>