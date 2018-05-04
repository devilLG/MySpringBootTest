<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>货道信息</title>
		<meta name="decorator" content="default" />
		<style>
			#siteGoods_table li {line-height:25px;margin:2px 5px;cursor:pointer;list-style:none;}
			#siteGoods_table i {margin:1px 5px;font-size:16px;}
			#siteGoods_table li:hover {background-color:#DCDCDC;}
	        #siteGoods_table li.active {background-color:#0088CC;color:#ffffff;}
		</style>
	</head>
	<body>
		<form:form id="searchForm" modelAttribute="siteGoods" action="${ctx}/site/siteGoods/showSiteGoodsInfo?site_id=${siteGoods.site_id}" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<label>货道号：</label><form:input path="box_id" htmlEscape="false" class="input-medium" maxlength="50" />
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
		</ul>
		</form:form>
		<input type="hidden" id="siteGoods_parms" />
		<table id="siteGoods_table" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<tr>
					<th>柜号</th>
					<th>列数</th>
					<th>层数</th>
					<th>货道号</th>
					<th>货道类型</th>
					<th>温度环境</th>
					<th>状态</th>
					<th style="text-align: center; width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="siteGoods">
					<tr>
						<td>${siteGoods.cabinet_id}</td>
						<td>${siteGoods.cloumn_id}</td>
						<td>${siteGoods.layer_num}</td>
						<td>${siteGoods.box_id}</td>
						<td>${siteGoods.box_type}</td>
						<td>${fns:getDictLabel(siteGoods.temper_type, 'temper_type', '未知')}</td>
						<td>${fns:getDictLabel(siteGoods.cur_state, 'cur_state', '未知')}</td>
						<td style="text-align: center;">
							<li>
								<i class="icon-ok magnifying"></i>
								<input type="hidden" value="${siteGoods.box_id}">
							</li>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
				$(document).ready(function(){
			    	$("#siteGoods_table li").click(function(){
			    		debugger;
			    		$("#siteGoods_table li").removeClass("active");
	    				$("#siteGoods_table li i").removeClass("icon-white");
			    		$(this).addClass("active");
	    				$(this).children("i").addClass("icon-white");
			    		$("#siteGoods_parms").val($(this).children("input").val());
			    	});
			    	$("#siteGoods_table li").dblclick(function(){
			    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
			    	});
			    });
		</script>
	</body>
</html>