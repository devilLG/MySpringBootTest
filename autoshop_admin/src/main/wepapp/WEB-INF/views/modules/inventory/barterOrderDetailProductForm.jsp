<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>添加换货详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#addBarterOrderDetailProductForm").submit();
	    	return false;
	    }
	    
	    function showProductInfo (){
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
							$("#addBarterOrderDetailProductForm input[name='product_id']").val(parms.split(",")[0]);
							$("#addBarterOrderDetailProductForm input[name='product_name']").val(parms.split(",")[1]);
							$("#addBarterOrderDetailProductForm input[name='invalid_date']").val(parms.split(",")[8]);
						}
					}
				}, 
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function showInventoryProductInfo (){
		    var site_id = $("#site_id").val();
		    if(site_id == ''){
				toastr.warning("请选择站点");
				return false;
			}
			top.$.jBox.open("iframe:${ctx}/inventory/inventoryProduct/showInventoryProductInfo?site_id="+site_id+"&type=1", "选择商品信息", 950, 600, {
				buttons:{"确认":"ok","关闭":true}, 
				submit:function(v, h, f){
					debugger;
					if (v=="ok"){
						var parms = h.find("iframe")[0].contentWindow.$("#inventoryProduct_parms").val();
						if(parms){
							$("#addBarterOrderDetailProductForm input[name='bproduct_id']").val(parms.split(",")[0]);
							$("#addBarterOrderDetailProductForm input[name='bproduct_name']").val(parms.split(",")[1]);
							/* $("#addBarterOrderDetailProductForm input[name='corp_id']").val(parms.split(",")[6]);
							$("#addBarterOrderDetailProductForm input[name='corp_name']").val(parms.split(",")[7]); */
							$("#addBarterOrderDetailProductForm input[name='temper_type']").val(parms.split(",")[8]);
						}
					}
				}, 
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
	</script>
</head>
<body>
	<form:form id="addBarterOrderDetailProductForm" modelAttribute="barterOrderDetail" action="" method="post" class="form-horizontal">
	<sys:message content="${message}"/>
		<div class="control-group">
		<form:hidden path="site_id" id="site_id"/>
		<form:hidden path="corp_id" id="corp_id"/>
		<form:hidden path="temper_type" id="temper_type"/>
		</div>
		<div class="control-group">
			<label class="control-label">原本商品编码:</label>
			<div class="controls">
				<form:input path="bproduct_id" htmlEscape="false" maxlength="30" class="required" readonly="true" id="bproduct_id_addBarterOrderDetailProductForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<c:if test="${empty barterOrderDetail.bproduct_id}">
				<i class="icon-search magnifying" onclick="showInventoryProductInfo()"></i>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原本商品名称:</label>
			<div class="controls">
				<form:input path="bproduct_name" htmlEscape="false" maxlength="50" class="required" style="width:700px" readonly="true" id="bproduct_name_addBarterOrderDetailProductForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">替换商品编码:</label>
			<div class="controls">
				<form:input path="product_id" htmlEscape="false" maxlength="30" class="required" readonly="true" id="product_id_addBarterOrderDetailProductForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<c:if test="${empty barterOrderDetail.bproduct_id}">
				<i class="icon-search magnifying" onclick="showProductInfo()"></i>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">替换商品名称:</label>
			<div class="controls">
				<form:input path="product_name" htmlEscape="false" maxlength="50" class="required" style="width:700px" readonly="true" id="product_name_addBarterOrderDetailProductForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">替换商品过期时间:</label>
			<div class="controls">
				<form:input path="invalid_date" htmlEscape="false" maxlength="30" class="required" readonly="true" id="invalid_date_addBarterOrderDetailProductForm"/> (H)
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">替换商品最大库存数:</label>
			<div class="controls">
				<form:input path="inventory_num" htmlEscape="false" maxlength="11" class="required digits" id="inventory_num_addBarterOrderDetailProductForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">替换商品临界值:</label>
			<div class="controls">
				<form:input path="warn_num" htmlEscape="false" maxlength="11" class="required digits" id="warn_num_addBarterOrderDetailProductForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
	</form:form>
</body>
</html>