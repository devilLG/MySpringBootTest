<%@ page contentType="text/html;charset=UTF-8"
	import="com.zhilai.master.common.config.Global"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/head.jsp" %>		
<html>
	<head>
		<script type="text/javascript" src="${ctxStatic}/zzw/js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/layer/layer.js"></script>
		<!-- bootstrapFileinput -->
		<link rel="stylesheet" href="${ctxStatic}/zzw/css/bootstrap.css"/>
		<link rel="stylesheet" href="${ctxStatic}/bootstrap-fileinput/css/fileinput.css"/>
		
		<link rel="stylesheet" href="${ctxStatic}/bootstrap-toastr/toastr.min.css" />
		<script type="text/javascript" src="${ctxStatic}/bootstrap-toastr/toastr.min.js" ></script>
		
		<script type="text/javascript" src="${ctxStatic}/zzw/js/bootstrap.js"></script>
		<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/fileinput.js"></script>
		<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/plugins/piexif.js"></script>
		<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/plugins/piexif.js"></script>
		<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/plugins/sortable.js"></script>
		<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/plugins/purify.js"></script>
		
		
		<script type="text/javascript" src="${ctxStatic}/bootstrap-fileinput/js/locales/zh.js"></script>
	</head>	
	<body style="height:446px;">
		<input id="uploadImage" name="images" type="file" accept=".jpg,.png,.jpeg,.bmp,.gif" multiple ></input>
		
	<script>
	
		//配置toastr
		toastr.options = {
			"closeButton": true,
			"debug": false,
			"positionClass": "toast-top-center",
			"onclick": null,
			"showDuration": "1000",
			"hideDuration": "1000",
			"timeOut": "1000",
			"extendedTimeOut": "1000",
			"showEasing": "swing",
			"hideEasing": "linear",
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut"
		};
		var obj = ${materielImgList};
		var initialPreview = [] , initialPreviewConfig = [];
		var option = {
				language : 'zh',
				uploadUrl : "${ctx}/broadcast/advertMedia/imgUpload",
				uploadAsync : true,
				showCancel :false,
				showColse : false,
				maxFileCount : '1',
				validateInitialCount : true,
				enctype : 'multipart/form-data',
				overwriteInitial : false,
				allowedFileExtensions:['jpg', 'png', 'jpeg', 'bmp', 'gif'],
				overwriteInitial :false,
				initialPreviewAsData : true,
				uploadExtraData:function(previewId, index){
					debugger;
					var obj={};
					obj.mediaId='${mediaId}';
					return obj;
				},
				previewSettings: {
		            image: {width: "250px", height: "160px"},
		        },
				layoutTemplates : {
					actionUpload :  ''
				}
			}
		
		var adminPath = '<%=Global.getConfig("adminPath")%>'
		var ctx = '${ctx}';
		ctx = ctx.substring(0,ctx.lastIndexOf(adminPath));
		debugger;
		 if(obj.length>0){debugger;
			for(var i = 0 ; i<obj.length;i++){
				var name = obj[i].substring(obj[i].lastIndexOf("\\")+1,obj[i].length);
				var dId = obj[i].substring(obj[i].lastIndexOf("\\")-8,obj[i].lastIndexOf("\\"))
				initialPreview.push(ctx+obj[i])
				initialPreviewConfig.push({
					caption:name,
					url:"${ctx}/broadcast/advertMedia/imgDelete",
					key:i,
					extra:{mediaId:dId,originalName:name}
				});
			}
			debugger;
			option.initialPreview=initialPreview,
			option.initialPreviewConfig = initialPreviewConfig
		} 
		var arrImg=[];
		
		$("#uploadImage").fileinput(option).on("fileuploaded", function(event, data, previewId, index){
			debugger;
			toastr.success(data.response.originalFilename+"上传成功");
			arrImg[index] = data.response.logid;
			setTimeout(function(){
				$(".jbox-button.jbox-button-focus").click();
			},3000);
			top.$.jBox.getBox().find("button[value='true']").trigger("click");
		}).on('fileerror', function(event, data, msg) {
			toastr.error("上传失败");
			console.log(data);
		}).on('filedeleted',function(event, key, jqXHR, data){
			debugger;
			toastr.success(data.originalName+"删除成功");
			top.$.jBox.getBox().find("button[value='true']").trigger("click");
		});
		
	</script>
	</body>
</html>