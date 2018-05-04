<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售订单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#orderApplyForm").validate({
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
		
		function showProductInfoDialog(){
		    debugger;
		    var site_id = $("input[name='site_id']").val();
		    if(site_id == ''){
				toastr.warning("请选择站点");
				return false;
			}
		    top.$.jBox.open("iframe:${ctx}/inventory/inventoryProduct/selectInventoryProduct?site_id="+site_id, "选择商品", 950, 600, {
			buttons:{"确定":"ok","关闭":true}, submit:function(v, h, f){debugger;
				if (v=="ok"){
					 var table = h.find("iframe")[0].contentWindow.document.getElementById('materialInventoryTable');
					 var length = h.find("iframe")[0].contentWindow.document.all.length;
					 var ckList = "";
					 for(var i=0 ; i<length ; i++){
					 	if(h.find("iframe")[0].contentWindow.document.all[i].type == 'checkbox' && h.find("iframe")[0].contentWindow.document.all[i].checked == true){
					 	 ckList = ckList + "," + h.find("iframe")[0].contentWindow.document.all[i].value;
					 	}
					 }
				     debugger;
					 ckList = ckList.substring(1).split(',');
					 var logids = "";
					 if(ckList != undefined && ckList != "" && ckList.length>0){
						 for(var i=0;i<ckList.length;i++){
							 logids=ckList[i];
					 	 	 var timestamp=new Date().getTime();
							 var productInfo = logids.split(';');
							 var product_id=productInfo[0];
							 var product_name=productInfo[1];
							 var productType_name=productInfo[2];
							 var brand_name=productInfo[3];
							 var normal_price=productInfo[4];
							 var normal_price_str=$("input[title='normal_price_"+product_id+"']").attr("value");
							 if(normal_price_str=="" || normal_price_str==undefined || normal_price_str=='undefined'){
								 $("#selectProductInfoListUl").append("<tr><td><input type='text' readonly='readonly' size='3' name='product_id' value='"+product_id
					    		+"'></td><td><input onmouseover='this.title=this.value' style='white-space:nowrap;overflow:hidden;text-overflow:ellipsis;' type='text' readonly='readonly' size='30' name='product_name' value='"+product_name
					    		+"'></td><td><input type='text' readonly='readonly' size='30' name='productType_name' value='"+productType_name
					    		+"'></td><td><input type='text' style='width:60px' readonly='readonly' size='30' name='brand_name' value='"+brand_name
					    		+"'></td><td><input type='text' style='width:60px' readonly='readonly' size='30' title='normal_price_"+product_id+"' name='normal_price' value='"+normal_price+"' id='normal_price_"+product_id
					    		+"'></td><td><input type='text' style='width:60px' size='8' maxlength='6' title='SaleNum_"+product_id+"' class='onlyNum' name='sale_num' id='sale_num_"+product_id+"' onkeyup='fillTotalMoney(\""+product_id+'\")'
					    		+"'></td><td><input type='text' style='width:60px' readonly='readonly' size='30' name='total_money' id='total_money_"+product_id
					    		+"'></td><td><a title='移除' href='javascript:void();' onclick='removeProductItem(this)' class='btnDel'>X</a></td></tr>");
				    		}else{
				    		   var saleNum=$("input[title='SaleNum_"+product_id+"']").attr("value");
				    		   $("input[title='SaleNum_"+product_id+"']").attr("value",saleNum*1+1);
				    		}  
						 }
					 }
					}
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
		}
		
		function removeProductItem(item){
		debugger;
		    $(item).parent().parent().remove();
		    var total_totalMoney = 0;
		    var obj = document.getElementsByName("total_money"); 
			for(var i = 0; i < obj.length; i++)
			 { 
			    total_totalMoney = total_totalMoney*1 + obj[i].value*1;
			 }
			 document.getElementById("sale_total").value=total_totalMoney;
		}
		
		function fillTotalMoney(product_id){
		debugger;
			var sale_num=$("#sale_num_"+product_id).attr("value");
			if((sale_num | 0) != sale_num || sale_num<1 || sale_num>999){
			    if(sale_num=="" || sale_num==undefined){
			      return false;
			    }
			    toastr.warning("只能输入1~999的整数");
			    $("#total_money_"+product_id).attr("value","0");
			    return false;
			}
            var normal_price=$("#normal_price_"+product_id).attr("value");
            $("#total_money_"+product_id).attr("value",(normal_price*sale_num).toFixed(2));
            //统计
			var total_totalMoney = 0;
            var obj = document.getElementsByName("total_money"); 
			for(var i = 0; i < obj.length; i++)
			 { 
			    total_totalMoney = total_totalMoney*1 + obj[i].value*1;
			 }
			 document.getElementById("sale_total").value=total_totalMoney;
        }
        
        function validCheckForm(){
        debugger;
            var obj = document.getElementsByName("product_id"); 
			if(obj.length <= 0){
				toastr.warning("请选择商品信息");
				return false;
			}
			for(var m=0 ; m < obj.length ; m++){
				var a = obj[m].value;
				for(var n=m+1 ; n < obj.length ; n++){
					if(a==obj[n].value){
						toastr.warning("商品信息重复");
						return false;
					}
				}
			}
			var obj = document.getElementsByName("sale_num"); 
			for(var i = 0; i < obj.length; i++)
			 { 
				if((obj[i].value | 0) != obj[i].value || obj[i].value<1 || obj[i].value>999){
					toastr.warning("商品数量只能输入1~999的整数");
					return false;
				}
			 }
			$("#orderApplyForm").submit();
		}
		
		/* function changeSaleChannel(){
		debugger;
			var sale_channel=document.getElementById("sale_channel_orderApplyForm").value;
			if(sale_channel == '1'){
				var obj=document.getElementById("pay_type_orderApplyForm");
				obj.selectedIndex=0;
			}
		} */
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/order/orderApply/list">销售订单列表</a></li>
		<li class="active"><a href="${ctx}/order/orderApply/applyForm?logid=${orderApply.logid}">销售订单申请</a></li>
	</ul><br/>
	<form:form id="orderApplyForm" modelAttribute="orderApply" action="${ctx}/order/orderApply/apply" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
			<sys:tableselect id="site" cssStyle="width:210px;height:23px;" name="site_id" value="${orderApply.site_id}" labelName="site_name" labelValue="${orderApply.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下单渠道:</label>
			<div class="controls">
				<form:select path="sale_channel" class="input-small required" onchange="changeSaleChannel()" id="sale_channel_orderApplyForm">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sale_channel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户名称:</label>
			<div class="controls">
				 <sys:treeselect id="user" name="login_id" value="${orderApply.login_id}" labelName="login_name" labelValue="${orderApply.login_name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付方式:</label>
			<div class="controls">
				<form:select path="pay_type" class="input-small required" id="pay_type_orderApplyForm">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付金额:</label>
			<div class="controls">
				<form:input path="sale_total" htmlEscape="false" maxlength="30" class="required" id="sale_total" readonly="true"/>（元）
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
		<input class="btn btn-primary" type="button" value="选择商品信息" onclick="showProductInfoDialog()"/>
		</div>
				 <table id="selectProductInfoListUl" class="table table-striped table-bordered table-condensed">
				    <thead>
				    <tr>
						<th>商品编码</th>
						<th>商品名称</th>
						<th>商品分类</th>
						<th>商品品牌 </th>
						<th>标准价（元） </th>
						<th>商品数量</th>
						<th>金额（元）</th>
						<th>操作</th>
					</tr>
					</thead>
				 </table>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="validCheckForm()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>