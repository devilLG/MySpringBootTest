<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选择货道</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#selectSiteGoodsForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<form:form id="selectSiteGoodsForm" modelAttribute="siteGoods" action="${ctx}/site/siteGoods/selectSiteGoods?site_id=${siteGoods.site_id}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>货道号：</label><form:input path="box_id" htmlEscape="false" class="input-medium" maxlength="50" />
		<label>温度环境：</label><form:select path="temper_type" class="input-small">
								<form:option value="" label="请选择"/>
								<form:options items="${fns:getDictList('temper_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
		<input class="btn btn-warning" type="button" value="重置" onclick="myResult('selectSiteGoodsForm');"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="selectSiteGoodsTable" class="table table-striped table-bordered table-condensed" style="cellspacing:0,width:100%">
		<thead><tr>
		<th></th>
		<th>柜号</th>
		<th>列数</th>
		<th>层数</th>
		<th>货道号</th>
		<th>货道类型</th>
		<th>温度环境</th>
		<th>状态</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="siteGoods">
			<tr>
				<td style="width: 20px; text-align: center;">
					<input name="logids" type="checkbox" value="${siteGoods.cabinet_id};${siteGoods.cloumn_id};${siteGoods.layer_num};${siteGoods.box_id};${siteGoods.temper_type};${siteGoods.cur_state}">
				</td>
				<td>${siteGoods.cabinet_id}</td>
				<td>${siteGoods.cloumn_id}</td>
				<td>${siteGoods.layer_num}</td>
				<td>${siteGoods.box_id}</td>
				<td>${siteGoods.box_type}</td>
				<td>${fns:getDictLabel(siteGoods.temper_type, 'temper_type', '未知')}</td>
				<td>${fns:getDictLabel(siteGoods.cur_state, 'cur_state', '未知')}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>