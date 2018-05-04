<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>虚拟仓库</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/warehouseInfo/list">虚拟仓库列表</a></li>
		<li class="active"><a href="${ctx}/inventory/warehouseInfo/detail">详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="warehouseInfo" action="${ctx}/inventory/warehouseInfo/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
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
			<label class="control-label">仓库编号:</label>
			<div class="controls">
				<form:input path="warehouse_id" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仓库名称:</label>
			<div class="controls">
				<form:input path="warehouse_name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仓库代码:</label>
			<div class="controls">
				<form:input path="warehouse_code" htmlEscape="false" maxlength="80"  readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仓库经度:</label>
			<div class="controls">
				<form:input path="longitude" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仓库维度:</label>
			<div class="controls">
				<form:input path="latitude" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仓库详细地址:</label>
			<div class="controls">
				<form:textarea path="address" htmlEscape="false" rows="2" maxlength="80"  readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品种类:</label>
			<div class="controls">
				<form:input path="classify_total" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存总数:</label>
			<div class="controls">
				<form:input path="inventory_total" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正常库存数:</label>
			<div class="controls">
				<form:input path="inventory_num" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在途库存数:</label>
			<div class="controls">
				<form:input path="inventory_transit" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回收库存数:</label>
			<div class="controls">
				<form:input path="inventory_renum" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">过期库存数:</label>
			<div class="controls">
				<form:input path="overdue_num" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
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