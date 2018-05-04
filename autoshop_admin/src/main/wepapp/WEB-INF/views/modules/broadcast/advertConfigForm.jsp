<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>广告配置</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#advertConfigForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/broadcast/advertConfig/">广告配置列表</a></li>
		<li class="active"><a href="${ctx}/broadcast/advertConfig/form?logid=${advertConfig.logid}">广告配置${not empty advertConfig.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="advertConfigForm" modelAttribute="advertConfig" action="${ctx}/broadcast/advertConfig/save" method="post" class="form-horizontal">
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
					<label class="control-label">区域数:</label>
					<div class="controls">
						<form:select path="areaNum" class="input-large">
							<form:options items="${fns:getDictList('area_num')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
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
					<label class="control-label">计划开播日期:</label>
					<div class="controls">
						<input  type="text" readonly="readonly" name="planStime" id="aCplanStime"  class="Wdate input"  
							onfocus="WdatePicker({minDate:'#F{\'%y-%M-%d\'}' ,dateStart:'%y-%M-%d',alwaysUseStartDate:false,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
							size="3" value="${advertConfig.planStime}"/>
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
					<label class="control-label">归属公司：</label>
					<div class="controls">
						<sys:treeselect id="corpId" name="corpId" value="${advertConfig.corpId}" labelName="corpName" labelValue="${advertConfig.corpName}" 
						title="公司" url="/sys/office/treeData?type=1" cssClass="input" allowClear="true"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">播放媒体数量:</label>
					<div class="controls">
						<form:input path="mediaNum" htmlEscape="false" maxlength="10" class="required number" /> 
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">媒体显示像素y:</label>
					<div class="controls">
						<form:input path="resolutionY" htmlEscape="false" maxlength="30" class="required number" /> 
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">计划停播日期:</label>
					<div class="controls">
						<input  type="text" readonly="readonly" name="planEtime" id="aCplanEtime"  class="Wdate input "  
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'aCplanStime\')}',dateStart:'%y-%M-%d',alwaysUseStartDate:false,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
							size="3" value="${advertConfig.planEtime}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">默认播放:</label>
					<div class="controls">
						<form:select path="isDefalut" class="input-large">
							<form:options items="${fns:getDictList('is_defalut')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
	$("#advertConfigForm").validate({
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