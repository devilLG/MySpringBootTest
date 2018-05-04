<%@ page contentType="text/html;charset=UTF-8" 
import="com.zhilai.master.common.config.Global"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备信息</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#siteDeviceConfigForm .span6{
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
		<li><a href="${ctx}/monitor/siteDeviceConfig/">站点设备列表</a></li>
		<li class="active"><a href="${ctx}/monitor/siteDeviceConfig/form?sdeviceId=${siteDeviceConfig.sdeviceId}">站点设备详情</a></li>
	</ul><br/>
	<form:form id="siteDeviceConfigForm" modelAttribute="siteDeviceConfig" action="${ctx}/monitor/siteDeviceConfig/save" method="post" class="form-horizontal">
		<input name="logid" type="hidden" value="${siteDeviceConfig.logid}">
		<input name="sdeviceId" id="sdeviceId" type="hidden" value="${siteDeviceConfig.sdeviceId}">
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="span6">
				<div class="control-group">
					<label class="control-label">站点编号:</label>
					<div class="controls">
						<form:input path="siteId" htmlEscape="false" readonly="true"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">设备名称:</label>
					<div class="controls">
						<form:select path="deviceName" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('device_info')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">设备型号:</label>
					<div class="controls">
						<form:select path="deviceModel" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('device_model')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group" id="dm">
					<label class="control-label">供应商:</label>
					<div class="controls">
						<form:select path="deviceMfrs" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('device_mfrs')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">设备状态:</label>
					<div class="controls">
						<form:select path="errorLevel" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('siteDevice_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">状态描述:</label>
					<div class="controls">
						<form:input path="cont" htmlEscape="false" readonly="true"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">上次检测日期:</label>
					<div class="controls">
						<form:input path="pcheckTime" htmlEscape="false" readonly="true"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">检测状态:</label>
					<div class="controls">
						<form:select path="deviceBard" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('siteDeviceCheck_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<label class="control-label">站点名称:</label>
					<div class="controls">
						<form:input path="siteName" htmlEscape="false" readonly="true"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">设备品牌:</label>
					<div class="controls">
						<form:select path="deviceBard" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('device_bard')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">故障等级:</label>
					<div class="controls">
						<form:select path="deviceBard" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('error_level_info')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">状态时间:</label>
					<div class="controls">
						<form:input path="stateTime" htmlEscape="false" readonly="true"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">维护周期:</label>
					<div class="controls">
						<form:input path="maintenance" htmlEscape="false" readonly="true"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">下次检测日期:</label>
					<div class="controls">
						<form:input path="lcheckTime" htmlEscape="false" readonly="true"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">创建时间:</label>
					<div class="controls">
						<form:input path="createTime" htmlEscape="false" readonly="true"/>
					</div>
				</div>
			</div>	
		 	<div class="col-lg-10 col-md-12 col-sm-12 col-xs-12 span12" style="margin-top: 2%;">
             	<div class="portlet box blue">
                      <div class="portlet-title">
                          <div class="caption">
                              <i class="fa fa-comments"></i><h3>设备运行参数信息</h3>
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
                                          <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 实际值</th>
                                          <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 参数状态</th>
                                          <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 状态时间</th>
                                          <th style="height: 200%; text-align: center; font-size: 16px; width: 300px"> 创建时间 </th>
                                      </tr>
                                  </thead>
                                 <tbody id="objTbody">
                                 
                                 <c:if test="${empty siteDeviceConfig.siteEnv}">
                                 	<tr class="success" id="objClone">
                                         <td style='text-align: center;'><input type="text" style="width: 80px" maxlength="30" readonly="readonly"/></td>
                                         <td style='text-align: center;'><input type="text" style="width: 80px" maxlength="15" readonly="readonly"/></td>
                                         <td style='text-align: center;'><input type="text" style="width: 100px" maxlength="15" readonly="readonly"/></td>
                                         <td style='text-align: center;'><input type="text" style="width: 100px" maxlength="15" readonly="readonly"/></td>
                                         <td style='text-align: center;'><input type="text" style="width: 120px" maxlength="15" readonly="readonly"/></td>
                                         <td style='text-align: center;'><input type="text" style="width: 120px" maxlength="15" readonly="readonly"/></td>
                                         <td style='text-align: center;'><input type="text" style="width: 120px" maxlength="15" readonly="readonly"/></td>
                                         <td style='text-align: center;'><input type="text" style="width: 80px" maxlength="15" readonly="readonly"/></td>
                                         <td style='text-align: center;'><input type="text" style="width: 120px" maxlength="15" readonly="readonly"/></td>
                                         <td style='text-align: center;'><input type="text" style="width: 120px" maxlength="15" readonly="readonly"/></td>
                                         <td style='text-align: center;'><input type="text" style="width: 120px" maxlength="15" readonly="readonly"/></td>
                                 	</tr>
                                 </c:if> 
                                 <c:forEach items="${siteDeviceConfig.siteEnv}" var="siteEnv">
                                      <tr class="success">
                                          <td style='text-align: center;'><input type="text" name="envCode" class="envCodeList"  value="${siteEnv.envCode}" style="text-align: center;width: 80px" readonly="readonly"/></td>
                                          <td style='text-align: center;'><input type="text" name="envName" class="envNameList" value="${siteEnv.envName}" style="text-align: center;width: 80px"  readonly="readonly"/></td>
                                          <td style='text-align: center;'><input type="text" name="envSvalue" class="envSvalueList"  value="${siteEnv.envSvalue}" style="text-align: center;width: 100px" readonly="readonly"/></td>
                                          <td style='text-align: center;'><input type="text" name="envEvalue" class="envEvalueList" value="${siteEnv.envEvalue}" style="text-align: center;width: 100px" readonly="readonly"/></td>
                                          <td style='text-align: center;'><input type="text" name="envWsvalue" class="envWsvalueList"  value="${siteEnv.envWsvalue}" style="text-align: center;width: 120px" readonly="readonly"/></td>
                                          <td style='text-align: center;'><input type="text" name="envWevalue" class="envWevalueList" value="${siteEnv.envWevalue}" style="text-align: center;width: 120px"readonly="readonly"/></td>
                                          <td style='text-align: center;'>
	                                          <select  class="input-large unitNameList" id="unitName" name="unitName" disabled="true">
											      <c:forEach var="unitName" items="${deviceUnitList}">
												  	<option value="${unitName.value}" <c:if test="${unitName.value ==siteEnv.unitName}"> selected="selected" </c:if> >${unitName.label}</option>
												  </c:forEach>
										  	  </select>
                                          </td>
                                          <td style='text-align: center;'><input type="text" name="runValue" class="runValueList" value="${siteEnv.runValue}" style="text-align: center;width: 80px" readonly="readonly"/></td>
                                          <td style='text-align: center;'>
										   	  <select  class="input-large curStateList" name="curState"  disabled="true">
												  <c:forEach var="curState" items="${siteDeviceCheckStateList}">
													  <option value="${curState.value}" <c:if test="${curState.value ==siteEnv.curState}"> selected="selected" </c:if> >${curState.label}</option>
												  </c:forEach>
											  </select>
									   	  </td>
									   	  <td style='text-align: center;'><input type="text" name="stateTime" class="stateTimeList" value="${siteEnv.stateTime}" style="text-align: center;width: 120px" readonly="readonly"/></td>
									   	  <td style='text-align: center;'><input type="text" name="createTime" class="createTimeList" value="${siteEnv.createTime}" style="text-align: center;width: 120px" readonly="readonly"/></td>
                                      </tr>
                                 </c:forEach>
                                 </tbody>
                              </table>
                          </div>
                      </div>
                  </div>
            </div>		
		</div>
		<div class="form-actions">
			<div style="margin-left:360px">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</div>
	</form:form>
</body>
</html>
