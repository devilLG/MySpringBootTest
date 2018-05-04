<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Meta, title, CSS, favicons, etc. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<%@ taglib prefix="tangs" uri="/WEB-INF/tlds/shiros.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
  <!-- jQuery -->
<%-- <script src="${ctx}/static/btp/jquery/dist/jquery.min.js"></script> --%>
<script src="${ctx}/static/zzw/js/btp_jquery.js"></script>
<script type="text/javascript">
	function myreset(rel) {
		if (rel) {
			$("#" + rel + " table select").each(function(i, val) {
				val.value = "";
			});
			$("#" + rel + " table input[type='text']").each(function(i, b) {
				b.value = "";
			});
			$("#" + rel + " table input[type='hidden']").each(function(i, b) {
				b.value = "";
			});
		}
		
	}
	$(function(){
		     //checkbox全选
	    $("#checkAll").click(function () {
	   		if ($(this).prop("checked") === false) {
	          $("input[name='check-all']").attr("checked","checked");
	                //$("#dataTable tbody tr").addClass('selected');
	        } else {
	          $("input[name='check-all']").attr("checked","");
	        }
	    }); 
	});

$(document).ready(function(){
$("select").each(function() { 
    $(this).css("width", ($(this).width()>100?100:$(this).width()) + "px"); 
});
});
	
$(window).load(function() {
$("select").each(function() { 
    $(this).css("width", ($(this).width()>100?100:$(this).width()) + "px"); 
}); 
});
   


</script>

<link href="${ctx}/static/zzw/css/bootstrap.css" rel="stylesheet" />
<link href="${ctx}/static/zzw/css/font-awesome.css" rel="stylesheet" />
<link href="${ctx}/static/zzw/css/nprogress.css" rel="stylesheet" />
<link href="${ctx}/static/zzw/css/green.css" rel="stylesheet" />
<link href="${ctx}/static/zzw/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="${ctx}/static/zzw/css/buttons.bootstrap.css" rel="stylesheet" />
<link href="${ctx}/static/zzw/css/fixedHeader.bootstrap.css" rel="stylesheet" />
<link href="${ctx}/static/zzw/css/responsive.bootstrap.css" rel="stylesheet" />
<link href="${ctx}/static/zzw/css/scroller.bootstrap.css" rel="stylesheet" />
<link href="${ctx}/static/zzw/css/custom.css" rel="stylesheet" />
<script src="${ctx}/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
