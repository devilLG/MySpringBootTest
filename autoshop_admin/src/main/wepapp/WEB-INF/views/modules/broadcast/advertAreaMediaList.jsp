<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/broadcast/advertAreaMedia/">区域媒体列表</a></li>
		<li><a href="${ctx}/broadcast/advertAreaMedia/form">区域媒体添加</a></li>
	</ul>
	<form:form id="advertAreaMedia" modelAttribute="advertAreaMedia" action="${ctx}/broadcast/advertAreaMedia/list" method="post" class="breadcrumb form-search">
		<input id="advertAreaMediaPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="advertAreaMediaPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>配置名称：</label>
				<form:input path="configName" htmlEscape="false" maxlength="60" class="input-large" style="width:250px"/>
			</li>
			<li><label>媒体编号：</label>
				<input type="text"  name="mediaId" id="areaId_advertAreaMediaForm" value="${param.mediaId}" readonly="readonly" />
				<i class="icon-search magnifying" onclick="advertMediaOneSelect()"></i>
			</li>
			<li><label>区域名称：</label>
				<sys:treeselect id="tree" name="areaId" value="${param.areaId}" labelName="areaName" labelValue="${param.areaName}"
					title="区域名称" url="/sys/area/treeData" extId="${area.name}"  allowClear="true"/>
			</li>
			<li><label>媒体格式：</label>
				<select name="mimeType">
					<option value="">-- ALL --</option>
					<c:forEach items="${mimeTypeList}" var="mimeType">
						<option value="${mimeType.value}"
							${mimeType.value==param.mimeType?"selected='selected'":""}>${mimeType.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('advertAreaMedia');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="advertAreaMediaListForm" method="post">
		<table id="advertAreaMediaList" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>配置名称</th>
					<th>区域名称</th>
					<th>播放顺序</th>
					<th>媒体编号</th>
					<th>媒体类型</th>
					<th>媒体格式</th>
					<th>媒体地址</th>
					<th>媒体大小(KB)</th>
					<th>状态</th>
					<th style="width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="advertAreaMedia">
					<tr id="${advertAreaMedia.logid}" >
						<td>
						<input type="hidden" name="_logid" value="${advertAreaMedia.logid}">
						${advertAreaMedia.configName}
						</td>
						<td>${advertAreaMedia.areaName}</td>
						<td>${advertAreaMedia.mediaSeq}</td>
						<td>${advertAreaMedia.mediaId}</td>
						<td>${mediaTypeMap[advertAreaMedia.mediaType]}</td>
						<td>${mimeTypeMap[advertAreaMedia.mimeType]}</td>
						<td>${advertAreaMedia.mediaAddress}</td>
						<td>${advertAreaMedia.mediaSize}</td>
						<td style="text-align: center;">
							<input type="checkbox" value="${advertAreaMedia.curState}" ${advertAreaMedia.curState == '0' ? 'checked=checked ' : ''}>
						</td>
						<td nowrap>
							<a href="${ctx}/broadcast/advertAreaMedia/delete?logid=${advertAreaMedia.logid}" onclick="return confirmx('要删除该区域媒体吗？', this.href)">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
   	//开关切换
   	$('#advertAreaMediaList input[type="checkbox"]').bootstrapSwitch({
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
			var href = "${ctx}/broadcast/advertAreaMedia/updateState?logid="+logid+"&curStatus="+status;
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
	
	//	选择媒体
	var advertMediaOneSelect = function() {
		 top.$.jBox.open("iframe:${ctx}/broadcast/advertMedia/advertMediaOneSelect", "选择媒体编号", 950, 600, {
			buttons:{"确认":"ok","关闭":true}, 
			submit:function(v, h, f){
				if (v=="ok"){
					debugger;
					var parms = h.find("iframe")[0].contentWindow.$("#advert_media").val();
					if(parms){
						$("#advertAreaMedia input[name='mediaId']").val(parms.split(",")[0]);
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
			$("#advertAreaMediaPageNo").val(n);
		}
		if(s) {
			$("#advertAreaMediaPageSize").val(s);
		}
		$("#advertAreaMedia").attr("action","${ctx}/broadcast/advertAreaMedia/list");
		$("#advertAreaMedia").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出区域媒体数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#advertAreaMedia").attr("action","${ctx}/broadcast/advertAreaMedia/export");
					$("#advertAreaMedia").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



