<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>补货计划管理</title>
<meta name="decorator" content="default" />
<style>
#replenishmentPlanSearchForm .input-xlarge{
	width: 100px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inventory/replenishmentPlan/">补货计划列表</a></li>
		<%-- <shiro:hasPermission name="inventory:replenishmentPlan:edit"> --%>
			<li><a href="${ctx}/inventory/replenishmentPlan/form">添加补货计划</a></li>
		<%-- </shiro:hasPermission> --%>
	</ul>
	<form:form id="replenishmentPlanSearchForm" modelAttribute="replenishmentPlan" action="${ctx}/inventory/replenishmentPlan/list" method="post" class="breadcrumb form-search ">
		<input id="replenishmentPlanPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="replenishmentPlanPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>对象类型：</label>
				<form:select path="planType" class="input-small" onchange="changeTarget(this.value)" >
					<form:option value="" label="全部"></form:option>
					<form:options items="${fns:getDictList('planType')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-medium"/>
				</form:select>
			</li>
			<li>
				<label>计划对象：</label>
				<div id="site" style="float: right;">
					<sys:tableselect id="target_site" name="site_id" value="${replenishmentPlan.targetId}" labelName="site_name" labelValue="${replenishmentPlan.targetName}"
				 		title="站点名称" url="/site/assite/tableData" allowClear="true" notAllowSelectParent="true"/>
			 	</div>
			 	<div id="area" style="float: right;">
				 	<sys:treeselect id="area" name="area_id" value="${replenishmentPlan.targetId}" labelName="area_name" labelValue="${replenishmentPlan.targetName}"
						title="区域名称" url="/sys/area/treeData" allowClear="true"/>
				</div>
				<div id="showAll" style="float: right;">
					<input type="text" class="input-medium" readonly="readonly">
				</div>
			</li>
			<li>
				<label>周期类型：</label>
				<form:select path="cycleType" class="input-small" >
					<form:option value="" label="全部"></form:option>
					<form:options items="${fns:getDictList('cycleType')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-medium"/>
				</form:select>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return replenishmentPlanPage();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"><br></li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<table id="replenishmentPlanTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">计划对象类型</th>
				<th>计划对象编号</th>
				<th>计划对象名称</th>
				<th style="text-align:center;">周期类型</th>
				<th style="text-align:center;">周期值</th>
				<th style="text-align:center;">状态</th>
				<th style="text-align:center;width: 15%;">状态时间</th>
				<th style="text-align:center;width: 15%;">创建时间</th>
				<%-- <shiro:hasPermission name="inventory:replenishmentPlan:edit"> --%><th style="width: 20%;">操作</th><%-- </shiro:hasPermission> --%> 
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list }" var="replenishmentPlan">
				<tr id="${replenishmentPlan.planId}" >
					<td style="text-align: center;">
						<input type="hidden" name="_replenishmentPlanId" value="${replenishmentPlan.planId}">
						${planTypeMap[replenishmentPlan.planType]}
					</td>
					<td>${replenishmentPlan.targetId}</td>
					<td>${replenishmentPlan.targetName}</td>
					<td style="text-align: center;">${cycleTypeMap[replenishmentPlan.cycleType]}</td>
					<td style="text-align: center;">${replenishmentPlan.cycleValue }</td>
					<td style="text-align: center;">${curStateMap[replenishmentPlan.curState] }</td>
					<td style="text-align: center;">${replenishmentPlan.stateTime }</td>
					<td style="text-align: center;">${replenishmentPlan.createTime }</td>
					<%-- <shiro:hasPermission name="inventory:replenishmentPlan:edit"> --%>
						<td nowrap>
							<a href="${ctx}/inventory/replenishmentPlan/form?planId=${replenishmentPlan.planId}">修改</a> 
							<a href="${ctx}/inventory/replenishmentPlan/delete?planId=${replenishmentPlan.planId}" onclick="return confirmx('确认删除么？',this.href)">删除</a>
						</td>
					<%-- </shiro:hasPermission> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		$(document).ready(function() {
			changeTarget("${replenishmentPlan.planType }");
			
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出补货计划吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#replenishmentPlanSearchForm").attr("action","${ctx}/inventory/replenishmentPlan/export");
						$("#replenishmentPlanSearchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function replenishmentPlanPage(n,s){
			if(n) $("#replenishmentPlanPageNo").val(n);
			if(s) $("#replenishmentPlanPageSize").val(s);
			$("#replenishmentPlanSearchForm").attr("action","${ctx}/inventory/replenishmentPlan/list");
			$("#replenishmentPlanSearchForm").submit();
	    	return false;
	    }
		
		var changeTarget = function (type) {
			if (type == "01") {
				$("#site").show();
				$("#area").hide();
				$("#showAll").hide();
			}else if (type == "02") {
				$("#site").hide();
				$("#area").show();
				$("#showAll").hide();
			}else{
				$("#site").hide();
				$("#area").hide();
				$("#showAll").show();
			}
		}
	</script>
</body>
</html>