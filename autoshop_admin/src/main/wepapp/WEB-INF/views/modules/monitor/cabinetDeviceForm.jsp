<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品规格</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#cabinetDeviceForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/monitor/cabinetDevice/">柜子设备列表</a></li>
		<li class="active"><a href="${ctx}/monitor/cabinetDevice/form?cadeviceId=${cabinetDevice.cadeviceId}">柜子设备${not empty cabinetDevice.logid?'详情':'添加'}</a></li>
	</ul><br/>
	<form:form id="cabinetDeviceForm" modelAttribute="cabinetDevice" action="${ctx}/monitor/cabinetDevice/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<form:hidden path="cadeviceId"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="span6">
				<div class="control-group">
					<label class="control-label">柜子配置名称:</label>
					<div class="controls">
					<c:if test="${empty cabinetDevice.logid}">
						<sys:tableselect id="cabconfig" cssStyle="width:210px;height:23px;" name="cabconfigId" value="${cabinetDevice.cabconfigId}" labelName="cabconfigName" labelValue="${cabinetDevice.cabconfigName}"
						 title="柜子规格" url="/standard/cabinetStandard/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</c:if>
					<c:if test="${not empty cabinetDevice.logid}">
					 	<form:input path="cabconfigId" htmlEscape="false"  readonly="true"/>
					 	<span class="help-inline"><font color="red">*</font> </span>
					</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">设备型号:</label>
					<div class="controls">
						<input type="text" name="deviceModelName" value="${deviceModelMap[cabinetDevice.deviceModel]}" class="required" readonly="readonly"/> 
						<form:hidden path="deviceModel" htmlEscape="false" class="required" readonly="true"/> 
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">设备生产商:</label>
					<div class="controls">
						<input type="text" name="deviceMfrsName" value="${deviceMfrsMap[cabinetDevice.deviceMfrs]}" class="required" readonly="readonly"/> 
						<form:hidden path="deviceMfrs" htmlEscape="false" class="required" readonly="true"/> 
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<label class="control-label">设备名称:</label>
					<div class="controls">
						<input type="text" name="deviceNameName" value="${deviceInfoMap[cabinetDevice.deviceName]}" class="required" readonly="readonly"/>
						<input type="hidden"  name="deviceName" id="deviceName" value="${cabinetDevice.deviceName}" readonly="readonly" class="required" />
						<input type="hidden"  name="deviceId" id="deviceId" value="${cabinetDevice.deviceId}" readonly="readonly" class="required" />
						<c:if test="${empty cabinetDevice.logid}">
							<i class="icon-search magnifying" onclick="showDeviceInfo()"></i>
						</c:if>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">设备品牌:</label>
					<div class="controls">
						<input type="text" name="deviceBardName" value="${deviceBardMap[cabinetDevice.deviceBard]}" class="required" readonly="readonly"/> 
						<form:hidden path="deviceBard" htmlEscape="false" class="required" readonly="true"/> 
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<c:if test="${empty cabinetDevice.logid}">
				<input id="btnSubmit" class="btn btn-primary" onclick="return toSave()" value="保 存"/>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
<script type="text/javascript">

//选择设备
var showDeviceInfo = function() {
	 top.$.jBox.open("iframe:${ctx}/monitor/monitorDevice/showDeviceInfo", "选择设备", 950, 600, {
		buttons:{"确认":"ok","关闭":true}, 
		submit:function(v, h, f){
			if (v=="ok"){
				debugger;
				var parms = h.find("iframe")[0].contentWindow.$("#monitorDevice").val();
				if(parms){
					$("#cabinetDeviceForm input[name='deviceName']").val(parms.split(",")[0]);
					$("#cabinetDeviceForm input[name='deviceNameName']").val(parms.split(",")[1]);
					$("#cabinetDeviceForm input[name='deviceBard']").val(parms.split(",")[2]);
					$("#cabinetDeviceForm input[name='deviceBardName']").val(parms.split(",")[3]);
					$("#cabinetDeviceForm input[name='deviceModel']").val(parms.split(",")[4]);
					$("#cabinetDeviceForm input[name='deviceModelName']").val(parms.split(",")[5]);
					$("#cabinetDeviceForm input[name='deviceMfrs']").val(parms.split(",")[6]);
					$("#cabinetDeviceForm input[name='deviceMfrsName']").val(parms.split(",")[7]);
					$("#cabinetDeviceForm input[name='deviceId']").val(parms.split(",")[8]);
				}						
			}
		}, 
		loaded:function(h){
			$(".jbox-content", top.document).css("overflow-y","hidden");
		}
	});
};

//表单提交
	var toSave = function(){
		debugger;
		//设备不可重复
		var checkdeviceName = true;
		var save = true;
		var logid = $("#cabinetDeviceForm input[name='logid']").val();
		var cabconfigId = $("#cabinetDeviceForm input[name='cabconfigId']").val();
		var deviceName = $("#cabinetDeviceForm select[name='deviceName']").val();
		$.ajax({
			type : "POST",
			url : "${ctx}/monitor/cabinetDevice/checkDeviceName/" + logid+","+cabconfigId + "," + deviceName,
			async : false,
			success : function(ret) {
				debugger;
				if(ret == "0") {
					toastr.error("该柜子已配置相同设备！");
					save = false;
				}
			}
		});
		//主表信息验证
		if(cabconfigId == "" || cabconfigId == null){
			toastr.error("请选择柜子配置名称");
			save = false;
		}
		if(save){
			$("#cabinetDeviceForm").submit();
		} else {
			return;
		}
	}
</script>
</body>
</html>