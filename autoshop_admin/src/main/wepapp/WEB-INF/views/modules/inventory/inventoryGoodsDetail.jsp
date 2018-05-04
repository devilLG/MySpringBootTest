<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>货道库存</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/inventoryGoods/list">货道库存列表</a></li>
		<li class="active"><a href="${ctx}/inventory/inventoryGoods/detail">详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="inventoryGoods" action="${ctx}/inventory/inventoryGoods/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">站点编号:</label>
			<div class="controls">
				<form:input path="site_id" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
				<form:input path="site_name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司编号:</label>
			<div class="controls">
				<form:input path="corp_id" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司名称:</label>
			<div class="controls">
				<form:input path="corp_name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">柜号:</label>
			<div class="controls">
				<form:input path="cabinet_id" htmlEscape="false" maxlength="11" class="required digits" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">列数:</label>
			<div class="controls">
				<form:input path="cloumn_id" htmlEscape="false" maxlength="30" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">层数:</label>
			<div class="controls">
				<form:input path="layer_num" htmlEscape="false" maxlength="11" class="required digits" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道号:</label>
			<div class="controls">
				<form:input path="box_id" htmlEscape="false" maxlength="30" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道状态:</label>
			<div class="controls">
				<form:input path="cur_state" htmlEscape="false" value="${fns:getDictLabel(inventoryGoods.cur_state, 'cur_state', '未知')}" maxlength="30" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">温度环境:</label>
			<div class="controls">
				<form:input path="temper_type" htmlEscape="false" value="${fns:getDictLabel(inventoryGoods.temper_type, 'temper_type', '')}" maxlength="30" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品编码:</label>
			<div class="controls">
				<form:input path="product_id" htmlEscape="false" maxlength="30" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品名称:</label>
			<div class="controls">
				<form:input path="product_name" htmlEscape="false" maxlength="50" class="required" style="width:700px" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">条码:</label>
			<div class="controls">
				<form:input path="bar_code" htmlEscape="false" maxlength="30" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">过期时间:</label>
			<div class="controls">
				<form:input path="overdue_time" htmlEscape="false" maxlength="30" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大库存数:</label>
			<div class="controls">
				<form:input path="inventory_max" htmlEscape="false" maxlength="11" class="required digits" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺货临界值:</label>
			<div class="controls">
				<form:input path="warn_num" htmlEscape="false" maxlength="11" class="required digits" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存总数:</label>
			<div class="controls">
				<form:input path="inventory_total" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可售库存数:</label>
			<div class="controls">
				<form:input path="inventory_num" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在途数量:</label>
			<div class="controls">
				<form:input path="inventory_transit" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">过期数量:</label>
			<div class="controls">
				<form:input path="overdue_num" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回收数量:</label>
			<div class="controls">
				<form:input path="inventory_recovery" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存状态:</label>
			<div class="controls">
				<form:input path="inventory_state" htmlEscape="false" value="${fns:getDictLabel(inventoryGoods.inventory_state, 'inventory_state', '')}" maxlength="30" class="required" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态时间:</label>
			<div class="controls">
				<form:input path="state_time" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有过期商品:</label>
			<div class="controls">
				<form:input path="is_overdue" htmlEscape="false" value="${fns:getDictLabel(inventoryGoods.is_overdue, 'is_overdue', '')}" maxlength="30" class="required" readonly="true" />
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