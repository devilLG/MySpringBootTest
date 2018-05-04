<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出货道信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/site/siteGoods/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			});
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/site/siteGoods/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/site/siteGoods/">货道列表</a></li>
		<%-- -%><shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/site/assite/form">站点添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="siteGoods" action="${ctx}/site/siteGoods/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点名称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${siteGoods.site_id}" labelName="site_name" labelValue="${siteGoods.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<label>状态：</label><form:select path="cur_state" class="input-medium">
			        <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('cur_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></li>
				<li><label>温度环境：</label><form:select path="temper_type" class="input-medium">
			        <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('cabinetTemp_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>站点编号</th>
		<th>站点简称</th>
		<th>柜号</th>
		<th>列数</th>
		<th>层数</th>
		<th>货道号</th>
		<th>货道类型</th>
		<th>货道状态</th>
		<th>温度环境</th>
		<th>状态时间</th>
		<th>刷新时间</th>
		<th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="siteGoods">
			<tr>
				<td>${siteGoods.site_id}</td>
				<td>${siteGoods.site_name}</td>
				<td>${siteGoods.cabinet_id}</td>
				<td>${siteGoods.cloumn_id}</td>
				<td>${siteGoods.layer_num}</td>
				<td>${siteGoods.box_id}</td>
				<td>${siteGoods.box_type}</td>
				<td>${fns:getDictLabel(siteGoods.cur_state, 'cur_state', '无')}</td>
				<td>${fns:getDictLabel(siteGoods.temper_type, 'cabinetTemp_type', '无')}</td>
				<td>${siteGoods.state_time}</td>
				<td>${siteGoods.create_time}</td>
				<td>
    				<a href="${ctx}/site/siteGoods/detail?id=${siteGoods.logid}">明细</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>