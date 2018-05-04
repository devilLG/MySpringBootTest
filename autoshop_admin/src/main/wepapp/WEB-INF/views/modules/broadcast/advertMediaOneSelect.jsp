<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>选择媒体信息</title>
		<meta name="decorator" content="default" />
		<style>
			#advertMedia_table li {line-height:25px;margin:2px 5px;cursor:pointer;list-style:none;}
			#advertMedia_table i {margin:1px 5px;font-size:16px;}
			#advertMedia_table li:hover {background-color:#DCDCDC;}
	        #advertMedia_table li.active {background-color:#0088CC;color:#ffffff;}
		</style>
	</head>
	<body>
		<input type="hidden" id="advert_media" />
		<table id="advertMedia_table" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th style="text-align: center;">媒体编号</th>
					<th style="text-align: center;">媒体名称</th>
					<th style="text-align: center;">媒体类型</th>
					<th style="text-align: center;">媒体格式</th>
					<th style="text-align: center;">媒体地址</th>
					<th style="text-align: center;">媒体大小(KB)</th>
					<th style="text-align: center; width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${advertMediaList}" var="advertMedia">
					<tr id="${advertMedia.mediaId}" style="">
						<td style="text-align: center;" nowrap>${advertMedia.mediaId}</td>
						<td style="text-align: center;" nowrap>${advertMedia.mediaName}</td>
						<td style="text-align: center;">${mediaTypeMap[advertMedia.mediaType]}</td>
						<td style="text-align: center;">${mimeTypeMap[advertMedia.mimeType]}</td>
						<td style="text-align: center;">${advertMedia.mediaAddress}</td>
						<td style="text-align: center;">${advertMedia.mediaSize}</td>
						<td style="text-align: center;">
							<li>
								<i class="icon-ok magnifying"></i>
								<input type="hidden" value="${advertMedia.mediaId},${advertMedia.mediaType},${advertMedia.mimeType},${advertMedia.mediaAddress},${advertMedia.mediaSize}">
							</li>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
				$(document).ready(function(){
			    	$("#advertMedia_table li").click(function(){
			    		$("#advertMedia_table li").removeClass("active");
	    				$("#advertMedia_table li i").removeClass("icon-white");
			    		$(this).addClass("active");
	    				$(this).children("i").addClass("icon-white");
			    		$("#advert_media").val($(this).children("input").val());
			    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
			    	});
			    });
		</script>
	</body>
</html>