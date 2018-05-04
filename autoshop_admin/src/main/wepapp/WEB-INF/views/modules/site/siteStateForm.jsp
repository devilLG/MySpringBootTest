<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品品牌</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
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
		<li><a href="${ctx}/site/siteState/list">地点网络状态列表</a></li>
		<li class="active"><a href="${ctx}/site/siteState/form?logid=${siteState.logid}">网络状态${not empty siteState.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="siteState" action="${ctx}/site/siteState/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		
		<c:if test="${not empty siteState.logid}">
		<div class="control-group">
			<label class="control-label">地点名称:</label>
			<div class="controls">
				<form:input path="site_id" htmlEscape="false" maxlength="50" type="hidden" class="required" readonly="true"/>
				<form:input path="site_name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">货道总数:</label>
			<div class="controls">
				<form:input path="box_all" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道空箱数量:</label>
			<div class="controls">
				<form:input path="box_empty" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用中货道数量:</label>
			<div class="controls">
				<form:input path="box_using" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道缺货数量:</label>
			<div class="controls">
				<form:input path="box_short" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道障碍数量:</label>
			<div class="controls">
				<form:input path="box_damage" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">刷新状态:</label>
			<div class="controls">
				<form:input path="refresh_state" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">刷新次数:</label>
			<div class="controls">
				<form:input path="refresh_num" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">刷新时间:</label>
			<div class="controls">
				<input id="refresh_time" name="refresh_time" type="text" readonly="readonly" maxlength="20" class="required input-medium Wdate"
					value="${siteState.refresh_time}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<br/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网络状态:</label>
			<div class="controls">
				<form:input path="compute_state" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<c:if test="${empty siteState.logid}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="owner_id" value="${siteState.owner_id}" labelName="owner_id" labelValue="${siteState.owner_id}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>