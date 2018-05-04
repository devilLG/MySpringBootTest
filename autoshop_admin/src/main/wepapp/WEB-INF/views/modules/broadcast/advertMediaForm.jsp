<%@ page contentType="text/html;charset=UTF-8" 
import="com.zhilai.master.common.config.Global"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>媒体信息</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/bootstrap-fileinput/css/fileinput.css"/>
	<%-- <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.js"></script> --%>
	<script type="text/javascript" src="${ctxStatic}/layer/layer.js"></script>
	<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/fileinput.js"></script>
	<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/plugins/piexif.js"></script>
	<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/plugins/piexif.js"></script>
	<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/plugins/sortable.js"></script>
	<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/plugins/purify.js"></script>
	<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/locales/zh.js"></script>
	<style type="text/css">
		#advertMediaForm .span6{
			width:46%;
		}
		.select2-container {
		    margin: 0;
		    position: relative;
		    display: inline-block;
		    zoom: 1;
		    vertical-align: middle;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/broadcast/advertMedia/">媒体信息列表</a></li>
		<li class="active"><a href="${ctx}/broadcast/advertMedia/form?logid=${advertMedia.logid}">媒体信息${not empty advertMedia.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#basic" data-toggle="tab">媒体信息</a></li>
		<li id="pictureType" class="${advertMedia.mediaType eq '02' ? '' : 'hide'}"><a href="#picture" data-toggle="tab"><span>图片信息</span></a></li>
	</ul><br/>
	<form:form id="advertMediaForm" modelAttribute="advertMedia" action="${ctx}/broadcast/advertMedia/save" method="post" class="form-horizontal">
		<input name="logid" type="hidden" value="${advertMedia.logid}">
		<input name="mediaId" id="mediaId" type="hidden" value="${advertMedia.mediaId}">
		<input id="resolutionXS" type="hidden" >
		<input id="resolutionYS" type="hidden" >
		<input id="mediaAddressS" name="mediaAddressS" type="hidden" >
		<input id="mediaSizeS" type="hidden" >
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="basic">
				<div class="span6">
					<div class="control-group">
						<label class="control-label">媒体名称:</label>
						<div class="controls">
							<form:input path="mediaName" htmlEscape="false" maxlength="50" class="required" /> 
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">媒体类型:</label>
						<div class="controls">
							<form:select path="mediaType" class="input-large" onchange="typeChange(this)">
								<form:options items="${fns:getDictList('media_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">媒体显示像素x:</label>
						<div class="controls">
							<form:input path="resolutionX" htmlEscape="false" maxlength="30" readonly="${advertMedia.mediaType eq '02' ? 'true' : 'false'}" class="required number" /> 
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">媒体地址:</label>
						<div class="controls">
							<form:input path="mediaAddress" htmlEscape="false" maxlength="255" readonly="${advertMedia.mediaType eq '02' ? 'true' : 'false'}" class="required" /> 
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">媒体描述:</label>
						<div class="controls">
							<form:textarea path="description" htmlEscape="false" rows="3" maxlength="500" class="input-xxlarge"/>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label">媒体标题:</label>
						<div class="controls">
							<form:input path="mediaTitile" htmlEscape="false" maxlength="50" class="required" /> 
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">媒体格式:</label>
						<div class="controls">
							<form:select path="mimeType" class="input-large">
								<form:options items="${fns:getDictList('mime_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">媒体显示像素y:</label>
						<div class="controls">
							<form:input path="resolutionY" htmlEscape="false" maxlength="30" readonly="${advertMedia.mediaType eq '02' ? 'true' : 'false'}" class="required number" /> 
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">媒体大小:</label>
						<div class="controls">
							<form:input path="mediaSize" htmlEscape="false" maxlength="11" readonly="${advertMedia.mediaType eq '02' ? 'true' : 'false'}" class="required number" />(KB) 
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</div>	
			</div>
			<div class="tab-pane fade" id="picture">
				<div  id="imgDiv">
					<label class="control-label">上传图片:</label>
					<div class="controls photo-group" style="width:700px">
						<c:forEach var="image" items="${imageList}">
							<div>
								<img src="${ctxOriginal}${image}">
							</div>
						</c:forEach>
					</div>
					<div class="controls">
						<input id="btnSubmit" class="btn btn-success" onclick="toUploadImg('${advertMedia.mediaId}','false')" type="button" value="上传图片"/>
					</div>
				</div>
				<script>
					layer.photos({
						photos:"#imgDiv>div.photo-group",
						closeBtn:4,
						anim:0
					})
				</script>
			</div>
		</div>
		<div class="form-actions">
			<div style="margin-left:360px">
				<input id="btnSubmit"  class="btn btn-primary" type="submit" value="保 存"/>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</div>
	</form:form>
	<script type="text/javascript">
	
		//格式验证
		function typeChange(e) {
			debugger;
			$("#resolutionX").val("");
			$("#resolutionY").val("");
			$("#mediaAddress").val("");
			$("#mediaSize").val("");
			var type = $(e).val();
			var picturetype = $("#pictureType");
			if(type == 02){
				picturetype.removeClass('hide');
				$("#resolutionX").attr("readonly","true");
				$("#resolutionY").attr("readonly","true");
				$("#mediaAddress").attr("readonly","true");
				$("#mediaSize").attr("readonly","true");
				$("#resolutionX").val($("#resolutionXS").val());
				$("#resolutionY").val($("#resolutionYS").val());
				$("#mediaAddress").val($("#mediaAddressS").val());
				$("#mediaSize").val($("#mediaSizeS").val());
			} else {
				picturetype.addClass('hide');
				$("#resolutionX").removeAttr("readonly","true");
				$("#resolutionY").removeAttr("readonly","true");
				$("#mediaAddress").removeAttr("readonly","true");
				$("#mediaSize").removeAttr("readonly","true");
			}
		}
		
		//上傳圖片
		function toUploadImg(mediaId,Tf){
			debugger;
			if(mediaId==""){
				mediaId = "xxxx";//防止传空值
			}
			top.$.jBox.open("iframe:${ctx}/broadcast/advertMedia/toImgUpload/"+mediaId,"图片上传",950,600,{
				buttons:{"关闭":true},
				submit:function(v,h,f){
					debugger;
					if(Tf){
						$.ajax({	
							type:"POST",
							url:"${ctx}/broadcast/advertMedia/findImgUrl",
							data:{mediaId:$("#mediaId").val()},
							dataType:"JSON",
							success:function(data){
								debugger;
								var imgDiv = $("#imgDiv .photo-group");
								imgDiv.empty();
								if(data.length>0){
									if(data[0]!=""){
										imgDiv.append("<div><img src=${ctxOriginal}"+data[0]+"></div>")
									}
									$("#mediaAddress").val(data[0]);
									$("#resolutionX").val(data[1]);
									$("#resolutionY").val(data[2]);
									$("#mediaSize").val(data[3]);
									$("#mediaAddressS").val(data[0]);
									$("#resolutionXS").val(data[1]);
									$("#resolutionYS").val(data[2]);
									$("#mediaSizeS").val(data[3]);
								}
							}
						})	
					}
					layer.photos({
						photos:"#imgDiv>div.photo-group",
						closeBtn:4,
						anim:0
					})
				},
				loaded:function(h){
					$(".jbox-content",top.document).css("overflow-y","hidden");
				}
			});
		}
	
		$(document).ready(function() {
			$("#advertMediaForm").validate({
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
	</script>
</body>
</html>
