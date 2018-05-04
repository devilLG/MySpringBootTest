<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点换货</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/barterOrder/list">站点换货列表</a></li>
		<li class="active"><a href="${ctx}/inventory/barterOrder/detail">详情【${barterOrder.order_id}】</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#main" data-toggle="tab">主单信息</a></li>
		<li><a href="#sub" data-toggle="tab"><span>换货详情</span></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="barterOrder" action="${ctx}/inventory/barterOrder/detail" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<form:hidden path="order_id"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
		<div class="tab-pane fade in active" id="main">
		<div class="control-group">
			<label class="control-label">换货编号:</label>
			<div class="controls">
				<form:input path="order_id" htmlEscape="false" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点编号:</label>
			<div class="controls">
			<form:input path="site_id" htmlEscape="false" maxlength="50" class="required" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
			<form:input path="site_name" htmlEscape="false" maxlength="50" class="required" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">换货商品数量:</label>
			<div class="controls">
				<form:input path="barter_num" htmlEscape="false" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">换货种类数量:</label>
			<div class="controls">
				<form:input path="barter_product" htmlEscape="false" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">换货货道数量:</label>
			<div class="controls">
				<form:input path="barter_box" htmlEscape="false" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">换货日期:</label>
			<div class="controls">
				 <form:input path="barter_date" htmlEscape="false" maxlength="50" class="required" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">换货人名称:</label>
			<div class="controls">
				<form:input path="barter_name" htmlEscape="false" readonly="true" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">换货类型:</label>
			<div class="controls">
				<form:select path="barter_type" class="input-small required" disabled="true">
					<form:options items="${fns:getDictList('barter_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">换货状态:</label>
			<div class="controls">
				<form:select path="barter_state" class="input-small required" disabled="true">
					<form:options items="${fns:getDictList('barter_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态时间:</label>
			<div class="controls">
				<form:input path="state_time" htmlEscape="false" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下发状态:</label>
			<div class="controls">
				<form:select path="down_state" class="input-small required" disabled="true">
					<form:options items="${fns:getDictList('down_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下发时间:</label>
			<div class="controls">
				<form:input path="down_time" htmlEscape="false" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
				<form:input path="create_time" htmlEscape="false" readonly="true" />
			</div>
		</div>
		</div>
		<div class="tab-pane fade" id="sub">
				 <table class="table table-striped table-bordered table-condensed">
				    <thead>
				    <tr><th>货道号</th><th>原本商品编码</th><th>原本商品名称</th><th>原本商品剩余数</th><th>替换商品编码</th><th>替换商品名称 </th><th>替换商品过期时间(H)</th><th>替换商品最大库存数</th><th>替换商品临界值</th><th>替换商品库存数</th><th>状态</th><th>状态时间</th></tr>
					</thead>
					<tbody>
					<c:forEach items="${barterOrderDetailList}" var="barterOrderDetail">
						<tr>
						<td>${barterOrderDetail.box_id}</td>
						<td>${barterOrderDetail.bproduct_id}</td>
						<td title="${barterOrderDetail.bproduct_name}">
					  	     <c:choose>
						  	   <c:when test="${fn:length(barterOrderDetail.bproduct_name)>5}">
						  	      ${fn:substring(barterOrderDetail.bproduct_name,0,5)}...
						  	   </c:when>
						  	   <c:otherwise>
						  	      ${barterOrderDetail.bproduct_name}
						  	   </c:otherwise>
						  	 </c:choose>
					  	</td>
						<td>${barterOrderDetail.surplus_num}</td>
						<td>${barterOrderDetail.product_id}</td>
						<td title="${barterOrderDetail.product_name}">
					  	     <c:choose>
						  	   <c:when test="${fn:length(barterOrderDetail.product_name)>5}">
						  	      ${fn:substring(barterOrderDetail.product_name,0,5)}...
						  	   </c:when>
						  	   <c:otherwise>
						  	      ${barterOrderDetail.product_name}
						  	   </c:otherwise>
						  	 </c:choose>
					  	</td>
						<td>${barterOrderDetail.invalid_date}</td>
						<td>${barterOrderDetail.inventory_num}</td>
						<td>${barterOrderDetail.warn_num}</td>
						<td>${barterOrderDetail.barter_num}</td>
						<td>${fns:getDictLabel(barterOrder.barter_state, 'barter_state', '未知')}</td>
						<td>${barterOrderDetail.state_time}</td>
						</tr>
					</c:forEach>
					</tbody>
				 </table>
		</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>