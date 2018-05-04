<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>补货计划管理</title>
	<meta name="decorator" content="default"/>
	<style>
		#checkPlanInputForm .height{
			height: 75px;
		}
		#checkPlanInputForm .input-xlarge{
			width: 15%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/checkPlan/">补货计划列表</a></li>
		<li class="active">
			<a href="${ctx}/inventory/checkPlan/form?planId=${checkPlan.planId}"><%-- <shiro:hasPermission name="inventory:checkPlan:edit"> --%>${not empty checkPlan.planId?'修改':'添加'}<%-- </shiro:hasPermission><shiro:lacksPermission name="inventory:checkPlan:edit">查看</shiro:lacksPermission> --%>补货计划</a>
		</li>
	</ul><br/>
	<form:form id="checkPlanInputForm" modelAttribute="checkPlan" action="${ctx}/inventory/checkPlan/save" method="post" class="form-horizontal">
		<form:hidden path="planId"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">对象类型:</label>
			<div class="controls">
				<form:select path="planType" class="required input-xlarge" onchange="changeTarget(this.value)">
					<form:options items="${fns:getDictList('planType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">对象名称:</label>
			<div class="controls" id="chooseTarget">
				<div id="site">
					<sys:tableselect id="target_site" name="site_id" value="${checkPlan.planType eq '01' ? checkPlan.targetId : ''}" labelName="site_name" labelValue="${checkPlan.planType eq '01' ? checkPlan.targetName : ''}"
				 		title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				 	<font color="red">*</font>
			 	</div>	
			 	<div id="area">
				 	<sys:treeselect id="area" name="area_id" value="${checkPlan.planType eq '02' ? checkPlan.targetId : ''}" labelName="area_name" labelValue="${checkPlan.planType eq '02' ? checkPlan.targetName : ''}"
						title="区域名称" url="/sys/area/treeData" cssClass="required" allowClear="true"/>
					<font color="red">*</font>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">盘点对象:</label>
			<div class="controls">
				<form:select path="checkObj" class="required input-xlarge">
					<form:options items="${fns:getDictList('checkObj')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">盘点方式:</label>
			<div class="controls">
				<form:select path="checkType" class="required input-xlarge">
					<form:options items="${fns:getDictList('checkType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">周期类型:</label>
			<div class="controls">
				<form:select path="cycleType" class="required input-xlarge" onchange="changeCycleType(this.value)">
					<form:options items="${fns:getDictList('cycleType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">周期值:</label>
			<div class="controls">
				<form:input path="cycleValue" htmlEscape="false" maxlength="30" class="required input-xlarge digits" />
				<span class="help-inline">（表示每周几或者每月几号）<font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${not empty checkPlan.planId }">
			<div class="control-group">
				<label class="control-label">状态:</label>
				<div class="controls">
					<form:select path="curState" class="required input-xlarge">
						<form:options items="${fns:getDictList('Cur_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<%-- <shiro:hasPermission name="inventory:checkPlan:edit"> --%><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/><%-- </shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script type="text/javascript">
		$(document).ready(function() {
			changeTarget("${checkPlan.planType }");
			changeCycleValue("${checkPlan.cycleType }");
			
			$("#checkPlanInputForm").validate({
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
		
		var changeTarget = function (type) {
			if (type == "01") {
				$("#site").show();
				$("#area").hide();
			}else {
				$("#site").hide();
				$("#area").show();
			}
		}
		
		var changeCycleType = function (type) {
			$("#cycleValue").val("1");
			changeCycleValue(type);
		}
		
		var changeCycleValue = function (type) {
			if (type == "1") {//每周
				$("#cycleValue").removeClass("month_v");
				$("#cycleValue").addClass("week_v");
			}else {
				$("#cycleValue").addClass("month_v");
				$("#cycleValue").removeClass("week_v");
			}
		}
		
		var checkValue = function (min, big) {
			var num = $("#cycleValue").val();
			if (num == undefined || num == null || big < num || num < min) {
				toastr.error("请输入符合周期类型的数据。");
				$("#cycleValue").focus();
			}
		}
	</script>
</body>
</html>