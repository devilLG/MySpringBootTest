<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点换货</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#barterOrderForm").validate({
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
		
		function addBarterOrderDetailDialog(){
		debugger;
			var site_id = $("input[name='site_id']").val();
		    if(site_id == ''){
				toastr.warning("请选择站点");
				return false;
			}
			var corp_id = $("#siteMA").val();
       		var barter_type = $("#barter_type_barterOrderForm").val();
       		if(barter_type == '1'){
			    top.$.jBox.open("iframe:${ctx}//inventory/barterOrder/barterOrderDetailProductForm?site_id="+site_id+"&corp_id="+corp_id, "添加换货详情", 950, 380, {
				buttons:{"确定":"ok","关闭":true}, submit:function(v, h, f){debugger;
					if (v=="ok"){
						 var bproduct_id = h.find("iframe")[0].contentWindow.$("#bproduct_id_addBarterOrderDetailProductForm").val();
						 if(bproduct_id == ''){
						 	toastr.warning("请选择原本商品信息");
							return false;
						 }
						 var obj = document.getElementsByName("bproduct_id");
						 for(var i=0 ; i < obj.length ; i++){
							if(obj[i].value == bproduct_id){
								toastr.warning("需换货商品信息已存在");
								return false;
							}
						 } 
						 var bproduct_name = h.find("iframe")[0].contentWindow.$("#bproduct_name_addBarterOrderDetailProductForm").val();
						 var product_id = h.find("iframe")[0].contentWindow.$("#product_id_addBarterOrderDetailProductForm").val();
						 if(product_id == ''){
						 	toastr.warning("请选择替换商品信息");
							return false;
						 }
						 if(bproduct_id == product_id){
						 	toastr.warning("替换商品信息不能与原本商品信息相同");
							return false;
						 }
						 var product_name = h.find("iframe")[0].contentWindow.$("#product_name_addBarterOrderDetailProductForm").val();
						 var invalid_date = h.find("iframe")[0].contentWindow.$("#invalid_date_addBarterOrderDetailProductForm").val();
						 /* var inventory_num = h.find("iframe")[0].contentWindow.$("#inventory_num_addBarterOrderDetailProductForm").val();
						 if((inventory_num | 0) != inventory_num || inventory_num<1 || inventory_num>999){
							toastr.warning("替换商品最大库存数只能输入1~999的整数");
							return false;
						 }
						 var warn_num = h.find("iframe")[0].contentWindow.$("#warn_num_addBarterOrderDetailProductForm").val();
						 if((warn_num | 0) != warn_num || warn_num<1 || parseInt(warn_num)>parseInt(inventory_num)){
							toastr.warning("替换商品临界值只能输入大于0小于最大库存数的整数");
							return false;
						 } */
						 var boxId = "";
						 $.ajax({
					       type:"POST",
					       url:"${ctx}/inventory/inventoryGoods/findGoodsByProduct",
					       data:{"method":"findGoodsByProduct","site_id":site_id,"product_id":bproduct_id},
					       dataType:"json",
					       global:false,
					       success : function(data) {
							if (null != data && null != data.result) {
								if(data.result == 'false'){
								 toastr.warning("商品货道信息不存在");
								 return false;
								}else{
								  for(var i=0 ; i < data.boxId.substring(1).split(";").length ; i++){
									 $("#addBarterOrderDetailListUl").append("<tr><td><input type='text' style='width:60px' readonly='readonly' name='box_id' value='"+data.boxId.substring(1).split(";")[i]
									+"'><td><input type='text' style='width:120px' readonly='readonly' name='bproduct_id' value='"+bproduct_id
						    		+"'></td><td><input type='text' onmouseover='this.title=this.value' style='width:120px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;' readonly='readonly' name='bproduct_name' value='"+bproduct_name
						    		+"'></td><td><input type='text' style='width:120px' readonly='readonly' name='product_id' value='"+product_id+"' id='product_id_"+data.boxId.substring(1).split(";")[i]
						    		+"'></td><td><input type='text' onmouseover='this.title=this.value' style='width:120px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;' readonly='readonly' name='product_name' value='"+product_name
						    		+"'></td><td><input type='text' style='width:120px' readonly='readonly' name='invalid_date' value='"+invalid_date
						    		+"'></td><td><input type='text' style='width:60px'  size='30' name='inventory_num' value='' id='inventory_num_"+data.boxId.substring(1).split(";")[i]+"' onkeyup='inventoryNumCheck(\""+data.boxId.substring(1).split(";")[i]+'\")'
						    		+"'></td><td><input type='text' style='width:60px'  size='30' name='warn_num' value='' id='warn_num_"+data.boxId.substring(1).split(";")[i]+"' onkeyup='warnNumCheck(\""+data.boxId.substring(1).split(";")[i]+'\")'
						    		+"'></td><td><a title='移除' href='javascript:void();' onclick='removeBarterOrderDetailItem(this)' id='remove_"+data.boxId.substring(1).split(";")[i]+"' class='btnDel'>删除</a></td></tr>");  
								 } 
								}
							}
						   }
						});
				   }
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
       		}else if(barter_type == '2'){
			    top.$.jBox.open("iframe:${ctx}//inventory/barterOrder/barterOrderDetailGoodsForm?site_id="+site_id+"&corp_id="+corp_id, "添加换货详情", 950, 480, {
				buttons:{"确定":"ok","关闭":true}, submit:function(v, h, f){debugger;
					if (v=="ok"){
						 var box_id = h.find("iframe")[0].contentWindow.$("#box_id_addBarterOrderDetailGoodsForm").val();
						 var obj = document.getElementsByName("box_id");
						 for(var i=0 ; i < obj.length ; i++){
							if(obj[i].value == box_id){
								toastr.warning("需换货货道信息已存在");
								return false;
							}
						 } 
						 var bproduct_id = h.find("iframe")[0].contentWindow.$("#bproduct_id_addBarterOrderDetailGoodsForm").val();
						 if(bproduct_id == ''){
						 	toastr.warning("请选择原本商品信息");
							return false;
						 }
						 var bproduct_name = h.find("iframe")[0].contentWindow.$("#bproduct_name_addBarterOrderDetailGoodsForm").val();
						 var product_id = h.find("iframe")[0].contentWindow.$("#product_id_addBarterOrderDetailGoodsForm").val();
						 if(product_id == ''){
						 	toastr.warning("请选择替换商品信息");
							return false;
						 }
						  if(bproduct_id == product_id){
						 	toastr.warning("替换商品信息不能与原本商品信息相同");
							return false;
						 }
						 var product_name = h.find("iframe")[0].contentWindow.$("#product_name_addBarterOrderDetailGoodsForm").val();
						 var invalid_date = h.find("iframe")[0].contentWindow.$("#invalid_date_addBarterOrderDetailGoodsForm").val();
						 var inventory_num = h.find("iframe")[0].contentWindow.$("#inventory_num_addBarterOrderDetailGoodsForm").val();
						 var warn_num = h.find("iframe")[0].contentWindow.$("#warn_num_addBarterOrderDetailGoodsForm").val();
						 if((inventory_num | 0) != inventory_num || inventory_num<1 || inventory_num>999){
							toastr.warning("请输入替换商品最大库存数");
							return false;
						 }
						 if((warn_num | 0) != warn_num || warn_num<1 || parseInt(warn_num)>parseInt(inventory_num)){
							toastr.warning("请输入替换商品临界值");
							return false;
						 } 
						 $("#addBarterOrderDetailListUl").append("<tr><td><input type='text' style='width:60px' readonly='readonly' name='box_id' value='"+box_id
						+"'><td><input type='text' style='width:120px' readonly='readonly' name='bproduct_id' value='"+bproduct_id
			    		+"'></td><td><input type='text' onmouseover='this.title=this.value' style='width:120px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;' readonly='readonly' name='bproduct_name' value='"+bproduct_name
			    		+"'></td><td><input type='text' style='width:120px' readonly='readonly' name='product_id' value='"+product_id+"' id='product_id_"+box_id
			    		+"'></td><td><input type='text' onmouseover='this.title=this.value' style='width:120px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;' readonly='readonly' name='product_name' value='"+product_name
			    		+"'></td><td><input type='text' style='width:120px' readonly='readonly' name='invalid_date' value='"+invalid_date
			    		+"'></td><td><input type='text' style='width:60px'  size='30' name='inventory_num' value='"+inventory_num+"' id='inventory_num_"+box_id+"' onkeyup='inventoryNumCheck(\""+box_id+'\")'
			    		+"'></td><td><input type='text' style='width:60px'  size='30' name='warn_num' value='"+warn_num+"' id='warn_num_"+box_id+"' onkeyup='warnNumCheck(\""+box_id+'\")'
			    		+"'></td><td><a title='移除' href='javascript:void();' onclick='removeBarterOrderDetailItem(this)' id='remove_"+box_id+"' class='btnDel'>删除</a></td></tr>");  
				   }
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
       		}
		}
		
		function removeBarterOrderDetailItem(item){
		    $(item).parent().parent().remove();
		}
		
		function changeBarterType(){
		    $("#addBarterOrderDetailListUl").remove();
		    $("#barterOrderDetailTable").append("<tbody id='addBarterOrderDetailListUl'></tbody>");  
		}
		
        function validCheckForm(){
        debugger;
        	var id=document.getElementById('logid_barterOrderForm').value;
       		var site_id = $("input[name='site_id']").val();
		    if(site_id == ''){
				toastr.warning("请选择站点");
				return false;
			}
       	   $.ajax({
		       type:"POST",
		       url:"${ctx}/inventory/barterOrder/checkExist",
		       data:{"method":"checkExist","id":id,"site_id":site_id},
		       dataType:"json",
		       global:false,
		       success : function(data) {
				if (null != data && null != data.result) {
					if(data.result == 'false'){
						toastr.warning("当前站点存在未换货完成的换货单，请先完成换货");
						return false;
					}else{
					    var obj = document.getElementsByName("product_id"); 
						if(obj.length <= 0){
							toastr.warning("请添加换货详情");
							return false;
						}
						var obj = document.getElementsByName("inventory_num"); 
						for(var i = 0; i < obj.length; i++)
						 { 
							if(obj[i].value==''){
								toastr.warning("请输入替换商品最大库存数");
								return false;
							}
						 }
						 var obj = document.getElementsByName("warn_num"); 
						for(var i = 0; i < obj.length; i++)
						 { 
							if(obj[i].value==''){
								toastr.warning("请输入替换商品临界值");
								return false;
							}
						 }
						var barter_id = $("input[name='barter_id']").val();
					    if(barter_id == ''){
							toastr.warning("请选择换货人");
							return false;
						}
						var barter_date = $("input[name='barter_date']").val();
					    if(barter_date == ''){
							toastr.warning("请选择换货日期");
							return false;
						}
						$("#barterOrderForm").submit();
					}
				}
	
			}
		    });
		}
		
		function showSitePersonInfo (){
			var site_id = $("input[name='site_id']").val();
		    if(site_id == ''){
				toastr.warning("请选择站点");
				return false;
			}
			top.$.jBox.open("iframe:${ctx}/person/sitePerson/showSitePersonInfo?site_id="+site_id, "选择地点人员信息", 950, 600, {
				buttons:{"确认":"ok","关闭":true}, 
				submit:function(v, h, f){
					debugger;
					if (v=="ok"){
						var parms = h.find("iframe")[0].contentWindow.$("#sitePerson_parms").val();
						if(parms){
							$("#barterOrderForm input[name='barter_id']").val(parms.split(",")[0]);
							$("#barterOrderForm input[name='barter_name']").val(parms.split(",")[1]);
						}
					}
				}, 
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function inventoryNumCheck(box_id){
		debugger;
			 var inventory_num=$("input[id='inventory_num_"+box_id+"']").attr("value");
			 var site_id = $("input[name='site_id']").val();
			 var product_id=$("input[id='product_id_"+box_id+"']").attr("value");
			  $.ajax({
			       type:"POST",
			       url:"${ctx}/inventory/inventoryGoods/checkExist",
			       data:{"method":"checkExist","site_id":site_id,"box_id":box_id,"product_id":product_id},
			       dataType:"json",
			       global:false,
			       success : function(data) {
					if (null != data && null != data.result) {
						if(data.result == 'false'){
						 toastr.warning("替换商品大小超出货道规格，已删除");
						 $("a[id='remove_"+box_id+"']").click();
						 return false;
						}else{
						 if((inventory_num | 0) != inventory_num || parseInt(inventory_num)<1 || parseInt(inventory_num)>data.inventory_max){
							toastr.warning("替换商品最大库存数只能输入1~"+data.inventory_max+"的整数");
							$("input[id='inventory_num_"+box_id+"']").attr("value","");
						 }
						 }
					}
				   }
				});
        }
        
        function warnNumCheck(box_id){
		debugger;
			 var inventory_num=$("input[id='inventory_num_"+box_id+"']").attr("value");
			 if(inventory_num == ''){
			 	toastr.warning("请先输入替换商品最大库存数");
				$("input[id='warn_num_"+box_id+"']").attr("value","");
				return false;
			 }
			 var warn_num=$("input[id='warn_num_"+box_id+"']").attr("value");
			 if((warn_num | 0) != warn_num || parseInt(warn_num)<1 || parseInt(warn_num)>parseInt(inventory_num)){
				toastr.warning("替换商品临界值只能输入大于0小于等于最大库存数的整数");
				$("input[id='warn_num_"+box_id+"']").attr("value","");
				return false;
			 }
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/barterOrder/list">站点换货列表</a></li>
		<li class="active"><a href="${ctx}/inventory/barterOrder/form?logid=${barterOrder.logid}">站点换货${not empty barterOrder.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#main" data-toggle="tab">主单信息</a></li>
		<li><a href="#sub" data-toggle="tab"><span>换货详情</span></a></li>
	</ul><br/>
	<form:form id="barterOrderForm" modelAttribute="barterOrder" action="${ctx}/inventory/barterOrder/save" method="post" class="form-horizontal">
		<form:hidden path="logid" id="logid_barterOrderForm"/>
		<form:hidden path="order_id"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
		<div class="tab-pane fade in active" id="main">
		<c:if test="${empty barterOrder.logid}">
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
			<sys:tableselect id="site" cssStyle="width:210px;height:23px;" name="site_id" value="${barterOrder.site_id}" labelName="site_name" labelValue="${barterOrder.site_name}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty barterOrder.logid}">
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
			<form:hidden path="corp_id" id="siteMA"/>
			<form:hidden path="site_id" id="site_id"/>
			<form:input path="site_name" htmlEscape="false" maxlength="50" class="required" readonly="true" />
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">换货人名称:</label>
			<div class="controls">
				<form:hidden path="barter_id"/>
				<form:input path="barter_name" htmlEscape="false" maxlength="50" class="required" readonly="true" />
				<i class="icon-search magnifying" onclick="showSitePersonInfo()"></i>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">换货日期:</label>
			<div class="controls">
				 <input name="barter_date" type="text" readonly="readonly" maxlength="20" class="required input-medium Wdate"
					value="${barterOrder.barter_date}" onclick="WdatePicker({minDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${empty barterOrder.logid}">
		<div class="control-group">
			<label class="control-label">换货类型:</label>
			<div class="controls">
				<form:select path="barter_type" class="input-small required" id="barter_type_barterOrderForm" onchange="changeBarterType()">
					<form:options items="${fns:getDictList('barter_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty barterOrder.logid}">
		<div class="control-group">
			<label class="control-label">换货类型:</label>
			<div class="controls">
				<form:select path="barter_type" class="input-small required" id="barter_type_barterOrderForm" disabled="true">
					<form:options items="${fns:getDictList('barter_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		</div>
		<div class="tab-pane fade" id="sub">
		<div class="control-group">
		<input class="btn btn-primary" type="button" value="添加换货详情" onclick="addBarterOrderDetailDialog()"/>
		</div>
				 <table id="barterOrderDetailTable" class="table table-striped table-bordered table-condensed">
				    <thead>
				    <tr><th>货道号</th><th>原本商品编码</th><th>原本商品名称</th><th>替换商品编码</th><th>替换商品名称 </th><th>替换商品过期时间(H)</th><th>替换商品最大库存数</th><th>替换商品临界值</th><th>操作</th></tr>
					</thead>
					<tbody id="addBarterOrderDetailListUl">
					<c:forEach items="${barterOrderDetailList}" var="barterOrderDetail">
						<tr>
						<td><input type='text' readonly='readonly' style='width:60px' name='box_id' value='${barterOrderDetail.box_id}'></td>
						<td><input type='text' readonly='readonly' style='width:120px' name='bproduct_id' value='${barterOrderDetail.bproduct_id}'></td>
						<td><input type='text' onmouseover='this.title=this.value' readonly='readonly' style='width:120px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;' name='bproduct_name' value='${barterOrderDetail.bproduct_name}'></td>
						<td><input type='text' readonly='readonly' style='width:120px' name='product_id' value='${barterOrderDetail.product_id}' id="product_id_${barterOrderDetail.box_id}"></td>
						<td><input type='text' onmouseover='this.title=this.value' readonly='readonly' style='width:120px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;' name='product_name' value='${barterOrderDetail.product_name}'></td>
						<td><input type='text' readonly='readonly' style='width:120px' name='invalid_date' value='${barterOrderDetail.invalid_date}'></td>
						<td><input type='text'  style='width:60px' name='inventory_num' value='${barterOrderDetail.inventory_num}'  id="inventory_num_${barterOrderDetail.box_id}" onkeyup="inventoryNumCheck('${barterOrderDetail.box_id}')"></td>
						<td><input type='text'  style='width:60px' name='warn_num' value='${barterOrderDetail.warn_num}'  id="warn_num_${barterOrderDetail.box_id}" onkeyup="warnNumCheck('${barterOrderDetail.box_id}')"></td>
						<td>
						<a title='移除' href='javascript:void();' onclick='removeBarterOrderDetailItem(this)' id="remove_${barterOrderDetail.box_id}" class='btnDel'>删除</a>
						</td>
						</tr>
					</c:forEach>
					</tbody>
				 </table>
		</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="validCheckForm()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>