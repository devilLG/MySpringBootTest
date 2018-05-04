<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点规格管理</title>
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
		<li><a href="${ctx}/standard/cabinetType/">柜子类型列表</a></li>
		<li class="active"><a href="${ctx}/standard/cabinetType/form?id=${cabinetType.logid}">柜子类型<shiro:hasPermission name="site:assite:view">${not empty cabinetType.logid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cabinetType" action="${ctx}/standard/cabinetType/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		
		<%--<c:if test="${empty cabinetType.logid}">
		<div class="control-group">
			<label class="control-label">所属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="corp_name" value="${cabinetType.corp_id}" labelName="corp_name" labelValue="${cabinetType.corp_id}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty cabinetType.logid}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="text" value="${cabinetType.corp_id}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		</c:if>
		<%-- -%><c:if test="${empty cabinetType.logid}">
		<div class="control-group">
			<label class="control-label">柜子配置标识:</label>
			<div class="controls">
                <sys:treeselect id="company" name="corp_name" value="${sitePerson.corp_name}" labelName="corp_name" labelValue="${sitePerson.corp_name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if>--%>
		<%--<c:if test="${not empty cabinetType.logid}">--%>
		
		<%--</c:if>--%>
		<div class="control-group">
			<label class="control-label">类型编号:</label>
			<div class="controls">
				<form:input path="type_id" htmlEscape="false" maxlength="10" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
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
				<form:input path="fullname" htmlEscape="false" maxlength="80" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道总数:</label>
			<div class="controls">
				<form:input path="box_num" htmlEscape="false" maxlength="7" class="required digits" min="1"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总列数:</label>
			<div class="controls">
				<form:input path="column_num" htmlEscape="false" maxlength="7" class="required digits" min="1"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="site:assite:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>