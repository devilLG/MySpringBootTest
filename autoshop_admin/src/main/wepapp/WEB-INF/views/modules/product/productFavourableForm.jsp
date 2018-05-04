<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品优惠</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#productFavourableForm").validate({
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
			top.$.jBox.open("iframe:${ctx}/product/productInfo/showProductInfo", "选择商品信息", 950, 600, {
				buttons:{"确认":"ok","关闭":true}, 
				submit:function(v, h, f){
					debugger;
					if (v=="ok"){
						var parms = h.find("iframe")[0].contentWindow.$("#productInfo_parms").val();
						if(parms){
							$("#productFavourableForm input[name='product_id']").val(parms.split(",")[0]);
							$("#productFavourableForm input[name='product_name']").val(parms.split(",")[1]);
							$("#productFavourableForm input[name='bar_code']").val(parms.split(",")[2]);
							$("#productFavourableForm input[name='nomarl_title']").val(parms.split(",")[3]);
							$("#productFavourableForm input[name='nomarl_price']").val(parms.split(",")[4]);
							$("#productFavourableForm textarea[name='nomarl_url']").val(parms.split(",")[5]);
							$("#productFavourableForm input[name='corp_id']").val(parms.split(",")[6]);
							$("#productFavourableForm input[name='corp_name']").val(parms.split(",")[7]);
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
			top.$.jBox.open("iframe:${ctx}/inventory/inventoryProduct/showInventoryProductInfo?site_id="+site_id, "选择商品信息", 950, 600, {
				buttons:{"确认":"ok","关闭":true}, 
				submit:function(v, h, f){
					debugger;
					if (v=="ok"){
						var parms = h.find("iframe")[0].contentWindow.$("#inventoryProduct_parms").val();
						if(parms){
							$("#productFavourableForm input[name='product_id']").val(parms.split(",")[0]);
							$("#productFavourableForm input[name='product_name']").val(parms.split(",")[1]);
							$("#productFavourableForm input[name='bar_code']").val(parms.split(",")[2]);
							$("#productFavourableForm input[name='nomarl_title']").val(parms.split(",")[3]);
							$("#productFavourableForm input[name='nomarl_price']").val(parms.split(",")[4]);
							$("#productFavourableForm textarea[name='nomarl_url']").val(parms.split(",")[5]);
							$("#productFavourableForm input[name='corp_id']").val(parms.split(",")[6]);
							$("#productFavourableForm input[name='corp_name']").val(parms.split(",")[7]);
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
			var id=document.getElementById('logid_productFavourableForm').value;
			var corp_id=document.getElementById('corp_id_productFavourableForm').value;
		    var favourable_type = $("#favourable_type_productFavourableForm").val();
		    if(favourable_type == '01'){
		     	var site_id = $("#site_id").val();
			    if(site_id == ''){
					toastr.warning("请选择站点");
					return false;
				}
		    }
		    var product_id = $("#product_id_productFavourableForm").val();
		    if(product_id == ''){
				toastr.warning("请选择商品信息");
				return false;
			}
			var favourable_stime = $("#favourable_stime_productFavourableForm").val();
		    if(favourable_stime == ''){
				toastr.warning("请选择优惠开始日期");
				return false;
			}
			var favourable_etime = $("#favourable_etime_productFavourableForm").val();
			if(favourable_etime == ''){
				toastr.warning("请选择优惠结束日期");
				return false;
			}
		     $.ajax({
			       type:"POST",
			       url:"${ctx}/product/productFavourable/checkExist",
			       data:{"method":"checkExist","id":id,"favourable_type":favourable_type,"site_id":site_id,"product_id":product_id,"favourable_stime":favourable_stime,"favourable_etime":favourable_etime,"corp_id":corp_id},
			       dataType:"json",
			       global:false,
			       success : function(data) {
					if (null != data && null != data.result) {
						if(data.result == 'false'){
						 toastr.warning("同一对象同一商品相同时间段不能存在多个优惠");
						 return false;
						}else{
						 $("#productFavourableForm").submit();
						}
					}
		
				}
			    });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productFavourable/list">商品优惠列表</a></li>
		<c:if test="${empty productFavourable.logid}">
		<c:if test="${productFavourable.favourable_type==01}">
		<li><a href="${ctx}/product/productFavourable/form?favourable_type=02">按商品优惠</a></li>
		<li class="active"><a href="${ctx}/product/productFavourable/form?favourable_type=01">按网点优惠</a></li>
		</c:if>
		<c:if test="${productFavourable.favourable_type==02}">
		<li class="active"><a href="${ctx}/product/productFavourable/form?favourable_type=02">按商品优惠</a></li>
		<li><a href="${ctx}/product/productFavourable/form?favourable_type=01">按网点优惠</a></li>
		</c:if>
		</c:if>
		<c:if test="${not empty productFavourable.logid}">
		<li class="active"><a href="${ctx}/product/productFavourable/form?logid=${productFavourable.logid}">商品优惠修改</a></li>
		</c:if>
	</ul><br/>
	<form:form id="productFavourableForm" modelAttribute="productFavourable" action="${ctx}/product/productFavourable/save" method="post" class="form-horizontal">
		<form:hidden path="logid" id="logid_productFavourableForm"/>
		<form:hidden path="favourable_type" id="favourable_type_productFavourableForm"/>
		<form:hidden path="corp_id"  id="corp_id_productFavourableForm"/>
		<form:hidden path="corp_name"/>
		<form:hidden path="site_id" id="site_id"/>
		<sys:message content="${message}"/>
		<c:if test="${empty productFavourable.logid}">
		<c:if test="${productFavourable.favourable_type==01}">
		<div class="control-group">
			<label class="control-label">站点编号:</label>
			<div class="controls">
			<sys:tableselect id="site" cssStyle="width:210px;height:23px;" name="site_id" value="${productFavourable.site_id}" labelName="site_id" labelValue="${productFavourable.site_id}"
			 title="站点" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		</c:if>
		<div class="control-group">
			<label class="control-label">商品编码:</label>
			<div class="controls">
				<form:input path="product_id" htmlEscape="false" maxlength="30" class="required" readonly="true" id="product_id_productFavourableForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<c:if test="${empty productFavourable.logid}">
				<c:if test="${productFavourable.favourable_type==01}">
				<i class="icon-search magnifying" onclick="showInventoryProductInfo()"></i>
				</c:if>
				<c:if test="${productFavourable.favourable_type==02}">
				<i class="icon-search magnifying" onclick="showProductInfo()"></i>
				</c:if>
				</c:if>
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
			<label class="control-label">商品条码:</label>
			<div class="controls">
				<form:input path="bar_code" htmlEscape="false" maxlength="13" class="required digits" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">恢复商品标题:</label>
			<div class="controls">
				<form:input path="nomarl_title" htmlEscape="false" maxlength="50" class="required" style="width:700px"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</div><div class="control-group">
			<label class="control-label">恢复商品标价:</label>
			<div class="controls">
				<form:input path="nomarl_price" htmlEscape="false" maxlength="11" class="required number"/>（元）
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">恢复商品介绍地址:</label>
			<div class="controls">
				<form:textarea path="nomarl_url" htmlEscape="false" rows="3" maxlength="150" class="required input-xlarge url"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠商品标题:</label>
			<div class="controls">
				<form:input path="favourable_title" htmlEscape="false" maxlength="50" class="required" style="width:700px"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</div><div class="control-group">
			<label class="control-label">优惠商品标价:</label>
			<div class="controls">
				<form:input path="favourable_price" htmlEscape="false" maxlength="11" class="required number"/>（元）
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠商品介绍地址:</label>
			<div class="controls">
				<form:textarea path="favourable_url" htmlEscape="false" rows="3" maxlength="150" class="required input-xlarge url"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠描述:</label>
			<div class="controls">
				<form:textarea path="favourable_desc" htmlEscape="false" rows="3" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠开始日期:</label>
			<div class="controls">
				<input id="favourable_stime_productFavourableForm" name="favourable_stime" value="${productFavourable.favourable_stime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					onclick="WdatePicker({minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'favourable_etime_productFavourableForm\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<br/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠结束日期:</label>
			<div class="controls">
				<input id="favourable_etime_productFavourableForm" name="favourable_etime" value="${productFavourable.favourable_etime}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'favourable_stime_productFavourableForm\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
				<span class="help-inline"><font color="red">*</font> </span>
				<br/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="validCheckForm()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>