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
		<li><a href="${ctx}/standard/cabinetAttr/">柜子排列列表</a></li>
		<li class="active"><a href="${ctx}/standard/cabinetAttr/form?attr_id=${cabinetAttr.attr_id}">柜箱<shiro:hasPermission name="site:assite:view">${not empty cabinetAttr.attr_id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cabinetAttr" action="${ctx}/standard/cabinetAttr/save" method="post" class="form-horizontal">
		<form:hidden path="attr_id"/>
		<sys:message content="${message}"/>
		
		<%-- -%><c:if test="${empty cabinetAttr.attr_id}">
		<div class="control-group">
			<label class="control-label">所属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="corp_id" value="${cabinetAttr.corp_id}" labelName="corp_name" labelValue="${cabinetAttr.corp_id}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty cabinetAttr.attr_id}">
		<div class="control-group">
			<label class="control-label">公司编号:</label>
			<div class="controls">
				<form:input path="corp_id" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
			</div>
		</div>
		</c:if>--%>
		<div class="control-group">
			<label class="control-label">柜子类型编号:</label>
                <div class="controls">
               <sys:tableselect id="cabinettype" cssStyle="width:210px;height:23px;" name="cabinettype_id" value="${cabinetAttr.cabinettype_id}" labelName="cabinettype_name" labelValue="${cabinetAttr.cabinettype_id}"
					title="柜子类型" url="/standard/cabinetType/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道类型编号:</label>
                <div class="controls">
               <sys:tableselect id="boxtype" cssStyle="width:210px;height:23px;" name="boxtype_id" value="${cabinetAttr.boxtype_id}" labelName="type_name" labelValue="${cabinetAttr.boxtype_id}"
					title="货道类型" url="/standard/boxType/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">列数:</label>
			<div class="controls">
				<form:input path="cloumn_id" htmlEscape="false" maxlength="5" class="required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道编号:</label>
			<div class="controls">
				<form:input path="box_id" htmlEscape="false" maxlength="10" class="required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">层数:</label>
			<div class="controls">
				<form:input path="layer_num" htmlEscape="false" maxlength="2" class="required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">序号:</label>
			<div class="controls">
				<form:input path="seq_id" htmlEscape="false" maxlength="8" class="required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="state" class="input-medium">
					<form:options items="${fns:getDictList('State')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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