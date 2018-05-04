<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pay/payPaymentInfo/">支付平台账单列表</a></li>
	</ul>
	<form:form id="payPaymentInfo" modelAttribute="payPaymentInfo" action="${ctx}/pay/payPaymentInfo/list" method="post" class="breadcrumb form-search">
		<input id="payPaymentInfoPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="payPaymentInfoPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('payPaymentInfo');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="payPaymentInfoList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>对账日期</th>
					<th>支付编号</th>
					<th>支付方式</th>
					<th>对账金额</th>
					<th>币种</th>
					<th>交易流水号</th>
					<th>交易时间</th>
					<th>交易总金额</th>
					<th>订单编号</th>
					<th>地点名称</th>
					<th>对账状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="payPaymentInfo">
					<tr id="${payPaymentInfo.logid}" >
						<td><a href="${ctx}/pay/payPaymentInfo/form?trade_number=${payPaymentInfo.trade_number}">${payPaymentInfo.trade_day}</a></td>
						<td>${payPaymentInfo.pay_id}</td>
						<td>${payChannelMap[payPaymentInfo.pay_channel]}</td>
						<td>${payPaymentInfo.fee_sum}</td>
						<td>${currencyMap[payPaymentInfo.currency]}</td>
						<td>${payPaymentInfo.trade_number}</td>
						<td>${payPaymentInfo.trade_time}</td>
						<td>${payPaymentInfo.trade_sum}</td>
						<td>${payPaymentInfo.order_id}</td>
						<td>${payPaymentInfo.site_name}</td>
						<td>${paymentStateMap[payPaymentInfo.cur_state]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#payPaymentInfoPageNo").val(n);
		}
		if(s) {
			$("#payPaymentInfoPageSize").val(s);
		}
		$("#payPaymentInfo").attr("action","${ctx}/pay/payPaymentInfo/list");
		$("#payPaymentInfo").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出支付平台账单数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#payPaymentInfo").attr("action","${ctx}/pay/payPaymentInfo/export");
					$("#payPaymentInfo").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



