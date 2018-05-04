<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售订单</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/order/orderApply/list">销售订单列表</a></li>
		<li class="active"><a href="${ctx}/order/orderApply/refundForm">退款【${orderApply.order_id}】</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#main" data-toggle="tab">订单信息</a></li>
		<li><a href="#sub" data-toggle="tab"><span>商品信息</span></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orderApply" action="${ctx}/order/orderApply/refund" method="post" class="form-horizontal">
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
			<label class="control-label">交易流水号:</label>
			<div class="controls">
				<form:input path="OutTradeNo" htmlEscape="false" readonly="true"/>
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
		</div>
		<div class="tab-pane fade" id="sub">
				 <table class="table table-striped table-bordered table-condensed">
				    <thead>
				    <tr>
						<th>商品编码</th>
						<th>商品名称</th>
						<th>商品分类</th>
						<th>商品品牌 </th>
						<th>标准价（元） </th>
						<th>商品数量</th>
						<th>金额（元）</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${orderProductList}" var="orderProduct">
						<tr>
							<td>${orderProduct.product_id}</td>
							<td>${orderProduct.product_name}</td>
							<td>${orderProduct.productType_name}</td>
							<td>${orderProduct.brand_name}</td>
							<td>${orderProduct.normal_price}</td>
							<td>${orderProduct.sale_num}</td>
							<td>${orderProduct.sale_total}</td>
						</tr>
					</c:forEach>
					</tbody>
				 </table>
		</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-warning" type="submit" value="退款"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>