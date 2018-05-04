<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品优惠</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productFavourable/list">商品优惠列表</a></li>
		<li class="active"><a href="${ctx}/product/productFavourable/detail">详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="productFavourable" action="${ctx}/product/productFavourable/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">优惠对象:</label>
			<div class="controls">
			<form:input path="site_id" htmlEscape="false" maxlength="30" class="required" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠类型:</label>
			<div class="controls">
			<form:input path="favourable_type" htmlEscape="false" maxlength="30" class="required" readonly="true" value="${fns:getDictLabel(productFavourable.favourable_type, 'favourable_type', '未知')}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品编码:</label>
			<div class="controls">
				<form:input path="product_id" htmlEscape="false" maxlength="30" class="required" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品名称:</label>
			<div class="controls">
				<form:input path="product_name" htmlEscape="false" maxlength="50" class="required" style="width:700px" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品条码:</label>
			<div class="controls">
				<form:input path="bar_code" htmlEscape="false" maxlength="13" class="required digits" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">恢复商品标题:</label>
			<div class="controls">
				<form:input path="nomarl_title" htmlEscape="false" readonly="true" maxlength="50" class="required" style="width:700px"/>
			</div>
		</div>
		</div><div class="control-group">
			<label class="control-label">恢复商品标价:</label>
			<div class="controls">
				<form:input path="nomarl_price" htmlEscape="false" readonly="true" maxlength="11" class="required number"/>（元）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">恢复商品介绍地址:</label>
			<div class="controls">
				<form:textarea path="nomarl_url" htmlEscape="false" readonly="true" rows="3" maxlength="150" class="required input-xlarge url"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠商品标题:</label>
			<div class="controls">
				<form:input path="favourable_title" htmlEscape="false" readonly="true"  maxlength="50" class="required" style="width:700px"/>
			</div>
		</div>
		</div><div class="control-group">
			<label class="control-label">优惠商品标价:</label>
			<div class="controls">
				<form:input path="favourable_price" htmlEscape="false" readonly="true" maxlength="11" class="required number"/>（元）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠商品介绍地址:</label>
			<div class="controls">
				<form:textarea path="favourable_url" htmlEscape="false" readonly="true" rows="3" maxlength="150" class="required input-xlarge url"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠描述:</label>
			<div class="controls">
				<form:textarea path="favourable_desc" htmlEscape="false" readonly="true" rows="3" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠开始日期:</label>
			<div class="controls">
				<input name="favourable_stime" value="${productFavourable.favourable_stime}" type="text" readonly="readonly" maxlength="20" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠结束日期:</label>
			<div class="controls">
				<input name="favourable_etime" value="${productFavourable.favourable_etime}" type="text" readonly="readonly" maxlength="20" /></li>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<input name="cur_state" value="${fns:getDictLabel(productFavourable.cur_state, 'product_favourable_state', '未知')}" type="text" readonly="readonly" maxlength="20" /></li>
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