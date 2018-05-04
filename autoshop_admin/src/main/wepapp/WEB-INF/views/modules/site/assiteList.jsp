<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出站点信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/site/assite/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			});
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/site/assite/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    function myResult(rel){
			debugger;
		  	$("#"+rel+" select").each(function(i,val){
		  		val.value="";
		  	});
			$("#"+rel+" input[type='text']").each(function(i,b){b.value="";});
			$("#"+rel+" input[type='hidden']").each(function(i,b){b.value="";});
			$("#searchForm").submit();
		}
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/site/assite/">站点列表</a></li>
		<shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/site/assite/form">站点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="asSite" action="${ctx}/site/assite/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<input name="warn_type" type="hidden" value="${asSite.warn_type}"/>
		<ul class="ul-form">
			<li><label>站点信息：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${asSite.site_id}" labelName="site_name" labelValue="${asSite.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			
		 <label>区域名称：</label><sys:treeselect id="tree" name="tree_id" value="${asSite.tree_id}" labelName="tree_name" labelValue="${asSite.tree_name}"
					title="区域名称" url="/sys/area/treeData" extId="${area.name}" cssClass="" allowClear="true"/></li>

			<li><label>状态：</label><form:select path="site_state" class="input-medium">
			        <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('site_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>站点编号</th>
		<th>站点简称</th>
		<th>区域名称</th>
		<th>当前温度(℃)</th>
		<th>温度状态</th>
		<th>警报类型</th>
		<th>警报内容</th>
		<th>是否需要补货</th>
		<th>在线状态</th>
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="site">
			<tr>
				<td>${fns:abbr(site.site_id,20)}</td>
				<td>${site.site_name}</td>
				<td>${site.tree_name}</td>
				<td>${site.cur_temperature}</td>
				<td>${fns:getDictLabel(site.temperature_state, 'temperature_state', '无')}</td>
				<td>${fns:getDictLabel(site.warn_type, 'warn_type', '无')}</td>
				<td>${fns:abbr(site.warn_cont,20)}</td>
				<td>${fns:getDictLabel(site.is_replenishment, 'is_replenishment', '无')}</td>
				<td>${fns:getDictLabel(site.site_state, 'site_state', '无')}</td>
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/site/assite/form?site_id=${site.site_id}">修改</a>
					<a href="${ctx}/site/assite/delete?site_id=${site.site_id}${site.delFlag ne 0?'&isRe=true':''}" onclick="return confirmx('确认要${site.delFlag ne 0?'恢复':''}删除该站点吗？', this.href)" >${site.delFlag ne 0?'恢复':''}删除</a>
					<a href="${ctx}/site/assite/detail?site_id=${site.site_id}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>