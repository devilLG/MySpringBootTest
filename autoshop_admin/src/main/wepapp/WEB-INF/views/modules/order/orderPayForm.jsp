<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link rel="stylesheet" charset="utf-8" type="text/css" href="${ctx}/static/foxibox/style/style.css" />
<script type="text/javascript" charset="utf-8" src="${ctx}/static/foxibox/script/jquery-foxibox-0.2.min.js"></script>
<html>
<head>
	<title>销售订单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
	 		$("#pics a").foxibox();
			$("#no").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
	function getPayCode(){
	debugger;
	    var pay_type=document.getElementById('pay_type_orderPayForm').value;
	    if(pay_type == ''){
	     toastr.warning("请选择支付方式");
	     return;
	    }
	    var id=document.getElementById('logid_orderPayForm').value;
	     $.ajax({
		       type:"POST",
		       url:"${ctx}/order/orderApply/getPayCode",
		       data:{"method":"getPayCode","id":id,"pay_type":pay_type},
		       dataType:"json",
		       global:false,
		       success : function(data) {
				if (null != data && null != data.img_url) {
					$("#img_url_orderPayForm").html("<div class='control-group'><label class='control-label'>支付二维码:</label><div class='controls'><img src='"+data.img_url+"' alt=''  /></div></div>");
					document.getElementById("OutTradeNo_orderPayForm").value=data.OutTradeNo;
				} else {
					 toastr.warning("获取支付二维码失败");
				}
	
			}
		    });
	}
	
	function confirmPaid(){
	debugger;
	    var OutTradeNo=document.getElementById('OutTradeNo_orderPayForm').value;
	    if(OutTradeNo == ''){
	     toastr.warning("请先获取支付二维码进行支付");
	     return;
	    }
	    var id=document.getElementById('logid_orderPayForm').value;
	     $.ajax({
		       type:"POST",
		       url:"${ctx}/order/orderApply/confirmPaid",
		       data:{"method":"confirmPaid","id":id,"OutTradeNo":OutTradeNo},
		       dataType:"json",
		       global:false,
		       success : function(data) {
				if (null != data && null != data.error) {
					 toastr.warning("确认支付失败");
				} else {
					 toastr.warning("确认支付成功");
				}
	
			}
		    });
	}
	
	function queryPayStatus(){
	debugger;
	    var OutTradeNo=document.getElementById('OutTradeNo_orderPayForm').value;
	    if(OutTradeNo == ''){
	     toastr.warning("查询失败，请先获取支付二维码");
	     return;
	    }
	    var id=document.getElementById('logid_orderPayForm').value;
	     $.ajax({
		       type:"POST",
		       url:"${ctx}/order/orderApply/queryPayStatus",
		       data:{"method":"queryPayStatus","id":id,"OutTradeNo":OutTradeNo},
		       dataType:"json",
		       global:false,
		       success : function(data) {
				if (null != data && null != data.pay_state) {
					if (data.pay_state == '00'){
					 toastr.warning("等待支付");
					}else if (data.pay_state == '01'){
					 /* toastr.warning("支付成功"); */
					 window.location .href="${ctx}/order/orderApply/paySuccess";
					}else if (data.pay_state == '02'){
					 toastr.warning("支付失效");
					}
				} else {
					 toastr.warning("查询失败");
				}
	
			}
		    });
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/order/orderApply/list">销售订单列表</a></li>
		<li class="active"><a href="${ctx}/order/orderApply/payForm">支付【${orderApply.order_id}】</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#main" data-toggle="tab">订单信息</a></li>
		<li><a href="#sub" data-toggle="tab"><span>商品信息</span></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orderApply" action="${ctx}/order/orderApply/queryPayStatus" method="post" class="form-horizontal">
		<form:hidden path="logid" id="logid_orderPayForm"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
		<div class="tab-pane fade in active" id="main">
		<div class="control-group">
			<label class="control-label">站点编号:</label>
			<div class="controls">
				<form:input path="site_id" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
				<form:input path="site_name" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单编号:</label>
			<div class="controls">
				<form:input path="order_id" htmlEscape="false" readonly="true" style="width:250px"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户名称:</label>
			<div class="controls">
				<form:input path="login_name" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请时间:</label>
			<div class="controls">
			<form:input path="create_time" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下单渠道:</label>
			<div class="controls">
				<form:select path="sale_channel" class="input-small required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sale_channel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付状态:</label>
			<div class="controls">
				<form:select path="pay_state" class="input-small required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pay_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付超期:</label>
			<div class="controls">
				<form:input path="pay_overtime" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="cur_state" class="input-medium" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('order_apply_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态时间:</label>
			<div class="controls">
				<form:input path="state_time" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回收状态:</label>
			<div class="controls">
				<form:select path="recovery_type" class="input-small required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('recovery_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取件密码:</label>
			<div class="controls">
				<form:input path="pass_word" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取件超期:</label>
			<div class="controls">
				<form:input path="fetch_overTime" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下发状态:</label>
			<div class="controls">
				<form:select path="down_state" class="input-small required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('down_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下发时间:</label>
			<div class="controls">
				<form:input path="down_time" htmlEscape="false" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付方式:</label>
			<div class="controls">
				<form:select path="pay_type" class="input-small required" id="pay_type_orderPayForm">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付金额:</label>
			<div class="controls">
				<form:input path="sale_total" htmlEscape="false" readonly="true"/>（元）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易流水号:</label>
			<div class="controls">
				<form:input path="OutTradeNo" htmlEscape="false" readonly="true" id="OutTradeNo_orderPayForm"/>
			</div>
		</div>
		</div>
		<div class="tab-pane fade" id="sub">
				 <table class="table table-striped table-bordered table-condensed">
				    <thead>
				    <tr>
						<th>商品编码</th>
						<th>商品名称</th>
						<th>商品分类</th>
						<th>商品品牌 </th>
						<th>标准价（元）</th>
						<th>商品数量</th>
						<th>金额（元）</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${orderProductList}" var="orderProduct">
						<tr>
							<td>${orderProduct.product_id}</td>
							<td>${orderProduct.product_name}</td>
							<td>${orderProduct.productType_name}</td>
							<td>${orderProduct.brand_name}</td>
							<td>${orderProduct.normal_price}</td>
							<td>${orderProduct.sale_num}</td>
							<td>${orderProduct.sale_total}</td>
						</tr>
					</c:forEach>
					</tbody>
				 </table>
		</div>
		</div>
		<div class="control-group" id="img_url_orderPayForm">
		</div>
		<div class="form-actions">
			<input id="btnGetPayCode" class="btn btn-primary" type="button" value="获取支付二维码"  onclick="getPayCode()"/>
			<input id="btnConfirmPaid" class="btn btn-primary" type="button" value="确认支付"  onclick="confirmPaid()"/>
			<input id="btnSubmit" class="btn btn-primary" type="button" value="查询支付状态" onclick="queryPayStatus()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>