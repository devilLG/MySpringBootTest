<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productInfo/list">商品信息列表</a></li>
		<li class="active"><a href="${ctx}/product/productInfo/detail">详情【${productInfo.product_id}】</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="productInfo" action="${ctx}/product/productInfo/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">商品编码:</label>
			<div class="controls">
				<form:input path="product_id" htmlEscape="false" maxlength="13" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品条码:</label>
			<div class="controls">
				<form:input path="bar_code" htmlEscape="false" maxlength="13" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品名称:</label>
			<div class="controls">
				<form:input path="product_name" htmlEscape="false" maxlength="50" class="required" style="width:700px" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品标题:</label>
			<div class="controls">
				<form:input path="product_title" htmlEscape="false" maxlength="50" class="required" style="width:700px" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品型号:</label>
			<div class="controls">
				<form:input path="product_model" htmlEscape="false" maxlength="30" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计量单位:</label>
			<div class="controls">
				<form:select path="unit_id" class="input-medium" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('unit_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">容量:</label>
			<div class="controls">
				<form:input path="metering_num" htmlEscape="false" maxlength="15" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">容量单位:</label>
			<div class="controls">
				<form:select path="metering_id" class="input-medium" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('metering_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="hidden" value="${productInfo.corp_id}">
			<input name="corp_name" type="text" value="${productInfo.corp_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品分类:</label>
			<div class="controls">
			<input name="productType_id" type="hidden" value="${productInfo.productType_id}">
			<input name="productType_name" type="text" value="${productInfo.productType_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌名称:</label>
			<div class="controls">
			<input name="brand_id" type="hidden" value="${productInfo.brand_id}">
			<input name="brand_name" type="text" value="${productInfo.brand_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">温度环境:</label>
			<div class="controls">
				<form:select path="temper_type" class="input-medium" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('temper_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保质期:</label>
			<div class="controls">
				<form:input path="overdue_date" htmlEscape="false" maxlength="11" class="required" readonly="true"/>（H）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准价:</label>
			<div class="controls">
				<form:input path="normal_price" htmlEscape="false" maxlength="11" class="required" readonly="true"/>（元）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">长:</label>
			<div class="controls">
				<form:input path="pro_length" htmlEscape="false" maxlength="11" class="required" readonly="true"/>（cm）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宽:</label>
			<div class="controls">
				<form:input path="pro_width" htmlEscape="false" maxlength="11" class="required" readonly="true"/>（cm）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高:</label>
			<div class="controls">
				<form:input path="pro_height" htmlEscape="false" maxlength="11" class="required" readonly="true"/>（cm）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品描述:</label>
			<div class="controls">
				<form:textarea path="pro_desc" htmlEscape="false" rows="3" maxlength="150" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品介绍地址:</label>
			<div class="controls">
				<form:textarea path="detail_url" htmlEscape="false" rows="3" maxlength="150" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
				<form:input path="create_time" htmlEscape="false" maxlength="11" class="required" readonly="true"/>
			</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>