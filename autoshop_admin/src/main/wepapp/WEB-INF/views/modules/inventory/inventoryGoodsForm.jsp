<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>货道库存</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#inventoryGoodsForm").validate({
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
		
		function showSiteGoodsInfo (){
		    var site_id = $("#site_id").val();
		    if(site_id == ''){
				toastr.warning("请选择站点");
				return false;
			}
			top.$.jBox.open("iframe:${ctx}/site/siteGoods/selectSiteGoods?site_id="+site_id, "选择货道信息", 950, 600, {
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
	 				 var cabinet_ids =  "";
	 				 var cloumn_ids =  "";
	 				 var layer_nums =  "";
					 var box_ids =  "";
					 var temper_types =  "";
					 var cur_states =  "";
					 if(ckList != undefined && ckList != "" && ckList.length>0){
						 for(var i=0;i<ckList.length;i++){
							 logids=ckList[i];
							 var boxInfo = logids.split(';');
							 var cabinet_id=boxInfo[0];
							 var cloumn_id=boxInfo[1];
							 var layer_num=boxInfo[2];
							 var box_id=boxInfo[3];
							 var temper_type=boxInfo[4];
							 var cur_state=boxInfo[5];
							 cabinet_ids=cabinet_ids+'|'+cabinet_id;
							 cloumn_ids=cloumn_ids+':'+cloumn_id;
							 layer_nums=layer_nums+'|'+layer_num;
							 box_ids=box_ids+':'+box_id;
							 temper_types=temper_types+'|'+temper_type;
							 cur_states=cur_states+'|'+cur_state;
						 }
						 var cabinetIds = cabinet_ids.substring(1).split('|');
						 var cabinetId = cabinetIds[0];
						 for(var i=0;i<cabinetIds.length;i++){
						 	if(cabinetIds[i] != cabinetId){
							 	toastr.warning("请选择在同一柜号同一层温度环境一样且相邻的货道");
								return false;
						 	}
						 }
						 var temperTypes = temper_types.substring(1).split('|');
						 var temperType = temperTypes[0];
						 for(var i=0;i<temperTypes.length;i++){
						 	if(temperTypes[i] != temperType){
							 	toastr.warning("请选择在同一柜号同一层温度环境一样且相邻的货道");
								return false;
						 	}
						 }
						 var layerNums = layer_nums.substring(1).split('|');
						 var layerNum = layerNums[0];
						 for(var i=0;i<layerNums.length;i++){
						 	if(layerNums[i] != layerNum){
							 	toastr.warning("请选择在同一柜号同一层温度环境一样且相邻的货道");
								return false;
						 	}
						 }
						 var curStates = cur_states.substring(1).split('|');
						 var curState = curStates[0];
						 for(var i=0;i<curStates.length;i++){
						 	if(curStates[i] != '1'){
							 	toastr.warning("请选择正常状态的货道");
								return false;
						 	}
						 }
						 var boxIds = box_ids.substring(1).split(':');
						 var min = boxIds[0];
						 var max = boxIds[0];
						 for(var i=0;i<boxIds.length;i++){
						 	if(min > boxIds[i] && min.length >= boxIds[i].length){
						 	 min = boxIds[i];
						 	}
						 	if(max < boxIds[i] || max.length <= boxIds[i].length){
						 	 max = boxIds[i];
						 	}
						 }
						 var cha = max-min;
						 if(max-min > boxIds.length-1){
						 	toastr.warning("请选择在同一柜号同一层温度环境一样且相邻的货道");
							return false;
						 }
						 $("#inventoryGoodsForm input[name='cabinet_id']").val(cabinetId); 
						 $("#inventoryGoodsForm input[name='cloumn_id']").val(cloumn_ids.substring(1)); 
						 $("#inventoryGoodsForm input[name='layer_num']").val(layerNum); 
						 $("#inventoryGoodsForm input[name='box_id']").val(box_ids.substring(1));
						 $("#inventoryGoodsForm input[name='temper_type']").val(temperType);
						 var temperType_name = '';
						 if(temperType == '1'){
						    temperType_name='常温';
						 }else if(temperType == '2'){
						    temperType_name='冷藏';
						 }else if(temperType == '3'){
						    temperType_name='保鲜';
						 }
						 $("#inventoryGoodsForm input[name='detail_url']").val(temperType_name);
						 $("#inventoryGoodsForm input[name='cur_state']").val(curState);     
					 }
					}
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
		}
		
		function showProductInfo (){
		 var logid = $("#logid_inventoryGoodsForm").val();
		 if(logid == ''){
			var corp_id = $("#siteMA").val();
		    if(corp_id == ''){
				toastr.warning("请选择站点");
				return false;
			}
		 }else{
		    var corp_id = $("#corp_id_inventoryGoodsForm").val();
		 }
		 var temper_type = $("#temper_type_inventoryGoodsForm").val();
		 if(temper_type == ''){
			toastr.warning("请选择货道信息");
			return false;
	     }
			top.$.jBox.open("iframe:${ctx}/product/productInfo/showProductInfo?corp_id="+corp_id+"&temper_type="+temper_type, "选择商品信息", 950, 600, {
				buttons:{"确认":"ok","关闭":true}, 
				submit:function(v, h, f){
					debugger;
					if (v=="ok"){
						var parms = h.find("iframe")[0].contentWindow.$("#productInfo_parms").val();
						if(parms){
							$("#inventoryGoodsForm input[name='product_id']").val(parms.split(",")[0]);
							$("#inventoryGoodsForm input[name='product_name']").val(parms.split(",")[1]);
							$("#inventoryGoodsForm input[name='bar_code']").val(parms.split(",")[2]);
							$("#inventoryGoodsForm input[name='corp_id']").val(parms.split(",")[6]);
							$("#inventoryGoodsForm input[name='corp_name']").val(parms.split(",")[7]);
							/* $("#inventoryGoodsForm input[name='overdue_time']").val(parms.split(",")[8]); */
							$("#inventoryGoodsForm input[name='productType_id']").val(parms.split(",")[9]);
							$("#inventoryGoodsForm input[name='productType_name']").val(parms.split(",")[10]);
						}
					}
				}, 
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function validCheckForm(){
		debugger;
	     	var site_id = $("#site_id").val();
		    if(site_id == ''){
				toastr.warning("请选择站点");
				return false;
			}
		    var box_id = $("#box_id_inventoryGoodsForm").val();
		    if(box_id == ''){
				toastr.warning("请选择货道信息");
				return false;
			}
			var product_id = $("#product_id_inventoryGoodsForm").val();
		    if(product_id == ''){
				toastr.warning("请选择商品信息");
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
						 var inventory_max = $("#inventory_max_inventoryGoodsForm").val();
						 var warn_num = $("#warn_num_inventoryGoodsForm").val(); 
						 if(inventory_max != '' && parseInt(inventory_max) > data.inventory_max){
							 toastr.warning("最大库存数不能超过"+data.inventory_max);
							 return false;
						 }else if(inventory_max != '' && warn_num != ''){
						  	 if((warn_num | 0) != warn_num || warn_num<1 || parseInt(warn_num)>parseInt(inventory_max)){
								toastr.warning("缺货临界值只能输入大于0小于等于最大库存数的整数");
								return false;
							 }else{
							  $("#inventoryGoodsForm").submit();
							 }
						   }else{
							  $("#inventoryGoodsForm").submit();
						   }
						}
					}
				   }
				});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/inventoryGoods/list">货道库存列表</a></li>
		<li class="active"><a href="${ctx}/inventory/inventoryGoods/form?logid=${inventoryGoods.logid}">货道库存${not empty inventoryGoods.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inventoryGoodsForm" modelAttribute="inventoryGoods" action="${ctx}/inventory/inventoryGoods/save" method="post" class="form-horizontal">
		<form:hidden path="logid" id="logid_inventoryGoodsForm"/>
		<form:hidden path="corp_id" id="corp_id_inventoryGoodsForm"/>
		<form:hidden path="corp_name"/>
		<form:hidden path="cur_state"/>
		<form:hidden path="productType_id"/>
		<form:hidden path="productType_name"/>
		<sys:message content="${message}"/>
		<c:if test="${empty inventoryGoods.logid}">
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
			<sys:tableselect id="site" cssStyle="width:210px;height:23px;" name="site_id" value="${inventoryGoods.site_id}" labelName="site_name" labelValue="${inventoryGoods.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty inventoryGoods.logid}">
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
			<form:hidden path="site_id"/>
			<form:input path="site_name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">柜号:</label>
			<div class="controls">
				<form:input path="cabinet_id" htmlEscape="false" maxlength="11" class="required digits" readonly="true"/>
				<c:if test="${empty inventoryGoods.logid}">
				<i class="icon-search magnifying" onclick="showSiteGoodsInfo()"></i>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">列数:</label>
			<div class="controls">
				<form:input path="cloumn_id" htmlEscape="false" maxlength="30" class="required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">层数:</label>
			<div class="controls">
				<form:input path="layer_num" htmlEscape="false" maxlength="11" class="required digits" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货道号:</label>
			<div class="controls">
				<form:input path="box_id" htmlEscape="false" maxlength="30" class="required" readonly="true" id="box_id_inventoryGoodsForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">温度环境:</label>
			<div class="controls">
			    <form:hidden path="temper_type" id="temper_type_inventoryGoodsForm"/>
				<form:input path="detail_url" htmlEscape="false" value="${fns:getDictLabel(inventoryGoods.temper_type, 'temper_type', '')}" maxlength="30" class="required" readonly="true" id="temper_type_inventoryGoodsForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品编码:</label>
			<div class="controls">
				<form:input path="product_id" htmlEscape="false" maxlength="30" class="required" readonly="true" id="product_id_inventoryGoodsForm"/>
				<i class="icon-search magnifying" onclick="showProductInfo()"></i>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品名称:</label>
			<div class="controls">
				<form:input path="product_name" htmlEscape="false" maxlength="50" class="required" style="width:700px" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">条码:</label>
			<div class="controls">
				<form:input path="bar_code" htmlEscape="false" maxlength="30" class="required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">过期时间:</label>
			<div class="controls">
				<form:input path="overdue_time" htmlEscape="false" maxlength="30" class="required" readonly="true"/> (H)
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">最大库存数:</label>
			<div class="controls">
				<form:input path="inventory_max" htmlEscape="false" maxlength="8" class="required digits" id="inventory_max_inventoryGoodsForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺货临界值:</label>
			<div class="controls">
				<form:input path="warn_num" htmlEscape="false" maxlength="8" class="required digits" id="warn_num_inventoryGoodsForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="validCheckForm()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>