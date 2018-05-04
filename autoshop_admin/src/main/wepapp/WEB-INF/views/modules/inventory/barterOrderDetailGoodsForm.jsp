<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>添加换货详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#addBarterOrderDetailGoodsForm").validate({
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
	    
	    function showProductInfo (){
	 	    var site_id = $("#site_id").val();
	 	    var box_id = $("#box_id_addBarterOrderDetailGoodsForm").val();
	 	    var corp_id = $("#corp_id").val();
	 	    var temper_type = $("#temper_type").val();
		    if(temper_type == ''){
				toastr.warning("请选择原本商品信息");
				return false;
			}
			top.$.jBox.open("iframe:${ctx}/product/productInfo/showProductInfo?corp_id="+corp_id+"&temper_type="+temper_type, "选择商品信息", 950, 600, {
				buttons:{"确认":"ok","关闭":true}, 
				submit:function(v, h, f){
					debugger;
					if (v=="ok"){
						var parms = h.find("iframe")[0].contentWindow.$("#productInfo_parms").val();
						if(parms){
						     $.ajax({
						       type:"POST",
						       url:"${ctx}/inventory/inventoryGoods/checkExist",
						       data:{"method":"checkExist","site_id":site_id,"box_id":box_id,"product_id":parms.split(",")[0]},
						       dataType:"json",
						       global:false,
						       success : function(data) {
								if (null != data && null != data.result) {
									if(data.result == 'false'){
									 toastr.warning("商品大小超出货道规格，请重新选择");
									 return false;
									}else{
									$("#addBarterOrderDetailGoodsForm input[name='product_id']").val(parms.split(",")[0]);
									$("#addBarterOrderDetailGoodsForm input[name='product_name']").val(parms.split(",")[1]);
									$("#addBarterOrderDetailGoodsForm input[name='invalid_date']").val(parms.split(",")[8]);
									 }
								}
							   }
							});
						}
					}
				}, 
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function showInventoryGoodsInfo (){
		    var site_id = $("#site_id").val();
		    if(site_id == ''){
				toastr.warning("请选择站点");
				return false;
			}
			top.$.jBox.open("iframe:${ctx}/inventory/inventoryGoods/showInventoryGoodsInfo?site_id="+site_id+"&type=1", "选择商品信息", 950, 600, {
				buttons:{"确认":"ok","关闭":true}, 
				submit:function(v, h, f){
					debugger;
					if (v=="ok"){
						var parms = h.find("iframe")[0].contentWindow.$("#inventoryGoods_parms").val();
						if(parms){
							$("#addBarterOrderDetailGoodsForm input[name='box_id']").val(parms.split(",")[0]);
							$("#addBarterOrderDetailGoodsForm input[name='bproduct_id']").val(parms.split(",")[1]);
							$("#addBarterOrderDetailGoodsForm input[name='bproduct_name']").val(parms.split(",")[2]);
							/* $("#addBarterOrderDetailGoodsForm input[name='corp_id']").val(parms.split(",")[4]);
							$("#addBarterOrderDetailGoodsForm input[name='corp_name']").val(parms.split(",")[5]); */
							$("#addBarterOrderDetailGoodsForm input[name='temper_type']").val(parms.split(",")[6]);
						}
					}
				}, 
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function inventoryNumCheck(){
		debugger;
			 var inventory_num = $("#inventory_num_addBarterOrderDetailGoodsForm").val();
			 var site_id = $("#site_id").val();
			 var box_id = $("#box_id_addBarterOrderDetailGoodsForm").val();
			  if(box_id == ''){
				toastr.warning("请选择原本商品信息");
				$("#inventory_num_addBarterOrderDetailGoodsForm").val("");
				return false;
			  }
			 var product_id = $("#product_id_addBarterOrderDetailGoodsForm").val();
			  if(product_id == ''){
				toastr.warning("请选择替换商品信息");
				$("#inventory_num_addBarterOrderDetailGoodsForm").val("");
				return false;
			}
			  $.ajax({
			       type:"POST",
			       url:"${ctx}/inventory/inventoryGoods/checkExist",
			       data:{"method":"checkExist","site_id":site_id,"box_id":box_id,"product_id":product_id},
			       dataType:"json",
			       global:false,
			       success : function(data) {
					if (null != data && null != data.result) {
						if(data.result == 'false'){
						 toastr.warning("商品大小超出货道规格");
						 return false;
						}else{
						 if((inventory_num | 0) != inventory_num || parseInt(inventory_num)<1 || parseInt(inventory_num)>data.inventory_max){
							toastr.warning("替换商品最大库存数只能输入1~"+data.inventory_max+"的整数");
							$("#inventory_num_addBarterOrderDetailGoodsForm").val("");
						 }
						 }
					}
				   }
				});
        }
        
        function warnNumCheck(){
		debugger;
			var inventory_num = $("#inventory_num_addBarterOrderDetailGoodsForm").val();
			 if(inventory_num == ''){
			 	toastr.warning("请先输入替换商品最大库存数");
				$("#warn_num_addBarterOrderDetailGoodsForm").val("");
				return false;
			 }
			 var warn_num=$("#warn_num_addBarterOrderDetailGoodsForm").val();
			 if((warn_num | 0) != warn_num || parseInt(warn_num)<1 || parseInt(warn_num)>parseInt(inventory_num)){
				toastr.warning("替换商品临界值只能输入大于0小于等于最大库存数的整数");
				$("#warn_num_addBarterOrderDetailGoodsForm").val("");
				return false;
			 }
        }
	</script>
</head>
<body>
	<form:form id="addBarterOrderDetailGoodsForm" modelAttribute="barterOrderDetail" action="" method="post" class="form-horizontal">
	<sys:message content="${message}"/>
		<div class="control-group">
		<form:hidden path="site_id" id="site_id"/>
		<form:hidden path="corp_id" id="corp_id"/>
		<form:hidden path="temper_type" id="temper_type"/>
		</div>
		<div class="control-group">
			<label class="control-label">货道号:</label>
			<div class="controls">
				<form:input path="box_id" htmlEscape="false" maxlength="30" class="required" readonly="true" id="box_id_addBarterOrderDetailGoodsForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<c:if test="${empty barterOrderDetail.bproduct_id}">
				<i class="icon-search magnifying" onclick="showInventoryGoodsInfo()"></i>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原本商品编码:</label>
			<div class="controls">
				<form:input path="bproduct_id" htmlEscape="false" maxlength="30" class="required" readonly="true" id="bproduct_id_addBarterOrderDetailGoodsForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原本商品名称:</label>
			<div class="controls">
				<form:input path="bproduct_name" htmlEscape="false" maxlength="50" class="required" style="width:700px" readonly="true" id="bproduct_name_addBarterOrderDetailGoodsForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">替换商品编码:</label>
			<div class="controls">
				<form:input path="product_id" htmlEscape="false" maxlength="30" class="required" readonly="true" id="product_id_addBarterOrderDetailGoodsForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<c:if test="${empty barterOrderDetail.bproduct_id}">
				<i class="icon-search magnifying" onclick="showProductInfo()"></i>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">替换商品名称:</label>
			<div class="controls">
				<form:input path="product_name" htmlEscape="false" maxlength="50" class="required" style="width:700px" readonly="true" id="product_name_addBarterOrderDetailGoodsForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">替换商品过期时间:</label>
			<div class="controls">
				<form:input path="invalid_date" htmlEscape="false" maxlength="30" class="required" readonly="true" id="invalid_date_addBarterOrderDetailGoodsForm"/> (H)
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">替换商品最大库存数:</label>
			<div class="controls">
				<form:input path="inventory_num" htmlEscape="false" maxlength="11" class="required digits" id="inventory_num_addBarterOrderDetailGoodsForm" onkeyup="inventoryNumCheck()"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">替换商品临界值:</label>
			<div class="controls">
				<form:input path="warn_num" htmlEscape="false" maxlength="11" class="required digits" id="warn_num_addBarterOrderDetailGoodsForm" onkeyup="warnNumCheck()"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	</form:form>
</body>
</html>