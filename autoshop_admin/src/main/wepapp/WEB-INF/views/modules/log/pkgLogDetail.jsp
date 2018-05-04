<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报文日志</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/log/pkgLog/">交易列表</a></li>
		<li class="active"><a href="${ctx}/log/pkgLog/detail">详情【${pkgLog.trade_code}】</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pkgLog" action="${ctx}/log/pkgLog/detail" method="post" class="form-horizontal">
	<form:hidden path="log_id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">授权用户:</label>
			<div class="controls">
				<form:input path="auth_name" htmlEscape="false" maxlength="2" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平台代码:</label>
			<div class="controls">
				<form:input path="plat_code" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">交易代码:</label>
			<div class="controls">
				<form:input path="trade_code" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易时间:</label>
			<div class="controls">
				<form:input path="trade_time" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">交易简称:</label>
			<div class="controls">
				<form:input path="trade_name" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>--%>

		<div class="control-group">
			<label class="control-label">请求报文:</label>
			<div class="controls">
				<form:textarea path="pkg_up" htmlEscape="false" rows="2" cssStyle="width:275px;height:100px;"  readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回应报文:</label>
			<div class="controls">
				<form:textarea path="pkg_down" htmlEscape="false" rows="2" cssStyle="width:275px;height:100px;"  readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议:</label>
			<div class="controls">
				<form:input path="protocol" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>