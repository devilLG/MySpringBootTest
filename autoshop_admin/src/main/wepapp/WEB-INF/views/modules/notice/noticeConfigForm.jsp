<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知配置信息</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#noticeConfigForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/notice/noticeConfig/">通知配置信息列表</a></li>
		<li class="active"><a href="${ctx}/notice/noticeConfig/form?logid=${noticeConfig.logid}">通知配置信息${not empty noticeConfig.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="noticeConfigForm" modelAttribute="noticeConfig" action="${ctx}/notice/noticeConfig/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="span6">
				<div class="control-group">
					<label class="control-label">配置类型:</label>
					<div class="controls">
						<form:select path="config_type" class="input-large" >
							<form:options items="${fns:getDictList('config_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">通知方式:</label>
					<div class="controls">
						<form:select path="notice_type" class="input-large" >
							<form:options items="${fns:getDictList('noticeType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<label class="control-label">通知类型:</label>
					<div class="controls">
						<form:select path="notice_channel" class="input-large" >
							<form:options items="${fns:getDictList('notice_channel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">通知地址:</label>
					<div class="controls">
						<form:input path="notice_address" htmlEscape="false" maxlength="30" class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<div style="margin-left:360px">
				<input id="btnSubmit"  class="btn btn-primary" type="submit" value="保 存"/>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</div>
	</form:form>
	
<script type="text/javascript">
$(document).ready(function() {
	$("#noticeConfigForm").validate({
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
</body>
</html>