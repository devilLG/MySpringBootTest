<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
%>
<link rel="stylesheet" href="<%=path%>/static/zzw/css/bootstrap.css">
<link rel="stylesheet" href="<%=path%>/static/zzw/css/AdminLTE.css">
<link rel="stylesheet" href="<%=path%>/static/zzw/css/_all-skins.css">
<link rel="stylesheet" href="<%=path%>/static/zzw/css/blue.css">
<link rel="stylesheet" href="<%=path%>/static/zzw/css/dataTables.bootstrap.css">
<link rel="stylesheet" href="<%=path%>/static/zzw/css/font-awesome.css">
<link rel="stylesheet" href="<%=path%>/static/zzw/css/ionicons.css">
<link rel="stylesheet" href="<%=path%>/static/zzw/css/toastr.min.css">
<link rel="stylesheet" href="<%=path%>/static/zzw/css/sweetalert.css">
<link rel="stylesheet" href="<%=path%>static/zzw/css/sy.css">
<script src="<%=path%>/static/zzw/js/jquery-2.2.3.min.js"></script>
<script src="<%=path%>/static/zzw/js/bootstrap.js"></script>
<script src="<%=path%>/static/zzw/js/icheck.js"></script>
<script src="<%=path%>/static/zzw/js/jquery.dataTables.js"></script>
<script src="<%=path%>/static/zzw/js/dataTables.bootstrap.js"></script>
<script src="<%=path%>/static/zzw/js/jquery.slimscroll.js"></script>
<script src="<%=path%>static/zzw/js/sy.js"></script>
<script src="<%=path%>/static/zzw/js/toastr.min.js"></script>
<script src="<%=path%>/static/zzw/js/sweetalert.min.js"></script>
<script type="text/javascript">
<!--
	toastr.options = {  
        closeButton: false,  
        debug: false,  
        progressBar: true,  
        positionClass: "toast-bottom-center",  
        onclick: null,  
        showDuration: "300",  
        hideDuration: "1000",  
        timeOut: "2000",  
        extendedTimeOut: "1000",  
        showEasing: "swing",  
        hideEasing: "linear",  
        showMethod: "fadeIn",  
        hideMethod: "fadeOut"  
    };  
//-->
</script>