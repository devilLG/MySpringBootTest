<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>操作日志</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/log/">日志列表</a></li>
		<li class="active"><a href="${ctx}/sys/log/detail">详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="log" action="${ctx}/sys/log/detail" method="post" class="form-horizontal">
	<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">操作菜单:</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="2" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户ID:</label>
			<div class="controls">
				<form:input path="createBy.logid" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">操作用户:</label>
			<div class="controls">
				<form:input path="createBy.name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在公司:</label>
			<div class="controls">
				<form:input path="createBy.company.name" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在部门:</label>
			<div class="controls">
				<form:input path="createBy.office.name" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">URI:</label>
			<div class="controls">
				<form:input path="requestUri" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">异常信息:</label>
			<div class="controls">
				<form:textarea path="exception" htmlEscape="false" rows="5" cssStyle="width:600px;height:300px;"  readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提交方式:</label>
			<div class="controls">
				<form:input path="method" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作者IP:</label>
			<div class="controls">
				<form:input path="remoteAddr" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>