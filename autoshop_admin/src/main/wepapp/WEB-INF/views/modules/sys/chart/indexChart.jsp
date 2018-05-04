<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/sys/chart/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
   <head><title>DataTables | zzw index</title></head>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
      <%@ include file="/WEB-INF/views/modules/sys/chart/left.jsp"%>
         <!-- page content -->

<%-- <iframe src="${iframeUrl}" name='main' id="iframe-page-content" frameborder="0" width="86%" height="1080" scrolling="no" 
      marginheight="0" marginwidth="0" align="right" onLoad="iFrameHeight()">
      
      </iframe> --%>
    <%@ include file="/WEB-INF/views/modules/sys/chart/myindex.jsp"%>
        <!-- /page content -->

       <%@ include file="/WEB-INF/views/modules/sys/chart/footer.jsp"%>
      </div>
    </div>
    <%-- <%@ include file="/views/lyt/indexfooter.jsp"%> --%>
  </body>
</html>
