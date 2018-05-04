<%@ page contentType="text/html;charset=UTF-8" 
import="com.zhilai.master.common.config.Global"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备信息</title>
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
		#monitorDeviceForm .span6{
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
		<li><a href="${ctx}/monitor/monitorDevice/">设备信息列表</a></li>
		<li class="active"><a href="${ctx}/monitor/monitorDevice/form?deviceId=${deviceInfo.deviceId}">设备信息${not empty deviceInfo.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#basic" data-toggle="tab">设备信息</a></li>
		<li><a href="#picture" data-toggle="tab"><span>图片信息</span></a></li>
	</ul><br/>
	<form:form id="monitorDeviceForm" modelAttribute="deviceInfo" action="${ctx}/monitor/monitorDevice/save" method="post" class="form-horizontal">
		<input name="logid" type="hidden" value="${deviceInfo.logid}">
		<input name="deviceId" id="deviceId" type="hidden" value="${deviceInfo.deviceId}">
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="basic">
				<div class="span6">
					<div class="control-group">
						<label class="control-label">设备名称:</label>
						<c:if test="${empty deviceInfo.logid}">
							<div class="controls">
								<form:select path="deviceName" class="input-large">
									<form:options items="${fns:getDictList('device_info')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</c:if>
						 <c:if test="${not empty deviceInfo.logid}">
							<div class="controls">
								<form:select path="deviceName" class="input-large" disabled="true">
									<form:options items="${fns:getDictList('device_info')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</c:if>
					</div>
					<div class="control-group">
						<label class="control-label">设备型号:</label>
						<div class="controls">
							<form:select path="deviceModel" class="input-large">
								<form:options items="${fns:getDictList('device_model')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">维护周期:</label>
						<div class="controls">
							<form:input path="maintenance" htmlEscape="false" maxlength="11" onblur='checkNum(this)' class="required number" />(天)
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group" id="dm">
						<label class="control-label">供应商:</label>
						<div class="controls">
							<form:select path="deviceMfrs" class="input-large">
								<form:options items="${fns:getDictList('device_mfrs')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">设备属性特别描述:</label>
						<div class="controls">
							<form:textarea path="specialNature" htmlEscape="false" rows="3" maxlength="255" class="input-xxlarge"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">设备提供的功能描述:</label>
						<div class="controls">
							<form:textarea path="deviceEffect" htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge"/>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label">设备品牌:</label>
						<div class="controls">
							<form:select path="deviceBard" class="input-large">
								<form:options items="${fns:getDictList('device_bard')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">设备尺寸:</label>
						<div class="controls">
							<input type="text" style="width: 49px" name="deviceLength" id="deviceLength" value="${deviceInfo.deviceLength}" onblur='checkNum(this)' maxlength="9"><h4 style="display: inline-block;">*</h4>
							<input type="text" style="width: 49px" name="deviceWidth" id="deviceWidth" value="${deviceInfo.deviceWidth}" onblur="checkNum(this)" maxlength="9"><h4 style="display: inline-block;">*</h4>
							<input type="text" style="width: 49px" name="deviceHeight" id="deviceHeight" value="${deviceInfo.deviceHeight}" onblur="checkNum(this)" maxlength="9">(长*宽*高 cm³)
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">故障严重等级:</label>
						<div class="controls">
							<form:select path="errorLevel" class="input-large">
								<form:options items="${fns:getDictList('error_level_info')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">设备所在柜子位置描述:</label>
						<div class="controls">
							<form:textarea path="devicePostion" htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge"/>
						</div>
					</div>
				</div>	
			 	<div class="col-lg-10 col-md-12 col-sm-12 col-xs-12 span12" style="margin-top: 2%;">
	             	<div class="portlet box blue">
                       <div class="portlet-title">
                           <div class="caption">
                               <i class="fa fa-comments"></i><h3>注：请配置设备运行参数(全部必填)</h3>
                           </div>
                       </div>
                       <div class="portlet-body">
                           <div class="table-scrollable">
                               <table class="table table-bordered table-hover">
                                   <thead>
                                       <tr>
                                           <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 参数代码  </th>
                                           <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 参数名称 </th>
                                           <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 参数最低正常值 </th>
                                           <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 参数最高正常值  </th>
                                           <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 参数警报最低正常值 </th>
                                           <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 参数警报最高正常值 </th>
                                           <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 参数单位</th>
                                           <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 是否触发设备故障状态 </th>
                                           <th id="receiveItemTh" style="height: 200%;  text-align: center; font-size: 16px;"> 移除 </th>
                                       </tr>
                                   </thead>
                                  <tbody id="objTbody">
                                  <c:if test="${empty deviceInfo.logid}">
                                   	   <tr class="success" id="objClone">
                                           <td style='text-align: center;'><input type="text" name="envCode" class="envCodeList" style="text-align: center;width: 160px" maxlength="30"/></td>
                                           <td style='text-align: center;'><input type="text" name="envName" class="envNameList" style="text-align: center;width: 160px" maxlength="15"/></td>
                                           <td style='text-align: center;'><input type="text" name="envSvalue" class="envSvalueList" style="text-align: center;width: 160px" maxlength="15"/></td>
                                           <td style='text-align: center;'><input type="text" name="envEvalue" class="envEvalueList" style="text-align: center;width: 160px" maxlength="15"/></td>
                                           <td style='text-align: center;'><input type="text" name="envWsvalue" class="envWsvalueList"  style="text-align: center;width: 160px" maxlength="15"/></td>
                                           <td style='text-align: center;'><input type="text" name="envWevalue" class="envWevalueList" style="text-align: center;width: 160px" maxlength="15"/></td>
                                           <td style='text-align: center;'>
                                           <select  class="input-large unitNameList" id="unitName" name="unitName">
										       <c:forEach var="unitName" items="${deviceUnitList}">
												    <option value="${unitName.value}">${unitName.label}</option>
											   </c:forEach>
										   </select>
                                           </td>
                                           <td style='text-align: center;'>
									   	   <select  class="input-large errorLevelList" name="errorLevelEvn"  >
										       <c:forEach var="errorLevel" items="${errorLevelList}">
												    <option value="${errorLevel.value}">${errorLevel.label}</option>
											   </c:forEach>
										   </select>
										   </td>
                                           <td style="text-align: center;">
                                           <a  class="fa fa-remove" href="javascript:void(0)" onclick="removeTr(this)" style="cursor:pointer;">
	                                           		<i class="icon-minus"></i>
	                                       </a>
                                           </td>
                                       </tr>
                                  </c:if>
                                  <c:forEach items="${deviceInfo.deviceEnvList}" var="deviceEnv">
                                       <tr class="success">
                                           <td style='text-align: center;'><input type="text" name="envCode" class="envCodeList"  value="${deviceEnv.envCode}" style="text-align: center;width: 160px" maxlength="30" readonly="readonly"/></td>
                                           <td style='text-align: center;'><input type="text" name="envName" class="envNameList" value="${deviceEnv.envName}" style="text-align: center;width: 160px" maxlength="15" readonly="readonly"/></td>
                                           <td style='text-align: center;'><input type="text" name="envSvalue" class="envSvalueList"  value="${deviceEnv.envSvalue}" style="text-align: center;width: 160px" maxlength="15"/></td>
                                           <td style='text-align: center;'><input type="text" name="envEvalue" class="envEvalueList" value="${deviceEnv.envEvalue}" style="text-align: center;width: 160px" maxlength="15"/></td>
                                           <td style='text-align: center;'><input type="text" name="envWsvalue" class="envWsvalueList"  value="${deviceEnv.envWsvalue}" style="text-align: center;width: 160px" maxlength="15"/></td>
                                           <td style='text-align: center;'><input type="text" name="envWevalue" class="envWevalueList" value="${deviceEnv.envWevalue}" style="text-align: center;width: 160px" maxlength="15"/></td>
                                           <td style='text-align: center;'>
                                           <select  class="input-large unitNameList" id="unitName" name="unitName">
										       <c:forEach var="unitName" items="${deviceUnitList}">
												    <option value="${unitName.value}" <c:if test="${unitName.value ==deviceEnv.unitName}"> selected="selected" </c:if> >${unitName.label}</option>
											   </c:forEach>
										   </select>
                                           </td>
                                           <td style='text-align: center;'>
									   	   <select  class="input-large errorLevelList" name="errorLevelEvn"  >
										       <c:forEach var="errorLevel" items="${errorLevelList}">
												    <option value="${errorLevel.value}" <c:if test="${errorLevel.value ==deviceEnv.errorLevel}"> selected="selected" </c:if> >${errorLevel.label}</option>
											   </c:forEach>
										   </select>
										   </td>
                                           <td style="text-align: center;">
                                           <a  class="fa fa-remove" href="javascript:void(0)" onclick="removeTr(this)" style="cursor:pointer;">
	                                           	<i class="icon-minus"></i>
	                                       </a>
                                           </td>
                                       </tr>
                                  </c:forEach>
                                  <tr id="addFromTr" class="warning">
                                      <td colspan="9" style="text-align: center;"> 
                                       	<a class="button button-primary button-rounded button-small" href=javascript:appendTr(addFromTr) style="cursor:pointer;">
                                       		<i class="icon-plus"></i>
                                       	</a>
                                      </td>
                                  </tr>
                                  </tbody>
                               </table>
                           </div>
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
						<input id="btnSubmit" class="btn btn-success" onclick="toUploadImg('${deviceInfo.deviceId}','false')" type="button" value="上传图片"/>
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
				<input id="btnSubmit"  class="btn btn-primary" onclick="return toSave()" type="button" value="保 存"/>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</div>
	</form:form>
	<script type="text/javascript">
	
		//格式验证
		function checkNum(e) {
			debugger;
			var length = $(e).val();
			if(length!=""){
				if(!isNaNCheck(length)){
					$(e).val("");
				};
			}
		}
		function isNaNCheck(ival){
			var r = /^\+?[1-9][0-9]*$/;
			if(r.test(ival)){
				return true;
			}else{
				toastr.warning("只能输入正整数！");
				return false;
			}
		}
		
		//上傳圖片
		function toUploadImg(deviceId,Tf){
			debugger;
			if(deviceId==""){
				deviceId = "xxxx";//防止传空值
			}
			top.$.jBox.open("iframe:${ctx}/monitor/monitorDevice/toImgUpload/"+deviceId,"图片上传",950,600,{
				buttons:{"关闭":true},
				submit:function(v,h,f){
					debugger;
					if(Tf){
						$.ajax({	
							type:"POST",
							url:"${ctx}/monitor/monitorDevice/findImgUrl",
							data:{deviceId:$("#deviceId").val()},
							dataType:"JSON",
							success:function(data){
								debugger;
								var imgDiv = $("#imgDiv .photo-group");
								imgDiv.empty();
								for(var i = 0 ;i<data.length;i++){
									imgDiv.append("<div><img src=${ctxOriginal}"+data[i]+"></div>")
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
	
		//移除一行
		function removeTr(removeSpan){
			debugger;
			if($(removeSpan).parent().parent().parent().children().length>2){
				$(removeSpan).parent().parent().remove();
			} else {
				toastr.error("至少配置一个运行参数！");
			}
		}
		
		//动态添加表格行数
		function appendTr(addFromTr){
			var trNode;
			trNode = $("<tr  class=\"success\">"+
						"<td style='text-align: center;'><input type=\"text\" name=\"envCode\" class=\"envCodeList\" style=\"text-align: center;width: 160px\" maxlength=\"30\"/></td>"+
						"<td style='text-align: center;'><input type=\"text\" name=\"envName\" class=\"envNameList\" style=\"text-align: center;width: 160px\" maxlength=\"15\"/></td>"+
						"<td style='text-align: center;'><input type=\"text\" name=\"envSvalue\" class=\"envSvalueList\" style=\"text-align: center;width: 160px\" maxlength=\"15\"/></td>"+
						"<td style='text-align: center;'><input type=\"text\" name=\"envEvalue\" class=\"envEvalueList\" style=\"text-align: center;width: 160px\" maxlength=\"15\"/></td>"+
						"<td style='text-align: center;'><input type=\"text\" name=\"envWsvalue\" class=\"envWsvalueList\" style=\"text-align: center;width: 160px\" maxlength=\"15\"/></td>"+
						"<td style='text-align: center;'><input type=\"text\" name=\"envWevalue\" class=\"envWevalueList\" style=\"text-align: center;width: 160px\" maxlength=\"15\"/></td>"+
                        "<td style='text-align: center;'><select  class=\"input-large unitNameList\" id=\"unitName\" name=\"unitName\" ><c:forEach var='unitName' items='${deviceUnitList}'><option value='${unitName.value}'>${unitName.label}</option></c:forEach></select></td>"+
                        "<td style='text-align: center;'><select  class=\"input-large errorLevelList\" name=\"errorLevelEvn\"><c:forEach var='errorLevel' items='${errorLevelList}'><option value='${errorLevel.value}'>${errorLevel.label}</option></c:forEach></select></td>"+
						"<td style='text-align: center;'><a class='fa fa-remove' href='javascript:void(0)' onclick='removeTr(this)' style='cursor:pointer;'><i class='icon-minus'></i></a></td>"+
						"</tr>");
			trNode.insertBefore($("#addFromTr"));
			$("select").select2();//初始化select，渲染
		}
		
		//表单提交
		var toSave = function(){
			var save = true;
			
			//设备不可重复
			/* var checkdeviceName = true;
			var deviceName = $("#monitorDeviceForm select[name='deviceName']").val();
			var deviceId = $("#monitorDeviceForm input[name='deviceId']").val();
			$.ajax({
				type : "POST",
				url : "${ctx}/monitor/monitorDevice/checkDeviceName/" + deviceName + "," + deviceId,
				async : false,
				success : function(ret) {
					if(ret == "0") {
						checkdeviceName = false;
					}
				}
			});
			if(!checkdeviceName) {
				toastr.error("设备已存在，请重新选择！");
				save = false;
			} */
			
			//主表信息验证
			var deviceLength = $("#deviceLength").val();
			var deviceWidth = $("#deviceWidth").val();
			var deviceHeight = $("#deviceHeight").val();
			var maintenance = $("#maintenance").val();
			if(deviceLength == "" || deviceLength == null||deviceWidth == "" || deviceWidth == null
					||deviceHeight == "" || deviceHeight == null||maintenance == "" || maintenance == null){
				toastr.error("请填写完整的设备信息");
				save = false;
			}
			//参数格式验证
			debugger;
			var envCodeList = $(".envCodeList");
			var envNameList = $(".envNameList");
			var envSvalueList = $(".envSvalueList");
			var envEvalueList = $(".envEvalueList");
			var envWsvalueList = $(".envWsvalueList");
			var envWevalueList = $(".envWevalueList");
			var unitNameList = $(".unitNameList");
			var errorLevelList = $(".errorLevelList");
			for(var i = 0; i < envCodeList.length; i++){
				for(var j = i; j < envCodeList.length; j++){
					if(envCodeList[i].value == "" || envCodeList[i].value == null||envNameList[i].value == "" || envNameList[i].value == null
							||envSvalueList[i].value == "" || envSvalueList[i].value == null||envEvalueList[i].value == "" || envEvalueList[i].value == null
							||envWsvalueList[i].value == "" || envWsvalueList[i].value == null||envWevalueList[i].value == "" || envWevalueList[i].value == null){
						toastr.error("请填写完整的参数信息");
						save = false;
					};
					if((envCodeList[i].value == envCodeList[j].value||envNameList[i].value == envNameList[j].value) && i != j){
						toastr.warning("参数代码或参数名称存在相同项！");
						save = false;
					} 
				}
			}
			if(save){
				$("#monitorDeviceForm").submit();
			} else {
				return;
			}
		} 
	</script>
</body>
</html>
