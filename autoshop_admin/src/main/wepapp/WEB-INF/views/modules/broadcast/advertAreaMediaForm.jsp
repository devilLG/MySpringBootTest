<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/broadcast/advertAreaMedia/">区域媒体列表</a></li>
		<li class="active"><a href="${ctx}/broadcast/advertAreaMedia/form">添加区域媒体</a></li>
	</ul>
	<form:form id="advertAreaMediaForm" modelAttribute="advertAreaMedia" action="${ctx}/broadcast/advertAreaMedia/save" method="post" class="form-horizontal">
		<input type="hidden" name="logid" id="logid" value="${advertAreaMedia.logid}"/>
		<input type="hidden" name="playId" id="playId" value="${advertAreaMedia.playId}"/>
		<sys:message content="${message}"/>	
		<div class="row-fluid">
       		<fieldset>
        		<div class="control-group">
					<label class="control-label">配置名称：</label>
					<div class="controls">
						<input type="text" name="configName"id="configName_advertAreaMediaForm"  class="required"  readonly = "readonly"/>
						<input type="hidden" name="configId" id="configId_advertAreaMediaForm"  class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
					
				<div class="control-group">
					<label class="control-label">区域名称：</label>
					<div class="controls">
						<input type="hidden"  name="areaId" id="areaId_advertAreaMediaForm"  class="required" />
						<input type="text" name="areaName" id="areaName_advertAreaMediaForm" class="required" readonly = "readonly"/>
						<i class="icon-search magnifying" onclick="showAdvertAreaInfo()"></i>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>		
       		</fieldset>
       		
        	<fieldset>
        		<legend>媒体信息</legend>
        		<div class="control-group">
					<input class="btn btn-primary" type="button" value="添加媒体信息" onclick="showAdvertAreaMediaDialog()"/>
				</div>
	        	<table id="SelectAdvertMediaList" class="table table-striped table-bordered table-condensed">
					<thead>
				    	<tr>
							<th style="text-align: center;">媒体编号</th>
							<th style="text-align: center;">媒体类型</th>
							<th style="text-align: center;">媒体格式</th>
							<th style="text-align: center;">媒体地址</th>
							<th style="text-align: center;">媒体大小(KB)</th>
							<th style="width: 8%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${advertAreaMediaMaterielList}" var="dataObj">
							<tr> 
								<td style="text-align: center;" nowrap>${advertMedia.mediaId}</td>
								<td style="text-align: center;">${mediaTypeMap[advertMedia.mediaType]}</td>
								<td style="text-align: center;">${mimeTypeMap[advertMedia.mimeType]}</td>
								<td style="text-align: center;">${advertMedia.mediaAddress}</td>
								<td style="text-align: center;">${advertMedia.mediaSize}</td>
				    			<td><a title="移除" href="javascript:void();" onclick="removeMaterialItem(this)" class="btnDel">移除</a></td> 
				    			<td style="display: none;"><input type="text" name="mediaId" value="${dataObj.mediaId}" /></td>
				    			<td style="display: none;"><input type="text" name="mediaType" value="${dataObj.mediaType}" /></td>
				    			<td style="display: none;"><input type="text" name="mimeType" value="${dataObj.mimeType}"/></td>
				    			<td style="display: none;"><input type="text" name="mediaAddress" value="${dataObj.mediaAddress}"/></td>
				    			<td style="display: none;"><input type="text" name="mediaSize" value="${dataObj.mediaSize}"/></td>
				    		</tr>
						</c:forEach>
					</tbody>
				</table>
        	</fieldset>
       	</div>
		<div class="form-actions" style="padding-left: 28%">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="return addMaterialIndex();" />
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

<script>

//	选择区域配置
var showAdvertAreaInfo = function() {
	 top.$.jBox.open("iframe:${ctx}/broadcast/advertArea/showAdvertAreaInfo", "选择区域配置", 950, 600, {
		buttons:{"确认":"ok","关闭":true}, 
		submit:function(v, h, f){
			if (v=="ok"){
				debugger;
				var parms = h.find("iframe")[0].contentWindow.$("#advertArea_parms").val();
				if(parms){
					var preConfigId = $("#advertAreaMediaForm input[name='configId']").val();
					$("#advertAreaMediaForm input[name='configId']").val(parms.split(",")[0]);
					$("#advertAreaMediaForm input[name='configName']").val(parms.split(",")[1]);
					$("#advertAreaMediaForm input[name='areaId']").val(parms.split(",")[2]);
					$("#advertAreaMediaForm input[name='areaName']").val(parms.split(",")[3]);
					//	更改区域配置，清空媒体信息
					/* if(preConfigId != parms.split(",")[0]) {
						$("#SelectAdvertMediaList tbody").empty();
					} */
				}						
			}
		}, 
		loaded:function(h){
			$(".jbox-content", top.document).css("overflow-y","hidden");
		}
	});
};

