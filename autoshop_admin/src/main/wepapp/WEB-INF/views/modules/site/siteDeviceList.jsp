<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>地点柜子配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出地点柜子配置数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/site/siteDevice/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/site/siteDevice/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/site/siteDevice/list">柜箱排列列表</a></li>
		<li><a href="${ctx}/site/siteDevice/form">柜箱排列添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="siteDevice" action="${ctx}/site/siteDevice/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			
			<li><label>站点信息：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${siteDevice.site_id}" labelName="site_name" labelValue="${siteDevice.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>站点编号</th>
		<th>站点简称</th>
		<th>排列位置</th>
		<th>柜型</th>
		<th>绑定状态</th>
		<th>创建时间</th>
		<th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="siteDevice">
			<tr>
				<td>${siteDevice.site_id}</td>
				<td>${siteDevice.site_name}</td>
				<td>${siteDevice.cabinet_location}</td>
				<td>${siteDevice.cabinet_type}</td>
				<td>${fns:getDictLabel(siteDevice.bind_state, 'bind_state', '无')}</td>
				<td>${siteDevice.create_time}</td>
		        <td>
		        	<a href="${ctx}/site/siteDevice/form?logid=${siteDevice.logid}">修改</a>
					<a href="${ctx}/site/siteDevice/delete?logid=${siteDevice.logid}" onclick="return confirmx('确认要删除该柜子配置吗？', this.href)">删除</a>
					<%-- <a href="${ctx}/site/siteDevice/detail?id=${siteDevice.logid}">明细</a>--%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>