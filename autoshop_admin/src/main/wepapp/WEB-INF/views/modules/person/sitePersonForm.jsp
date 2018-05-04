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
		<li><a href="${ctx}/person/sitePerson/">站点人员列表</a></li>
		<li class="active"><a href="${ctx}/person/sitePerson/form?id=${sitePerson.logid}">站点人员<shiro:hasPermission name="site:assite:view">${not empty sitePerson.logid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sitePerson" action="${ctx}/person/sitePerson/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">站点名称:</label>
                <div class="controls">
               <sys:tableselect id="site" cssStyle="width:210px;height:23px;" name="site_id" value="${sitePerson.site_id}" labelName="site_name" labelValue="${sitePerson.site_name}"
					title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		
		<%--<c:if test="${empty sitePerson.logid}">
		<div class="control-group">
			<label class="control-label">所属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="corp_name" value="${sitePerson.corp_name}" labelName="corp_name" labelValue="${sitePerson.corp_name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty sitePerson.logid}">
		<%--<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="hidden" value="${sitePerson.corp_id}">
			<input name="corp_name" type="text" value="${sitePerson.corp_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		</c:if>--%>
		<div class="control-group">
			<label class="control-label">账号信息:</label>
			<div class="controls">
				 <sys:treeselect id="login" name="login_id" value="${sitePerson.login_id}" labelName="login_name" labelValue="${sitePerson.login_name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">登录帐号:</label>
			<div class="controls">
				<form:input path="login_id" htmlEscape="false" maxlength="80" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户名:</label>
			<div class="controls">
				<form:input path="login_name" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
			</div>
		</div>
		 -%><div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<input id="Pass_word" name="Pass_word" type="password" value="" maxlength="50" minlength="3" class="${empty sitePerson.logid?'required':''}"/>
				<c:if test="${empty sitePerson.logid}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty sitePerson.logid}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">手机号:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">人员类型:</label>
			<div class="controls">
				<form:select path="Emp_type" class="input-medium">
					<form:options items="${fns:getDictList('Emp_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="Cur_state" class="input-medium">
					<form:options items="${fns:getDictList('Cur_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="site:assite:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>