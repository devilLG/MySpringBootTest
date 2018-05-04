<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点人员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出站点人员信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/person/sitePerson/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
	});
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/person/sitePerson/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/person/sitePerson/">站点人员列表</a></li>
		<shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/person/sitePerson/form">站点人员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sitePerson" action="${ctx}/person/sitePerson/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>站点信息：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${sitePerson.site_id}" labelName="site_name" labelValue="${sitePerson.site_name}"
			 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			 
			 <li><label>账户信息：</label>
			 <sys:treeselect id="login" cssStyle="width:150px;height:23px;" name="login_id" value="${sitePerson.login_id}" labelName="login_name" labelValue="${sitePerson.login_name}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			 <li><label>人员类型：</label><form:select path="emp_type" class="input-medium">
			        <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('Emp_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
		<thead><tr>
		<th>账户编号</th>
		<th>用户名称</th>
		<th>人员类型</th>
		<th>站点编号</th>
		<th>站点名称</th>
		<th>手机号</th>
		<th>邮箱地址</th>
		<th>下发状态</th>
		<th>下发时间</th>
		<th>创建时间</th>
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="sitePerson">
			<tr>
			    <td>${sitePerson.login_id}</td>
				<td>${sitePerson.login_name}</td>
				<td>${fns:getDictLabel(sitePerson.emp_type, 'Emp_type', '无')}</td>
				<td>${sitePerson.site_id}</td>
				<td>${sitePerson.site_name}</td>
				<td>${sitePerson.mobile}</td>
				<td>${sitePerson.email}</td>
				<td>${fns:getDictLabel(sitePerson.down_state, 'down_state', '无')}</td>
				<td>${sitePerson.down_time}</td>
				<td>${sitePerson.create_time}</td>
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/person/sitePerson/form?logid=${sitePerson.logid}">修改</a>
    				<a href="${ctx}/person/sitePerson/detail?logid=${sitePerson.logid}">明细</a>
					<a href="${ctx}/person/sitePerson/delete?logid=${sitePerson.logid}${sitePerson.delFlag ne 0?'&isRe=true':''}" onclick="return confirmx('确认要${sitePerson.delFlag ne 0?'恢复':''}删除该人员吗？', this.href)" >${sitePerson.delFlag ne 0?'恢复':''}删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>