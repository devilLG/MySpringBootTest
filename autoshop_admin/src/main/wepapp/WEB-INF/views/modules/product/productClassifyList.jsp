<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商品分类管理</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/productClassify/">商品分类列表</a>
		</li>
		<li><a href="${ctx}/product/productClassify/form">商品分类添加</a>
		</li>
	</ul>
	<sys:message content="${message }" />
	<form id="productClassifyListForm" method="post">
	<table id="productClassifyTreeTable"
		class="table table-striped table-bordered table-condensed hide">
		<thead>
			<tr>                  
				<th>分类名称</th>
				<th>分类代码</th>
				<th>排序</th>
				<th style="width: 20%;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ProductClassifyList }" var="ProductClassify">
				<tr id="${ProductClassify.classify_id}" pId="${ProductClassify.parent_id ne '1' ? ProductClassify.parent_id : '0'}">
					<td nowrap>
						<input type="hidden" name="_classify_id" value="${ProductClassify.classify_id}">
						<a href="${ctx}/product/productClassify/form?classify_id=${ProductClassify.classify_id}">${ProductClassify.classify_name}</a>
					</td>
					<td>${ProductClassify.classify_id}</td>
					<td style="text-align:center;">
							<input type="hidden" name="ids" value="${ProductClassify.logid}"/>
							<input name="sorts" type="text" value="${ProductClassify.sort_by}" style="width:50px;margin:0;padding:0;text-align:center;">
					</td>
					<td nowrap>
					<a href="${ctx}/product/productClassify/form?classify_id=${ProductClassify.classify_id}">修改</a>
					<a href="${ctx}/product/productClassify/delete?classify_id=${ProductClassify.classify_id}" onclick="return confirmx('要删除该分类及所有子分类项吗？', this.href)">删除</a>
					<a href="${ctx}/product/productClassify/image?classify_id=${ProductClassify.classify_id}">图片管理</a> 
					<a href="${ctx}/product/productClassify/form?parent_id=${ProductClassify.classify_id}">添加下级分类</a> 
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="form-actions pagination-left">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/>
	</div>
	</form>
<script type="text/javascript">
	$(document).ready(function() {
		$("#productClassifyTreeTable").treeTable({expandLevel : 10}).show();
	});
	
	function updateSort() {
			loading('正在提交，请稍等...');
	    	$("#productClassifyListForm").attr("action", "${ctx}/product/productClassify/updateSort");
	    	$("#productClassifyListForm").submit();
    	}
   	
</script>
</body>
</html>