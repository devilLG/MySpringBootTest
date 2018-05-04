<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#assiteForm").validate({
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
	     	 var tree_id = $("#treeName").val();
			 if(tree_id == ''){
					toastr.warning("请选择区域！");
					return false;
			}
			var cabinetStandard_name = $("#cabinetStandard_name").val();
			 if(cabinetStandard_name == ''){
					toastr.warning("请选择柜子配置！");
					return false;
			}
		   $("#assiteForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/site/assite/">站点列表</a></li>
		<li class="active"><a href="${ctx}/site/assite/form?site_id=${assite.site_id}">站点<shiro:hasPermission name="site:assite:view">${not empty assite.logid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="site:assite:view">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="assiteForm" modelAttribute="assite" action="${ctx}/site/assite/save" method="post" class="form-horizontal">
		<form:hidden path="site_id"/>
		<form:hidden path="logid" id="logid_assiteForm"/>
		<sys:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label">区域名称:</label>
			<div class="controls">
				<sys:treeselect id="tree" name="tree_id" value="${assite.tree_id}" labelName="tree_name" labelValue="${assite.tree_name}"
					title="区域" url="/sys/area/treeData" extId="${area.name}" cssClass="required" allowClear="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <c:if test="${user.corpId == 8888 || user.corpId == 1  && empty assite.site_id}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="owner_id" value="${assite.owner_id}" labelName="owner_name" labelValue="${assite.owner_name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		</c:if> --%>
		<c:if test="${empty assite.site_id}">
		     <c:if test="${user.corpId == 8888 || user.corpId == 1}">
		     <div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
                <sys:treeselect id="company" name="owner_id" value="${assite.owner_id}" labelName="owner_name" labelValue="${assite.owner_name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		</c:if>
		<c:if test="${not empty assite.site_id}">
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			
			<input type="text" value="${assite.owner_name}"  readonly="readonly" class="input-xlarge">
			</div>
		</div>
		</c:if>
		<%-- <c:if test="${not empty assite.site_id}">
		<form:hidden path="owner_id"/>
		<form:hidden path="owner_name"/>
		</c:if> --%>
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
				<form:input path="site_name" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点全称:</label>
			<div class="controls">
				<form:input path="fullname" htmlEscape="false" maxlength="80" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${empty assite.site_id}">
		<div class="control-group">
			<label class="control-label">柜子配置:</label>
                <div class="controls">
               <sys:tableselect id="cabinetStandard" cssStyle="width:210px;height:23px;" name="cabinetStandard_id" value="${assite.cabinetStandard_id}" labelName="cabinetStandard_name" labelValue="${assite.cabinetStandard_name}"
					title="柜子配置" url="/standard/cabinetStandard/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty assite.site_id}">
		<div class="control-group">
			<label class="control-label">柜子配置:</label>
			<div class="controls">
				<form:hidden path="cabinet_type"/>
				<form:input path="cabinet_type_name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">经度:</label>
			<div class="controls">
				<form:input path="latitude" htmlEscape="false" maxlength="15" class="required number"/>
				<span class="help-inline"><font color="red">*</font> 按百度经度录入，样例：东经114.06录入为114.06，西经23.45录入为-23.45</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">纬度:</label>
			<div class="controls">
				<form:input path="longitude" htmlEscape="false" maxlength="15" class="required number"/>
				<span class="help-inline"><font color="red">*</font> 按百度纬度录入，样例：北纬22.63录入为22.63，南纬49.52录入为-49.52</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址:</label>
			<div class="controls">
				<form:textarea path="addr" htmlEscape="false" rows="2" cssStyle="width:275px;height:70px;"  maxlength="100"  class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">特征信息:</label>
			<div class="controls">
			<form:textarea path="searcher" htmlEscape="false" rows="2" cssStyle="width:275px;height:70px;"  maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">警报内容:</label>
			<div class="controls">
			<form:textarea path="warn_cont" htmlEscape="false" rows="2" cssStyle="width:275px;height:70px;"  maxlength="100"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">服务状态:</label>
			<div class="controls">
				<form:select path="service_type" class="input-medium">
					<form:options items="${fns:getDictList('service_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺货箱格数临界值:</label>
			<div class="controls">
				<form:input path="boxlack_num" htmlEscape="false" maxlength="11" class="required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品少于百分比临界值:</label>
			<div class="controls">
				<form:input path="prolack_num" htmlEscape="false"  maxlength="11" class="required number"/>(%)
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	
		<div class="control-group">
			<label class="control-label">标准最低温度:</label>
			<div class="controls">
				<form:input path="config_stemperature" htmlEscape="false" maxlength="8" class="required number"/>(℃)
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准最高温度:</label>
			<div class="controls">
				<form:input path="config_etemperature" htmlEscape="false" maxlength="8" class="required number"/>(℃)
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="site:assite:view"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="validCheckForm()"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>