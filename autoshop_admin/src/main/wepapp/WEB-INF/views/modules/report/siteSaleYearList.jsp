<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>年销售额</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出年销售额数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/report/siteSaleYear/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/report/siteSaleYear/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/report/siteSaleYear/list">年销售额列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="siteSaleYear" action="${ctx}/report/siteSaleYear/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${siteSaleYear.site_id}" labelName="site_name" labelValue="${siteSaleYear.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<li><label>报表年份：</label><input id="rpt_date_siteSaleYearList" style="width:110px" name="rpt_year" value="${siteSaleYear.rpt_year}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'%y',dateFmt:'yyyy',isShowClear:false});"/>
								</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead><tr>
		<th>报表年份</th>
		<th>站点编号</th>
		<th>站点简称</th>
		<th>销售金额</th>
		<th>销售数量</th>
		<th>补货数量</th>
		<th>补货次数</th>
		<th>缺货数量</th>
		<th>创建时间</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="siteSaleYear">
				<tr>
				<td>${siteSaleYear.rpt_year}</td>
				<td>${siteSaleYear.site_id}</td>
				<td>${siteSaleYear.site_name}</td>
				<td>${siteSaleYear.sale_money}</td>
				<td>${siteSaleYear.sale_num}</td>
				<td>${siteSaleYear.repment_num}</td>
				<td>${siteSaleYear.rement_time}</td>
				<td>${siteSaleYear.lack_num}</td>
				<td>${siteSaleYear.create_time}</td>
				</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>