<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/monitor/siteDeviceLog/">设备日志列表</a></li>
	</ul>
	<form:form id="siteDeviceLog" modelAttribute="siteDeviceLog" action="${ctx}/monitor/siteDeviceLog/list" method="post" class="breadcrumb form-search">
		<input id="siteDeviceLogPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="siteDeviceLogPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站点信息：</label>
				<sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="siteId" value="${siteDeviceLog.siteId}" labelName="siteName" labelValue="${siteDeviceLog.siteName}"
				 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>生成时间：</label>
				<input  type="text" readonly="readonly" name="generateTimeStart" id="sDgenerateTimeStart"  class="Wdate input-small"  
					onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'sDGenerateTimeEnd\') || \'%y-%M-%d\'}' ,dateStart:'%y-%M-%d',alwaysUseStartDate:true,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
					size="3" value="${param.generateTimeStart}"/> - 
				<input  type="text" readonly="readonly" name="generateTimeEnd" id="sDGenerateTimeEnd"  class="Wdate input-small"  
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'mDCreateTimeStart\')}',maxDate:'%y-%M-%d',dateStart:'%y-%M-%d',alwaysUseStartDate:true,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
					size="3" value="${param.generateTimeEnd}"/>
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
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('siteDeviceLog');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="siteDeviceLogList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>站点编号</th>
					<th>站点名称</th>
					<th>公司名称</th>
					<th>设备名称</th>
					<th>发出内容</th>
					<th>结果内容</th>
					<th>操作状态</th>
					<th>日志等级</th>
					<th>操作内容</th>
					<th>操作者编号</th>
					<th>操作者名称</th>
					<th>生成时间</th>
					<th>创建时间时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="siteDeviceLog">
					<tr id="${siteDeviceLog.sitedeviceId}" >
						<td>${siteDeviceLog.siteId}</td>
						<td>${siteDeviceLog.siteName}</td>
						<td>${siteDeviceLog.corpName}</td>
						<td>${deviceInfoMap[siteDeviceLog.deviceName]}</td>
						<td>${siteDeviceLog.sendCmd}</td>
						<td>${siteDeviceLog.acceptCmd}</td>
						<td>${operStateMap[siteDeviceLog.operState]}</td>
						<td>${logLevelMap[siteDeviceLog.logLevel]}</td>
						<td title="${siteDeviceLog.cont}">${fn:length(siteDeviceLog.cont) > 20 ?(fn:substring(siteDeviceLog.cont,0,15)).concat("...") : siteDeviceLog.cont}</td>
						<td>${siteDeviceLog.operId}</td>
						<td>${siteDeviceLog.operName}</td>
						<td>${siteDeviceLog.generateTime}</td>
						<td>${siteDeviceLog.createTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#siteDeviceLogPageNo").val(n);
		}
		if(s) {
			$("#siteDeviceLogPageSize").val(s);
		}
		$("#siteDeviceLog").attr("action","${ctx}/monitor/siteDeviceLog/list");
		$("#siteDeviceLog").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出设备信息数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#siteDeviceLog").attr("action","${ctx}/monitor/siteDeviceLog/export");
					$("#siteDeviceLog").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	
	</script>
</body>
</html>



