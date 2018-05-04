

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#officeForm").validate({
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
		
		function typeChange(){
		debugger;
			var type = $("#type_officeForm").val();
			$("#officeForm input[name='authId']").val('');
			$("#officeForm input[name='authName']").val('');
			$("#officeForm input[name='authBegin']").val('');
			$("#officeForm input[name='authEnd']").val('');
			if(type == '1'){
				document.getElementById("authId_controlGroup").style.display= "block";
				document.getElementById("authName_controlGroup").style.display= "block";
				document.getElementById("authBegin_controlGroup").style.display= "block";
				document.getElementById("authEnd_controlGroup").style.display= "block";
			}else{
				document.getElementById("authId_controlGroup").style.display= "none";
				document.getElementById("authName_controlGroup").style.display= "none";
				document.getElementById("authBegin_controlGroup").style.display= "none";
				document.getElementById("authEnd_controlGroup").style.display= "none";
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/office/list?id=${office.parent.logid}&parentIds=${office.parentIds}">机构列表</a></li>
		<li class="active"><a href="${ctx}/sys/office/form?id=${user.logid}&parent.logid=${office.parent.logid}">机构<shiro:hasPermission name="sys:office:edit">${not empty office.logid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:office:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="officeForm" modelAttribute="office" action="${ctx}/sys/office/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">上级机构:</label>
			<div class="controls">
                <sys:treeselect id="office" name="parent.logid" value="${office.parent.logid}" labelName="parent.name" labelValue="${office.parent.name}"
					title="机构" url="/sys/office/treeData" extId="${user.logid}" cssClass="required" allowClear="${office.currentUser.admin}"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属区域:</label>
			<div class="controls">
                <sys:treeselect id="area" name="area.logid" value="${office.area.logid}" labelName="area.name" labelValue="${office.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构名称:</label>
			<div class="controls">
				<form:hidden path="corpId"/>
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构编码:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构类型:</label>
			<div class="controls">
				<form:select path="type" class="input-medium" id="type_officeForm" onchange="typeChange()">
					<form:options items="${fns:getDictList('sys_office_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构级别:</label>
			<div class="controls">
				<form:select path="grade" class="input-medium">
					<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<c:if test="${empty office.logid}">
		<div class="control-group" id="authId_controlGroup" style="display:none">
			<label class="control-label">授权编号:</label>
			<div class="controls">
				<form:input path="authId" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" id="authName_controlGroup" style="display:none">
			<label class="control-label">授权名称:</label>
			<div class="controls">
				<form:input path="authName" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" id="authBegin_controlGroup" style="display:none">
			<label class="control-label">授权开始日期:</label>
			<div class="controls">
				<input name="authBegin" id="auth_begin_officeForm"  value="${office.authBegin }" type="text" class="Wdate required" readonly="readonly" style="height:15px;width:145px" 
                          onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'auth_end_officeForm\')}'})" />
			</div>
		</div>
		<div class="control-group" id="authEnd_controlGroup" style="display:none">
			<label class="control-label">授权结束日期:</label>
			<div class="controls">
				<input name="authEnd" type="text" id="auth_end_officeForm" value="${office.authEnd }" class="Wdate required" readonly="readonly" style="height:15px;width:145px"
                          onFocus="WdatePicker({minDate:'#F{$dp.$D(\'auth_begin_officeForm\')}'})"  />
			</div>
		</div>
		</c:if>
		<c:if test="${office.type == '1' && not empty office.logid}">
		<div class="control-group" id="authId_controlGroup">
			<label class="control-label">授权编号:</label>
			<div class="controls">
				<form:input path="authId" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" id="authName_controlGroup">
			<label class="control-label">授权名称:</label>
			<div class="controls">
				<form:input path="authName" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" id="authBegin_controlGroup">
			<label class="control-label">授权开始日期:</label>
			<div class="controls">
				<input name="authBegin" id="auth_begin_officeForm"  value="${office.authBegin }" type="text" class="Wdate required" readonly="readonly" style="height:15px;width:145px" 
                          onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'auth_end_officeForm\')}'})" />
			</div>
		</div>
		<div class="control-group" id="authEnd_controlGroup">
			<label class="control-label">授权结束日期:</label>
			<div class="controls">
				<input name="authEnd" type="text" id="auth_end_officeForm" value="${office.authEnd }" class="Wdate required" readonly="readonly" style="height:15px;width:145px"
                          onFocus="WdatePicker({minDate:'#F{$dp.$D(\'auth_begin_officeForm\')}'})"  />
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">是否可用:</label>
			<div class="controls">
				<form:select path="useable">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主负责人:</label>
			<div class="controls">
				 <sys:treeselect id="primaryPerson" name="primaryPerson.name" value="${office.primaryPerson.name}" labelName="primaryPerson.logid" labelValue="${office.primaryPerson.logid}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">副负责人:</label>
			<div class="controls">
				 <sys:treeselect id="deputyPerson" name="deputyPerson.name" value="${office.deputyPerson.name}" labelName="deputyPerson.logid" labelValue="${office.deputyPerson.logid}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系地址:</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮政编码:</label>
			<div class="controls">
				<form:input path="zipCode" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">负责人:</label>
			<div class="controls">
				<form:input path="master" htmlEscape="false" maxlength="50"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传真:</label>
			<div class="controls">
				<form:input path="fax" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<c:if test="${empty user.logid}">
			<div class="control-group">
				<label class="control-label">快速添加下级部门:</label>
				<div class="controls">
					<form:checkboxes path="childDeptList" items="${fns:getDictList('sys_office_common')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="sys:office:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>