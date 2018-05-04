<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售订单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#orderConfirmForm").validate({
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
		
        function validCheckForm(){
        debugger;
        	var lock_type=document.getElementById("lock_type_orderConfirmForm").value;
        	if(lock_type == ''){
        		toastr.warning("请选择锁定类型");
				return false;
        	}
        	if(lock_type == 1){
        		var reg = /^[0-9:]+$/;
	            var obj = document.getElementsByName("box_id"); 
				for(var i = 0; i < obj.length; i++)
				 { 
					if(!reg.test(obj[i].value)){
						toastr.warning("请填写锁定货道信息");
						return false;
					}
				 }
        	}
			$("#orderConfirmForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/order/orderApply/list">销售订单列表</a></li>
		<li class="active"><a href="${ctx}/order/orderApply/confirmForm">确认【${orderApply.order_id}】</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#main" data-toggle="tab">订单信息</a></li>
		<li><a href="#sub" data-toggle="tab"><span>商品信息</span></a></li>
	</ul><br/>
	<form:form id="orderConfirmForm" modelAttribute="orderApply" action="${ctx}/order/orderApply/confirm" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
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
			<label class="control-label">支付方式:</label>
			<div class="controls">
				<form:select path="pay_type" class="input-small required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			<label class="control-label">支付金额:</label>
			<div class="controls">
				<form:input path="sale_total" htmlEscape="false" readonly="true"/>（元）
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
			<label class="control-label">锁定类型:</label>
			<div class="controls">
				<form:select path="lock_type" class="input-small required" id="lock_type_orderConfirmForm">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('lock_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
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
						<th>商品数量</th>
						<th>锁定货道</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${orderProductList}" var="orderProduct">
						<tr>
						<td><input type='text' readonly='readonly'  name='product_id' value='${orderProduct.product_id}'></td>
						<td><input onmouseover='this.title=this.value' style='white-space:nowrap;overflow:hidden;text-overflow:ellipsis;' type='text' readonly='readonly' name='product_name' value='${orderProduct.product_name}'></td>
						<td><input type='text' readonly='readonly' name='productType_name' value='${orderProduct.productType_name}'></td>
						<td><input type='text' readonly='readonly' name='brand_name' value='${orderProduct.brand_name}'></td>
						<td><input type='text' style='width:60px' readonly='readonly' name='sale_num' value='${orderProduct.sale_num}'></td>
						<!-- <td><input type='text' style='width:60px' name='box_id' value=''></td> -->
						<td><select  style='width:60px' name="box_id"><option value=" "></option><c:forEach items="${orderProduct.inventoryGoodsList}" var="inventoryGoods"><option value="${inventoryGoods.box_id}">${inventoryGoods.box_id }</option></c:forEach></select></td>
						</tr>
					</c:forEach>
					</tbody>
				 </table>
		</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="确认" onclick="validCheckForm()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>