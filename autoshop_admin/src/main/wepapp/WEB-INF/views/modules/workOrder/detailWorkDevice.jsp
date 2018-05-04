<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<meta name="decorator" content="default"/>
	<style>
		.control-group{
			margin-left: -40px;
		}
		.control-label{
			padding-top: 6px !important;
		}
		.controls input, .controls textarea{
			background: none;
		}
	</style>
</head>
<body>
	<form:form method="post" class="form-horizontal" modelAttribute="workDevice" style="margin-top: 10px;">
		<fieldset>
			<legend>设备信息</legend>
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label">设备编号：</label>
						<div class="controls">
							<input type="text" value="${workDevice.deviceId}" class="m-wrap small" readonly="readonly"/>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label">设备名称：</label>
						<div class="controls">
							<form:select path="deviceName" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('device_info')}" itemLabel="label"  itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label">设备型号：</label>
						<div class="controls">
							<form:select path="deviceModel" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('device_model')}" itemLabel="label"  itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label">设备品牌：</label>
						<div class="controls">
							<form:select path="deviceBard" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('device_bard')}" itemLabel="label"  itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label">设备尺寸：</label>
						<div class="controls">
							<input type="text" value="${workDevice.deviceSize}" class="m-wrap small" readonly="readonly"  />
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label">设备状态：</label>
						<div class="controls">
							<form:select path="curState" class="input-large"  disabled="true">
								<form:options items="${fns:getDictList('workDevice_state')}" itemLabel="label"  itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		
		<fieldset>
       		<legend>设备运行参数信息</legend>
        	<table id="siteEnvList" class="table table-striped table-bordered table-condensed">
				<thead>
			    	<tr>
						<th>参数名称</th>
						<th>最高值</th>
						<th>最低值</th>
						<th>警告最高值</th>
						<th>警告最低值</th>
						<th>实际值</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${siteEnvList}" var="siteEnv">
						<tr> 
							<td>${siteEnv.envName}</td> 
			    			<td>${siteEnv.envEvalue}</td> 
			    			<td>${siteEnv.envSvalue}</td> 
							<td>${siteEnv.envWevalue}</td>
							<td>${siteEnv.envWsvalue}</td>
							<td>${siteEnv.runValue}</td>
							<td>${workDeviceStateMap[siteEnv.curState]}</td>
			    		</tr>
					</c:forEach>
				</tbody>
			</table>
       	</fieldset>
	</form:form>
</body>
</html>