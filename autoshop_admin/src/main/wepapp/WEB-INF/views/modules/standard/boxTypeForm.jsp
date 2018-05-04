<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
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
		<li><a href="${ctx}/standard/boxType/">货道规格列表</a></li>
		<li class="active"><a href="${ctx}/standard/boxType/form?type_id=${boxType.type_id}">货道规格<shiro:hasPermission name="site:assite:view">${not empty boxType.type_id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="boxType" action="${ctx}/standard/boxType/save" method="post" class="form-horizontal">
		<form:hidden path="type_id"/>
		<sys:message content="${message}"/>
		
		<%-- <c:if test="${empty boxType.type_id}">
		<div class="control-group">
			<label class="control-label">所属公司:</label>
			<div class="controls">
                <sys:treeselect id="corp" name="corp_id" value="${boxType.corp_id}" labelName="corp_name" labelValue="${boxType.corp_id}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty boxType.type_id}">
		<div class="control-group">
			<label class="control-label">公司编号:</label>
			<div class="controls">
				<form:input path="corp_id" htmlEscape="false" maxlength="30" class="input-xlarge required" readonly="readonly"/>
			</div>
		</div>
		</c:if>--%>
		
		<div class="control-group">
			<label class="control-label">类型简称:</label>
			<div class="controls">
				<form:input path="type_name" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型全称:</label>
			<div class="controls">
				<form:input path="fullname" htmlEscape="false" maxlength="80" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">长度:</label>
			<div class="controls">
				<form:input path="size_length" htmlEscape="false" maxlength="20" class="required number"/>(cm)
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宽度:</label>
			<div class="controls">
				<form:input path="size_width" htmlEscape="false" maxlength="20" class="required number"/>(cm)
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高度:</label>
			<div class="controls">
				<form:input path="size_height" htmlEscape="false" maxlength="20" class="required number"/>(cm)
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">大号规格:</label>
			<div class="controls">
				<form:input path="exceed_type" htmlEscape="false" maxlength="20" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="site:assite:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>