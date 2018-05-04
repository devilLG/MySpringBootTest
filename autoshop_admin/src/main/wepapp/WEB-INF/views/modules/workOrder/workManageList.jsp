<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${types eq 01}">
			<li class="active"><a href="${ctx}/workOrder/workManage/">工单列表</a></li>
			<li><a href="${ctx}/workOrder/workManage/form">工单添加</a></li>
		</c:if>
		<c:if test="${types eq 02}">
			<li class="active"><a href="${ctx}/workOrder/workSolving/">工单列表</a></li>
		</c:if>
	</ul>
	<form:form id="work" modelAttribute="work" action="${ctx}/workOrder/workManage/list" method="post" class="breadcrumb form-search">
		<input id="workPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="workPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站点信息：</label>
				<sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="siteId" value="${param.siteId}" labelName="siteName" labelValue="${param.siteName}"
					 title="站点信息" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>责任人：</label>
			<sys:treeselect id="managerId" cssStyle="width:150px;height:23px;" name="managerId" value="${param.managerId}" labelName="managerName" labelValue="${param.managerName}"
				title="责任人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<c:if test="${types eq 01}">
				<li><label>处理人：</label>
				<sys:treeselect id="handlerId" cssStyle="width:150px;height:23px;" name="handlerId" value="${param.handlerId}" labelName="handlerName" labelValue="${param.handlerName}"
					title="处理人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
				</li>
			</c:if>
			<li><label>工单状态：</label>
				<select name="curState">
					<option value="">-- ALL --</option>
					<c:forEach items="${workCurStateList}" var="workCurState">
						<option value="${workCurState.value}"
							${workCurState.value==param.curState?"selected='selected'":""}>${workCurState.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('work');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li>|</li>
			<li class="btns">
				<c:if test="${types eq 01}">
					<button class="btn btn-primary" type="button" onclick="genaralOperation('accept');">受理</button>
					<button class="btn btn-primary" type="button" onclick="genaralOperation('delegation');">委派</button>
					<button class="btn btn-primary" type="button" onclick="genaralOperation('remove');">撤单</button>
					<button class="btn btn-primary" type="button" onclick="genaralOperation('end');">结单</button>
				</c:if>
				<c:if test="${types eq 02}">
					<button class="btn btn-primary" type="button" onclick="genaralOperation('deal');">接单</button>
					<button class="btn btn-primary" type="button" onclick="genaralOperation('back');">退回</button>
					<button class="btn btn-primary" type="button" onclick="genaralOperation('solve');">处理</button>
				</c:if>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="workList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th width="22"></th>
					<th>工单标题</th>
					<th>站点名称</th>
					<th>工单类型</th>
					<th>责任人</th>
					<th>处理人</th>
					<th>工单状态</th>
					<th>严重等级</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="work">
					<tr id="${work.logid}" >
						<td>
							<input type="radio" name="workRadio" value="${work.logid}" curState="${work.curState}" />
						</td>
						<td><a href="${ctx}/workOrder/workManage/form?logid=${work.logid}">${work.workTitle}</a></td>
						<td>${work.siteName}</td>
						<td>${workTypeMap[work.workType]}</td>
						<td>${work.managerName}</td>
						<td>${work.handlerName}</td>
						<td>${workCurStateMap[work.curState]}</td>
						<td>${workLevelMap[work.workLevel]}</td>
						<td>${work.createTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	//	操作（受理，委派，撤单，结单）
	var genaralOperation = function(flag) {
		debugger;
		var logid = $("#workList table tbody td input[type='radio']:checked").val();
		//受理
		if(flag=="accept"){
			debugger;
			var curState = $("#workList table tbody td input[type='radio']:checked").attr("curState");
			if(curState == 01) {
				window.location.href = "${ctx}/workOrder/workManage/accept?logid=" + logid;
			} else {
				toastr.error("已经受理，不允许重复审核操作！");
				return;
			}
		}else if(flag=="delegation"){
			var curState = $("#workList table tbody td input[type='radio']:checked").attr("curState");
			if(curState == 02){
				window.location.href = "${ctx}/workOrder/workManage/delegation?logid=" + logid;
			} else {
				toastr.error("操作失败，只能操作已受理的工单！");
				return;
			}
		}else if(flag=="remove"){
			var curState = $("#workList table tbody td input[type='radio']:checked").attr("curState");
			if(curState == 03||curState == 04){
				window.location.href = "${ctx}/workOrder/workManage/remove?logid=" + logid;
			} else {
				toastr.error("撤单失败，只有委派且未被处理的工单可以操作！");
				return;
			}
		}else if(flag=="end"){
			var curState = $("#workList table tbody td input[type='radio']:checked").attr("curState");
			if(curState == 06){
				window.location.href = "${ctx}/workOrder/workManage/end?logid=" + logid;
			} else {
				toastr.error("结单失败，只能操作已处理的工单！");
				return;
			}
		}else if(flag=="deal"){
			var curState = $("#workList table tbody td input[type='radio']:checked").attr("curState");
			if(curState == 03){
				window.location.href = "${ctx}/workOrder/workSolving/deal?logid=" + logid;
			} else {
				toastr.error("接单失败，只能操作正在派单的工单！");
				return;
			}
		}else if(flag=="back"){
			var curState = $("#workList table tbody td input[type='radio']:checked").attr("curState");
			if(curState == 03){
				window.location.href = "${ctx}/workOrder/workSolving/back?logid=" + logid;
			} else {
				toastr.error("退回失败，只能操作正在派单的工单！");
				return;
			}
		}else if(flag=="solve"){
			var curState = $("#workList table tbody td input[type='radio']:checked").attr("curState");
			if(curState == 04){
				window.location.href = "${ctx}/workOrder/workSolving/solve?logid=" + logid;
			} else {
				toastr.error("只能处理已接单的工单！");
				return;
			}
		}
	}
	
	var page = function(n,s) {
		if(n) {
			$("#workPageNo").val(n);
		}
		if(s) {
			$("#workPageSize").val(s);
		}
		$("#work").attr("action","${ctx}/workOrder/workManage/list");
		$("#work").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出工单数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#work").attr("action","${ctx}/workOrder/workManage/export");
					$("#work").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



