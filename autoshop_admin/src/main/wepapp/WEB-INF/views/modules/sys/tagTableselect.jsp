<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据选择</title>
	<meta name="decorator" content="blank"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
</head>
<body>
	<div style="position:absolute;right:8px;top:5px;cursor:pointer;" onclick="search();">
		<i class="icon-search"></i><label id="txt">搜索</label>
	</div>
	<div id="search" class="form-search hide" style="padding:10px 0 0 13px;">
		<label for="key" class="control-label" style="padding:5px 5px 3px 0;">关键字：</label>
		<input type="text" class="empty" id="key" name="key" maxlength="50" style="width:110px;">
		<button class="btn" id="btn" onclick="searchNode()">搜索</button>
	</div>
	<div id="tipMsg" style="padding:15px 20px;"></div>
	<table id="zzwSelectTable" class="table" width="100%" layoutH="125"><tbody>
	<script type="text/javascript">
	var key, lastValue = "";
	//var nodeList = [], type = getQueryString("type", "${url}");
    //var waitInputid;
	$(document).ready(function(){
			//var url="/MqttZladmin/a/sys/office/treeData?type=2&&extId=&isAll=&module=&t="+ new Date().getTime();
			var url="${ctx}${url}${fn:indexOf(url,'?')==-1?'?':'&'}&extId=${extId}&isAll=${isAll}&module=${module}&t="+ new Date().getTime();
			//var oldUlr="${url}";
			//waitInputid=oldUlr.substr(oldUlr.lastIndexOf("&waitInputid=")+"&waitInputid=".length);
			var $table= $("#zzwSelectTable");
			$.get(url, function(zNodes){
			      debugger;
				// 初始化
				if(null==zNodes||zNodes.length<=0){
					return;
				}
				var th1,th2,th3,arr1;
				for(var i=0; i<zNodes.length; i++) {
					var ro=zNodes[i];
					
					if(i==0){
						var y=0;
						var arr = new Array(4);
						for(var key in ro){
						arr[y]=key;
	                       if(y>=4){
	                          break;
	                       }
	                       y++;
	                    }
	                /*   arr1 =arr.sort(); */
	                  arr1 =arr;
	                  if(arr1[0]==undefined&&arr1[1]==undefined&&arr1[2]==undefined&&arr1[3]==undefined){
	                         $("#tipMsg").text("No data from server");
								return;				
                       }
	                  
		              var th0="<thead><tr><th style='text-align:center;'>"+arr1[0]+"</th>";
		              if(undefined==arr1[1]||null==arr1[1]||""==arr1[1]){
		                th1="";
		              }else{
		                th1="<th style='text-align:center;'>"+arr1[1]+"</th>";
		              }
		              if(undefined==arr1[2]||null==arr1[2]||""==arr1[2]){
		               th2="";
		              }else if(arr1[2].substring(0,2) == 'c_'){
		                th2="<th style='display:none;'>"+arr1[2]+"</th>";
		              }else{
		                th2="<th style='text-align:center;'>"+arr1[2]+"</th>";
		              }
		              if(undefined==arr1[3]||null==arr1[3]||""==arr1[3]){
		                th3="";
		              }else if(arr1[3].substring(0,2) == 'c_'){
		                th3="<th style='display:none;'>"+arr1[3]+"</th>";
		              }else{
		                th3="<th style='text-align:center;'>"+arr1[3]+"</th>";
		              }		               
					  var th4="<th style='text-align:center;'>选择</th></tr></thead>";
				      $table.append(th0+th1+th2+th3+th4);
					}
					 if(arr1[0]==undefined&&arr1[1]==undefined&&arr1[2]==undefined&&arr1[3]==undefined){
								return;				
                       }
                    var mTr="<tr><td style='text-align:center;'>"+ro[arr1[0]]+"</td>";
                    var mtr1="",mtr2="",mtr3="";
                    if(th1!=""){
                      mtr1="<td style='text-align:center;'>"+ro[arr1[1]]+"</td>";
                    }
                    if(th2!="" && arr1[2].substring(0,2) != 'c_'){
                      mtr2="<td style='text-align:center;'>"+ro[arr1[2]]+"</td>";
                    }else if(th2!="" && arr1[2].substring(0,2) == 'c_'){
                      mtr2="<td style='display:none;'>"+ro[arr1[2]]+"</td>";
                    }
                    if(th3!="" && arr1[3].substring(0,2) != 'c_'){
                      mtr3="<td style='text-align:center;'>"+ro[arr1[3]]+"</td>";
                    }else if(th3!="" && arr1[3].substring(0,2) == 'c_'){
                      mtr2="<td style='display:none;'>"+ro[arr1[3]]+"</td>";
                    }
                    var mtr4="<td style='text-align:center;'><input value="+ro[arr1[0]]+" lang="+ro[arr1[1]]+" alt="+ro[arr1[2]]+" title="+ro[arr1[3]]+" onclick='selectOK()' id='selectTableCheck' type='checkbox'/></td></tr>";			
                    $table.append(mTr+mtr1+mtr2+mtr3+mtr4);
				}
			});
			key = $("#key");
			key.bind("focus", focusKey).bind("blur", blurKey).bind("change cut input propertychange", searchNode);
			key.bind('keydown', function (e){if(e.which == 13){searchNode();}});
			setTimeout("search();", "300");
	});
		
		
  	function focusKey(e) {
		if (key.hasClass("empty")) {
			key.removeClass("empty");
		}
	}
	function blurKey(e) {
		if (key.get(0).value === "") {
			key.addClass("empty");
		}
		searchNode(e);
	}
		
	//搜索节点
	function searchNode() {
		// 取得输入的关键字的值
		debugger;
		var value = $.trim(key.get(0).value);	
		/* // 按名字查询
		var keyType = "name";			
		// 如果和上次一次，就退出不查了。
		if (lastValue === value) {
			return;
		}			
		// 保存最后一次
		lastValue = value;			
		// 如果要查空字串，就退出不查了。
		if (value == "") {
			return;
		}		 */
		if (value != "") {
			$("table tbody tr").stop().hide() //将tbody中的tr都隐藏
   	    	.filter(":contains('"+value+"')").show(); //，将符合条件的筛选出来
		}else{
			$("table tbody tr").filter(":contains('"+value+"')").show(); 
		}
	}
		
	// 开始搜索
	function search() {
		$("#search").slideToggle(200);
		$("#txt").toggle();
		$("#key").focus();
	}
    function selectOK(){
    //debugger;               
	top.$.jBox.getBox().find("button[value='ok']").trigger("click");
	}
	</script>
		</tbody>
	</table>
	</div>
</body>