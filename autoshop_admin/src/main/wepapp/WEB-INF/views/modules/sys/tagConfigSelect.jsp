<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据选择</title>
	<meta name="decorator" content="blank"/>
</head>
<body >
		<select name="groupId" id="groupId" style="margin-left: 10px;margin-top: 10px;width: 200px;" id="groupId">
		<!-- <option value="">所有组</option> -->
		<c:forEach items="${page.list}" var="grouplist">
				<option value="${grouplist.grpId}">${grouplist.grpName}</option>
		</c:forEach>
		</select>
		<div style="margin-top: 20px;margin-left: 10px;">
		<textarea type="radio"  name="description" id="description" style="width: 95%;height: 100px;" value="01" placeholder="请输入参数配置文本"></textarea>
		<div style="margin-top: 20px;">
		<input type="radio" class="restart" name="restart" value="00">现在备份
		<input type="radio" class="restart"  name="restart" style="margin-left: 10%;" value="01">定时备份
		</div>
		<div  style="margin-top: 20px;margin-left: 10px;">开始时间:&nbsp;<input type="text" id="reStartTime" name="reStartTime" readonly="readonly" value="${proportion.startTime}" maxlength="30" class="Wdate input-sm" style="height:15px;width:140px;margin-top: 8px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></div>
		</div>
		
		<input type="button" style="margin-left: 10px;margin-top: 20px;" id="subSave" value="<sp:message code='save'/>"/>
		<input type="button"  style="margin-top: 20px;margin-left: 50px;"  value="<sp:message code='back'/>"  onclick="back()">
			<i class="fa fa-sign-in"></i> 
		</input>
		
<script type="text/javascript">
/* window.location.href="${ctx}/sys/taskAssignment/siteUpgrade"; */
	$(document).ready(function(){
			var url="${ctx}${url}${fn:indexOf(url,'?')==-1?'?':'&'}t="+ new Date().getTime();
			url="${ctx}/sys/taskAssignment/byGroupUpgrade";
			var oldUlr="${url}";
			$("#subSave").click(function(){
			var restart=document.querySelector('[name="restart"]:checked').value;
			var reStartTime=$("#reStartTime").val();
			var groupId=$("#groupId").val();
			var description=$("#description").val();
			if(restart==""){
				alert("请选择重启类型");
				return;
			}
			if(restart!="00"&&reStartTime==""){
				alert("请输入重启时间");
				return;
			}
			  var submitData = {
					reStart : restart,
					reStartTime : reStartTime,
					groupId:groupId,
					directive:"paraConfig",
					description:description
				};
			  $.post(url,submitData, function(e){
			  alert(e);
			  back();
			  
			  }); 
			});
	});
  	function back() {
	 top.$.jBox.getBox().find("button[value='true']").trigger("click");
	}
</script>
</body>