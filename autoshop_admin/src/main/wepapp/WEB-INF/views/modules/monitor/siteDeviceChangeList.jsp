<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/monitor/siteDeviceChange/">设备日志列表</a></li>
	</ul>
	<form:form id="siteDeviceChange" modelAttribute="siteDeviceChange" action="${ctx}/monitor/siteDeviceChange/list" method="post" class="breadcrumb form-search">
		<input id="siteDeviceChangePageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="siteDeviceChangePageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站点信息：</label>
				<sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="siteId" value="${siteDeviceChange.siteId}" labelName="siteName" labelValue="${siteDeviceChange.siteName}"
				 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
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
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('siteDeviceChange');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="siteDeviceChangeList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>站点编号</th>
					<th>站点名称</th>
					<th>公司名称</th>
					<th>设备名称</th>
					<th>操作动作</th>
					<th>操作时间</th>
					<th>操作者编号</th>
					<th>操作者名称</th>
					<th>生成时间</th>
					<th>创建时间时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="siteDeviceChange">
					<tr id="${siteDeviceChange.sitedeviceId}" >
						<td>${siteDeviceChange.siteId}</td>
						<td>${siteDeviceChange.siteName}</td>
						<td>${siteDeviceChange.corpName}</td>
						<td>${deviceInfoMap[siteDeviceChange.deviceName]}</td>
						<td>${operActionMap[siteDeviceChange.operAction]}</td>
						<td>${siteDeviceChange.operTime}</td>
						<td>${siteDeviceChange.operId}</td>
						<td>${siteDeviceChange.operName}</td>
						<td title="${siteDeviceChange.cont}">${fn:length(siteDeviceChange.cont) > 20 ?(fn:substring(siteDeviceChange.cont,0,15)).concat("...") : siteDeviceChange.cont}</td>
						<td>${siteDeviceChange.createTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#siteDeviceChangePageNo").val(n);
		}
		if(s) {
			$("#siteDeviceChangePageSize").val(s);
		}
		$("#siteDeviceChange").attr("action","${ctx}/monitor/siteDeviceChange/list");
		$("#siteDeviceChange").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出设备信息数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#siteDeviceChange").attr("action","${ctx}/monitor/siteDeviceChange/export");
					$("#siteDeviceChange").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	
	</script>
</body>
</html>



