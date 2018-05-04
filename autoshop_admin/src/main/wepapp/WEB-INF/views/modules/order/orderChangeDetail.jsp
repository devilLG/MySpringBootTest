<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单状态</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/order/orderChange/list">订单状态列表</a></li>
		<li class="active"><a href="${ctx}/order/orderChange/detail">详情【${orderChange.order_id}】</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orderChange" action="${ctx}/order/orderChange/detail" method="post" class="form-horizontal">
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
		<%-- <div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="hidden" value="${orderChange.corp_id}">
			<input name="corp_name" type="text" value="${orderChange.corp_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">条码:</label>
			<div class="controls">
				<form:input path="bar_code" htmlEscape="false" readonly="true" style="width:250px"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作状态:</label>
			<div class="controls">
				<form:select path="oper_action" class="input-medium" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('order_change_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作人编号:</label>
			<div class="controls">
				<form:input path="oper_id" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作人名称:</label>
			<div class="controls">
				<form:input path="oper_name" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作时间:</label>
			<div class="controls">
				<form:input path="oper_time" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作内容:</label>
			<div class="controls">
				<form:textarea path="oper_cont" htmlEscape="false" rows="3" maxlength="150" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推送状态:</label>
			<div class="controls">
				<form:select path="poc_state" class="input-medium" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('orderChange_push_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推送次数:</label>
			<div class="controls">
				<form:input path="poc_times" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推送结果:</label>
			<div class="controls">
				<form:textarea path="poc_result" htmlEscape="false" rows="3" maxlength="150" class="input-xlarge" readonly="true"/>
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