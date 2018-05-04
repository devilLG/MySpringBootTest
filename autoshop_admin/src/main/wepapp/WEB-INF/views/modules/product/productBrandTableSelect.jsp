<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>商品品牌信息</title>
		<meta name="decorator" content="default" />
		<style>
			#productBrand_table li {line-height:25px;margin:2px 5px;cursor:pointer;list-style:none;}
			#productBrand_table i {margin:1px 5px;font-size:16px;}
			#productBrand_table li:hover {background-color:#DCDCDC;}
	        #productBrand_table li.active {background-color:#0088CC;color:#ffffff;}
		</style>
	</head>
	<body>
		<form:form id="searchForm" modelAttribute="productBrand" action="${ctx}/product/productBrand/showProductBrandInfo?corp_id=${productBrand.corp_id}" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>品牌名称：</label><form:input path="brand_name" htmlEscape="false" maxlength="50" class="input-large"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
		</ul>
		</form:form>
		<input type="hidden" id="productBrand_parms" />
		<table id="productBrand_table" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<tr>
					<th>品牌编号</th>
					<th>品牌名称</th>
					<th>品牌公司</th>
					<th style="text-align: center; width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="productBrand">
					<tr>
						<td>${productBrand.brand_id}</td>
						<td>${productBrand.brand_name}</td>
						<td>${productBrand.brandCorp_name}</td>
						<td style="text-align: center;">
							<li>
								<i class="icon-ok magnifying"></i>
								<input type="hidden" value="${productBrand.brand_id},${productBrand.brand_name}">
							</li>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
				$(document).ready(function(){
			    	$("#productBrand_table li").click(function(){
			    		debugger;
			    		$("#productBrand_table li").removeClass("active");
	    				$("#productBrand_table li i").removeClass("icon-white");
			    		$(this).addClass("active");
	    				$(this).children("i").addClass("icon-white");
			    		$("#productBrand_parms").val($(this).children("input").val());
			    	});
			    	$("#productBrand_table li").dblclick(function(){
			    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
			    	});
			    });
		</script>
	</body>
</html>