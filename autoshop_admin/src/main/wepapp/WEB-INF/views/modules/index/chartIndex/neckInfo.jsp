<%@page import="com.zhilai.master.modules.sys.utils.UserUtils"%>
<%@ page contentType="text/html;charset=UTF-8" 
		import="com.zhilai.master.modules.utils.GParameter"
		import="com.zhilai.master.modules.sys.utils.UserUtils"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<style>
	.badge {
	    position: absolute;
	    font-family: "HelveticaNeue", "Helvetica Neue", Helvetica, Arial;
	    font-size: 11px !important;
	    font-weight: 300;
	    top: -5px;
	    right: -5px;
	    padding: 3px 7px 3px 7px;
	    color: white !important;
	    text-shadow: none;
	    border-width: 0;
	    border-style: solid;
	    -webkit-border-radius: 12px !important;
	    -moz-border-radius: 12px !important;
	    border-radius: 12px !important;
	    -webkit-box-shadow: none;
	    -moz-box-shadow: none;
	    box-shadow: none;
	}
	.badge-important {
	    background-color: #ed4e2a;
	    background-image: none !important;
	    text-shadow: none !important;
	}
</style>
<div class="col-lg-3 col-xs-6">
	<div class="small-box bg-orange">
		<div class="inner">
			<a href="${ctx }/operation/operationFlow/index?node=1">
				<div style="text-align: center;cursor: pointer;color: white;">
					<span>
						<i style="font-size: x-large;" class=" ion-no-tasks"></i>
						<span style="position: relative;top: -3px;"> 待处理业务</span>
					</span>
					<span class="badge badge-important">${operationDataMap["flowWait"] }</span>
				</div>
			</a>
		</div>
	</div>
</div>

<div class="col-lg-3 col-xs-6">
	<div class="small-box bg-green">
		<div class="inner">
		<a href="${ctx }/operation/operationFlow/index?node=2">
			<div style="text-align: center;cursor: pointer;color: white;">
				<span>
					<i style="font-size: x-large;" class=" ion-tasks"></i>
					<span style="position: relative;top: -3px;"> 当月已完成业务</span>
				</span>
				<span class="badge badge-important">${operationDataMap["flowComplete"] }</span>
			</div>
		</a>
		</div>
	</div>
</div>
<div class="col-lg-3 col-xs-6">
	<div class="small-box bg-blue">
		<div class="inner">
			<a href="${ctx }/operation/menu/createOperation">
				<div style="text-align: center;cursor: pointer;color: white;">
					<span>
						<i style="font-size: x-large;" class=" ion-add-position"></i>
						<span style="position: relative;top: -3px;"> 新增业务</span>
					</span>
				</div>
			</a>
		</div>
	</div>
</div>
<div class="col-lg-3 col-xs-6">
	<div class="small-box bg-yellow">
		<div class="inner">
			<a href="${ctx }/stock/goodPositionInventory">
				<div style="text-align: center;cursor: pointer;color: white;">
					<span>
						<i style="font-size: x-large;" class=" ion-search"></i>
						<span style="position: relative;top: -3px;"> 物料查询</span>
					</span>
				</div>
			</a>
		</div>
	</div>
</div>