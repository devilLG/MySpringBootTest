<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品分类管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#productClassifyInputForm").validate({
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
			var classify_id=document.getElementById('classify_id_productClassifyInputForm').value;
		    var classify_name = $("#classify_name_productClassifyInputForm").val();
		    if(classify_name == ''){
				toastr.warning("请填写分类名称");
				return false;
			}
			var corp_id = $("#companyId").val();
		    if(corp_id == ''){
				toastr.warning("请选择归属公司");
				return false;
			}
		     $.ajax({
			       type:"POST",
			       url:"${ctx}/product/productClassify/checkExist",
			       data:{"method":"checkExist","classify_id":classify_id,"classify_name":classify_name,"corp_id":corp_id},
			       dataType:"json",
			       global:false,
			       success : function(data) {
					if (null != data && null != data.result) {
						if(data.result == 'false'){
						 toastr.warning("该公司下该分类名称已存在");
						 return false;
						}else{
						 $("#productClassifyInputForm").submit();
						}
					}
		
				}
			    });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productClassify/">商品分类列表</a></li>
		<li class="active"><a href="${ctx}/product/productClassify/form?classify_id=${productClassify.classify_id}&parent_id=${productClassify.parent_id}">商品分类${not empty productClassify.classify_id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="productClassifyInputForm" modelAttribute="productClassify" action="${ctx}/product/productClassify/save" method="post" class="form-horizontal">
		<form:hidden path="classify_id" id="classify_id_productClassifyInputForm"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">上级分类:</label>
			<div class="controls">
                <sys:treeselect id="productClassify" name="parent_id" value="${productClassify.parent_id }" labelName="parentName" labelValue="${productClassify.parentName }"
					title="商品分类" url="/product/productClassify/treeData" extId="${productClassify.classify_id }" cssClass="required input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类名称:</label>
			<div class="controls">
				<form:input path="classify_name" htmlEscape="false" maxlength="50" class="required input-xlarge" id="classify_name_productClassifyInputForm"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">级别:</label>
			<div class="controls">
				<form:input path="level" htmlEscape="false" maxlength="50" class="required digits input-small" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="sort_by" htmlEscape="false" maxlength="50" class="required digits input-small"/>
				<span class="help-inline">排列顺序，升序。</span>
			</div>
		</div>
		<c:if test="${empty productClassify.classify_id}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="corp_id" value="${productClassify.corp_id}" labelName="corp_name" labelValue="${productClassify.corp_name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty productClassify.classify_id}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<input name="corp_id" type="hidden" value="${productClassify.corp_id}" id="companyId">
			<input name="corp_name" type="text" value="${productClassify.corp_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">分类描述:</label>
			<div class="controls">
				<form:textarea path="classify_desc" htmlEscape="false" rows="3" maxlength="100" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="validCheckForm()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>