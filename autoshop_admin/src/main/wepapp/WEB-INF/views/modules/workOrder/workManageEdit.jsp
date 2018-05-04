<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工单</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#workForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${types eq 01}">
			<li><a href="${ctx}/workOrder/workManage/">工单列表</a></li>
		</c:if>
		<c:if test="${types eq 02}">
			<li><a href="${ctx}/workOrder/workSolving/">工单列表</a></li>
		</c:if>
		<li class="active"><a>工单${type eq 01?'修改':''}${type eq 02?'受理':''}${type eq 03?'委派':''}${type eq 04?'撤单':''}${type eq 05?'结单':''}${type eq 06?'接单':''}${type eq 07?'退回':''}${type eq 05?'处理':''}</a></li>
	</ul><br/>
	<form:form id="workForm" modelAttribute="work" action="${ctx}/workOrder/workManage/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<input  type = "hidden" name="type" value = "${type}"/>
		<sys:message content="${message}"/>
		<div class="row-fluid">
       		<fieldset>
       			<legend>工单信息</legend>
				<div class="span6">
					<div class="control-group">
						<label class="control-label">工单标题:</label>
						<div class="controls">
							<form:input path="workTitle" readonly="${type eq 04||type eq 05?'true':''}" htmlEscape="false" maxlength="50" class="required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">工单类型:</label>
						<div class="controls">
							<form:select path="workType" class="input-large" disabled="${type eq 04||type eq 05?'true':''}">
								<form:options items="${fns:getDictList('work_type')}" itemLabel="label"  itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
					<c:if test="${empty work.logid}">
						<div class="control-group">
							<label class="control-label">责任人：</label>
							<div class="controls">
								<sys:treeselect id="managerId" cssStyle="width:150px;height:23px;" name="managerId" value="${work.managerId}" labelName="managerName" labelValue="${work.managerName}"
									title="责任人" url="/sys/office/treeData?type=3" allowClear="true" cssClass="required" notAllowSelectParent="true"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</c:if>
					<c:if test="${not empty work.logid}">
						<div class="control-group">
							<label class="control-label">责任人：</label>
							<div class="controls">
								<form:input path="managerName" readonly="true" htmlEscape="false"/>
								<form:hidden path="managerId" htmlEscape="false"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</c:if>
					<c:if test="${type eq 03}">
						<div class="control-group">
							<label class="control-label">处理人：</label>
							<div class="controls">
								<sys:treeselect id="handlerId" cssStyle="width:150px;height:23px;" name="handlerId" value="${work.handlerId}" labelName="handlerName" labelValue="${work.handlerName}"
									title="处理人" url="/sys/office/treeData?type=3" allowClear="true" cssClass="required" notAllowSelectParent="true"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</c:if>
					<c:if test="${type eq 04}">
						<div class="control-group">
							<label class="control-label">处理人：</label>
							<div class="controls">
								<form:input path="handlerName" readonly="true" htmlEscape="false"/>
								<form:hidden path="handlerId" htmlEscape="false"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</c:if>
					<c:if test="${type eq 08||type eq 06}">
						<div class="control-group">
							<label class="control-label">处理人：</label>
							<div class="controls">
								<form:input path="handlerName" readonly="true" htmlEscape="false"/>
								<form:hidden path="handlerId" htmlEscape="false"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">初步处理方案:</label>
							<div class="controls">
								<form:textarea path="handlePlan" disabled="${type eq 08?'true':''}" htmlEscape="false"  rows="3" maxlength="200" class="required input-xxlarge"/>
							</div>
						</div>
					</c:if>
					<c:if test="${type eq 07}">
						<div class="control-group">
							<label class="control-label">退单原因:</label>
							<div class="controls">
								<form:textarea path="handleResult" disabled="${type eq 04||type eq 05?'true':''}" htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge"/>
							</div>
						</div>
					</c:if>
				</div>
				<div class="span6">
					<c:if test="${empty work.logid}">
						<div class="control-group">
							<label class="control-label">站点：</label>
							<div class="controls">
								<sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="siteId" value="${work.siteId}" labelName="siteName" labelValue="${work.siteName}"
									 title="站点信息" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</c:if>
					<c:if test="${not empty work.logid}">
						<div class="control-group">
							<label class="control-label">站点：</label>
							<div class="controls">
								<form:input path="siteName" readonly="true" htmlEscape="false"/>
								<form:hidden path="siteId" htmlEscape="false"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</c:if>
					<div class="control-group">
						<label class="control-label">工单级别:</label>
						<div class="controls">
							<form:select path="workLevel" class="input-large" disabled="${type eq 04||type eq 05?'true':''}" >
								<form:options items="${fns:getDictList('work_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">问题描述:</label>
						<div class="controls">
							<form:input path="questionCont" htmlEscape="false" disabled="${type eq 04||type eq 05?'true':''}" maxlength="200" class="required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<c:if test="${type eq 06||type eq 08}">
						<div class="control-group">
							<label class="control-label">创建时间：</label>
							<div class="controls">
								<form:input path="createTime" readonly="true"  htmlEscape="false"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">实际处理方案:</label>
							<div class="controls">
								<form:textarea path="handleResult" disabled="${type eq 06?'true':''}" htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge"/>
							</div>
						</div>
					</c:if>
				</div>
			</fieldset>
			<fieldset>
        		<legend>设备信息</legend>
	        	<table id="selectMaterialListUl" class="table table-striped table-bordered table-condensed">
					<thead>
				    	<tr>
							<th>设备编号</th>
							<th>设备名称</th>
							<th>设备型号</th>
							<th>设备品牌</th>
							<th>设备尺寸</th>
							<th>设备状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${workDeviceList}" var="workDevice">
							<tr> 
								<td><a href="javascript:showDetail('${workDevice.logid}');">${workDevice.deviceId}</a></td> 
				    			<td>${deviceInfoMap[workDevice.deviceName]}</td> 
								<td>${deviceModelMap[workDevice.deviceModel]}</td>
								<td>${deviceBardMap[workDevice.deviceBard]}</td>
								<td>${workDevice.deviceSize}</td>
								<td>${workDeviceStateMap[workDevice.curState]}</td>
				    		</tr>
						</c:forEach>
					</tbody>
				</table>
        	</fieldset>
		</div>
		<div class="form-actions" style="padding-left: 28%">
			<div style="margin-left:360px">
				<c:if test="${type eq '01'}">
					<input id="btnSubmit"  class="btn btn-primary" type="submit" value="修改"/>
				</c:if>
				<c:if test="${type eq '02'}">
					<input id="btnSubmit"  class="btn btn-primary" type="submit" value="受理"/>
				</c:if>
				<c:if test="${type eq '03'}">
					<input id="btnSubmit"  class="btn btn-primary" type="submit" value="委派"/>
				</c:if>
				<c:if test="${type eq '04'}">
					<input id="btnSubmit"  class="btn btn-primary" type="submit" value="撤单"/>
				</c:if>
				<c:if test="${type eq '05'}">
					<input id="btnSubmit"  class="btn btn-primary" type="submit" value="结单"/>
				</c:if>
				<c:if test="${type eq '06'}">
					<input id="btnSubmit"  class="btn btn-primary" type="submit" value="接单"/>
				</c:if>
				<c:if test="${type eq '07'}">
					<input id="btnSubmit"  class="btn btn-primary" type="submit" value="退回"/>
				</c:if>
				<c:if test="${type eq '08'}">
					<input id="btnSubmit"  class="btn btn-primary" type="submit" value="处理"/>
				</c:if>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</div>
	</form:form>
	
<script type="text/javascript">

//查看物料详情
var showDetail = function(primaryKey) {
	debugger;
	top.$.jBox.open("iframe:${ctx}/workOrder/workManage/detailDevice/" + primaryKey, "查看设备信息详情", 950, 750, {
		buttons:{"关闭":true},
		loaded:function(h){
			$(".jbox-content", top.document).css("overflow-y","hidden");
		}
	});
};

$(document).ready(function() {
	$("#workForm").validate({
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