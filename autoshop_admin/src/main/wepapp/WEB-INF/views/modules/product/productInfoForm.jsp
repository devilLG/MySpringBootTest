<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#productInfoForm").validate({
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
			var normal_price = $("#normal_price_productInfoForm").val();
			if(normal_price !='' && normal_price.indexOf(".") > -1){
			 if(normal_price.substring(normal_price.indexOf(".")+1).length > 2){
                    toastr.warning("标准价只能精确到小数点后2位数！");
                    return false;
                }
			}
			var pro_length = $("#pro_length_productInfoForm").val();
			if(pro_length !='' && pro_length.indexOf(".") > -1){
			 if(pro_length.substring(pro_length.indexOf(".")+1).length > 2){
                    toastr.warning("商品长度只能精确到小数点后2位数！");
                    return false;
                }
			}
			var pro_width = $("#pro_width_productInfoForm").val();
			if(pro_width !='' && pro_width.indexOf(".") > -1){
			 if(pro_width.substring(pro_width.indexOf(".")+1).length > 2){
                    toastr.warning("商品宽度只能精确到小数点后2位数！");
                    return false;
                }
			}
			var pro_height = $("#pro_height_productInfoForm").val();
			if(pro_height !='' && pro_height.indexOf(".") > -1){
			 if(pro_height.substring(pro_height.indexOf(".")+1).length > 2){
                    toastr.warning("商品高度只能精确到小数点后2位数！");
                    return false;
                }
			}
			var id=document.getElementById('logid_productInfoForm').value;
		    var bar_code = $("#bar_code_productInfoForm").val();
		    if(bar_code == ''){
				toastr.warning("请填写商品条码");
				return false;
			}
			/* var corp_id = $("#companyId").val();
		    if(corp_id == ''){
				toastr.warning("请选择归属公司");
				return false;
			} */
			var corp_id = $("#productTypeCode").val();
		    if(corp_id == ''){
				toastr.warning("请选择商品分类");
				return false;
			}
		     $.ajax({
			       type:"POST",
			       url:"${ctx}/product/productInfo/checkExist",
			       data:{"method":"checkExist","id":id,"bar_code":bar_code,"corp_id":corp_id},
			       dataType:"json",
			       global:false,
			       success : function(data) {
					if (null != data && null != data.result) {
						if(data.result == 'false'){
						 toastr.warning("该公司下该商品信息已存在");
						 return false;
						}else{
						 $("#productInfoForm").submit();
						}
					}
		
				}
			    });
		}
		
		function showProductBrandInfo (){
			var corp_id = $("#productTypeCode").val();
		    if(corp_id == ''){
				toastr.warning("请选择商品分类");
				return false;
			}
			top.$.jBox.open("iframe:${ctx}/product/productBrand/showProductBrandInfo?corp_id="+corp_id, "选择品牌信息", 950, 600, {
				buttons:{"确认":"ok","关闭":true}, 
				submit:function(v, h, f){
					debugger;
					if (v=="ok"){
						var parms = h.find("iframe")[0].contentWindow.$("#productBrand_parms").val();
						if(parms){
							$("#productInfoForm input[name='brand_id']").val(parms.split(",")[0]);
							$("#productInfoForm input[name='brand_name']").val(parms.split(",")[1]);
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productInfo/list">商品信息列表</a></li>
		<li class="active"><a href="${ctx}/product/productInfo/form?logid=${productInfo.logid}">商品信息${not empty productInfo.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="productInfoForm" modelAttribute="productInfo" action="${ctx}/product/productInfo/save" method="post" class="form-horizontal">
		<form:hidden path="logid" id="logid_productInfoForm"/>
		<sys:message content="${message}"/>
		<c:if test="${empty productInfo.logid}">
		<div class="control-group">
			<label class="control-label">商品条码:</label>
			<div class="controls">
				<form:input path="bar_code" id="bar_code_productInfoForm" htmlEscape="false" maxlength="13" class="required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty productInfo.logid}">
		<div class="control-group">
			<label class="control-label">商品条码:</label>
			<div class="controls">
				<form:input path="bar_code" htmlEscape="false" maxlength="13" id="bar_code_productInfoForm" class="required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">商品名称:</label>
			<div class="controls">
				<form:input path="product_name" htmlEscape="false" maxlength="50" class="required" style="width:700px"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品标题:</label>
			<div class="controls">
				<form:input path="product_title" htmlEscape="false" maxlength="50" class="required" style="width:700px"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品型号:</label>
			<div class="controls">
				<form:input path="product_model" htmlEscape="false" maxlength="30" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计量单位:</label>
			<div class="controls">
				<form:select path="unit_id" class="input-medium required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('unit_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">容量:</label>
			<div class="controls">
				<form:input path="metering_num" htmlEscape="false" maxlength="15" class="required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">容量单位:</label>
			<div class="controls">
				<form:select path="metering_id" class="input-medium required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('metering_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <c:if test="${empty productInfo.logid}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="corp_id" value="${productInfo.corp_id}" labelName="corp_name" labelValue="${productInfo.corp_name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty productInfo.logid}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="hidden" value="${productInfo.corp_id}" id="companyId">
			<input name="corp_name" type="text" value="${productInfo.corp_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		</c:if> --%>
		<c:if test="${empty productInfo.logid}">
		<div class="control-group">
			<label class="control-label">商品分类:</label>
			<div class="controls">
                <sys:treeselect id="productType" name="productType_id" value="${productInfo.productType_id}" labelName="productType_name" labelValue="${productInfo.productType_name}"
					title="公司" url="/product/productClassify/treeData?type=1" cssClass="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty productInfo.logid}">
		<div class="control-group">
			<label class="control-label">商品分类:</label>
			<div class="controls">
			<input name="productType_id" type="hidden" value="${productInfo.productType_id}">
			<input name="corp_id" type="hidden" value="${productInfo.corp_id}" id="productTypeCode">
			<input name="productType_name" type="text" value="${productInfo.productType_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">品牌名称:</label>
			<div class="controls">
				<form:hidden path="brand_id" id="brand_id_productInfoForm"/>
				<form:input path="brand_name" htmlEscape="false" maxlength="50" class="required" readonly="true" id="brand_name_productInfoForm"/>
				<i class="icon-search magnifying" onclick="showProductBrandInfo()"></i>
				<span class="help-inline"><font color="red">*</font> </span>
               <%--  <sys:tableselect id="brand" cssStyle="width:210px;height:23px;" name="brand_id" value="${productInfo.brand_id}" labelName="brand_name" labelValue="${productInfo.brand_name}"
					title="商品品牌" url="/product/productBrand/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">温度环境:</label>
			<div class="controls">
				<form:select path="temper_type" class="input-medium required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('temper_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保质期:</label>
			<div class="controls">
				<form:input path="overdue_date" htmlEscape="false" maxlength="11" class="required digits"/>（H）
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div><div class="control-group">
			<label class="control-label">标准价:</label>
			<div class="controls">
				<form:input path="normal_price" htmlEscape="false" maxlength="11" class="required number"  id="normal_price_productInfoForm"/>（元）
				<span class="help-inline"><font color="red">*</font>  精确到小数点后两位数</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">长:</label>
			<div class="controls">
				<form:input path="pro_length" htmlEscape="false" maxlength="11" class="required number" id="pro_length_productInfoForm"/>（cm）
				<span class="help-inline"><font color="red">*</font>  精确到小数点后两位数</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宽:</label>
			<div class="controls">
				<form:input path="pro_width" htmlEscape="false" maxlength="11" class="required number" id="pro_width_productInfoForm"/>（cm）
				<span class="help-inline"><font color="red">*</font>  精确到小数点后两位数</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高:</label>
			<div class="controls">
				<form:input path="pro_height" htmlEscape="false" maxlength="11" class="required number" id="pro_height_productInfoForm"/>（cm）
				<span class="help-inline"><font color="red">*</font>  精确到小数点后两位数</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品描述:</label>
			<div class="controls">
				<form:textarea path="pro_desc" htmlEscape="false" rows="3" maxlength="150" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品介绍地址:</label>
			<div class="controls">
				<form:textarea path="detail_url" htmlEscape="false" rows="3" maxlength="150" class="input-xlarge url"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="validCheckForm()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>