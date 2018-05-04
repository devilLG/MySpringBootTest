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
		<li><a href="${ctx}/site/siteDevice/list">柜箱排列列表</a></li>
		<li class="active"><a href="${ctx}/site/siteDevice/form?logid=${siteDevice.logid}">柜箱排列${not empty siteDevice.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="siteDevice" action="${ctx}/site/siteDevice/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">站点名称:</label>
                <div class="controls">
               <sys:tableselect id="site" cssStyle="width:210px;height:23px;" name="site_id" value="${siteDevice.site_id}" labelName="site_name" labelValue="${siteDevice.site_name}"
					title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">柜型:</label>
                <div class="controls">
               <sys:tableselect id="cabinettype" cssStyle="width:210px;height:23px;" name="cabinettype_id" value="${siteDevice.cabinettype_id}" labelName="cabinettype_name" labelValue="${siteDevice.cabinettype_name}"
					title="柜子类型" url="/standard/cabinetType/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		
		<%-- -%><c:if test="${not empty siteDevice.logid}">
		<div class="control-group">
			<label class="control-label">柜型:</label>
			<div class="controls">
				<form:input path="cabinet_type" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>--%>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>