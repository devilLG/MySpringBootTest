<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link rel="stylesheet" charset="utf-8" type="text/css" href="${ctx}/static/foxibox/style/style.css" />
<script type="text/javascript" charset="utf-8" src="${ctx}/static/foxibox/script/jquery-foxibox-0.2.min.js"></script>
<html>
<head>
	<title>商品分类信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
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
		
	window.opener.document.location.reload();
	$(document).ready(function(){
	  $('#pics a').foxibox();
	});

	/** 
	* 从 file 域获取 本地图片 url 
	*/ 
	function getFileUrl(sourceId) { 
	var url; 
	if (navigator.userAgent.indexOf("MSIE")>=1) { // IE 
	url = document.getElementById(sourceId).value; 
	} else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox 
	url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0)); 
	} else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome 
	url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0)); 
	} 
	return url; 
	} 
	
	/** 
	* 将本地图片 显示到浏览器上 
	*/ 
	function preImg(sourceId, targetId) { 
	var url = getFileUrl(sourceId); 
	var imgPre = document.getElementById(targetId);
	imgPre.src = url; 
	} 
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productClassify/list">商品分类列表</a></li>
		<li class="active"><a href="${ctx}/product/productClassify/image">图片管理【${productClassify.classify_name}】</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#viewImage" data-toggle="tab">查看图片</a></li>
		<li><a href="#uploadImage" data-toggle="tab"><span>上传图片</span></a></li>
	</ul><br/>
	<form:form id="inputForm" enctype="multipart/form-data" modelAttribute="productClassify" action="${ctx}/product/productClassify/uploadImage?classify_id=${productClassify.classify_id}" method="post" class="form-horizontal">
		<form:hidden path="classify_id"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="viewImage">
			  <c:if test="${not empty productClassify.wechart_image}">
			    <div class="control-group">
					<label class="control-label">微信:</label>
					<div class="controls" style="background:#F00;width:100px">
						<a href="${productClassify.wechart_image}" onclick="return false;" rel="[gall1]" title="">
						     <img src="${productClassify.wechart_image}" alt="" width="100" height="100" />
						</a>
					</div>
				 </div>
				 </c:if>
				  <c:if test="${not empty productClassify.android_image}">
				   <div class="control-group">
						<label class="control-label">Android:</label>
						<div class="controls" style="background:#F00;width:100px">
							<a href="${productClassify.android_image}" onclick="return false;" rel="[gall1]" title="">
							     <img src="${productClassify.android_image}" alt="" width="100" height="100" />
							</a>
						</div>
					</div>
					</c:if>
					 <c:if test="${not empty productClassify.ios_image}">
				   <div class="control-group">
						<label class="control-label">IOS:</label>
						<div class="controls" style="background:#F00;width:100px">
							<a href="${productClassify.ios_image}" onclick="return false;" rel="[gall1]" title="">
							     <img src="${productClassify.ios_image}" alt="" width="100" height="100" />
							</a>
						</div>
					</div>
					</c:if>
				<c:if test="${not empty productClassify.terminal_image}">
				   <div class="control-group">
						<label class="control-label">终端:</label>
						<div class="controls" style="background:#F00;width:100px">
							<a href="${productClassify.terminal_image}" onclick="return false;" rel="[gall1]" title="">
							     <img src="${productClassify.terminal_image}" alt="" width="100" height="100" />
							</a>
						</div>
					</div>
					</c:if>
			<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
			</div>
			<div class="tab-pane fade" id="uploadImage">
				<div class="control-group">
					<label class="control-label">图片类型:</label>
					<div class="controls">
						<form:checkboxes path="chanel_type" items="${fns:getDictList('chanel_type_productClassify')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
						<span class="help-inline"><font color="red">*</font> </span>
						<%-- <form:select path="chanel_type" class="input-small" required="required">
							<form:option value="" label="请选择"/>
							<form:options items="${fns:getDictList('chanel_type_productClassify')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select> --%>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">选择图片:</label>
					<div class="controls">
						<input class="btn btn-primary" value="选择图片" style="width:60px" onclick="file.click()"/>
						<input type="file" required="required" accept="image/png, image/jpg, image/jpeg, image/gif" id="file" onchange="preImg(this.id,'imgPre');"  name="file" style="position:absolute;filter:alpha(opacity=0);-moz-opacity:0;opacity:0;margin-left:0px;" size="1"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">显示图片:</label>
					<div class="controls" style="background:#F00;width:100px">
						<img id="imgPre" src="" width="100px" height="100px" style="display: block;float:left;" />
					</div>
				</div>
				<div class="form-actions">
				<div style="margin-left:450px">
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
					<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
				</div>
				</div>
			</div>
		</div>
		</form:form>
</body>
</html>