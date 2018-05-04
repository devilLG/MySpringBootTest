<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<html>
  <head>
    <style>
		#lngLatitudesTable li {
			line-height:25px;
			margin:2px 5px;
			cursor:pointer;
			list-style:none;
		}
		#lngLatitudesTable i {
			margin:1px 5px;
			font-size:16px;
		}
		#lngLatitudesTable li:hover {
			background-color:#DCDCDC;
		}
        #lngLatitudesTable li.active {
        	background-color:#0088CC;
        	color:#ffffff;
        }
        #lngLatitudesTable li:hover i{
        	font-size:20px;
        }
	</style>
  </head>
  
  <body>
	<div class="row-fluid" style="padding-top: 10px">
		<div class="span3">
			<div class="well sidebar-nav">
				<ul class="nav nav-list link-page-ul"></ul>
			</div>
		</div>
		<div class="span9 link-page"></div>
	</div>
 
 <script src="${ctxStatic}/wq/js/lngLatitudes.js" type="text/javascript"></script>
</body>

</html>
