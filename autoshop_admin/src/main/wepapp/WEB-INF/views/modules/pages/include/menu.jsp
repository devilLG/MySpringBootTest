<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String paths = request.getContextPath();
%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel 菜单用户信息 -->

		<!-- search form -->
		<!-- /.search form -->
		
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header"><sp:message code="menu"/></li>
			<li class="treeview">
				<a href="<%=paths%>/com/list"> <i class="fa fa-user"></i><span><sp:message code="menu.company"/></span></a>
			</li>
			<li class="treeview">
				<a href="<%=paths%>/area/list"> <i class="fa fa-edit"></i><span><sp:message code="menu.area"/></span></a>
			</li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>