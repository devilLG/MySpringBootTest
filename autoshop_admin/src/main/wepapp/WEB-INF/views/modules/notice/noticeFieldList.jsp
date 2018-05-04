<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/notice/noticeField/">公共通知模板字段列表</a></li>
		<li><a href="${ctx}/notice/noticeField/form">公共通知模板字段添加</a></li>
	</ul>
	<form:form id="noticeField" modelAttribute="noticeField" action="${ctx}/notice/noticeField/list" method="post" class="breadcrumb form-search">
		<input id="noticeFieldPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="noticeFieldPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
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
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('noticeField');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="noticeFieldList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>字段编码</th>
					<th>业务大类</th>
					<th>数据库表名称</th>
					<th>数据库字段名称</th>
					<th>描述</th>
					<th>创建时间</th>
					<th style="width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="noticeField">
					<tr id="${noticeField.logid}" >
						<td><a href="${ctx}/notice/noticeField/form?logid=${noticeField.logid}">${noticeField.field_code}</a></td>
						<td>${mainTypeMap[noticeField.main_type]}</td>
						<td>${noticeField.table_name}</td>
						<td>${noticeField.field_name}</td>
						<td>${noticeField.field_desc}</td>
						<td>${noticeField.create_time}</td>
						<td nowrap>
							<a href="${ctx}/notice/noticeField/delete?logid=${noticeField.logid}" onclick="return confirmx('要删除该公共通知模板字段吗？', this.href)">删除</a>
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
			$("#noticeFieldPageNo").val(n);
		}
		if(s) {
			$("#noticeFieldPageSize").val(s);
		}
		$("#noticeField").attr("action","${ctx}/notice/noticeField/list");
		$("#noticeField").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出公共通知模板字段数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#noticeField").attr("action","${ctx}/notice/noticeField/export");
					$("#noticeField").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



