<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pay/payManageInfo/">管理平台账单列表</a></li>
	</ul>
	<form:form id="payManageInfo" modelAttribute="payManageInfo" action="${ctx}/pay/payManageInfo/list" method="post" class="breadcrumb form-search">
		<input id="payManageInfoPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="payManageInfoPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单单号：</label>
				<form:input path="order_id" htmlEscape="false" maxlength="64" style="width:250px"/>
			</li>
			<li><label>对账状态：</label>
				<select name="cur_state" style="width:135px">
					<option value="">-- ALL --</option>
					<c:forEach items="${paymentStateList}" var="paymentState">
						<option value="${paymentState.value}"
							${paymentState.value==param.cur_state?"selected='selected'":""}>${paymentState.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('payManageInfo');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="payManageInfoList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>对账日期</th>
					<th>客户名称</th>
					<th>支付方式</th>
					<th>币种</th>
					<th>交易流水号</th>
					<th>交易时间</th>
					<th>交易总金额</th>
					<th>订单编号</th>
					<th>对账状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="payManageInfo">
					<tr id="${payManageInfo.logid}" >
						<td><a href="${ctx}/pay/payManageInfo/form?trade_number=${payManageInfo.trade_number}">${payManageInfo.trade_day}</a></td>
						<td>${payManageInfo.login_name}</td>
						<td>${payChannelMap[payManageInfo.pay_channel]}</td>
						<td>${currencyMap[payManageInfo.currency]}</td>
						<td>${payManageInfo.trade_number}</td>
						<td>${payManageInfo.trade_time}</td>
						<td>${payManageInfo.trade_sum}</td>
						<td>${payManageInfo.order_id}</td>
						<td>${paymentStateMap[payManageInfo.cur_state]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#payManageInfoPageNo").val(n);
		}
		if(s) {
			$("#payManageInfoPageSize").val(s);
		}
		$("#payManageInfo").attr("action","${ctx}/pay/payManageInfo/list");
		$("#payManageInfo").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出管理平台账单数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#payManageInfo").attr("action","${ctx}/pay/payManageInfo/export");
					$("#payManageInfo").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



