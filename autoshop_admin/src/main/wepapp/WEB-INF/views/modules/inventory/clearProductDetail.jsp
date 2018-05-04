<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>货道清除</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/clearProduct/list">货道清除列表</a></li>
		<li class="active"><a href="${ctx}/inventory/clearProduct/detail">详情【${clearProduct.order_id}】</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="clearProduct" action="${ctx}/inventory/clearProduct/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">清除编号:</label>
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
		<%-- <div class="control-group">
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
		</div> --%>
		<div class="control-group">
			<label class="control-label">货道号:</label>
			<div class="controls">
				<form:input path="box_id" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品编码:</label>
			<div class="controls">
				<form:input path="product_id" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品名称:</label>
			<div class="controls">
				<form:input path="product_name" htmlEscape="false" readonly="true" style="width:700px"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">条码:</label>
			<div class="controls">
				<form:input path="bar_code" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">清除数量:</label>
			<div class="controls">
				<form:input path="clear_num" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">终端库存:</label>
			<div class="controls">
				<form:input path="inventory_num" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">清除日期:</label>
			<div class="controls">
				<form:input path="clear_date" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">清除人名称:</label>
			<div class="controls">
				<form:input path="clear_name" htmlEscape="false" readonly="true"/>
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