<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link rel="stylesheet" charset="utf-8" type="text/css" href="${ctx}/static/foxibox/style/style.css" />
<script type="text/javascript" charset="utf-8" src="${ctx}/static/foxibox/script/jquery-foxibox-0.2.min.js"></script>
<html>
<head>
	<title>商品信息</title>
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
	
	 function showimage(source)
     {
         $("#ShowImage_Form").find("#img_show").html("<image src='"+source+"' class='carousel-inner img-responsive img-rounded' />");
         $("#ShowImage_Form").modal();
     }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/productInfo/list">商品信息列表</a></li>
		<li class="active"><a href="${ctx}/product/productInfo/image">图片管理【${productInfo.product_id}】</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#viewImage" data-toggle="tab">查看图片</a></li>
		<li><a href="#uploadImage" data-toggle="tab"><span>上传图片</span></a></li>
	</ul><br/>
	<form:form id="inputForm" enctype="multipart/form-data" modelAttribute="productInfo" action="${ctx}/product/productInfo/uploadImage?id=${productInfo.logid}" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div id="ShowImage_Form" class="modal hide">         
         <div class="modal-header">
             <button data-dismiss="modal" class="close" type="button"></button>
         </div>
           <div class="modal-body">
            <div id="img_show">
            </div>
        </div>
        </div>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="viewImage">
			<c:forEach var="productImage" items="${productImageList}">
			   <div class="control-group">
					<label class="control-label">图片类型:   ${fns:getDictLabel(productImage.chanel_type, 'chanel_type', '未知')}</label>
					<div class="controls">
						<a href="${productImage.image_url}" onclick="return false;" rel="[gall1]" title="Image px :${productImage.image_px}">
						     <img onmouseover="this.style.cursor='pointer';this.style.cursor='hand'" onmouseout="this.style.cursor='default'" onclick="javascript:showimage('${productImage.image_url}');" src="${productImage.image_url}" alt="" width="100" height="100" />
						</a>
					</div>
				</div>
			</c:forEach>
			<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
			</div>
			<div class="tab-pane fade" id="uploadImage">
				<div class="control-group">
					<label class="control-label">图片类型:</label>
					<div class="controls">
					<form:checkboxes path="chanel_type" items="${fns:getDictList('chanel_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
						<%-- <form:select path="chanel_type" class="input-small" required="required">
							<form:option value="" label="请选择"/>
							<form:options items="${fns:getDictList('chanel_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
					<div class="controls">
						<img id="imgPre" src="" width="320px" height="320px" style="display: block;float:left;" />
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