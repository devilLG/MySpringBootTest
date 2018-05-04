<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资源状态</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出资源状态数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/site/siteState/export2");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/site/siteState/stateList");
			$("#searchForm").submit();
	    	return false;
	    } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/site/siteState/statelist2">资源状态列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="siteState" action="${ctx}/site/siteState/stateList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点信息：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${siteState.site_id}" labelName="site_name" labelValue="${siteState.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>站点编号</th><th>站点简称</th><th>货道总数</th><th>货道空箱数量</th><th>货道缺货数量</th><th>当前故障数量</th><th>刷新时间</th><%-- <th>操作</th>--%></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="siteState">
			<tr>
				<td>${siteState.site_id}</td>
				<c:if test="${siteState.box_damage>'0'}">
				<td><font color="red">${siteState.site_name}</font></td>
				</c:if>
				<c:if test="${siteState.box_damage=='0'}">
				<td>${siteState.site_name}</td>
				</c:if>
				<td>${siteState.box_all}</td>
				<td>${siteState.box_empty}</td>
				<td>${siteState.box_short}</td>
				<td>${siteState.box_damage}</td>
				<td>${siteState.refresh_time}</td>
		        <%-- <td>
		        	<a href="${ctx}/site/siteState/form?id=${siteState.logid}">修改</a>
					<a href="${ctx}/site/siteState/delete?id=${siteState.logid}" onclick="return confirmx('确认要删除该商品品牌吗？', this.href)">删除</a>
					<a href="${ctx}/site/siteState/detail?id=${siteState.logid}">明细</a>
				</td>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>