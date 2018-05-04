<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/broadcast/advertMedia/">媒体信息列表</a></li>
		<li><a href="${ctx}/broadcast/advertMedia/form">媒体信息添加</a></li>
	</ul>
	<form:form id="advertMedia" modelAttribute="advertMedia" action="${ctx}/broadcast/advertMedia/list" method="post" class="breadcrumb form-search">
		<input id="advertMediaPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="advertMediaPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>媒体名称：</label>
				<form:input path="mediaName" htmlEscape="false" maxlength="60" class="input-large" style="width:250px"/>
			</li>
			<li><label>媒体标题：</label>
				<form:input path="mediaTitile" htmlEscape="false" maxlength="60" class="input-large" style="width:250px"/>
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
			<li><label>媒体类型：</label>
				<select name="mediaType">
					<option value="">-- ALL --</option>
					<c:forEach items="${mediaTypeList}" var="mediaType">
						<option value="${mediaType.value}"
							${mediaType.value==param.mediaType?"selected='selected'":""}>${mediaType.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('advertMedia');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="advertMediaList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>媒体名称</th>
					<th>媒体标题</th>
					<th>媒体类型</th>
					<th>媒体格式</th>
					<th>媒体显示像素x</th>
					<th>媒体显示像素y</th>
					<th>媒体地址</th>
					<th>媒体大小</th>
					<th>状态</th>
					<th>媒体描述</th>
					<th>状态时间</th>
					<th style="width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="advertMedia">
					<tr id="${advertMedia.logid}" >
						<td><a href="${ctx}/broadcast/advertMedia/form?logid=${advertMedia.logid}">${advertMedia.mediaName}</a></td>
						<td>${advertMedia.mediaTitile}</td>
						<td>${mediaTypeMap[advertMedia.mediaType]}</td>
						<td>${mimeTypeMap[advertMedia.mimeType]}</td>
						<td>${advertMedia.resolutionX}</td>
						<td>${advertMedia.resolutionY}</td>
						<td>${advertMedia.mediaAddress}</td>
						<td>${advertMedia.mediaSize}</td>
						<td>${mediaState_key[advertMedia.curState]}</td>
						<td title="${advertMedia.description}">${fn:length(advertMedia.description) > 20 ? (fn:substring(advertMedia.description,0,19)).concat("...") : advertMedia.description }</td>
						<td>${advertMedia.stateTime}</td>
						<td nowrap>
							<a href="${ctx}/broadcast/advertMedia/delete?logid=${advertMedia.logid}" onclick="return confirmx('要删除该媒体信息吗？', this.href)">删除</a>
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
			$("#advertMediaPageNo").val(n);
		}
		if(s) {
			$("#advertMediaPageSize").val(s);
		}
		$("#advertMedia").attr("action","${ctx}/broadcast/advertMedia/list");
		$("#advertMedia").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出媒体信息数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#advertMedia").attr("action","${ctx}/broadcast/advertMedia/export");
					$("#advertMedia").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