//选择媒体信息
var showAdvertAreaMediaDialog = function() {
 	var config_id = $("#configId_advertAreaMediaForm").val();
 	if(config_id == ""){
   	    toastr.error("请先选择配置名称！");
   	    return;
    }
    top.$.jBox.open("iframe:${ctx}/broadcast/advertMedia/advertMediaSelect?config_id=" + config_id, "选择媒体", 950, 600, {
		buttons:{"确定":"ok","关闭":true}, 
		submit:function(v, h, f){
			if (v=="ok"){
				var table = h.find("iframe")[0].contentWindow.document.getElementById('advertMediaTable');
				var length = h.find("iframe")[0].contentWindow.document.all.length;
				var ckList = "";
				for(var i=0 ; i<length ; i++){
					if(h.find("iframe")[0].contentWindow.document.all[i].type == 'checkbox' && h.find("iframe")[0].contentWindow.document.all[i].checked == true){
						ckList = ckList + "," + h.find("iframe")[0].contentWindow.document.all[i].value;
					}
				}
				var existsMatCode=""; 
			    $("#SelectAdvertMediaList input[name='mediaId']").each(function(){
			    	existsMatCode = existsMatCode + $(this).val() + ",";
			    });
			    debugger;
				ckList = ckList.substring(1).split(',');
				if(ckList != undefined && ckList != "" && ckList.length>0){
					for(var i=0;i<ckList.length;i++){
						var materialInfo = ckList[i].split(';');
						var mediaId = materialInfo[0],
							mediaType = materialInfo[1],
							mimeType = materialInfo[2],
							mediaAddress = materialInfo[3],
							mediaSize = materialInfo[4]
							media = materialInfo[5]
							mime = materialInfo[6]
						//	已有的物料不添加tr
						if(existsMatCode.indexOf(mediaId) >= 0) {
							continue;
						}
						$("#SelectAdvertMediaList").append(
							"<tr>" + 
							"	<td>" + mediaId + "</td>" + 
				    		"	<td>" + media + "</td>" + 
				    		"	<td>" + mime + "</td>" + 
				    		"	<td>" + mediaAddress + "</td>" + 
							"	<td>" + mediaSize + "</td>" +
				    		"	<td><a title='移除' href='javascript:void();' onclick='removeMaterialItem(this)' class='btnDel'>移除</a></td>" + 
				    		"	<td style='display: none;'><input type='text' name='mediaId' value='" + mediaId + "' /></td>" + 
				    		"	<td style='display: none;'><input type='text' name='mediaType' value='" + mediaType + "' /></td>" + 
				    		"	<td style='display: none;'><input type='text' name='mimeType' value='" + mimeType + "' /></td>" + 
				    		"	<td style='display: none;'><input type='text' name='mediaAddress' value='" + mediaAddress + "' /></td>" + 
				    		"	<td style='display: none;'><input type='text' name='mediaSize' value='" + mediaSize + "' /></td>" + 
				    		"</tr>"
				    	);  
					}
				}
			}
		}, loaded:function(h){
			$(".jbox-content", top.document).css("overflow-y","hidden");
		}
	});
};

//移除媒体信息
var removeMaterialItem = function(item) {
	$(item).parent().parent().remove();
};

//给material元素增加list下标
var addMaterialIndex = function() {
	//	必须有媒体信息
	var digits = $("#SelectAdvertMediaList tbody tr").find("input[name=mediaId]");
	var mcount = 0;
	digits.each(function(i, item) {
		var value = $(item).val();
		if(value == "" || value == undefined) {
			mcount += 0;
		} else {
			mcount += parseInt(value);
		}
	});
	if(mcount == 0) {
		toastr.error("请添加媒体信息");
		return false;
	}
	//
/* 	$("#SelectAdvertMediaList tbody tr").each(function(a) {
		var inputs = $("#SelectAdvertMediaList tbody tr:eq(" + a + ") input");
		inputs.each(function(b) {
			var input = inputs.eq(b);
			input.prop("name", "ScrapOutApplyMaterielList[" + a + "]." + input.prop("name"));
		});
	}); */
};

</script>
</body>
</html>