<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品品牌</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#productBrandForm").validate({
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
			var id=document.getElementById('logid_productBrandForm').value;
		    var brand_name = $("#brand_name_productBrandForm").val();
		    if(brand_name == ''){
				toastr.warning("请填写品牌名称");
				return false;
			}
			var corp_id = $("#companyId").val();
		    if(corp_id == ''){
				toastr.warning("请选择归属公司");
				return false;
			}
		     $.ajax({
			       type:"POST",
			       url:"${ctx}/product/productBrand/checkExist",
			       data:{"method":"checkExist","id":id,"brand_name":brand_name,"corp_id":corp_id},
			       dataType:"json",
			       global:false,
			       success : function(data) {
					if (null != data && null != data.result) {
						if(data.result == 'false'){
						 toastr.warning("该公司下该品牌名称已存在");
						 return false;
						}else{
						 $("#productBrandForm").submit();
						}
					}
		
				}
			    });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productBrand/list">商品品牌列表</a></li>
		<li class="active"><a href="${ctx}/product/productBrand/form?logid=${productBrand.logid}">商品品牌${not empty productBrand.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="productBrandForm" modelAttribute="productBrand" action="${ctx}/product/productBrand/save" method="post" class="form-horizontal">
		<form:hidden path="logid" id="logid_productBrandForm"/>
		<sys:message content="${message}"/>
		<c:if test="${empty productBrand.logid}">
		<div class="control-group">
			<label class="control-label">品牌名称:</label>
			<div class="controls">
				<form:input path="brand_name" htmlEscape="false" maxlength="50" class="required" id="brand_name_productBrandForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty productBrand.logid}">
		<div class="control-group">
			<label class="control-label">品牌名称:</label>
			<div class="controls">
				<form:input path="brand_name" htmlEscape="false" maxlength="50" class="required" readonly="true" id="brand_name_productBrandForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">品牌公司:</label>
			<div class="controls">
				<form:input path="brandCorp_name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成立时间:</label>
			<div class="controls">
				<input id="establish_time" name="establish_time" type="text" readonly="readonly" maxlength="20" class="required input-medium Wdate"
					value="${productBrand.establish_time}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<br/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属行业:</label>
			<div class="controls">
				<form:input path="industry" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Logo地址:</label>
			<div class="controls">
				<form:textarea path="logo" htmlEscape="false" rows="2" maxlength="80" class="input-xlarge url"/>
			</div>
		</div>
		<c:if test="${empty productBrand.logid}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="corp_id" value="${productBrand.corp_id}" labelName="corp_name" labelValue="${productBrand.corp_name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty productBrand.logid}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="hidden" value="${productBrand.corp_id}" id="companyId">
			<input name="corp_name" type="text" value="${productBrand.corp_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		</c:if>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="validCheckForm()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>