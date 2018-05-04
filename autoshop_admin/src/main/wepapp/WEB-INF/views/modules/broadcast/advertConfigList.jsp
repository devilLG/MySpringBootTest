<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/broadcast/advertConfig/">广告配置列表</a></li>
		<li><a href="${ctx}/broadcast/advertConfig/form">广告配置添加</a></li>
	</ul>
	<form:form id="advertConfig" modelAttribute="advertConfig" action="${ctx}/broadcast/advertConfig/list" method="post" class="breadcrumb form-search">
		<input id="advertConfigPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="advertConfigPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>配置名称：</label>
				<form:input path="configName" htmlEscape="false" maxlength="60" class="input-large" style="width:250px"/>
			</li>
			<li><label>播放时间：</label>
				<input  type="hidden" readonly="readonly" name="planStime" id="aCplanStime"  class="Wdate input"  
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'aCplanEtime\') || \'%y-%M-%d\'}' ,dateStart:'%y-%M-%d',alwaysUseStartDate:false,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
					size="3" value="${param.planStime}"/> 
				<input  type="text" readonly="readonly" name="planEtime" id="aCplanEtime"  class="Wdate input "  
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'aCplanStime\')}',dateStart:'%y-%M-%d',alwaysUseStartDate:false,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
					size="3" value="${param.planEtime}"/>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('advertConfig');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="advertConfigList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>配置名称</th>
					<th>默认播放</th>
					<th>计划开播时间</th>
					<th>计划结播时间</th>
					<th>屏幕显示像素x轴</th>
					<th>屏幕显示像素y轴</th>
					<th>状态</th>
					<th>状态时间</th>
					<th>创建时间</th>
					<th style="width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="advertConfig">
					<tr id="${advertConfig.logid}" >
						<td><a href="${ctx}/broadcast/advertConfig/form?logid=${advertConfig.logid}">${advertConfig.configName}</a></td>
						<td>${isDefalutMap[advertConfig.isDefalut]}</td>
						<td>${advertConfig.planStime}</td>
						<td>${advertConfig.planEtime}</td>
						<td>${advertConfig.resolutionX}</td>
						<td>${advertConfig.resolutionY}</td>
						<td>${curStateMap[advertConfig.curState]}</td>
						<td>${advertConfig.stateTime}</td>
						<td>${advertConfig.createTime}</td>
						<td nowrap>
							<a href="${ctx}/broadcast/advertConfig/delete?logid=${advertConfig.logid}" onclick="return confirmx('要删除该广告配置吗？', this.href)">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#advertConfigPageNo").val(n);
		}
		if(s) {
			$("#advertConfigPageSize").val(s);
		}
		$("#advertConfig").attr("action","${ctx}/broadcast/advertConfig/list");
		$("#advertConfig").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出广告配置数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#advertConfig").attr("action","${ctx}/broadcast/advertConfig/export");
					$("#advertConfig").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



