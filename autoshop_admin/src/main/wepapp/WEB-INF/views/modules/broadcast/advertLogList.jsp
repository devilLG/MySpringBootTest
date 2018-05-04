<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/broadcast/advertLog/">广告日志列表</a></li>
	</ul>
	<form:form id="advertLog" modelAttribute="advertLog" action="${ctx}/broadcast/advertLog/list" method="post" class="breadcrumb form-search">
		<input id="advertLogPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="advertLogPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站点信息：</label>
				<sys:tableselect id="site" cssStyle="width:150px;height:23px;" name="siteId" value="${param.siteId}" labelName="siteName" labelValue="${param.siteName}"
					 title="站点名称" url="/site/assite/tableData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>配置名称：</label>
				<input type="hidden" name="configId"/>
				<input type="text"  name="configName" id="configName" value="${param.configName}" readonly="readonly" />
				<i class="icon-search magnifying" onclick="showAdvertConfigInfo()"></i>
			</li>
			<li><label>操作时间：</label>
				<input  type="text" readonly="readonly" name="operTimeStart" id="cDOperTimeStart"  class="Wdate input-small"  
					onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'cDOperTimeEnd\') || \'%y-%M-%d\'}' ,dateStart:'%y-%M-%d',alwaysUseStartDate:true,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
					size="3" value="${param.operTimeStart}"/> - 
				<input  type="text" readonly="readonly" name="operTimeEnd" id="cDOperTimeEnd"  class="Wdate input-small"  
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'cDOperTimeStart\')}',maxDate:'%y-%M-%d',dateStart:'%y-%M-%d',alwaysUseStartDate:true,lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" 
					size="3" value="${param.operTimeEnd}"/>
			</li>
			<li class="btns">
				<input class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input class="btn btn-warning" type="button" value="重置" onclick="myResult('advertLog');"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message }" />
	<form id="advertLogList" method="post">
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>配置名称</th>
					<th>配置编号</th>
					<th>公司编号</th>
					<th>公司名称</th>
					<th>站点编号</th>
					<th>站点名称</th>
					<th>操作</th>
					<th>操作时间</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="advertLog">
					<tr id="${advertLog.logid}" >
						<td>${advertLog.configName}</td>
						<td>${advertLog.configId}</td>
						<td>${advertLog.corpId}</td>
						<td>${advertLog.corpName}</td>
						<td>${advertLog.siteId}</td>
						<td>${advertLog.siteName}</td>
						<td>${operActionMap[advertLog.operAction]}</td>
						<td>${advertLog.operTime}</td>
						<td>${advertLog.createTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</form>
	<script>
	//	选择配置
	var showAdvertConfigInfo = function() {
		 top.$.jBox.open("iframe:${ctx}/broadcast/advertConfig/showAdvertConfigInfo", "选择广告配置", 950, 600, {
			buttons:{"确认":"ok","关闭":true}, 
			submit:function(v, h, f){
				if (v=="ok"){
					debugger;
					var parms = h.find("iframe")[0].contentWindow.$("#advertConfig_parms").val();
					if(parms){
						$("#advertLog input[name='configId']").val(parms.split(",")[0]);
						$("#advertLog input[name='configName']").val(parms.split(",")[1]);
					}						
				}
			}, 
			loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
	};
	
	var page = function(n,s) {
		if(n) {
			$("#advertLogPageNo").val(n);
		}
		if(s) {
			$("#advertLogPageSize").val(s);
		}
		$("#advertLog").attr("action","${ctx}/broadcast/advertLog/list");
		$("#advertLog").submit();
		return false;
	};
	
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出广告日志数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#advertLog").attr("action","${ctx}/broadcast/advertLog/export");
					$("#advertLog").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	</script>
</body>
</html>



