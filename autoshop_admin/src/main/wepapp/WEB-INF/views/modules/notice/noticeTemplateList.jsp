<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/notice/noticeTemplate/">公共通知模板列表</a></li>
		<li><a href="${ctx}/notice/noticeTemplate/form">公共通知模板添加</a></li>
	</ul>
	<form:form id="noticeTemplate" modelAttribute="noticeTemplate" action="${ctx}/notice/noticeTemplate/list" method="post" class="breadcrumb form-search">
		<input id="noticeTemplatePageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="noticeTemplatePageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模板名称：</label>
				<form:input path="temp_name" htmlEscape="false" maxlength="60" class="input" style="width:250px"/>
			</li>
			<li><label>模板内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="500" class="input" style="width:250px"/>
			</li>
			<li><label>模板大类：</label>
				<select name="main_type" style="width:135px">
					<option value="">-- ALL --</option>
					<c:forEach items="${mainTypeList}" var="mainType">
						<option value="${mainType.value}"
							${mainType.value==param.main_type?"selected='selected'":""}>${mainType.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('noticeTemplate');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="noticeTemplateList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>模板名称</th>
					<th>业务大类</th>
					<th>通知类型</th>
					<th>角色名称</th>
					<th>最大发送数</th>
					<th>间隔时间</th>
					<th>描述</th>
					<th>发送等级</th>
					<th style="text-align: center;">状态</th>
					<th>创建时间</th>
					<th style="width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="noticeTemplate">
					<tr id="${noticeTemplate.logid}" >
						<td>
							<input type="hidden" name="_logid" value="${noticeTemplate.logid}">
							<a href="${ctx}/notice/noticeTemplate/form?logid=${noticeTemplate.logid}">${noticeTemplate.temp_name}</a>
						</td>
						<td>${mainTypeMap[noticeTemplate.main_type]}</td>
						<td>${noticeTypeMap[noticeTemplate.notice_type]}</td>
						<td>${noticeTemplate.role_name}</td>
						<td>${noticeTemplate.send_num}</td>
						<td>${noticeTemplate.interval_time}</td>
						<td>${noticeTemplate.content}</td>
						<td>${noticeTemplate.level}</td>
						<td style="text-align: center;">
							<input type="checkbox" value="${noticeTemplate.cur_state}" ${noticeTemplate.cur_state == '0' ? 'checked=checked ' : ''}>
						</td>
						<td>${noticeTemplate.create_time}</td>
						<td nowrap>
							<a href="${ctx}/notice/noticeTemplate/delete?logid=${noticeTemplate.logid}" onclick="return confirmx('要删除该公共通知模板吗？', this.href)">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	
	//开关切换
   	$('#noticeTemplateList input[type="checkbox"]').bootstrapSwitch({
		onText:"正常",
		offText:"失效",
		onColor:"success",
		offColor:"danger",
		size:"mini",
		onSwitchChange : function(e, state) {
			debugger;
			var switnch;
			var status;
			if(state == true) {
				status = '0';
				switnch = "正常";
			} else {
				status = '1';
				switnch = "失效";
			}
			var target = $(e.currentTarget);
			var logid = target.parent().parent().parent().parent().children().children()[0].value;
			var href = "${ctx}/notice/noticeTemplate/updateState?logid="+logid+"&cur_state="+status;
			$.ajax({
				type:"POST",
				url: href,
				async:false,
				success:function(data){
					if (data = "success") {
						toastr.success("改变状态成功");
					}else {
						toastr.error("改变状态失败，请重试");
					}
				}
			});
		}
	});
	
	var page = function(n,s) {
		if(n) {
			$("#noticeTemplatePageNo").val(n);
		}
		if(s) {
			$("#noticeTemplatePageSize").val(s);
		}
		$("#noticeTemplate").attr("action","${ctx}/notice/noticeTemplate/list");
		$("#noticeTemplate").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出公共通知模板数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#noticeTemplate").attr("action","${ctx}/notice/noticeTemplate/export");
					$("#noticeTemplate").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



