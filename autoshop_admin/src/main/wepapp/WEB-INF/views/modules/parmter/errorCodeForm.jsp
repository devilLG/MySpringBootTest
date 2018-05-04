<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>错误码管理</title>
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
		<li><a href="${ctx}/parmter/errorCode/">错误码列表</a></li>
		<li class="active"><a href="${ctx}/parmter/errorCode/form?logid=${errorCode.logid}">错误码<shiro:hasPermission name="site:assite:view">${not empty errorCode.logid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="errorCode" action="${ctx}/parmter/errorCode/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">错误类型:</label>
			<div class="controls">
				<form:input path="err_type" htmlEscape="false" maxlength="20" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">错误代码:</label>
			<div class="controls">
				<form:input path="err_code" htmlEscape="false" maxlength="10" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">严重程度:</label>
			<div class="controls">
				<form:select path="err_level" class="input-medium">
					<form:options items="${fns:getDictList('err_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">减除标识:</label>
			<div class="controls">
				<form:select path="err_box" class="input-medium">
					<form:options items="${fns:getDictList('is_need')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">错误描述:</label>
			<div class="controls">
				<form:textarea path="err_desc" htmlEscape="false" rows="3" maxlength="100" class="input-xlarge"/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="site:assite:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>