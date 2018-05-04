<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>补货日志</title>
<meta name="decorator" content="default" />
<style>
#replenishmentChangeSearchForm .input-xlarge{
	width: 100px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/replenishmentChange/">补货日志列表</a></li>
	</ul>
	<form:form id="replenishmentChangeSearchForm" modelAttribute="replenishmentChange" action="${ctx}/inventory/replenishmentChange/list" method="post" class="breadcrumb form-search ">
		<input id="replenishmentChangePageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="replenishmentChangePageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>补货单号：</label>
				<form:input path="orderId" class="input-medium abc" maxlength="30"/>
			</li>
			<li>
				<label>记录时间：</label>
				<input type="text" id="beginTime" name="beginTime" value="${param.beginTime }" class="input-medium Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" > — 
				<input type="text" id="endTime" name="endTime" value="${param.endTime }" class="input-medium Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" >
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return replenishmentChangePage();"/>
				<input class="btn btn-primary" type="button" value="重置" onclick="myResult('replenishmentChangeSearchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"><br></li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<table id="replenishmentChangeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">补货单编号</th>
				<th style="text-align:center;">站点编号</th>
				<th style="text-align:center;">站点名称</th>
				<th style="text-align:center;">操作时间</th>
				<th style="text-align:center;">操作人编号</th>
				<th style="text-align:center;">操作人名称</th>
				<th style="text-align:center;">当前状态</th>
				<th style="text-align:center;">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list }" var="replenishmentChange">
				<tr>
					<td style="text-align: center;">${replenishmentChange.orderId }</td>
					<td style="text-align: center;">${replenishmentChange.siteId }</td>
					<td style="text-align: center;">${replenishmentChange.siteName }</td>
					<td style="text-align: center;">${replenishmentChange.operTime }</td>
					<td style="text-align: center;">${replenishmentChange.operId }</td>
					<td style="text-align: center;">${replenishmentChange.operName }</td>
					<td style="text-align: center;">${operActionMap[replenishmentChange.operAction] }</td>
					<td style="text-align: center;">${replenishmentChange.createTime }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		$(document).ready(function() {			
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出补货日志吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#replenishmentChangeSearchForm").attr("action","${ctx}/inventory/replenishmentChange/export");
						$("#replenishmentChangeSearchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function replenishmentChangePage(n,s){
			if(n) $("#replenishmentChangePageNo").val(n);
			if(s) $("#replenishmentChangePageSize").val(s);
			$("#replenishmentChangeSearchForm").attr("action","${ctx}/inventory/replenishmentChange/list");
			$("#replenishmentChangeSearchForm").submit();
	    	return false;
	    }
	</script>
</body>
</html>