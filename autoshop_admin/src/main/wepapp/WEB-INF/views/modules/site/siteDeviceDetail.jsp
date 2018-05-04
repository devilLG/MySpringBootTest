<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品品牌</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/site/siteDevice/list">柜子配置列表</a></li>
		<li class="active"><a href="${ctx}/site/siteDevice/detail">明细【${siteDevice.site_name}】</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="siteDevice" action="${ctx}/site/siteDevice/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">地点编号:</label>
			<div class="controls">
				<form:input path="site_id" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地点名称:</label>
			<div class="controls">
				<form:input path="site_name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司编号:</label>
			<div class="controls">
				<form:input path="owner_id" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<%-- 
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="hidden" value="${siteDevice.corp_id}">
			<input name="corp_name" type="text" value="${siteDevice.corp_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>--%>
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