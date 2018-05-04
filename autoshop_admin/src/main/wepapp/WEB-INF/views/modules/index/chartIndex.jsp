<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" style="height: auto; min-height: 100%;">
<head>
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	
	<link rel="stylesheet" href="${ctxStatic}/zzw/css/bootstrap.css">
	<link rel="stylesheet" href="${ctxStatic}/zzw/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/zzw/css/ionicons.css">
	<link rel="stylesheet" href="${ctxStatic}/zzw/css/AdminLTE.css">
	<link rel="stylesheet" href="${ctxStatic}/zzw/css/_all-skins.css">
	<link rel="stylesheet" href="${ctxStatic}/zzw/css/morris.css">
	<link rel="stylesheet" href="${ctxStatic}/zzw/css/jquery-jvectormap.css">
	<link rel="stylesheet" href="${ctxStatic}/zzw/css/bootstrap-datepicker.css">
	<link rel="stylesheet" href="${ctxStatic}/zzw/css/daterangepicker.css">
	<link rel="stylesheet" href="${ctxStatic}/zzw/css/bootstrap3-wysihtml5.css">
	<!-- 	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> -->
	
	<%-- <script src="${ctxStatic}/zzw/js/btp_jquery.js"></script> --%>
	<script src="${ctxStatic}/zzw/js/jquery.js"></script>
	<script src="${ctxStatic}/zzw/js/jquery-ui.js"></script>
	<script src="${ctxStatic}/zzw/js/bootstrap.js"></script>
	<script src="${ctxStatic}/zzw/js/raphael.js"></script>
	<script src="${ctxStatic}/zzw/js/morris.js"></script>
	<script src="${ctxStatic}/zzw/js/jquery.sparkline.js"></script>
	<script src="${ctxStatic}/zzw/js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="${ctxStatic}/zzw/js/jquery-jvectormap-world-mill-en.js"></script>
	<script src="${ctxStatic}/zzw/js/jquery.knob.min.js"></script>
	<script src="${ctxStatic}/zzw/js/moment.min.js"></script>
	<script src="${ctxStatic}/zzw/js/daterangepicker.js"></script>
	<script src="${ctxStatic}/zzw/js/bootstrap-datepicker.js"></script>
	<script src="${ctxStatic}/zzw/js/bootstrap3-wysihtml5.all.js"></script>
	<script src="${ctxStatic}/zzw/js/jquery.slimscroll.js"></script>
	<script src="${ctxStatic}/zzw/js/fastclick.js"></script>
	<script src="${ctxStatic}/zzw/js/adminlte.js"></script>
</head>
<body class="skin-blue sidebar-mini" style="height: auto; min-height: 100%;">
	<div>
		<section class="content-header">
			<h1>
				ZhiLai<small>Control panel</small>
			</h1>
		</section>
		<section class="content">
			<div class="row">
				<%@include file="chartIndex/headerInfo.jsp" %>
				<%@include file="chartIndex/neckInfo.jsp" %>
			</div>
			<div class="row">
				<%@include file="chartIndex/storeLine.jsp" %>
				<%@include file="chartIndex/stockMap.jsp" %>
			</div>
			<div class="row">
				<%@include file="chartIndex/businessSum.jsp" %>
				<%@include file="chartIndex/businessType.jsp" %>
			</div>
		</section>
	</div>

</body>
</html>