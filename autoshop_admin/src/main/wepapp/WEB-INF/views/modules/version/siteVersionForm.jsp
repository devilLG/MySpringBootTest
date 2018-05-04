<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端版本管理</title>
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
		<li><a href="${ctx}/version/siteVersion/">终端版本列表</a></li>
		<li class="active"><a href="${ctx}/version/siteVersion/form?id=${siteVersion.logid}">终端版本<shiro:hasPermission name="site:assite:view">${not empty siteVersion.logid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="siteVersion" action="${ctx}/version/siteVersion/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<%-- <div class="control-group">
			<label class="control-label">区域名称:</label>
			<div class="controls">
				<sys:treeselect id="tree" name="tree_id" value="${siteVersion.tree_id}" labelName="tree_name" labelValue="${siteVersion.tree_name}"
					title="区域" url="/sys/area/treeData" extId="${area.name}" cssClass="" allowClear="true"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">站点名称:</label>
                <div class="controls">
               <sys:tableselect id="site" cssStyle="width:210px;height:23px;" name="site_id" value="${siteVersion.site_id}" labelName="site_name" labelValue="${siteVersion.site_name}"
					title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平台代码:</label>
			<div class="controls">
				<form:input path="plat_code" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平台名称:</label>
			<div class="controls">
				<form:input path="plat_name" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平台版本:</label>
			<div class="controls">
				<form:input path="plat_version" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">升级状态:</label>
			<div class="controls">
				<form:select path="upgrade_state" class="input-medium">
					<form:options items="${fns:getDictList('upgrade_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">允许开始升级时间:</label>
			<div class="controls">
				<input id="allowUpg_stime" name="allowUpg_stime" type="text" readonly="readonly" maxlength="20" class="required input-medium Wdate"
					value="${siteVersion.allowUpg_stime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<br/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">允许升级结束时间:</label>
			<div class="controls">
				<input id="allowUpg_etime" name="allowUpg_etime" type="text" readonly="readonly" maxlength="20" class="required input-medium Wdate"
					value="${siteVersion.allowUpg_etime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<br/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="cur_state" class="input-medium">
					<form:options items="${fns:getDictList('use_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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