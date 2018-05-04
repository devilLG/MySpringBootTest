<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点补货</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/replenishmentOrder/list">站点补货列表</a></li>
		<li class="active"><a href="${ctx}/inventory/replenishmentOrder/detail">详情</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#main" data-toggle="tab">补货主单信息</a></li>
		<li><a href="#sub" data-toggle="tab"><span>货道商品信息</span></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="replenishmentOrder" action="${ctx}/inventory/replenishmentOrder/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
		<div class="tab-pane fade in active" id="main">
		<div class="control-group">
			<label class="control-label">补货编号:</label>
			<div class="controls">
				<form:input path="order_id" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点编号:</label>
			<div class="controls">
				<form:input path="site_id" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
				<form:input path="site_name" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司编号:</label>
			<div class="controls">
				<form:input path="corp_id" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司名称:</label>
			<div class="controls">
				<form:input path="corp_name" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺货商品数量:</label>
			<div class="controls">
				<form:input path="lack_num" htmlEscape="false"  readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺货种类数量:</label>
			<div class="controls">
				<form:input path="lack_product" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺货货道数量:</label>
			<div class="controls">
				<form:input path="lack_box" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">补货商品数量:</label>
			<div class="controls">
				<form:input path="replen_num" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">补货种类数量:</label>
			<div class="controls">
				<form:input path="replen_product" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">补货货道数量:</label>
			<div class="controls">
				<form:input path="replen_box" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">补货日期:</label>
			<div class="controls">
				<form:input path="replen_date" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">补货人名称:</label>
			<div class="controls">
				<form:input path="replener_name" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单据类型:</label>
			<div class="controls">
				<form:input path="order_type" value="${fns:getDictLabel(replenishmentOrder.order_type, 'replenishment_order_type', '未知')}" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:input path="cur_state" value="${fns:getDictLabel(replenishmentOrder.cur_state, 'replenishment_cur_state', '未知')}" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态时间:</label>
			<div class="controls">
				<form:input path="state_time" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
				<form:input path="create_time" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		</div>
		<div class="tab-pane fade" id="sub">
				 <table class="table table-striped table-bordered table-condensed">
				    <thead>
				    <tr>
						<th>货道号</th>
						<th>商品编码</th>
						<th>商品名称</th>
						<th>剩余数量</th>
						<th>补货数量</th>
						<th>售卖数量</th>
						<th>过期数量</th>
						<th>过期时间</th>
						<th>状态</th>
						<th>状态时间</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${replenishmentOrderDetailList}" var="replenishmentOrderDetail">
						<tr>
							<td>${replenishmentOrderDetail.box_id}</td>
							<td>${replenishmentOrderDetail.product_id}</td>
							<td title="${replenishmentOrderDetail.product_name}">
						  	     <c:choose>
							  	   <c:when test="${fn:length(replenishmentOrderDetail.product_name)>12}">
							  	      ${fn:substring(replenishmentOrderDetail.product_name,0,12)}...
							  	   </c:when>
							  	   <c:otherwise>
							  	      ${replenishmentOrderDetail.product_name}
							  	   </c:otherwise>
							  	 </c:choose>
						  	</td>
							<td>${replenishmentOrderDetail.surplus_num}</td>
							<td>${replenishmentOrderDetail.replen_num}</td>
							<td>${replenishmentOrderDetail.sold_num}</td>
							<td>${replenishmentOrderDetail.overdue_num}</td>
							<td>${replenishmentOrderDetail.invalid_date}</td>
							<td>${fns:getDictLabel(replenishmentOrderDetail.cur_state, 'replenishmentDetail_cur_state', '未知')}</td>
							<td>${replenishmentOrderDetail.state_time}</td>
						</tr>
					</c:forEach>
					</tbody>
				 </table>
		</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>