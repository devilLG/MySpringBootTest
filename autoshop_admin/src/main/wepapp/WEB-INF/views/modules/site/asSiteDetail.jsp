<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站点管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/site/assite/">站点列表</a></li>
		<li class="active"><a href="${ctx}/site/assite/detail?site_id=${assite.site_id}">详情【${assite.site_id}】</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="assite" action="${ctx}/site/assite/detail" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">区域名称:</label>
			<div class="controls">
				<form:input path="tree_name" htmlEscape="false" maxlength="40" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
			<form:input path="owner_name" htmlEscape="false" maxlength="40" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点编号:</label>
			<div class="controls">
				<form:input path="site_id" htmlEscape="false" maxlength="40" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点简称:</label>
			<div class="controls">
				<form:textarea path="site_name" htmlEscape="false" rows="2" cssStyle="width:275px;height:50px;"  maxlength="100"  class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点全称:</label>
			<div class="controls">
				<form:textarea path="fullname" htmlEscape="false" rows="2" cssStyle="width:275px;height:50px;"  maxlength="100"  class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">柜子配置:</label>
			<div class="controls">
				<form:hidden path="cabinet_type"/>
				<form:input path="cabinet_type_name" htmlEscape="false" maxlength="50" class="required input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经度:</label>
			<div class="controls">
				<form:input path="latitude" htmlEscape="false" maxlength="15" class="required number" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">纬度:</label>
			<div class="controls">
				<form:input path="longitude" htmlEscape="false" maxlength="15" class="required number" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址:</label>
			<div class="controls">
				<form:textarea path="addr" htmlEscape="false" rows="2" cssStyle="width:275px;height:70px;"  maxlength="100"  class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">特征信息:</label>
			<div class="controls">
			<form:textarea path="searcher" htmlEscape="false" rows="2" cssStyle="width:275px;height:70px;"  maxlength="100" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="site_state" class="input-medium" disabled="true">
					<form:options items="${fns:getDictList('site_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务状态:</label>
			<div class="controls">
				<form:select path="service_type" class="input-medium" disabled="true">
					<form:options items="${fns:getDictList('service_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态时间:</label>
			<div class="controls">
			<form:input path="state_time" htmlEscape="false" maxlength="40" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否需要补货:</label>
			<div class="controls">
				<form:select path="is_replenishment" class="input-medium" disabled="true">
					<form:options items="${fns:getDictList('is_replenishment')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺货箱格数临界值:</label>
			<div class="controls">
				<form:input path="boxlack_num" htmlEscape="false" maxlength="11" class="required number" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品少于百分比临界值:</label>
			<div class="controls">
				<form:input path="prolack_num" htmlEscape="false"  maxlength="11" class="required number" readonly="true"/>(%)
			</div>
		</div>
	
		<div class="control-group">
			<label class="control-label">标准最低温度:</label>
			<div class="controls">
				<form:input path="config_stemperature" htmlEscape="false" maxlength="8" class="required number" readonly="true"/>(℃)
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准最高温度:</label>
			<div class="controls">
				<form:input path="config_etemperature" htmlEscape="false" maxlength="8" class="required number" readonly="true"/>(℃)
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">警报类型:</label>
			<div class="controls">
				<form:select path="warn_type" class="input-medium" disabled="true">
					<form:options items="${fns:getDictList('warn_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">警报内容:</label>
			<div class="controls">
			<form:textarea path="warn_cont" htmlEscape="false" rows="2" cssStyle="width:275px;height:70px;"  maxlength="100" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">警报时间:</label>
			<div class="controls">
				<form:input path="warn_time" htmlEscape="false" maxlength="20" class="required number" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
				<form:input path="create_time" htmlEscape="false" maxlength="20" class="required number" readonly="true"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>