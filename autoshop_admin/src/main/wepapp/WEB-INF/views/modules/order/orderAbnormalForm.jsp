<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售订单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#orderAbnormalForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/order/orderApply/list">销售订单列表</a></li>
		<li class="active"><a href="${ctx}/order/orderApply/abnormalForm">异常【${orderApply.order_id}】</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#main" data-toggle="tab">订单信息</a></li>
		<li><a href="#sub" data-toggle="tab"><span>商品信息</span></a></li>
	</ul><br/>
	<form:form id="orderAbnormalForm" modelAttribute="orderApply" action="${ctx}/order/orderApply/abnormal" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
		<div class="tab-pane fade in active" id="main">
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
			<label class="control-label">订单编号:</label>
			<div class="controls">
				<form:input path="order_id" htmlEscape="false" readonly="true" style="width:250px"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户名称:</label>
			<div class="controls">
				<form:input path="login_name" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请时间:</label>
			<div class="controls">
			<form:input path="create_time" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下单渠道:</label>
			<div class="controls">
				<form:select path="sale_channel" class="input-small required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sale_channel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付方式:</label>
			<div class="controls">
				<form:select path="pay_type" class="input-small required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付状态:</label>
			<div class="controls">
				<form:select path="pay_state" class="input-small required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pay_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付金额:</label>
			<div class="controls">
				<form:input path="sale_total" htmlEscape="false" readonly="true"/>（元）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付超期:</label>
			<div class="controls">
				<form:input path="pay_overtime" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="cur_state" class="input-medium" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('order_apply_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			<label class="control-label">回收状态:</label>
			<div class="controls">
				<form:select path="recovery_type" class="input-small required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('recovery_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取件密码:</label>
			<div class="controls">
				<form:input path="pass_word" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取件超期:</label>
			<div class="controls">
				<form:input path="fetch_overTime" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下发状态:</label>
			<div class="controls">
				<form:select path="down_state" class="input-small required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('down_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下发时间:</label>
			<div class="controls">
				<form:input path="down_time" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">异常类型:</label>
			<div class="controls">
				<form:select path="abnormal_type" class="input-small required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('abnormal_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</div>
		<div class="tab-pane fade" id="sub">
				 <table class="table table-striped table-bordered table-condensed">
				    <thead>
				    <tr>
						<th>商品编码</th>
						<th>商品名称</th>
						<th>货道号</th>
						<th>待提数量</th>
						<th>提取数量</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${orderBoxList}" var="orderBox">
						<tr>
							<td>${orderBox.product_id}</td>
							<td>${orderBox.product_name}</td>
							<td>${orderBox.box_id}</td>
							<td>${orderBox.take_num}</td>
							<td>${orderBox.altake_num}</td>
						</tr>
					</c:forEach>
					</tbody>
				 </table>
		</div>
		</div>
		<div class="form-actions">
		    <input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>