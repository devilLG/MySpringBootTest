<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<form>
		<%-- <table class="table table-striped table-bordered table-condensed">
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
						<td>${mainTypeMap[noticeQueue.main_type]}<c:if test="${noticeQueue.send_state ne '2' }"><span style="color: red;">【未读】</span></c:if></td>
						<td title="${noticeQueue.cont }"><a style="cursor: pointer;" href="javascript:openDetail('${noticeQueue.queue_id }')">${fn:length(noticeQueue.cont) > 60 ? fn:substring(noticeQueue.cont, 0, 60).concat("...") : noticeQueue.cont}</a></td>
						<td>${noticeQueue.create_time}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div> --%>
		<div>
			<div style="padding-left: 20px;margin-top:  20px;">
				<span style="float: left;">所属业务：</span>
				<input type="text" readonly="readonly" value="${mainTypeMap[noticeQueue.main_type] }(${noticeQueue.create_time})" style="width: 600px;" />
			</div>
			<div style="padding-left: 20px;margin-top:  20px;">
				<span style="float: left;">通知内容：</span>
				<textarea style="width: 600px;height: 300px;">${noticeQueue.cont }</textarea>
			</div>
		</div>
	</form>
</body>
</html>



