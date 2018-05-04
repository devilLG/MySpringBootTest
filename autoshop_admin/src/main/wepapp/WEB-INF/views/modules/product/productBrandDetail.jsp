<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品品牌</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productBrand/list">商品品牌列表</a></li>
		<li class="active"><a href="${ctx}/product/productBrand/detail">详情【${productBrand.brand_name}】</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="productBrand" action="${ctx}/product/productBrand/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">品牌编号:</label>
			<div class="controls">
				<form:input path="brand_id" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌名称:</label>
			<div class="controls">
				<form:input path="brand_name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌公司:</label>
			<div class="controls">
				<form:input path="brandCorp_name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成立时间:</label>
			<div class="controls">
				<input id="establish_time" name="establish_time" type="text" readonly="readonly" maxlength="20" class="input-medium"
					value="${productBrand.establish_time}"/>
				<br/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属行业:</label>
			<div class="controls">
				<form:input path="industry" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Logo地址:</label>
			<div class="controls">
				<form:textarea path="logo" htmlEscape="false" rows="2" maxlength="80" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="hidden" value="${productBrand.corp_id}">
			<input name="corp_name" type="text" value="${productBrand.corp_name}"  readonly="readonly" class="input-xlarge">
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