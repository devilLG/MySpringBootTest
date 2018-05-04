<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点盘点</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/checkOrder/list">站点盘点列表</a></li>
		<li class="active"><a href="${ctx}/inventory/checkOrder/detail">详情</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#main" data-toggle="tab">盘点主单信息</a></li>
		<li><a href="#sub" data-toggle="tab"><span>盘点详情</span></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="checkOrder" action="${ctx}/inventory/checkOrder/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
		<div class="tab-pane fade in active" id="main">
		<div class="control-group">
			<label class="control-label">盘点编号:</label>
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
			<label class="control-label">盘点商品数量:</label>
			<div class="controls">
				<form:input path="check_num" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">盘点种类数量:</label>
			<div class="controls">
				<form:input path="check_product" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">盘点货道数量:</label>
			<div class="controls">
				<form:input path="check_box" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">盘点日期:</label>
			<div class="controls">
				<form:input path="check_date" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">盘点人名称:</label>
			<div class="controls">
				<form:input path="check_name" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:input path="check_state" value="${fns:getDictLabel(checkOrder.check_state, 'check_state', '未知')}" htmlEscape="false" readonly="true"/>
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
						<th>实际盘点数量</th>
						<th>终端库存</th>
						<th>服务器库存</th>
						<th>实际盘存与终端库存数量差</th>
						<th>盘点类型</th>
						<th>状态</th>
						<th>状态时间</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${checkOrderDetailList}" var="checkOrderDetail">
						<tr>
							<td>${checkOrderDetail.box_id}</td>
							<td>${checkOrderDetail.product_id}</td>
							<td title="${checkOrderDetail.product_name}">
						  	     <c:choose>
							  	   <c:when test="${fn:length(checkOrderDetail.product_name)>12}">
							  	      ${fn:substring(checkOrderDetail.product_name,0,12)}...
							  	   </c:when>
							  	   <c:otherwise>
							  	      ${checkOrderDetail.product_name}
							  	   </c:otherwise>
							  	 </c:choose>
						  	</td>
							<td>${checkOrderDetail.check_num}</td>
							<td>${checkOrderDetail.tinventory_num}</td>
							<td>${checkOrderDetail.winventory_num}</td>
							<td>${checkOrderDetail.difference_num}</td>
							<td>${fns:getDictLabel(checkOrderDetail.check_type, 'check_type', '未知')}</td>
							<td>${fns:getDictLabel(checkOrderDetail.cur_state, 'check_state', '未知')}</td>
							<td>${checkOrderDetail.state_time}</td>
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