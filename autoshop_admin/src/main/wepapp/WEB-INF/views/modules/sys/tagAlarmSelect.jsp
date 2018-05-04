<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据选择</title>
	<meta name="decorator" content="blank"/>
</head>
<body >
	<div style="margin-top: 10%;margin-left: 5%;">
		&nbsp;&nbsp;&nbsp;告警阈值≤:&nbsp;<input id="warValue" name="warValue" style="width: 100px;"  onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " htmlEscape="false" maxlength="3"/>
	</div>
	<input type="button" style="margin-left: 8%;margin-top: 10%;" id="subSave" value="<sp:message code='save'/>"/>
	<input type="button"  style="margin-top: 10%;margin-left: 15%;"  value="<sp:message code='back'/>"  onclick="back()">
		<i class="fa fa-sign-in"></i> 
	</input>
<script type="text/javascript">
/* window.location.href="${ctx}/sys/taskAssignment/siteUpgrade"; */
	$(document).ready(function(){
			var url="${ctx}${url}${fn:indexOf(url,'?')==-1?'?':'&'}t="+ new Date().getTime();
			url="${ctx}/sys/warnDefine/update";
			var oldUlr="${url}";
			$("#subSave").click(function(){
			var warValue=$("#warValue").val();
			if(warValue==""){
				alert("告警阈值为必填项");
				return;
			}
			  var submitData = {
			  		warnType:"00",
					warValue:warValue
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
