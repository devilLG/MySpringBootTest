<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单货道</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/order/orderBox/list">订单货道列表</a></li>
		<li class="active"><a href="${ctx}/order/orderBox/detail">详情【${orderBox.order_id}】</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orderBox" action="${ctx}/order/orderBox/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">订单编号:</label>
			<div class="controls">
				<form:input path="order_id" htmlEscape="false" readonly="true" style="width:250px"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">终端订单编号:</label>
			<div class="controls">
				<form:input path="torder_id" htmlEscape="false" readonly="true" style="width:250px"/>
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
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="hidden" value="${orderBox.corp_id}">
			<input name="corp_name" type="text" value="${orderBox.corp_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品编码:</label>
			<div class="controls">
				<form:input path="product_id" htmlEscape="false" readonly="true" style="width:250px"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品名称:</label>
			<div class="controls">
				<form:input path="product_name" htmlEscape="false" readonly="true" style="width:700px"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取货数量:</label>
			<div class="controls">
				<form:input path="take_num" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道号:</label>
			<div class="controls">
				<form:input path="box_id" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道类型:</label>
			<div class="controls">
				<form:input path="box_type" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提货数量:</label>
			<div class="controls">
				<form:input path="altake_num" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="cur_state" class="input-medium" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('order_box_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<form:input path="create_time" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>