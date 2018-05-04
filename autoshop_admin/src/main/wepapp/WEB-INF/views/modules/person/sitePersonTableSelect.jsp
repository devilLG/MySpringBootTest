<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>地点人员信息</title>
		<meta name="decorator" content="default" />
		<style>
			#sitePerson_table li {line-height:25px;margin:2px 5px;cursor:pointer;list-style:none;}
			#sitePerson_table i {margin:1px 5px;font-size:16px;}
			#sitePerson_table li:hover {background-color:#DCDCDC;}
	        #sitePerson_table li.active {background-color:#0088CC;color:#ffffff;}
		</style>
	</head>
	<body>
		<form:form id="searchForm" modelAttribute="sitePerson" action="${ctx}/person/sitePerson/showSitePersonInfo?site_id=${sitePerson.site_id}" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>登录账号：</label><form:input path="login_id" htmlEscape="false" maxlength="30" class="input-large"/></li>
			<li><label>用户名称：</label><form:input path="login_name" htmlEscape="false" maxlength="50" class="input-large"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
		</ul>
		</form:form>
		<input type="hidden" id="sitePerson_parms" />
		<table id="sitePerson_table" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<tr>
					<th>登录账号</th>
					<th>用户名称</th>
					<th>手机号</th>
					<th style="text-align: center; width: 10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="sitePerson">
					<tr>
						<td>${sitePerson.login_id}</td>
						<td>${sitePerson.login_name}</td>
						<td>${sitePerson.mobile}</td>
						<td style="text-align: center;">
							<li>
								<i class="icon-ok magnifying"></i>
								<input type="hidden" value="${sitePerson.login_id},${sitePerson.login_name}">
							</li>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
				$(document).ready(function(){
			    	$("#sitePerson_table li").click(function(){
			    		debugger;
			    		$("#sitePerson_table li").removeClass("active");
	    				$("#sitePerson_table li i").removeClass("icon-white");
			    		$(this).addClass("active");
	    				$(this).children("i").addClass("icon-white");
			    		$("#sitePerson_parms").val($(this).children("input").val());
			    	});
			    	$("#sitePerson_table li").dblclick(function(){
			    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
			    	});
			    });
		</script>
	</body>
</html>