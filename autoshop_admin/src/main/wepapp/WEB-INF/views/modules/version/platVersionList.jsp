<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规格管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出平台版本信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/version/platVersion/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
	        });
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/version/platVersion/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/version/platVersion/">平台版本列表</a></li>
		<shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/version/platVersion/form">平台版本添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="platVersion" action="${ctx}/version/platVersion/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<%-- <li><label>平台代码：</label><form:input path="plat_code" htmlEscape="false" maxlength="30" class="input-medium"/></li>
			<li><label>平台名称：</label><form:input path="plat_name" htmlEscape="false" maxlength="50" class="input-medium"/></li> --%>
			<li><label>平台名称：</label><form:select path="plat_code" class="input-medium">
			        <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('plat_code')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>平台版本：</label><form:input path="plat_version" htmlEscape="false" maxlength="30" class="input-medium"/></li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>编号</th>
		<th>平台代码</th>
		<th>平台名称</th>
		<th>平台版本</th>
		<th>下载地址</th>
		<th>强制安装</th>
		<th>版本状态</th>
		<th>有效开始时间</th>
		<th>有效结束时间</th>
		<th>刷新时间</th>
		<th>创建时间</th>
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="platVersion">
			<tr>
				<td><a href="${ctx}/version/platVersion/form?logid=${platVersion.logid}" title="${platVersion.logid}">${fns:abbr(platVersion.logid,10)}</a></td>
				<td>${platVersion.plat_code}</td>
				<td>${platVersion.plat_name}</td>
				<td>${platVersion.plat_version}</td>
				<td>${fns:abbr(platVersion.down_url,40)}</td>
				<td>${fns:getDictLabel(platVersion.upgrade_install, 'upgrade_install', '无')}</td>
				<%-- <td>${fns:getDictLabel(platVersion.cabinet_type, 'cabinet_type', '无')}</td>--%>
				<td>${fns:getDictLabel(platVersion.cur_state, 'use_state', '无')}</td>
				<td>${platVersion.valid_stime}</td>
				<td>${platVersion.valid_etime}</td>
				<td>${platVersion.refrest_time}</td>
				<td>${platVersion.create_time}</td>
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/version/platVersion/form?logid=${platVersion.logid}">修改</a>
					<a href="${ctx}/version/platVersion/delete?logid=${platVersion.logid}${platVersion.delFlag ne 0?'&isRe=true':''}" onclick="return confirmx('确认要${platVersion.delFlag ne 0?'恢复':''}删除该站点吗？', this.href)" >${platVersion.delFlag ne 0?'恢复':''}删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>