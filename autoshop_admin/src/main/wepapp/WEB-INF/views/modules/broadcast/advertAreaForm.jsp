<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域配置</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#advertAreaForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/broadcast/advertArea/">区域配置列表</a></li>
		<li class="active"><a href="${ctx}/broadcast/advertArea/form?logid=${advertArea.logid}">区域配置${not empty advertArea.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="advertAreaForm" modelAttribute="advertArea" action="${ctx}/broadcast/advertArea/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<form:hidden path="configId"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="span6">
				<div class="control-group">
					<label class="control-label">配置名称:</label>
					<div class="controls">
						<form:input path="configName" htmlEscape="false" maxlength="60" class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">媒体显示像素x:</label>
					<div class="controls">
						<form:input path="resolutionX" htmlEscape="false" maxlength="30" class="required number" /> 
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">排列顺序:</label>
					<div class="controls">
						<form:input path="seqId" htmlEscape="false" maxlength="11" class="required number" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">配置描述:</label>
					<div class="controls">
						<form:textarea path="description" htmlEscape="false" rows="3" maxlength="500" class="input-xxlarge"/>
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<label class="control-label">区域：</label>
					<sys:treeselect id="tree" name="areaId" value="${advertArea.areaId}" labelName="areaName" labelValue="${advertArea.areaName}"
					title="区域名称" url="/sys/area/treeData" cssClass="input"  allowClear="true"/>
				</div>
				<div class="control-group">
					<label class="control-label">媒体显示像素y:</label>
					<div class="controls">
						<form:input path="resolutionY" htmlEscape="false" maxlength="30" class="required number" /> 
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">媒体类型:</label>
					<div class="controls">
						<form:select path="mediaType" class="input-large" onchange="typeChange(this)">
							<form:options items="${fns:getDictList('media_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">媒体格式:</label>
					<div class="controls">
						<form:select path="mimeType" class="input-large">
							<form:options items="${fns:getDictList('mime_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
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
	$("#advertAreaForm").validate({
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