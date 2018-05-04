<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>换货日志</title>
<meta name="decorator" content="default" />
<style>
#barterChangeSearchForm .input-xlarge{
	width: 100px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/barterChange/">换货日志列表</a></li>
	</ul>
	<form:form id="barterChangeSearchForm" modelAttribute="barterChange" action="${ctx}/inventory/barterChange/list" method="post" class="breadcrumb form-search ">
		<input id="barterChangePageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="barterChangePageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>换货单号：</label>
				<form:input path="orderId" class="input-medium abc" maxlength="30"/>
			</li>
			<li>
				<label>记录时间：</label>
				<input type="text" id="beginTime" name="beginTime" value="${param.beginTime }" class="input-medium Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" > — 
				<input type="text" id="endTime" name="endTime" value="${param.endTime }" class="input-medium Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" >
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return barterChangePage();"/>
				<input class="btn btn-primary" type="button" value="重置" onclick="myResult('barterChangeSearchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"><br></li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<table id="barterChangeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">盘点单编号</th>
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
			<c:forEach items="${page.list }" var="barterChange">
				<tr>
					<td style="text-align: center;">${barterChange.orderId }</td>
					<td style="text-align: center;">${barterChange.siteId }</td>
					<td style="text-align: center;">${barterChange.siteName }</td>
					<td style="text-align: center;">${barterChange.operTime }</td>
					<td style="text-align: center;">${barterChange.operId }</td>
					<td style="text-align: center;">${barterChange.operName }</td>
					<td style="text-align: center;">${operActionMap[barterChange.operAction] }</td>
					<td style="text-align: center;">${barterChange.createTime }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		$(document).ready(function() {			
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出换货日志吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#barterChangeSearchForm").attr("action","${ctx}/inventory/barterChange/export");
						$("#barterChangeSearchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function barterChangePage(n,s){
			if(n) $("#barterChangePageNo").val(n);
			if(s) $("#barterChangePageSize").val(s);
			$("#barterChangeSearchForm").attr("action","${ctx}/inventory/barterChange/list");
			$("#barterChangeSearchForm").submit();
	    	return false;
	    }
	</script>
</body>
</html>