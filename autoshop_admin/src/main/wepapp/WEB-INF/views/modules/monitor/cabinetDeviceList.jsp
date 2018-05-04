<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/monitor/cabinetDevice/">柜子设备列表</a></li>
		<li><a href="${ctx}/monitor/cabinetDevice/form">柜子设备添加</a></li>
	</ul>
	<form:form id="cabinetDevice" modelAttribute="cabinetDevice" action="${ctx}/monitor/cabinetDevice/list" method="post" class="breadcrumb form-search">
		<input id="cabinetDevicePageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="cabinetDevicePageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<input type="text" name="deviceNameName" value="${deviceInfoMap[param.deviceName]}" class="required" readonly="readonly"/>
				<input type="hidden"  name="deviceName" id="deviceName" value="${param.deviceName}" readonly="readonly" class="required" />
				<input type="hidden"  name="deviceId" id="deviceId" value="${param.deviceId}" readonly="readonly" class="required" />
				<i class="icon-search magnifying" onclick="showDeviceInfo()"></i>
			</li>
			<li><label>创建时间：</label>
				<input  type="text" readonly="readonly" name="createTimeStart" id="cDCreateTimeStart"  class="Wdate input-small"  
					onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'cDCreateTimeEnd\') || \'%y-%M-%d\'}' ,dateStart:'%y-%M-%d',alwaysUseStartDate:true,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
					size="3" value="${param.createTimeStart}"/> - 
				<input  type="text" readonly="readonly" name="createTimeEnd" id="cDCreateTimeEnd"  class="Wdate input-small"  
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'cDCreateTimeStart\')}',maxDate:'%y-%M-%d',dateStart:'%y-%M-%d',alwaysUseStartDate:true,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
					size="3" value="${param.createTimeEnd}"/>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('cabinetDevice');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="cabinetDeviceList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>柜子配置名称</th>
					<th>设备名称</th>
					<th>设备型号</th>
					<th>设备品牌</th>
					<th>设备生产商</th>
					<th>创建时间</th>
					<th style="width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="cabinetDevice">
					<tr id="${cabinetDevice.cadeviceId}" >
						<td><a href="${ctx}/monitor/cabinetDevice/form?cadeviceId=${cabinetDevice.cadeviceId}">${cabinetDevice.cabconfigName}</a></td>
						<td>${deviceInfoMap[cabinetDevice.deviceName]}</td>
						<td>${deviceModelMap[cabinetDevice.deviceModel]}</td>
						<td>${deviceBardMap[cabinetDevice.deviceBard]}</td>
						<td>${deviceMfrsMap[cabinetDevice.deviceMfrs]}</td>
						<td>${cabinetDevice.createTime}</td>
						<td nowrap>
							<a href="${ctx}/monitor/cabinetDevice/delete?cadeviceId=${cabinetDevice.cadeviceId}" onclick="return confirmx('要删除该柜子设备吗？', this.href)">删除</a>
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
			$("#cabinetDevicePageNo").val(n);
		}
		if(s) {
			$("#cabinetDevicePageSize").val(s);
		}
		$("#cabinetDevice").attr("action","${ctx}/monitor/cabinetDevice/list");
		$("#cabinetDevice").submit();
		return false;
	};
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出设备信息数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#cabinetDevice").attr("action","${ctx}/monitor/cabinetDevice/export");
					$("#cabinetDevice").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	
	//选择设备
	var showDeviceInfo = function() {
		 top.$.jBox.open("iframe:${ctx}/monitor/monitorDevice/showDeviceInfo", "选择设备", 950, 600, {
			buttons:{"确认":"ok","关闭":true}, 
			submit:function(v, h, f){
				if (v=="ok"){
					debugger;
					var parms = h.find("iframe")[0].contentWindow.$("#monitorDevice").val();
					if(parms){
						$("#cabinetDevice input[name='deviceId']").val(parms.split(",")[8]);
						$("#cabinetDevice input[name='deviceName']").val(parms.split(",")[0]);
						$("#cabinetDevice input[name='deviceNameName']").val(parms.split(",")[1]);
					}						
				}
			}, 
			loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
	};
	
	</script>
</body>
</html>



