<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>补货计划管理</title>
	<meta name="decorator" content="default"/>
	<style>
		#replenishmentPlanInputForm .height{
			height: 75px;
		}
		#replenishmentPlanInputForm .input-xlarge{
			width: 15%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/replenishmentPlan/">补货计划列表</a></li>
		<li class="active">
			<a href="${ctx}/inventory/replenishmentPlan/form?planId=${replenishmentPlan.planId}"><%-- <shiro:hasPermission name="inventory:replenishmentPlan:edit"> --%>${not empty replenishmentPlan.planId?'修改':'添加'}<%-- </shiro:hasPermission><shiro:lacksPermission name="inventory:replenishmentPlan:edit">查看</shiro:lacksPermission> --%>补货计划</a>
		</li>
	</ul><br/>
	<form:form id="replenishmentPlanInputForm" modelAttribute="replenishmentPlan" action="${ctx}/inventory/replenishmentPlan/save" method="post" class="form-horizontal">
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
					<sys:tableselect id="target_site" name="site_id" value="${replenishmentPlan.planType eq '01' ? replenishmentPlan.targetId : ''}" labelName="site_name" labelValue="${replenishmentPlan.planType eq '01' ? replenishmentPlan.targetName : ''}"
				 		title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				 	<font color="red">*</font>
			 	</div>	
			 	<div id="area">
				 	<sys:treeselect id="area" name="area_id" value="${replenishmentPlan.planType eq '02' ? replenishmentPlan.targetId : ''}" labelName="area_name" labelValue="${replenishmentPlan.planType eq '02' ? replenishmentPlan.targetName : ''}"
						title="区域名称" url="/sys/area/treeData" cssClass="required" allowClear="true"/>
					<font color="red">*</font>
				</div>
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
		<c:if test="${not empty replenishmentPlan.planId }">
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
			<%-- <shiro:hasPermission name="inventory:replenishmentPlan:edit"> --%><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/><%-- </shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script type="text/javascript">
		$(document).ready(function() {
			changeTarget("${replenishmentPlan.planType }");
			changeCycleValue("${replenishmentPlan.cycleType }");
			
			$("#replenishmentPlanInputForm").validate({
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
				$("#cycleValue").attr("onblur","checkValue(1, 7)");
			}else {
				$("#cycleValue").attr("onblur","checkValue(1, 30)");
			}
		}
		
		var checkValue = function (min, big) {
			debugger;
			var num = $("#cycleValue").val();
			if (num == undefined || num == null || big < num || num < min) {
				toastr.error("请输入符合周期类型的数据。");
				$("#cycleValue").focus();
			}
		}
	</script>
</body>
</html>