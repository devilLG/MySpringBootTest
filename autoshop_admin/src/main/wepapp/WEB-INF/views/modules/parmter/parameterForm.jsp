<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参数管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/parmter/parameter/">参数列表</a></li>
		<li class="active"><a href="${ctx}/parmter/parameter/form?logid=${parameter.logid}">参数<shiro:hasPermission name="site:assite:view">${not empty parameter.logid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="parameter" action="${ctx}/parmter/parameter/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">参数类型:</label>
			<div class="controls">
				<form:input path="para_type" htmlEscape="false" maxlength="20" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="40" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数值:</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" maxlength="80" class="required abc"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数描述:</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="site:assite:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>