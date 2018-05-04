<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公共通知模板</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#noticeQueueForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/notice/noticeQueue/">公共通知模板列表</a></li>
		<li class="active"><a href="${ctx}/notice/noticeQueue/form?queueId=${noticeQueue.queue_id}">公共通知模板详情</a></li>
	</ul><br/>
	<form:form id="noticeQueueForm" modelAttribute="noticeQueue" action="${ctx}/notice/noticeQueue/save" method="post" class="form-horizontal">
		<form:hidden path="queue_id"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="span6">
				<div class="control-group">
					<label class="control-label">业务单号:</label>
					<div class="controls">
						<form:input path="queue_id" htmlEscape="false"  readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">业务大类:</label>
					<div class="controls">
						<form:select path="main_type" class="input-large"  disabled="true">
							<form:options items="${fns:getDictList('mainType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">通知渠道:</label>
					<div class="controls">
						<form:select path="notice_channel" class="input-large"  disabled="true">
							<form:options items="${fns:getDictList('notice_channel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">通知人员编号:</label>
					<div class="controls">
						<form:input path="login_id" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">发送最大次数:</label>
					<div class="controls">
						<form:input path="send_num" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">发送等级:</label>
					<div class="controls">
						<form:select path="level" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('notice_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">状态时间:</label>
					<div class="controls">
						<form:input path="state_time" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">创建时间:</label>
					<div class="controls">
						<form:input path="create_time" htmlEscape="false" readonly="true" />
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<label class="control-label">模板名称:</label>
					<div class="controls">
						<form:input path="temp_name" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<c:if test="${noticeQueue.main_type eq '01'}">
					<div  class="control-group">
						<label class="control-label">业务小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('order_apply_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${noticeQueue.main_type eq '02'}">
					<div  class="control-group">
						<label class="control-label">业务小类:</label>
						<div id="subTypeDiv" class="controls" disabled="true">
							<form:select path="sub_type" class="input-large">
								<form:options items="${fns:getDictList('site_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${noticeQueue.main_type eq '03'}">
					<div  class="control-group">
						<label class="control-label">业务小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('workOper_action')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${noticeQueue.main_type eq '04'}">
					<div  class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('none')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${noticeQueue.main_type eq '05'}">
					<div  class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('replenishment_cur_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${noticeQueue.main_type eq '06'}">
					<div  class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv"  class="controls">
							<form:select path="sub_type" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('barter_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${noticeQueue.main_type eq '07'}">
					<div class="control-group">
						<label class="control-label">业务小类:</label>
						<div id="subTypeDiv"  class="controls">
							<form:select path="sub_type" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('check_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${noticeQueue.main_type eq '08'}">
					<div  class="control-group">
						<label class="control-label">业务小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('none')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${noticeQueue.main_type eq '09'}">
					<div class="control-group">
						<label class="control-label">业务小类:</label>
						<div  id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large" disabled="true">
								<form:options items="${fns:getDictList('payment_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<div class="control-group">
					<label class="control-label">通知地址:</label>
					<div class="controls">
						<form:input path="notice_address" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">通知人员姓名:</label>
					<div class="controls">
						<form:input path="login_name" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">发送失败次数:</label>
					<div class="controls">
						<form:input path="fail_num" htmlEscape="false" readonly="true" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">发送状态:</label>
					<div class="controls">
						<form:select path="send_state" class="input-large" disabled="true">
							<form:options items="${fns:getDictList('send_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">通知内容:</label>
					<div class="controls">
						<form:textarea path="cont" htmlEscape="false" rows="3" class="input-xxlarge" disabled="true"/>
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