<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/notice/noticeQueue/">通知列表</a></li>
	</ul>
	<form:form id="noticeQueue" modelAttribute="noticeQueue" action="${ctx}/notice/noticeQueue/list" method="post" class="breadcrumb form-search">
		<input id="noticeQueuePageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="noticeQueuePageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>业务大类：</label>
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
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('noticeQueue');"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="noticeQueueList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th style="width: 10%;">业务大类</th>
					<th>通知内容</th>
					<th style="width: 13%;">发送时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="noticeQueue">
					<tr id="${noticeQueue.logid}" >
						<td>${mainTypeMap[noticeQueue.main_type]}<c:if test="${noticeQueue.send_state ne '3' }"><span style="color: red;">【未读】</span></c:if></td>
						<td title="${noticeQueue.cont }"><a style="cursor: pointer;" href="javascript:openDetail('${noticeQueue.queue_id }')">${fn:length(noticeQueue.cont) > 60 ? fn:substring(noticeQueue.cont, 0, 60).concat("...") : noticeQueue.cont}</a></td>
						<td>${noticeQueue.create_time}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#noticeQueuePageNo").val(n);
		}
		if(s) {
			$("#noticeQueuePageSize").val(s);
		}
		$("#noticeQueue").attr("action","${ctx}/notice/noticeQueue/loginNotice");
		$("#noticeQueue").submit();
		return false;
	};
	
	var openDetail = function(queue_id) {
		top.$.jBox.open("iframe:${ctx}/notice/noticeQueue/showDetail?queueId="+queue_id, "通知详细", 750, 500, {
			buttons:{"关闭":true}, 
			submit:function(v, h, f){
				
			}, 
			loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
	}
	</script>
</body>
</html>



