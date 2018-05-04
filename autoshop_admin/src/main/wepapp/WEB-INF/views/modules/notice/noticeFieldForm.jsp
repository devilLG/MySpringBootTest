<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公共通知模板字段</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#noticeFieldForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/notice/noticeField/">公共通知模板字段列表</a></li>
		<li class="active"><a href="${ctx}/notice/noticeField/form?logid=${noticeField.logid}">公共通知模板字段${not empty noticeField.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="noticeFieldForm" modelAttribute="noticeField" action="${ctx}/notice/noticeField/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="span6">
				<div class="control-group">
					<label class="control-label">模板字段名称:</label>
					<div class="controls">
						<form:input path="field_code" htmlEscape="false" maxlength="30" class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">数据库名称:</label>
					<div class="controls">
						<form:input path="table_name" htmlEscape="false" maxlength="30" class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">描述:</label>
					<div class="controls">
						<form:textarea path="field_desc" htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge"/>
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<label class="control-label">业务类型:</label>
					<div class="controls">
						<form:select path="main_type" class="input-large" onchange="typeChange(this)">
							<form:options items="${fns:getDictList('mainType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">字段名称:</label>
					<div class="controls">
						<form:input path="field_name" htmlEscape="false" maxlength="30" class="required" />
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
	$("#noticeFieldForm").validate({
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