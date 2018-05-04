<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点规格管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#platVersionForm").validate({
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
		
		function validCheckForm(){
		debugger;
	     	var doc_size = $("#doc_size_platVersionForm").val();
		    if(parseFloat(doc_size) > 51200){
				toastr.warning("文件大小不能超过51200KB(50M)");
				return false;
			}
		    $("#platVersionForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/version/platVersion/">平台版本列表</a></li>
		<li class="active"><a href="${ctx}/version/platVersion/form?id=${platVersion.logid}">平台版本<shiro:hasPermission name="site:assite:view">${not empty platVersion.logid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="platVersionForm" modelAttribute="platVersion" action="${ctx}/version/platVersion/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<%-- <div class="control-group">
			<label class="control-label">平台代码:</label>
			<div class="controls">
				<form:input path="plat_code" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平台名称:</label>
			<div class="controls">
				<form:input path="plat_name" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">平台名称:</label>
			<div class="controls">
				<form:select path="plat_code" class="input-medium">
					<form:options items="${fns:getDictList('plat_code')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">平台版本:</label>
			<div class="controls">
				<form:input path="plat_version" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下载地址:</label>
			<div class="controls">
				<form:input path="down_url" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开启日志:</label>
			<div class="controls">
				<form:select path="upgrade_log" class="input-medium">
					<form:options items="${fns:getDictList('is_log')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">升级标识:</label>
			<div class="controls">
				<form:input path="upgrade_id" htmlEscape="false" maxlength="11" class="required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">强制安装:</label>
			<div class="controls">
				<form:select path="upgrade_install" class="input-medium">
					<form:options items="${fns:getDictList('upgrade_install')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本有效开始时间:</label>
			<div class="controls">
				<input id="valid_stime" name="valid_stime" type="text" readonly="readonly" maxlength="20" class="required input-medium Wdate"
					value="${platVersion.valid_stime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<br/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效结束时间:</label>
			<div class="controls">
				<input id="valid_etime" name="valid_etime" type="text" readonly="readonly" maxlength="20" class="required input-medium Wdate"
					value="${platVersion.valid_etime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<br/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本状态:</label>
			<div class="controls">
				<form:select path="cur_state" class="input-medium">
					<form:options items="${fns:getDictList('use_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">升级文件名称:</label>
			<div class="controls">
				<form:input path="doc_name" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件大小:</label>
			<div class="controls">
				<form:input path="doc_size" htmlEscape="false" maxlength="50" class="required number" id="doc_size_platVersionForm"/>(KB)
				<span class="help-inline"><font color="red">*</font> 51200KB (50M) 以内</span>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" onclick="validCheckForm()" value="保 存" type="button"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>