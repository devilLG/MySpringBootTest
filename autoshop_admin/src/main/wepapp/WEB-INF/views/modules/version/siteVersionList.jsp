<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>版本管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出终端版本信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/version/siteVersion/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
	       });
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/version/siteVersion/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/version/siteVersion/">终端版本列表</a></li>
		<shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/version/siteVersion/form">终端版本添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="siteVersion" action="${ctx}/version/siteVersion/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>平台代码：</label><form:input path="plat_code" htmlEscape="false" maxlength="30" class="input-medium"/></li>
			<li><label>站点编号：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${siteVersion.site_id}" labelName="site_name" labelValue="${siteVersion.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
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
		<th>区域编号</th>
		<th>区域名称</th>
		<th>站点编号</th>
		<th>站点名称</th>
		<th>平台代码</th>
		<th>平台名称</th>
		<th>平台版本</th>
		<th>升级状态</th>
		<th>允许开始时间</th>
		<th>允许结束时间</th>
		<th>状态</th>
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="siteVersion">
			<tr>
				<td><a href="${ctx}/version/siteVersion/form?logid=${siteVersion.logid}" title="${siteVersion.logid}">${fns:abbr(siteVersion.logid,10)}</a></td>
				<td>${siteVersion.tree_id}</td>
				<td>${siteVersion.tree_name}</td>
				<td>${siteVersion.site_id}</td>
				<td>${siteVersion.site_name}</td>
				<%-- <td>${fns:abbr(siteVersion.down_url,40)}</td>--%>
				<td>${siteVersion.plat_code}</td>
				<td>${siteVersion.plat_name}</td>
				<%-- <td>${fns:getDictLabel(siteVersion.cur_state, 'use_state', '无')}</td>--%>
				<td>${siteVersion.plat_version}</td>
				<td>${fns:getDictLabel(siteVersion.upgrade_state, 'upgrade_state', '无')}</td>
				<td>${siteVersion.allowUpg_stime}</td>
				<td>${siteVersion.allowUpg_etime}</td>
				<td>${fns:getDictLabel(siteVersion.cur_state, 'use_state', '无')}</td>
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/version/siteVersion/form?logid=${siteVersion.logid}">修改</a>
					<a href="${ctx}/version/siteVersion/delete?logid=${siteVersion.logid}${siteVersion.delFlag ne 0?'&isRe=true':''}" onclick="return confirmx('确认要${siteVersion.delFlag ne 0?'恢复':''}删除该站点吗？', this.href)" >${siteVersion.delFlag ne 0?'恢复':''}删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>