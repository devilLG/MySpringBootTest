<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工单</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#workForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/workOrder/workManage/">工单列表</a></li>
		<li class="active"><a href="${ctx}/workOrder/workManage/form?logid=${work.logid}">工单${not empty work.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="workForm" modelAttribute="work" action="${ctx}/workOrder/workManage/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="span6">
				<div class="control-group">
					<label class="control-label">工单标题:</label>
					<div class="controls">
						<form:input path="workTitle" htmlEscape="false" maxlength="50" class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">工单类型:</label>
					<div class="controls">
						<form:select path="workType" class="input-large" >
							<form:options items="${fns:getDictList('work_type')}" itemLabel="label"  itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<c:if test="${empty work.logid}">
					<div class="control-group">
						<label class="control-label">责任人：</label>
						<div class="controls">
							<sys:treeselect id="managerId" cssStyle="width:150px;height:23px;" name="managerId" value="${work.managerId}" labelName="managerName" labelValue="${work.managerName}"
								title="责任人" url="/sys/office/treeData?type=3" allowClear="true" cssClass="required" notAllowSelectParent="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty work.logid}">
					<div class="control-group">
						<label class="control-label">责任人：</label>
						<div class="controls">
							<form:input path="managerName" readonly="true" htmlEscape="false"/>
							<form:hidden path="managerId" htmlEscape="false"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</c:if>
			</div>
			<div class="span6">
				<c:if test="${empty work.logid}">
					<div class="control-group">
						<label class="control-label">站点：</label>
						<div class="controls">
							<sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="siteId" value="${work.siteId}" labelName="siteName" labelValue="${work.siteName}"
								 title="站点信息" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty work.logid}">
					<div class="control-group">
						<label class="control-label">站点：</label>
						<div class="controls">
							<form:input path="siteName" readonly="true" htmlEscape="false"/>
							<form:hidden path="siteId" htmlEscape="false"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</c:if>
				<div class="control-group">
					<label class="control-label">工单级别:</label>
					<div class="controls">
						<form:select path="workLevel" class="input-large"  >
							<form:options items="${fns:getDictList('work_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">问题描述:</label>
					<div class="controls">
						<form:input path="questionCont" htmlEscape="false" maxlength="200" class="required" />
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
	$("#workForm").validate({
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