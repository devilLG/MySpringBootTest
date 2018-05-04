<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>管理平台账单</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#payManageInfoForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pay/payManageInfo/">管理平台账单列表</a></li>
		<li class="active"><a href="${ctx}/pay/payManageInfo/form?trade_number=${payManageInfo.trade_number}">管理平台账单详情</a></li>
	</ul><br/>
	<form:form id="payManageInfoForm" modelAttribute="payManageInfo" action="${ctx}/pay/payManageInfo/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<form:hidden path="trade_number"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="span6">
				<div class="control-group">
					<label class="control-label">对账日期:</label>
					<div class="controls">
						<form:input path="trade_day" htmlEscape="false"  readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">支付方式:</label>
					<div class="controls">
						<form:select path="pay_channel" class="input-large"  disabled="true">
							<form:options items="${fns:getDictList('pay_channel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">交易时间:</label>
					<div class="controls">
						<form:input path="trade_time" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">订单编号:</label>
					<div class="controls">
						<form:input path="order_id" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">支付状态:</label>
					<div class="controls">
						<form:select path="pay_state" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('pay_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">状态时间:</label>
					<div class="controls">
						<form:input path="state_time" htmlEscape="false" readonly="true" />
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<label class="control-label">客户名称:</label>
					<div class="controls">
						<form:input path="login_name" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">币种:</label>
					<div class="controls">
						<form:select path="currency" class="input-large"  disabled="true">
							<form:options items="${fns:getDictList('currency')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">交易流水号:</label>
					<div class="controls">
						<form:input path="trade_number" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">交易总金额:</label>
					<div class="controls">
						<form:input path="trade_sum" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">对账状态:</label>
					<div class="controls">
						<form:select path="cur_state" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('payment_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">创建时间:</label>
					<div class="controls">
						<form:input path="create_time" htmlEscape="false" readonly="true" />
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<div style="margin-left:360px">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</div>
	</form:form>

</body>
</html>