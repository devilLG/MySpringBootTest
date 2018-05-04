<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>盘点日志</title>
<meta name="decorator" content="default" />
<style>
#checkChangeSearchForm .input-xlarge{
	width: 100px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/checkChange/">盘点日志列表</a></li>
	</ul>
	<form:form id="checkChangeSearchForm" modelAttribute="checkChange" action="${ctx}/inventory/checkChange/list" method="post" class="breadcrumb form-search ">
		<input id="checkChangePageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="checkChangePageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>盘点单号：</label>
				<form:input path="orderId" class="input-medium abc" maxlength="30"/>
			</li>
			<li>
				<label>记录时间：</label>
				<input type="text" id="beginTime" name="beginTime" value="${param.beginTime }" class="input-medium Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" > — 
				<input type="text" id="endTime" name="endTime" value="${param.endTime }" class="input-medium Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" >
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return checkChangePage();"/>
				<input class="btn btn-primary" type="button" value="重置" onclick="myResult('checkChangeSearchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"><br></li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<table id="checkChangeTable" class="table table-striped table-bordered table-condensed">
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
			<c:forEach items="${page.list }" var="checkChange">
				<tr>
					<td style="text-align: center;">${checkChange.orderId }</td>
					<td style="text-align: center;">${checkChange.siteId }</td>
					<td style="text-align: center;">${checkChange.siteName }</td>
					<td style="text-align: center;">${checkChange.operTime }</td>
					<td style="text-align: center;">${checkChange.operId }</td>
					<td style="text-align: center;">${checkChange.operName }</td>
					<td style="text-align: center;">${operActionMap[checkChange.operAction] }</td>
					<td style="text-align: center;">${checkChange.createTime }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		$(document).ready(function() {			
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出盘点日志吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#checkChangeSearchForm").attr("action","${ctx}/inventory/checkChange/export");
						$("#checkChangeSearchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function checkChangePage(n,s){
			if(n) $("#checkChangePageNo").val(n);
			if(s) $("#checkChangePageSize").val(s);
			$("#checkChangeSearchForm").attr("action","${ctx}/inventory/checkChange/list");
			$("#checkChangeSearchForm").submit();
	    	return false;
	    }
	</script>
</body>
</html>