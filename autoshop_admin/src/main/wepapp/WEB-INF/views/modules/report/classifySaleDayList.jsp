 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日销售额</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出分类商品日销售额数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/report/classifySaleDay/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/report/classifySaleDay/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/report/classifySaleDay/list">日销售额列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="classifySaleDay" action="${ctx}/report/classifySaleDay/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>分类名称：</label><sys:tableselect id="classify" cssStyle="width:150px;height:23px;" name="classify_id" value="${classifySaleDay.classify_id}" labelName="classify_name" labelValue="${classifySaleDay.classify_name}"
			 title="分类信息" url="/product/productClassify/treeData1" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<li><label>报表日期：</label><input id="rpt_date_classifySaleDayList" style="width:110px" name="rpt_date" value="${classifySaleDay.rpt_date}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead><tr>
		<th>报表日期</th>
		<th>分类名称</th>
		<th>销售金额</th>
		<th>销售数量</th>
		<th>创建日期</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="classifySaleDay">
				<tr>
				<td>${classifySaleDay.rpt_date}</td>
				<td>${classifySaleDay.classify_name}</td>
				<td>${classifySaleDay.sale_money}</td>
				<td>${classifySaleDay.sale_num}</td>
				<td>${classifySaleDay.create_time}</td>
				</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>