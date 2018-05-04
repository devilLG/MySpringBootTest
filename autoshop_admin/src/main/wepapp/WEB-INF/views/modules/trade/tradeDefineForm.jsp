<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易管理</title>
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
		<li><a href="${ctx}/trade/tradeDefine/">交易列表</a></li>
		<li class="active"><a href="${ctx}/trade/tradeDefine/form?trade_code=${tradeDefine.trade_code}">交易<shiro:hasPermission name="site:assite:view">${not empty tradeDefine.trade_code?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tradeDefine" action="${ctx}/trade/tradeDefine/save" method="post" class="form-horizontal">
	<form:hidden path="trade_code"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">平台代码:</label>
			<div class="controls">
				<form:input path="plat_code" htmlEscape="false" maxlength="2" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易全称:</label>
			<div class="controls">
				<form:input path="trade_name" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">描述:</label>
			<div class="controls">
				<form:input path="trade_desc" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:input path="trade_state" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易日志:</label>
			<div class="controls">
				<form:input path="trade_log" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报文日志:</label>
			<div class="controls">
				<form:input path="pkg_log" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用方式:</label>
			<div class="controls">
				<form:input path="use_type" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="site:assite:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>