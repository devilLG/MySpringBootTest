<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出货道数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/standard/cabinetAttr/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			});
	    function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/standard/cabinetAttr/list");
			$("#searchForm").submit();
	    	return false;
	    }
	    $(function(){
	    	$(".ahref").click(function(){
	    		var con=confirm("确认要删除该信息吗？");
	    		if(con){
	    			var productId=$(this).siblings(".btnId").val();
	    			$("#attr_ids").val(productId);
	    			$("#searchForm").attr("action","${ctx}/standard/cabinetAttr/delete");
	    			$("#searchForm").submit();
	    			
	    		}
	    		
	    			
	    	});
	    	
	    });
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/standard/cabinetAttr/">柜箱列表</a></li>
		<shiro:hasPermission name="site:assite:view"><li><a href="${ctx}/standard/cabinetAttr/form">柜箱添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cabinetAttr" action="${ctx}/standard/cabinetAttr/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>柜子类型号：</label><form:input path="cabinettype_id" htmlEscape="false" maxlength="10" class="input-medium"/></li>
			<li><label>货道编号：</label><form:input path="box_id" htmlEscape="false" maxlength="10" class="input-medium"/></li>
			<li><label>货道列数：</label><form:input path="cloumn_id" htmlEscape="false" maxlength="5" class="input-medium"/></li>
			<li><label>货道层数：</label><form:input path="layer_num" htmlEscape="false" maxlength="2" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		</ul>
		<input type="hidden" id="attr_ids" name="attr_id"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>属性编号</th>
		<th>柜子类型编号</th>
		<th>货道类型编号</th>
		<th>货道编号</th>
		<th>货道列数</th>
		<th>层数</th>
		<th>状态</th>
		<shiro:hasPermission name="site:assite:view"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="cabinetAttr">
			<tr>
				<td><a href="${ctx}/standard/cabinetAttr/form?attr_id=${cabinetAttr.attr_id}" title="${cabinetAttr.attr_id}">${fns:abbr(cabinetAttr.attr_id,40)}</a></td>
				<td>${cabinetAttr.cabinettype_id}</td>
				<td>${cabinetAttr.boxtype_id}</td>
				<td>${cabinetAttr.box_id}</td>
				<td>${cabinetAttr.cloumn_id}</td>
				<td>${cabinetAttr.layer_num}</td>
				<td>${fns:getDictLabel(cabinetAttr.state, 'State', '无')}</td>
				<shiro:hasPermission name="site:assite:view"><td>
    				<a href="${ctx}/standard/cabinetAttr/form?attr_id=${cabinetAttr.attr_id}">修改</a>
					<a class="ahref" <%-- onclick="return confirmx('确认要${cabinetAttr.delFlag ne 0?'恢复':''}删除该信息吗？', this.href)" --%> >${cabinetAttr.delFlag ne 0?'恢复':''}删除</a>
					<input type="hidden" class="btnId" value="${cabinetAttr.attr_id}"/>
					<!--  href="${ctx}/standard/cabinetAttr/delete?attr_id=${cabinetAttr.attr_id}${cabinetAttr.delFlag ne 0?'&isRe=true':''}" -->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>