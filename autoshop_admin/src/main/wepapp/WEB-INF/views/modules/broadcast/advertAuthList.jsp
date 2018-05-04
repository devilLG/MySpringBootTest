<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/broadcast/advertAuth/">播控权限列表</a></li>
		<li><a href="${ctx}/broadcast/advertAuth/form">播控权限添加</a></li>
	</ul>
	<form:form id="advertAuth" modelAttribute="advertAuth" action="${ctx}/broadcast/advertAuth/list" method="post" class="breadcrumb form-search">
		<input id="advertAuthPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="advertAuthPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>配置名称：</label>
				<input type="hidden" name="configId"/>
				<input type="text"  name="configName" id="configName" value="${param.configName}" readonly="readonly" />
				<i class="icon-search magnifying" onclick="showAdvertConfigInfo()"></i>
			</li>
			<li><label>类型：</label>
				<select name="targetType" onchange="changeType()">
					<option value="">-- ALL --</option>
					<c:forEach items="${targetTypeList}" var="targetType">
						<option value="${targetType.value}"
							${targetType.value==param.targetType?"selected='selected'":""}>${targetType.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li><label>站点信息：</label>
				<sys:tableselect id="site" disabled="disabled" cssStyle="width:150px;height:23px;" name="targetId" value="${param.siteId}" labelName="siteName" labelValue="${param.siteName}"
					 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>区域名称：</label>
				<sys:treeselect id="tree" disabled="disabled" name="targetId" value="${param.areaId}" labelName="areaName" labelValue="${param.areaName}"
					title="区域名称" url="/sys/area/treeData" extId="${area.name}"  allowClear="true"/>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('advertAuth');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="advertAuthList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>配置名称</th>
					<th>类型</th>
					<th>地点编号</th>
					<th>地点名称</th>
					<th>计划开始播放日期</th>
					<th>计划开始播放日期</th>
					<th>状态</th>
					<th>状态时间</th>
					<th>创建时间</th>
					<th style="width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="advertAuth">
					<tr id="${advertAuth.logid}" >
						<td><a href="${ctx}/broadcast/advertAuth/form?logid=${advertAuth.logid}">${advertAuth.configName}</a></td>
						<td>${targetTypeMap[advertAuth.targetType]}</td>
						<td>${advertAuth.targetId}</td>
						<td>${advertAuth.targetName}</td>
						<td>${advertAuth.planStime}</td>
						<td>${advertAuth.planEtime}</td>
						<td>${advertConfigStateMap[advertAuth.curState]}</td>
						<td>${advertAuth.stateTime}</td>
						<td>${advertAuth.createTime}</td>
						<td nowrap>
							<a href="${ctx}/broadcast/advertAuth/delete?logid=${advertAuth.logid}" onclick="return confirmx('要删除该区域配置吗？', this.href)">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	//根据类型选择区域或站点
	var changeType = function(){
		debugger;
		var type = $("#advertAuth select[name='targetType']").val();
		if(type==1){
			$("#advertAuth a[id='siteButton']").removeClass("disabled");
			$("#advertAuth a[id='treeButton']").addClass("disabled");
			$("#advertAuth input[id='treeId']").val("");
			$("#advertAuth input[name='areaName']").val("");
		} else if(type==2){
			$("#advertAuth a[id='treeButton']").removeClass("disabled");
			$("#advertAuth a[id='siteButton']").addClass("disabled");
			$("#advertAuth input[id='site_id']").val("");
			$("#advertAuth input[name='siteName']").val("");
		} else {
			$("#advertAuth a[id='siteButton']").addClass("disabled");
			$("#advertAuth a[id='treeButton']").addClass("disabled");
			$("#advertAuth input[id='site_id']").val("");
			$("#advertAuth input[name='siteName']").val("");
			$("#advertAuth input[id='treeId']").val("");
			$("#advertAuth input[name='areaName']").val("");
		}
	}
	
	//	选择配置
	var showAdvertConfigInfo = function() {
		 top.$.jBox.open("iframe:${ctx}/broadcast/advertConfig/showAdvertConfigInfo", "选择广告配置", 950, 600, {
			buttons:{"确认":"ok","关闭":true}, 
			submit:function(v, h, f){
				if (v=="ok"){
					debugger;
					var parms = h.find("iframe")[0].contentWindow.$("#advertConfig_parms").val();
					if(parms){
						$("#advertAuth input[name='configId']").val(parms.split(",")[0]);
						$("#advertAuth input[name='configName']").val(parms.split(",")[1]);
					}						
				}
			}, 
			loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
	};
	
	var page = function(n,s) {
		if(n) {
			$("#advertAuthPageNo").val(n);
		}
		if(s) {
			$("#advertAuthPageSize").val(s);
		}
		$("#advertAuth").attr("action","${ctx}/broadcast/advertAuth/list");
		$("#advertAuth").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出区域配置数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#advertAuth").attr("action","${ctx}/broadcast/advertAuth/export");
					$("#advertAuth").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



