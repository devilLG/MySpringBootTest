<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/notice/noticeConfig/">通知配置信息列表</a></li>
		<li><a href="${ctx}/notice/noticeConfig/form">通知配置信息添加</a></li>
	</ul>
	<form:form id="noticeConfig" modelAttribute="noticeConfig" action="${ctx}/notice/noticeConfig/list" method="post" class="breadcrumb form-search">
		<input id="noticeConfigPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="noticeConfigPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>通知类型：</label>
				<select name="main_type" style="width:135px">
					<option value="">-- ALL --</option>
					<c:forEach items="${noticeTypeList}" var="noticeType">
						<option value="${noticeType.value}"
							${noticeType.value==param.main_type?"selected='selected'":""}>${noticeType.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('noticeConfig');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="noticeConfigList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>公司名称</th>
					<th>配置类型</th>
					<th>通知类型</th>
					<th>通知方式</th>
					<th>通知地址</th>
					<th style="text-align: center;">状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="noticeConfig">
					<tr id="${noticeConfig.logid}" >
						<td>
							<input type="hidden" name="_logid" value="${noticeConfig.logid}">
							<a href="${ctx}/notice/noticeConfig/form?logid=${noticeConfig.logid}">${noticeConfig.corp_name}</a>
						</td>
						<td>${configTypeMap[noticeConfig.config_type]}</td>
						<td>${noticeTypeMap[noticeConfig.notice_type]}</td>
						<td>${noticeChannelMap[noticeConfig.notice_channel]}</td>
						<td>${noticeConfig.notice_address}</td>
						<td style="text-align: center;">
							<input type="checkbox" value="${noticeConfig.cur_state}" ${noticeConfig.cur_state == '0' ? 'checked=checked ' : ''}>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	
	//开关切换
   	$('#noticeConfigList input[type="checkbox"]').bootstrapSwitch({
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
			var href = "${ctx}/notice/noticeConfig/updateState?logid="+logid+"&cur_state="+status;
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
			$("#noticeConfigPageNo").val(n);
		}
		if(s) {
			$("#noticeConfigPageSize").val(s);
		}
		$("#noticeConfig").attr("action","${ctx}/notice/noticeConfig/list");
		$("#noticeConfig").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出通知配置信息数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#noticeConfig").attr("action","${ctx}/notice/noticeConfig/export");
					$("#noticeConfig").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



