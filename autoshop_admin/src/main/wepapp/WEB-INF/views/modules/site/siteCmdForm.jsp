<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点控制管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#siteCmdForm").validate({
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
		
		function selectSiteCmdObjInfo(){
		debugger;
			var site_id = $("input[name='site_id']").val();
		    if(site_id == ''){
				toastr.warning("请选择站点");
				return false;
			}
       		var cmd_obj = $("#cmd_obj_siteCmdForm").val();
       		if(cmd_obj == '02'){
			   top.$.jBox.open("iframe:${ctx}/site/siteGoods/showSiteGoodsInfo?site_id="+site_id, "选择货道信息", 950, 600, {
				buttons:{"确认":"ok","关闭":true}, 
				submit:function(v, h, f){
					debugger;
					if (v=="ok"){
						var parms = h.find("iframe")[0].contentWindow.$("#siteGoods_parms").val();
						if(parms){
							$("#siteCmdForm input[name='cmd_val']").val(parms.split(",")[0]);
						}
					}
				}, 
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
       		}else if(cmd_obj == '03'){
			   top.$.jBox.open("iframe:${ctx}/inventory/inventoryGoods/showInventoryGoodsInfo?site_id="+site_id, "选择商品信息", 950, 600, {
				buttons:{"确认":"ok","关闭":true}, 
				submit:function(v, h, f){
					debugger;
					if (v=="ok"){
						var parms = h.find("iframe")[0].contentWindow.$("#inventoryGoods_parms").val();
						if(parms){
							$("#siteCmdForm input[name='cmd_val']").val(parms.split(",")[1]);
						}
					}
				}, 
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
       		}
		}
		 $(function(){  
         	var cmd_obj = $("#cmd_obj_siteCmdForm").val();
         	changeByCmdObj(cmd_obj);
         }); 
		
		function cmdObjChange(){
		debugger;
			var cmd_obj = $("#cmd_obj_siteCmdForm").val();
			$("#siteCmdForm input[name='cmd_val']").val('');
			if(cmd_obj == '01'){
				document.getElementById("cmdVal_controlGroup").style.display= "none";
			}else{
				document.getElementById("cmdVal_controlGroup").style.display= "block";
			}
			changeByCmdObj(cmd_obj);
		}
		
		function changeByCmdObj(cmd_obj){
			if(cmd_obj == '01'){
			  	$("#cmd_type_siteCmdForm").children("span").children('option[value="0101"]').unwrap();  
			  	$("#cmd_type_siteCmdForm").children("span").children('option[value="0102"]').unwrap();  
				$("#cmd_type_siteCmdForm").children('option[value="0201"]').wrap('<span>').hide(); 
				$("#cmd_type_siteCmdForm").children('option[value="0202"]').wrap('<span>').hide(); 
				$("#cmd_type_siteCmdForm").children('option[value="0301"]').wrap('<span>').hide(); 
				$("#cmd_type_siteCmdForm").children('option[value="0302"]').wrap('<span>').hide(); 
			}else if(cmd_obj == '02'){
				$("#cmd_type_siteCmdForm").children('option[value="0101"]').wrap('<span>').hide(); 
				$("#cmd_type_siteCmdForm").children('option[value="0102"]').wrap('<span>').hide(); 
				$("#cmd_type_siteCmdForm").children("span").children('option[value="0201"]').unwrap();  
			  	$("#cmd_type_siteCmdForm").children("span").children('option[value="0202"]').unwrap();  
				$("#cmd_type_siteCmdForm").children('option[value="0301"]').wrap('<span>').hide(); 
				$("#cmd_type_siteCmdForm").children('option[value="0302"]').wrap('<span>').hide(); 
			}else if(cmd_obj == '03'){
				$("#cmd_type_siteCmdForm").children('option[value="0101"]').wrap('<span>').hide(); 
				$("#cmd_type_siteCmdForm").children('option[value="0102"]').wrap('<span>').hide(); 
				$("#cmd_type_siteCmdForm").children('option[value="0201"]').wrap('<span>').hide(); 
				$("#cmd_type_siteCmdForm").children('option[value="0202"]').wrap('<span>').hide(); 
				$("#cmd_type_siteCmdForm").children("span").children('option[value="0301"]').unwrap();  
			  	$("#cmd_type_siteCmdForm").children("span").children('option[value="0302"]').unwrap();  
			}
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/site/siteCmd/">站点控制列表</a></li>
		<li class="active"><a href="${ctx}/site/siteCmd/form?cmd_id=${siteCmd.cmd_id}">站点控制<shiro:hasPermission name="site:assite:view">${not empty siteCmd.cmd_id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="siteCmdForm" modelAttribute="siteCmd" action="${ctx}/site/siteCmd/save" method="post" class="form-horizontal">
		<form:hidden path="cmd_id"/>
		<sys:message content="${message}"/>
		<c:if test="${empty siteCmd.logid}">
		<div class="control-group">
			<label class="control-label">站点简称:</label>
                <div class="controls">
               <sys:tableselect id="site" cssStyle="width:210px;height:23px;" name="site_id" value="${siteCmd.site_id}" labelName="site_name" labelValue="${siteCmd.site_name}"
					title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty siteCmd.logid}">
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
			<form:hidden path="site_id"/>
			<form:input path="site_name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<c:if test="${empty siteCmd.logid}">
		<div class="control-group">
			<label class="control-label">控制对象:</label>
			<div class="controls">
				<form:select path="cmd_obj" class="input-medium" id="cmd_obj_siteCmdForm" onchange="cmdObjChange()">
					<form:options items="${fns:getDictList('cmd_obj')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty siteCmd.logid}">
		<div class="control-group">
			<label class="control-label">控制对象:</label>
			<div class="controls">
				<form:select path="cmd_obj" class="input-medium required" id="cmd_obj_siteCmdForm" disabled="true">
					<form:options items="${fns:getDictList('cmd_obj')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		</c:if>
		<c:if test="${empty siteCmd.logid}">
		<div class="control-group" style="display:none" id="cmdVal_controlGroup">
			<label class="control-label">控制对象编号:</label>
			<div class="controls">
				<form:input path="cmd_val" htmlEscape="false" maxlength="30" class="input-xlarge required" readonly="true"/>
				<i class="icon-search magnifying" onclick="selectSiteCmdObjInfo()"></i>
			</div>
		</div>
		</c:if>
		<c:if test="${siteCmd.cmd_obj != '01' && not empty siteCmd.logid}">
		<div class="control-group" id="cmdVal_controlGroup">
			<label class="control-label">控制对象编号:</label>
			<div class="controls">
				<form:input path="cmd_val" htmlEscape="false" maxlength="30" class="input-xlarge required" readonly="true"/>
				<i class="icon-search magnifying" onclick="selectSiteCmdObjInfo()"></i>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">控制类型:</label>
			<div class="controls">
				<form:select path="cmd_type" class="input-medium required" id="cmd_type_siteCmdForm" >
				<c:if test="${empty siteCmd.logid}">
					<form:option value="" label="请选择"/>
				</c:if>
					<form:options items="${fns:getDictList('cmd_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="site:assite:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>