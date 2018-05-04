<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选择产品</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<sys:message content="${message}"/>
	<table id="materialInventoryTable" class="table table-striped table-bordered table-condensed" style="cellspacing:0,width:100%">
		<thead>
			<tr>
				<th style="text-align: center;">媒体编号</th>
				<th style="text-align: center;">媒体名称</th>
				<th style="text-align: center;">媒体类型</th>
				<th style="text-align: center;">媒体格式</th>
				<th style="text-align: center;">媒体地址</th>
				<th style="text-align: center;">媒体大小(KB)</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="advertMedia">
			<tr>
				<td style="text-align: center;" nowrap>${advertMedia.mediaId}</td>
				<td style="text-align: center;" nowrap>${advertMedia.mediaName}</td>
				<td style="text-align: center;">${mediaTypeMap[advertMedia.mediaType]}</td>
				<td style="text-align: center;">${mimeTypeMap[advertMedia.mimeType]}</td>
				<td style="text-align: center;">${advertMedia.mediaAddress}</td>
				<td style="text-align: center;">${advertMedia.mediaSize}</td>
				<td style="width: 20px; text-align: center;">
					<input name="logids" value="${advertMedia.mediaId};${advertMedia.mediaType};${advertMedia.mimeType};${advertMedia.mediaAddress};${advertMedia.mediaSize};${mediaTypeMap[advertMedia.mediaType]};${mimeTypeMap[advertMedia.mimeType]}" type="checkbox">
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>