<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>播控权限</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#advertAuthForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/broadcast/advertAuth/">播控权限列表</a></li>
		<li class="active"><a href="${ctx}/broadcast/advertAuth/form?logid=${advertAuth.logid}">播控权限${not empty advertAuth.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="advertAuthForm" modelAttribute="advertAuth" action="${ctx}/broadcast/advertAuth/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<form:hidden path="authId"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="span6">
				<c:if test="${advertAuth.logid ne null and advertAuth.logid ne ''}">
					<div class="control-group">
						<label class="control-label">类型:</label>
						<div class="controls">
							<form:select path="targetType" disabled="true" class="input-large" onchange="typeChange(this)">
								<form:options items="${fns:getDictList('target_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${advertAuth.logid eq null or advertAuth.logid eq ''}">
					<div class="control-group">
						<label class="control-label">类型:</label>
						<div class="controls">
							<form:select path="targetType" class="input-large" onchange="typeChange(this)">
								<form:options items="${fns:getDictList('target_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<div class="control-group">
					<label class="control-label">配置编码:</label>
					<div class="controls">
						<input type="text"  name="configId" id="configId" value="${advertAuth.configId}" readonly="readonly" class="required" />
						<i class="icon-search magnifying" onclick="showAdvertConfigInfo()"></i>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">计划开始时间:</label>
					<div class="controls">
						<form:input path="planStime" htmlEscape="false" class="required" readonly="true"/> 
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<c:if test="${advertAuth.logid ne null and advertAuth.logid ne ''}">
					<div class="control-group">
						<label class="control-label">状态:</label>
						<div class="controls">
							<form:select path="curState" class="input-large">
								<form:options items="${fns:getDictList('advertConfig_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
			</div>
			<div class="span6">
				<c:if test="${advertAuth.logid ne null and advertAuth.logid ne ''}">
					<div id="siteDiv" class="control-group" style="">
						<label class="control-label">站点:</label>
						<div class="controls">
							<sys:tableselect id="site" name="targetId" disabled="disabled" value="${advertAuth.targetId}" labelName="targetName" labelValue="${advertAuth.targetName}"
						 		title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
						 		<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div id="areaDiv" class="control-group" style="display: none">
						<label class="control-label">区域:</label>
						<div class="controls">
							<sys:treeselect id="tree" name="targetId" disabled="disabled"  value="${advertAuth.targetId}" labelName="targetName" labelValue="${advertAuth.targetName}"
								title="区域名称" url="/sys/area/treeData" extId="${area.name}"  cssClass="required" allowClear="true"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</c:if>
				<c:if test="${advertAuth.logid eq null or advertAuth.logid eq ''}">
					<div id="siteDiv" class="control-group" style="">
						<label class="control-label">站点:</label>
						<div class="controls">
							<sys:tableselect id="site" name="targetId"  value="${advertAuth.targetId}" labelName="targetName" labelValue="${advertAuth.targetName}"
						 		title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
						 		<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div id="areaDiv" class="control-group" style="display: none">
						<label class="control-label">区域:</label>
						<div class="controls">
							<sys:treeselect id="tree" name="targetId" value="${advertAuth.targetId}" labelName="targetName" labelValue="${advertAuth.targetName}"
								title="区域名称" url="/sys/area/treeData" extId="${area.name}"  cssClass="required" allowClear="true"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</c:if>
				<div class="control-group">
					<label class="control-label">配置名称:</label>
					<div class="controls">
						<input type="text"  name="configName" id="configName" value="${advertAuth.configName}" readonly="readonly" class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">计划结束时间:</label>
					<div class="controls">
						<form:input path="planEtime" htmlEscape="false" class="required" readonly="true" /> 
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<div style="margin-left:360px">
				<input id="btnSubmit"  class="btn btn-primary" type="submit" value="保 存"/>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</div>
	</form:form>
	
<script type="text/javascript">
//格式验证
function typeChange(e) {
	debugger;
	var type = $(e).val();
	if(type == 1){
		$("#areaDiv").css("display","none");
		$("#siteDiv").css("display","");
		$("#tree_id").val("");
		$("#area_name").val("");
	} else {
		$("#siteDiv").css("display","none");
		$("#areaDiv").css("display","");
		$("#site_id").val("");
		$("#site_name").val("");
	}
}

//	选择配置
var showAdvertConfigInfo = function() {
	 top.$.jBox.open("iframe:${ctx}/broadcast/advertConfig/showAdvertConfigInfo", "选择广告配置", 950, 600, {
		buttons:{"确认":"ok","关闭":true}, 
		submit:function(v, h, f){
			if (v=="ok"){
				debugger;
				var parms = h.find("iframe")[0].contentWindow.$("#advertConfig_parms").val();
				if(parms){
					$("#advertAuthForm input[name='configId']").val(parms.split(",")[0]);
					$("#advertAuthForm input[name='configName']").val(parms.split(",")[1]);
					$("#advertAuthForm input[name='planStime']").val(parms.split(",")[2]);
					$("#advertAuthForm input[name='planEtime']").val(parms.split(",")[3]);
				}						
			}
		}, 
		loaded:function(h){
			$(".jbox-content", top.document).css("overflow-y","hidden");
		}
	});
};


$(document).ready(function() {
	$("#advertAuthForm").validate({
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
</body>
</html>