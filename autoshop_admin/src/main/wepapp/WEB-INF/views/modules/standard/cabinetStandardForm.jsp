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
		<li><a href="${ctx}/standard/cabinetStandard/">规格配置列表</a></li>
		<li class="active"><a href="${ctx}/standard/cabinetStandard/form?id=${cabinetStandard.logid}">规格配置<shiro:hasPermission name="site:assite:view">${not empty cabinetStandard.logid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cabinetStandard" action="${ctx}/standard/cabinetStandard/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		
		<%-- <c:if test="${empty cabinetStandard.logid}">
		<div class="control-group">
			<label class="control-label">所属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="corp_name" value="${cabinetStandard.corp_id}" labelName="corp_name" labelValue="${cabinetStandard.corp_id}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty cabinetStandard.logid}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="text" value="${cabinetStandard.corp_id}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		</c:if>--%> 
		<div class="control-group">
			<label class="control-label">柜子配置标识:</label>
			<div class="controls">
				<form:input path="cabconfig_id" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">柜子配置名称:</label>
			<div class="controls">
				<form:input path="cabconfig_name" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">柜子类型:</label>
                <div class="controls">
               <sys:tableselect id="cabinettype" cssStyle="width:210px;height:23px;" name="cabinettype_id" value="${cabinetStandard.cabinettype_id}" labelName="cabinettype_name" labelValue="${cabinetStandard.cabinettype_name}"
					title="柜子类型" url="/standard/cabinetType/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">储存类型:</label>
			<div class="controls">
				<form:select path="cabinetTemp_type" class="input-medium">
					<form:options items="${fns:getDictList('cabinetTemp_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在列数:</label>
			<div class="controls">
				<form:input path="column_location" htmlEscape="false" maxlength="8" class="required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述:</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="2" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="site:assite:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>