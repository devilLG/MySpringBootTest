<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知模板配置</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#corpNoticeTemplateForm .span6{
			width:46%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/notice/corpNoticeTemplate/">通知模板配置列表</a></li>
		<li class="active"><a href="${ctx}/notice/corpNoticeTemplate/form?logid=${corpNoticeTemplate.logid}">通知模板配置${not empty corpNoticeTemplate.logid?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="corpNoticeTemplateForm" modelAttribute="corpNoticeTemplate" action="${ctx}/notice/corpNoticeTemplate/save" method="post" class="form-horizontal">
		<form:hidden path="logid"/>
		<form:hidden path="corptemp_id"/>
		<form:hidden path="temp_id"/>
		<sys:message content="${message}"/>
		<div class="tab-content">
			<div class="span6">
				<div class="control-group">
					<label class="control-label">模板名称:</label>
					<div class="controls">
						<form:input path="temp_name" htmlEscape="false" maxlength="60" class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">模板大类:</label>
					<div class="controls">
						<form:select path="main_type" class="input-large" onchange="typeChange(this)">
							<form:options items="${fns:getDictList('mainType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">通知类型:</label>
					<div class="controls">
						<form:select path="notice_type" class="input-large">
							<form:options items="${fns:getDictList('noticeType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">发送最大数:</label>
					<div class="controls">
						<form:input path="send_num" htmlEscape="false" maxlength="11" class="required number" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">模板内容:</label>
					<div class="controls">
						<form:textarea path="content" htmlEscape="false" rows="3" maxlength="500" class="input-xxlarge"/>
						<button type="button" class="btn" onclick="showTemplate()">选择字段</button>
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<label class="control-label">角色名称：</label>
					<div class="controls">
						<form:select path="role_id" class="input-large" >
							<form:options items="${fns:getDictList('noticeType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<c:if test="${corpNoticeTemplate.main_type eq '01'}">
					<div  class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large">
								<form:options items="${fns:getDictList('order_apply_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${corpNoticeTemplate.main_type eq '02'}">
					<div  class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large">
								<form:options items="${fns:getDictList('site_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${corpNoticeTemplate.main_type eq '03'}">
					<div  class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large">
								<form:options items="${fns:getDictList('workOper_action')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${corpNoticeTemplate.main_type eq '04'}">
					<div  class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large">
								<form:options items="${fns:getDictList('none')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${corpNoticeTemplate.main_type eq '05'}">
					<div  class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large">
								<form:options items="${fns:getDictList('replenishment_cur_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${corpNoticeTemplate.main_type eq '06'}">
					<div  class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv"  class="controls">
							<form:select path="sub_type" class="input-large">
								<form:options items="${fns:getDictList('barter_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${corpNoticeTemplate.main_type eq '07'}">
					<div class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv"  class="controls">
							<form:select path="sub_type" class="input-large">
								<form:options items="${fns:getDictList('check_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${corpNoticeTemplate.main_type eq '08'}">
					<div  class="control-group">
						<label class="control-label">模板小类:</label>
						<div id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large">
								<form:options items="${fns:getDictList('none')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<c:if test="${corpNoticeTemplate.main_type eq '09'}">
					<div class="control-group">
						<label class="control-label">模板小类:</label>
						<div  id="subTypeDiv" class="controls">
							<form:select path="sub_type" class="input-large">
								<form:options items="${fns:getDictList('payment_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</c:if>
				<div class="control-group">
					<label class="control-label">间隔时间:</label>
					<div class="controls">
						<form:input path="Interval_time" htmlEscape="false" maxlength="3" class="required number" />(H)
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">发送等级:</label>
					<div class="controls">
						<form:select path="level" class="input-large">
							<form:options items="${fns:getDictList('notice_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">描述:</label>
					<div class="controls">
						<form:textarea path="description" htmlEscape="false" rows="3" maxlength="450" class="input-xxlarge"/>
						
					</div>
				</div>
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

//根据模板大类选择模板小类
function typeChange (e) {
	debugger;
	var type = $(e).val();
	$.ajax({
		type:"POST",
		url:"${ctx}/notice/corpNoticeTemplate/typeChange",
		data:{'type':type},
		async: false,
		dataType: "json",
		success: function(data){debugger;
	    	$("#subTypeDiv").empty();
	    	var html = "<select name='sub_type' class='input-large'>";
	    	for(var i=0; i<data.length; ++i){
	    		html = html + "<option value='"+data[i].value+"' >"+data[i].label+"</option>";
	    	}
	    	html = html +"</select><br/>";
	    	$("#subTypeDiv").append(html);
	    	$("select").select2();//初始化select，渲染
	  	}
	});
}

$(document).ready(function() {
	$("#corpNoticeTemplateForm").validate({
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

//选择字段
var showTemplate = function() {
	 top.$.jBox.open("iframe:${ctx}/notice/noticeField/showTemplate", "选择字段", 500, 300, {
		buttons:{"确认":"ok","关闭":true}, 
		submit:function(v, h, f){
			if (v=="ok"){
				debugger;
				var parms = h.find("iframe")[0].contentWindow.$("#noticeField").val();
				var tem = $("#corpNoticeTemplateForm textarea[name='content']").val();
				if(parms){
					tem = tem+"\${"+parms.split(",")[0]+"}";
					$("#corpNoticeTemplateForm textarea[name='content']").val(tem);
				}						
			}
		}, 
		loaded:function(h){
			$(".jbox-content", top.document).css("overflow-y","hidden");
		}
	});
};
</script>
</body>
</html>