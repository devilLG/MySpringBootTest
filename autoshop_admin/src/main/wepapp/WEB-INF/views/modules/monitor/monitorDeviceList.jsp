<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/monitor/monitorDevice/">设备信息列表</a></li>
		<li><a href="${ctx}/monitor/monitorDevice/form">设备信息添加</a></li>
	</ul>
	<form:form id="deviceInfo" modelAttribute="deviceInfo" action="${ctx}/monitor/monitorDevice/list" method="post" class="breadcrumb form-search">
		<input id="deviceInfoPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="deviceInfoPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<select name="deviceName">
					<option value="">-- ALL --</option>
					<c:forEach items="${deviceInfoList}" var="deviceInfo">
						<option value="${deviceInfo.value }"
							${deviceInfo.value==param.deviceName?"selected='selected'":""}>${deviceInfo.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li><label>设备型号：</label>
				<select name="deviceModel">
					<option value="">-- ALL --</option>
					<c:forEach items="${deviceModelList}" var="deviceModel">
						<option value="${deviceModel.value }"
							${deviceModel.value==param.deviceModel?"selected='selected'":""}>${deviceModel.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li><label>设备品牌：</label>
				<select name="deviceBard">
					<option value="">-- ALL --</option>
					<c:forEach items="${deviceBardList}" var="deviceBard">
						<option value="${deviceBard.value }"
							${deviceBard.value==param.deviceBard?"selected='selected'":""}>${deviceBard.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li><label>供应商：</label>
				<select name="deviceMfrs">
					<option value="">-- ALL --</option>
					<c:forEach items="${deviceMfrsList}" var="deviceMfrs">
						<option value="${deviceMfrs.value }"
							${deviceMfrs.value==param.deviceMfrs?"selected='selected'":""}>${deviceMfrs.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li><label>创建时间：</label>
				<input  type="text" readonly="readonly" name="createTimeStart" id="mDCreateTimeStart"  class="Wdate input-small"  
					onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'mDCreateTimeEnd\') || \'%y-%M-%d\'}' ,dateStart:'%y-%M-%d',alwaysUseStartDate:true,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
					size="3" value="${param.createTimeStart}"/> - 
				<input  type="text" readonly="readonly" name="createTimeEnd" id="mDCreateTimeEnd"  class="Wdate input-small"  
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'mDCreateTimeStart\')}',maxDate:'%y-%M-%d',dateStart:'%y-%M-%d',alwaysUseStartDate:true,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
					size="3" value="${param.createTimeEnd}"/>
			</li>
			
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('deviceInfo');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="deviceInfoList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>设备名称</th>
					<th>设备型号</th>
					<th>设备品牌</th>
					<th>设备尺寸(长*宽*高 cm³)</th>
					<th>设备生产商</th>
					<th>设备图片地址信息</th>
					<th>设备属性特别描述</th>
					<th>设备所在柜子位置描述</th>
					<th>设备提供的功能描述</th>
					<th>维护周期(天)</th>
					<th>故障等级</th>
					<th>创建时间</th>
					<th style="width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="deviceInfo">
					<tr id="${deviceInfo.deviceId}" >
						<td><a href="${ctx}/monitor/monitorDevice/form?deviceId=${deviceInfo.deviceId}">${deviceInfoMap[deviceInfo.deviceName]}</a></td>
						<td>${deviceModelMap[deviceInfo.deviceModel]}</td>
						<td>${deviceBardMap[deviceInfo.deviceBard]}</td>
						<td>${deviceInfo.deviceSize}</td>
						<td>${deviceMfrsMap[deviceInfo.deviceMfrs]}</td>
						<td title="${deviceInfo.devicePic}">${fn:length(deviceInfo.devicePic) > 20 ? (fn:substring(deviceInfo.devicePic,0,19)).concat("...") : deviceInfo.devicePic }</td>
						<td>${deviceInfo.specialNature}</td>
						<td>${deviceInfo.devicePostion}</td>
						<td>${deviceInfo.deviceEffect}</td>
						<td>${deviceInfo.maintenance}</td>
						<td>${errorLevelInfoMap[deviceInfo.errorLevel]}</td>
						<td>${deviceInfo.createTime}</td>
						<td nowrap>
							<a href="${ctx}/monitor/monitorDevice/delete?deviceId=${deviceInfo.deviceId}" onclick="return confirmx('要删除该设备信息吗？', this.href)">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#deviceInfoPageNo").val(n);
		}
		if(s) {
			$("#deviceInfoPageSize").val(s);
		}
		$("#deviceInfo").attr("action","${ctx}/monitor/monitorDevice/list");
		$("#deviceInfo").submit();
		return false;
	};
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出设备信息数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#deviceInfo").attr("action","${ctx}/monitor/monitorDevice/export");
					$("#deviceInfo").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	
	</script>
</body>
</html>



