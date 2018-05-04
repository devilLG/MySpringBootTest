<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>选择模板字段</title>
		<meta name="decorator" content="default" />
		<style>
			#noticeField_table li {line-height:25px;margin:2px 5px;cursor:pointer;list-style:none;}
			#noticeField_table i {margin:1px 5px;font-size:16px;}
			#noticeField_table li:hover {background-color:#DCDCDC;}
	        #noticeField_table li.active {background-color:#0088CC;color:#ffffff;}
		</style>
	</head>
	<body>
		<input type="hidden" id="noticeField" />
		<table id="noticeField_table" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th style="text-align: center;">字段编码</th>
					<th style="text-align: center;">数据库表名</th>
					<th style="text-align: center;">数据库字段名</th>
					<th style="text-align: center;">描述</th>
					<th style="text-align: center; width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${noticeFieldList}" var="noticeField">
					<tr id="${noticeField.logid}" style="">
						<td style="text-align: center;">${noticeField.field_code}</td>
						<td style="text-align: center;">${noticeField.table_name}</td>
						<td style="text-align: center;">${noticeField.field_name}</td>
						<td style="text-align: center;">${noticeField.field_desc}</td>
						<td style="text-align: center;">
							<li>
								<i class="icon-ok magnifying"></i>
								<input type="hidden" value="${noticeField.field_code}">
							</li>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
				$(document).ready(function(){
			    	$("#noticeField_table li").click(function(){
			    		$("#noticeField_table li").removeClass("active");
	    				$("#noticeField_table li i").removeClass("icon-white");
			    		$(this).addClass("active");
	    				$(this).children("i").addClass("icon-white");
			    		$("#noticeField").val($(this).children("input").val());
			    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
			    	});
			    });
		</script>
	</body>
</html>