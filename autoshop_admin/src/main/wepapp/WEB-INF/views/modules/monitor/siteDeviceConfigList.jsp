<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/monitor/siteDeviceConfig/">站点设备列表</a></li>
	</ul>
	<form:form id="siteDeviceConfig" modelAttribute="siteDeviceConfig" action="${ctx}/monitor/siteDeviceConfig/list" method="post" class="breadcrumb form-search">
		<input id="siteDeviceConfigPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="siteDeviceConfigPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站点信息：</label>
				<sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="siteId" value="${siteDeviceConfig.siteId}" labelName="siteName" labelValue="${siteDeviceConfig.siteName}"
				 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>设备名称：</label>
				<input type="text" name="deviceNameName" value="${deviceInfoMap[param.deviceName]}" class="required" readonly="readonly"/>
				<input type="hidden"  name="deviceName" id="deviceName" value="${param.deviceName}" readonly="readonly" class="required" />
				<input type="hidden"  name="deviceId" id="deviceId" value="${param.deviceId}" readonly="readonly" class="required" />
				<i class="icon-search magnifying" onclick="showDeviceInfo()"></i>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('siteDeviceConfig');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="siteDeviceConfigList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>站点编号</th>
					<th>站点名称</th>
					<th>设备名称</th>
					<th>设备型号</th>
					<th>设备品牌</th>
					<th>设备生产商</th>
					<th>故障等级</th>
					<th>设备状态</th>
					<th>检测状态</th>
					<th>刷新时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="siteDeviceConfig">
					<tr id="${siteDeviceConfig.sdeviceId}" >
						<td><a href="${ctx}/monitor/siteDeviceConfig/form?sdeviceId=${siteDeviceConfig.sdeviceId}">${siteDeviceConfig.siteId}</a></td>
						<td>${siteDeviceConfig.siteName}</td>
						<td>${deviceInfoMap[siteDeviceConfig.deviceName]}</td>
						<td>${deviceModelMap[siteDeviceConfig.deviceModel]}</td>
						<td>${deviceBardMap[siteDeviceConfig.deviceBard]}</td>
						<td>${deviceMfrsMap[siteDeviceConfig.deviceMfrs]}</td>
						<td>${errorLevelInfoMap[siteDeviceConfig.errorLevel]}</td>
						<td>${siteDeviceStateMap[siteDeviceConfig.curState]}</td>
						<td>${siteDeviceCheckStateMap[siteDeviceConfig.checkState]}</td>
						<td>${siteDeviceConfig.stateTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#siteDeviceConfigPageNo").val(n);
		}
		if(s) {
			$("#siteDeviceConfigPageSize").val(s);
		}
		$("#siteDeviceConfig").attr("action","${ctx}/monitor/siteDeviceConfig/list");
		$("#siteDeviceConfig").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出设备信息数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#siteDeviceConfig").attr("action","${ctx}/monitor/siteDeviceConfig/export");
					$("#siteDeviceConfig").submit();
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
						$("#siteDeviceConfig input[name='deviceId']").val(parms.split(",")[8]);
						$("#siteDeviceConfig input[name='deviceName']").val(parms.split(",")[0]);
						$("#siteDeviceConfig input[name='deviceNameName']").val(parms.split(",")[1]);
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



