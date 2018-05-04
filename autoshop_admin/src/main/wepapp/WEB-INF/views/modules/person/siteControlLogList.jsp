<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员推送管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出推送信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/person/siteControlLog/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			});
	  
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/person/siteControlLog/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/person/siteControlLog/">列表</a></li>
		<%-- -%><shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/site/assite/form">站点添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="siteControlLog" action="${ctx}/person/siteControlLog/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点信息：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${siteControlLog.site_id}" labelName="site_name" labelValue="${siteControlLog.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<li><label>帐户编号：</label><form:input path="issued_key" htmlEscape="false" maxlength="30" class="input-medium" style="width:250px"/></li>
			<li>
			<%-- <label>状态：</label><form:select path="cur_state" class="input-medium">
			        <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('cur_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>--%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			
		
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>站点编号</th>
		<th>站点名称</th>
		<th>交易号</th>
		<th>交易名称</th>
		<th>帐户编号</th>
		<th>状态</th>
		<th>创建时间</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="siteControlLog">
			<tr>
				<td>${siteControlLog.site_id}</a></td>
				<td>${siteControlLog.site_name}</td>
				<td>${siteControlLog.trade_code}</td>
				<td>${siteControlLog.trade_name}</td>
				<td>${siteControlLog.issued_key}</td>
				<td>${fns:getDictLabel(siteControlLog.cur_state, 'siteControlLog_state', '无')}</td>
				<td>${siteControlLog.create_time}</td>
				
				<%--<td>
    				 <a href="${ctx}/person/siteControlLog/detail?id=${siteControlLog.logid}">明细</a>
    				<a href="${ctx}/person/siteControlLog/delete?id=${siteControlLog.logid}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a
				</td>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>