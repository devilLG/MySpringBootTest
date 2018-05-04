<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pay/payResult/">对账结果信息列表</a></li>
	</ul>
	<form:form id="payResult" modelAttribute="payResult" action="${ctx}/pay/payResult/list" method="post" class="breadcrumb form-search">
		<input id="payResultPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="payResultPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('payResult');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="payResultList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>对账日期</th>
					<th>币种</th>
					<th>对账总额</th>
					<th>对账笔数</th>
					<th>正确总额</th>
					<th>正确笔数</th>
					<th>差异总额</th>
					<th>异常笔数</th>
					<th>处理状态</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="payResult">
					<tr id="${payResult.logid}" >
						<td>${payResult.trade_day}</td>
						<td>${currencyMap[payResult.currency]}</td>
						<td>${payResult.fee_sum}</td>
						<td>${payResult.record_num}</td>
						<td>${payResult.correct_fee}</td>
						<td>${payResult.correct_num}</td>
						<td>${payResult.diff_sum}</td>
						<td>${payResult.error_num}</td>
						<td>${payResultCurStateMap[payResult.cur_state]}</td>
						<td>${payResult.create_time}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	var page = function(n,s) {
		if(n) {
			$("#payResultPageNo").val(n);
		}
		if(s) {
			$("#payResultPageSize").val(s);
		}
		$("#payResult").attr("action","${ctx}/pay/payResult/list");
		$("#payResult").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出对账结果信息数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#payResult").attr("action","${ctx}/pay/payResult/export");
					$("#payResult").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



