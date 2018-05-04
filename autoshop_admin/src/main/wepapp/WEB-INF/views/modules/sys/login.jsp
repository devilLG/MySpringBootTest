<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html style="height:100%;">
<head>
<title>${fns:getConfig('productName')} 登录</title>
<meta name="decorator" content="blank"/>
<style type="text/css">
      .footer {
	text-align: center;
	margin-bottom: 0px;	
}

.form-signin-heading {
	text-align: center;
	font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
	font-size: 18px;
	margin-bottom: 20px;
	color: #0663a2;
}

.form-signin {
	position: relative;
	top:25%;
	text-align: left;
	width: 300px;
	padding: 25px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .checkbox {
	margin-bottom: 10px;
	color: #0663a2;
}

.form-signin .input-label {
	font-size: 16px;
	line-height: 23px;
	color: #999;
}

.form-signin .input-block-level {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px;
	*width: 283px;
	*padding-bottom: 0;
	_padding: 7px 7px 9px 7px;
}

.form-signin .btn.btn-large {
	font-size: 16px;
}

.form-signin #themeSwitch {
	position: absolute;
	right: 15px;
	bottom: 10px;
}

.form-signin div.validateCode {
	padding-bottom: 15px;
}

.mid {
	vertical-align: middle;
}

.header {
	/* padding-top: 8%; */
}

.alert {
	position: relative;
	width: 300px;
	margin: 0 auto;
	*padding-bottom: 0px;
}

label.error {
	background: none;
	width: 270px;
	font-weight: normal;
	color: inherit;
	margin: 0;
}

.privatea:LINK, .privatea:HOVER, .privatea:VISITED, .privatea:ACTIVE{
	text-decoration: none;
}
.login_button{font-size: 16px;
	height: 40px;
	margin-bottom: 5px;
	padding: 7px;
	width: 300px !important;
	border-radius:4px;
	border: 0px;
	}
.divCol{
	display:inline;
}
.bottomDiv{
	color:#fff; 
	width:100%; 
	text-align: center;
	position: fixed;
	bottom: 0px;
}
    </style>	 
	
<style>
body{height:100%;width:100%; background:#3981cb;overflow:hidden;position: fixed;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="${ctxStatic}/zzw/js/Particleground.js"></script>
<script>
$(function() {
  //粒子背景特效
  var bg=$("#sub").css("background-color");
  /* alert(bg); */
  if(bg=="rgb(248, 148, 6)"){
  	$('body').css("background-color","#e99130");
  	$('body').particleground({
		  dotColor: '#FFCCCC',
		  lineColor: '#f7b267'
	  });
  }
  
  if(bg=="rgb(232, 101, 55)"){
  	$('body').css("background-color","#e86538");
  	$('body').particleground({
		  dotColor: '#FFCCCC',
		  lineColor: '#ef825d'
	  });
  }
  
   if(bg=="rgb(20, 188, 20)"){
  	$('body').css("background-color","#10b010");
  	$('body').particleground({
		  dotColor: '#FFCCCC',
		  lineColor: '#47e447'
	  });
  }
  
  if(bg=="rgb(61, 170, 233)"){
	  $('body').particleground({
		  dotColor: '#FFCCCC',
		  lineColor: '#70aded'
	  });
  }
  
  
/*  
 橙色：#e99130 linecolor：#f7b267     红色：#e86538 linecolor：#ef825d  flat：#10b010 linecolor：#47e447
 
 
 $("#loginForm").validate({  
		rules: {
			validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
		},
		messages: {
			username: {required: "请填写用户名."},password: {required: "请填写密码."},
			validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
		},
		errorLabelContainer: "#messageBox",
		errorPlacement: function(error, element) {
			error.appendTo($("#loginError").parent());
		} 
	}); */
});

// 如果在框架或在对话框中，则弹出提示并跳转到首页
if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
	alert('未登录或登录超时。请重新登录，谢谢！');
	top.location = "${ctx}";
}
</script>
</head>
<body>
 <div class="header">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div>
	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
		<h2 class="form-signin-heading"><a class="privatea">自动便利店服务器管理系统 </a><div style="height:40px; font-size: 12px;text-align: center;margin-top: -14px;"><em><a class="privatea">Management System</a></em></div></h2>
		
		<input type="text" id="username" name="username" value="${username}"  class="input-block-level required"  placeholder="账号">
		<input type="password" id="password" name="password"  class="input-block-level required"   placeholder="密码">
		<c:if test="${isValidateCodeLogin}">
		<div class="validateCode">
			<label class="input-label mid" for="validateCode">验证码</label>
			<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
		</div></c:if>
		<%-- <div id="themeSwitch" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
			<ul class="dropdown-menu">
			  <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
			</ul>
			<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
		</div> --%><%--
		<label for="mobile" title="手机登录"><input type="checkbox" id="mobileLogin" name="mobileLogin" ${mobileLogin ? 'checked' : ''}/></label> --%>
		<input class="btn btn-large btn-primary login_button" id="sub" type="submit" value="登 录"/>&nbsp;&nbsp;
		<!--  -->
		<div style="width: 300px;margin-top: -10px;">
			<table style="width: 300px;">
				<tr>
					<td align="left"><label  for="rememberMe" title="下次不需要再登录"><input style="margin-top: -6px;" type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> <a>记住我（公共场所慎用）</a></label></td>
					<td align="right">
						<div class="divCol">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
							<ul class="dropdown-menu">
							  <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
							</ul>
						</div>
					</td>
				</tr>
			</table> 
		</div>
		<br><br>
		<div class="footer">
			<a class="privatea">适用浏览器：google、360、FireFox、Opera、搜狗. 不支持IE8及以下浏览器。</a>
		</div>
	</form>
	<div class="bottomDiv" >
		Copyright &copy; 2012-${fns:getConfig('copyrightYear')} <a style="color: #fff;" href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> ${fns:getConfig('version')} 
	</div>
	<script src="${ctxStatic}/zzw/js/zoom.min.js" type="text/javascript"></script>
	
</body>
</html>
