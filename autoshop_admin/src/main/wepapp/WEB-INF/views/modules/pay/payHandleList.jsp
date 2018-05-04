<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pay/payHandle/">对账异常处理列表</a></li>
	</ul>
	<form:form id="payHandle" modelAttribute="payHandle" action="${ctx}/pay/payHandle/list" method="post" class="breadcrumb form-search">
		<input id="payHandlePageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="payHandlePageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>处理状态：</label>
				<select name="cur_state" style="width:135px">
					<option value="">-- ALL --</option>
					<c:forEach items="${payResultCurStateList}" var="payResultCurState">
						<option value="${payResultCurState.value}"
							${payResultCurState.value==param.cur_state?"selected='selected'":""}>${payResultCurState.label}
						</option>
					</c:forEach>
				</select>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('payHandle');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="payHandleList" method="post">
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
					<th>订单编号</th>
					<th>地点名称</th>
					<th>管理平台支付金额</th>
					<th>支付平台支付金额</th>
					<th>状态</th>
					<th>处理人名称</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="payHandle">
					<tr id="${payHandle.logid}" >
						<td>${payHandle.trade_day}</td>
						<td>${payHandle.pay_id}</td>
						<td>${payChannelMap[payHandle.pay_channel]}</td>
						<td>${payHandle.fee_sum}</td>
						<td>${currencyMap[payHandle.currency]}</td>
						<td>${payHandle.trade_number}</td>
						<td>${payHandle.trade_time}</td>
						<td>${payHandle.order_id}</td>
						<td>${payHandle.site_name}</td>
						<td>${payHandle.manage_money}</td>
						<td>${payHandle.payment_money}</td>
						<td>${procStatusMap[payHandle.proc_status]}</td>
						<td>${payHandle.handler_name}</td>
						<td>${payHandle.create_time}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#payHandlePageNo").val(n);
		}
		if(s) {
			$("#payHandlePageSize").val(s);
		}
		$("#payHandle").attr("action","${ctx}/pay/payHandle/list");
		$("#payHandle").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出对账异常处理数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#payHandle").attr("action","${ctx}/pay/payHandle/export");
					$("#payHandle").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



