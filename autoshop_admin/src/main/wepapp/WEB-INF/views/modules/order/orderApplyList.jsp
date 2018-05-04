<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售订单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出销售订单数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/order/orderApply/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/order/orderApply/list");
			$("#searchForm").submit();
	    	return false;
	    } 
	    
	    function cancel(){ 
	 	  var ids = selectCheck();
	 	  if(ids != false){
	 	 	 var orderInfo = ids.split(';');
	 	  	 var id = orderInfo[0];
	 	  	 var curState = orderInfo[1];
	 	  	/*  if(curState != '01'){
	 	  	  	 alertx("当前状态下不允许进行此操作！");
			 	 return false;
	 	  	 } */
	 	 	 window.location .href="${ctx}/order/orderApply/cancelForm?id="+id;
	 	  }
		}
		
		function confirm(){ 
	 	  var ids = selectCheck();
	 	  if(ids != false){
	 	   	 var orderInfo = ids.split(';');
	 	  	 var id = orderInfo[0];
	 	  	 var curState = orderInfo[1];
	 	  	 var payState = orderInfo[2];
	 	  	 if(curState != '01' && curState != '07'){
	 	  	  	 alertx("当前状态下不允许进行此操作！");
			 	 return false;
	 	  	 }
	 	  	 if(payState != '01'){
	 	  	  	 alertx("当前状态下不允许进行此操作！");
			 	 return false;
	 	  	 }
	 	 	 window.location .href="${ctx}/order/orderApply/confirmForm?id="+id;
	 	  }
		}
		
		function pay(){ 
	 	  var ids = selectCheck();
	 	  if(ids != false){
	 	 	 var orderInfo = ids.split(';');
	 	  	 var id = orderInfo[0];
	 	  	 var curState = orderInfo[1];
	 	  	/*  if(curState != '01'){
	 	  	  	 alertx("当前状态下不允许进行此操作！");
			 	 return false;
	 	  	 } */
	 	  	 var payState = orderInfo[2];
	 	  	 if(payState != '00'){
	 	  	  	 alertx("当前状态下不允许进行此操作！");
			 	 return false;
	 	  	 }
	 	 	 window.location .href="${ctx}/order/orderApply/payForm?id="+id;
	 	  }
		}
		
		function pickup(){ 
	 	  var ids = selectCheck();
	 	  if(ids != false){
	 	  	 var orderInfo = ids.split(';');
	 	  	 var id = orderInfo[0];
	 	  	 var curState = orderInfo[1];
	 	  	 if(curState != '02'){
	 	  	  	 alertx("当前状态下不允许进行此操作！");
			 	 return false;
	 	  	 }
	 	 	 window.location .href="${ctx}/order/orderApply/pickupForm?id="+id;
	 	  }
		}
		
		function fetch(){ 
	 	  var ids = selectCheck();
	 	  if(ids != false){
	 	   	 var orderInfo = ids.split(';');
	 	  	 var id = orderInfo[0];
	 	  	 var curState = orderInfo[1];
	 	  	 if(curState != '04'){
	 	  	  	 alertx("当前状态下不允许进行此操作！");
			 	 return false;
	 	  	 }
	 	 	 window.location .href="${ctx}/order/orderApply/fetchForm?id="+id;
	 	  }
		}
		
		function recover(){ 
	 	  var ids = selectCheck();
	 	  if(ids != false){
	 	  	 var orderInfo = ids.split(';');
	 	  	 var id = orderInfo[0];
	 	  	 var curState = orderInfo[1];
	 	  	 if(curState != '04'){
	 	  	  	 alertx("当前状态下不允许进行此操作！");
			 	 return false;
	 	  	 }
	 	 	 window.location .href="${ctx}/order/orderApply/recoverForm?id="+id;
	 	  }
		}
		
		function abnormal(){ 
	 	  var ids = selectCheck();
	 	  if(ids != false){
	 	  	 var orderInfo = ids.split(';');
	 	  	 var id = orderInfo[0];
	 	  	 var curState = orderInfo[1];
	 	  	/*  if(curState != '01'){
	 	  	  	 alertx("当前状态下不允许进行此操作！");
			 	 return false;
	 	  	 } */
	 	 	 window.location .href="${ctx}/order/orderApply/abnormalForm?id="+id;
	 	  }
		}
		
		function refund(){ 
	 	  var ids = selectCheck();
	 	  if(ids != false){
	 	  	 var orderInfo = ids.split(';');
	 	  	 var id = orderInfo[0];
	 	  	 var payState = orderInfo[2];
	 	  	 if(payState != '01' && payState != '06'){
	 	  	  	 alertx("当前状态下不允许进行此操作！");
			 	 return false;
	 	  	 }
	 	 	 window.location .href="${ctx}/order/orderApply/refundForm?id="+id;
	 	  }
		}
		 
		function selectCheck(){ 
		 	 var checkNum = 0;
		 	 var id = "";
		  	 var obj = document.getElementsByName("checkbox"); 
		  	 var $_checkBox = $("input[name='checkbox']:checked");
			 for(var i = 0; i < obj.length; i++)
			 { 
			    if(obj[i].checked)
				{
				  checkNum = checkNum+1;
			      id = obj[i].value;
				 }
			 }
			 if(checkNum > 1 || checkNum < 1){
			 	 alertx("请选择一条数据！");
			 	 return false;
			 }else{
			 	 return id;
			 }
		 }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/orderApply/list">销售订单列表</a></li>
		<li><a href="${ctx}/order/orderApply/applyForm">销售订单申请</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderApply" action="${ctx}/order/orderApply/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<input name="pay_state" type="hidden" value="${orderApply.pay_state}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label><form:input path="order_id" htmlEscape="false" maxlength="30" class="input-large" style="width:250px"/></li>
			<li><label>站点简称：</label><sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="site_id" value="${orderApply.site_id}" labelName="site_name" labelValue="${orderApply.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/></li>
			<li><label>客户名称：</label><form:input path="login_name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>下单渠道：</label><form:select path="sale_channel" class="input-small">
								<form:option value="" label="请选择"/>
								<form:options items="${fns:getDictList('sale_channel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select></li>
			<li class="clearfix"></li>
			<li><label>订单状态：</label><form:select path="cur_state" class="input-small">
								<form:option value="" label="请选择"/>
								<form:options items="${fns:getDictList('order_apply_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select></li>
			<li><label>下单时间：</label><input id="beginTime_orderApplyList" style="width:95px" name="beginTime" value="${orderApply.beginTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								-
								<input id="endTime_orderApplyList" style="width:95px" name="endTime" value="${orderApply.endTime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime_orderApplyList\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input class="btn btn-warning" type="button" value="重置" onclick="myResult('searchForm');"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
	    <div class="btn-group btn-group-xs">
		    <button type="button" class="btn btn-warning" onclick="cancel()">取消</button>
		    <button type="button" class="btn btn-primary" onclick="confirm()">确认</button>
		    <button type="button" class="btn btn-warning" onclick="pay()">支付</button>
		    <button type="button" class="btn btn-primary" onclick="pickup()">提货</button>
		    <button type="button" class="btn btn-warning" onclick="fetch()">取货</button>
		    <button type="button" class="btn btn-primary" onclick="recover()">回收</button>
		    <button type="button" class="btn btn-warning" onclick="abnormal()">异常</button>
		    <button type="button" class="btn btn-warning" onclick="refund()">退款</button>
		</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th style="text-align: center"></th><th>订单编号</th><th>站点编号</th><th>站点简称</th><th>客户名称</th><th>下单渠道</th><th>商品数量</th><th>商品总价（元）</th><th>支付方式</th><th>支付状态</th><th>订单状态</th><th>下发状态</th><th>下单时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderApply">
			<c:if test="${not empty orderApply.abnormalType}">
			<tr style="color:red;">
			</c:if>
			<c:if test="${empty orderApply.abnormalType}">
			<tr>
			</c:if>
				<td style="text-align: center"><input name="checkbox" value="${orderApply.logid};${orderApply.cur_state};${orderApply.pay_state}" type="checkbox"></td>
				<td>${orderApply.order_id}</td>
				<td>${orderApply.site_id}</td>
				<td title="${orderApply.site_name}">
			  	     <c:choose>
				  	   <c:when test="${fn:length(orderApply.site_name)>12}">
				  	      ${fn:substring(orderApply.site_name,0,12)}...
				  	   </c:when>
				  	   <c:otherwise>
				  	      ${orderApply.site_name}
				  	   </c:otherwise>
				  	 </c:choose>
			  	</td>
				<td>${orderApply.login_name}</td>
				<td>${fns:getDictLabel(orderApply.sale_channel, 'sale_channel', '未知')}</td>
				<td>${orderApply.product_num}</td>
				<td>${orderApply.sale_total}</td>
				<td>${fns:getDictLabel(orderApply.pay_type, 'pay_type', '未知')}</td>
				<td>${fns:getDictLabel(orderApply.pay_state, 'pay_state', '未知')}</td>
				<td>${fns:getDictLabel(orderApply.cur_state, 'order_apply_state', '未知')}</td>
				<td>${fns:getDictLabel(orderApply.down_state, 'down_state', '未知')}</td>
				<td>${orderApply.create_time}</td>
		        <td>
					<a href="${ctx}/order/orderApply/detail?id=${orderApply.logid}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>