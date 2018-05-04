<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="树结构数据地址"%>
<%@ attribute name="checked" type="java.lang.Boolean" required="false" description="是否显示复选框，如果不需要返回父节点，请设置notAllowSelectParent为true"%>
<%@ attribute name="extId" type="java.lang.String" required="false" description="排除掉的编号（不能选择的编号）"%>
<%@ attribute name="isAll" type="java.lang.Boolean" required="false" description="是否列出全部数据，设置true则不进行数据权限过滤（目前仅对Office有效）"%>
<%@ attribute name="notAllowSelectRoot" type="java.lang.Boolean" required="false" description="不允许选择根节点"%>
<%@ attribute name="notAllowSelectParent" type="java.lang.Boolean" required="false" description="不允许选择父节点"%>
<%@ attribute name="module" type="java.lang.String" required="false" description="过滤栏目模型（只显示指定模型，仅针对CMS的Category树）"%>
<%@ attribute name="selectScopeModule" type="java.lang.Boolean" required="false" description="选择范围内的模型（控制不能选择公共模型，不能选择本栏目外的模型）（仅针对CMS的Category树）"%>
<%@ attribute name="allowClear" type="java.lang.Boolean" required="false" description="是否允许清除"%>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="文本框可填写"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="smallBtn" type="java.lang.Boolean" required="false" description="缩小按钮显示"%>
<%@ attribute name="hideBtn" type="java.lang.Boolean" required="false" description="是否显示按钮"%>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled"%>
<%@ attribute name="dataMsgRequired" type="java.lang.String" required="false" description=""%>
<div class="input-append">
	<input id="${id}_id" name="${name}" class="${cssClass}" type="hidden" value="${value}"/>	
	<input id="${id}MA" type="hidden" />
	<input id="${id}MT" type="hidden"/>	
	<input id="${id}_name" name="${labelName}" ${allowInput?'':'readonly="readonly"'} type="text" value="${labelValue}" data-msg-required="${dataMsgRequired}" class="${cssClass}" style="${cssStyle}"/>
	<a id="${id}Button" href="javascript:" class="btn ${disabled} ${hideBtn ? 'hide' : ''}" style="${smallBtn?'padding:4px 2px;':''}">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
</div>

<script type="text/javascript">
	$("#${id}Button, #${id}_name").click(function(){
		// 是否限制选择，如果限制，设置为disabled
		if ($("#${id}Button").hasClass("disabled")){
			return true;
		}
		// 正常打开	
		top.$.jBox.open("iframe:${ctx}/tag/tableselect?url="+encodeURIComponent("${url}")+"&module=${module}&checked=${checked}&extId=${extId}&isAll=${isAll}&waitInputid=${id}", "选择${title}", 600, 420, {
			ajaxData:
			{selectIds: $("#${id}_id").val(),},
			 buttons:{
			           "确定":"ok", 
			           ${allowClear?"\"清除\":\"clear\", ":""}"关闭":true}, 
			submit:function(v, h, f){
			debugger;
			if (v=="ok"){
				var ids = [], names = [], alts = [], titles = [];
				var chklist = h.find("iframe")[0].contentWindow.$("input[id='selectTableCheck']:checked");
				for(var i=0; i<chklist.length; i++){
				 var ckInput=chklist[i];
				 if(undefined==ckInput||null==ckInput){
				 	continue;
				 }
				 if(undefined==ckInput.value||null==ckInput.value||"undefined"==ckInput.value){
				   ids.push("");
				 }else{
				  ids.push(ckInput.value);				  
				 }
				 
				 if(undefined==ckInput.lang||null==ckInput.lang||"undefined"==ckInput.lang){
				   names.push("");
				 }else{
				  names.push(ckInput.lang);				  
				 }
				 if(undefined==ckInput.alt||null==ckInput.alt||"undefined"==ckInput.alt){
				   alts.push("");
				 }else{
				  alts.push(ckInput.alt);				  
				 }
				 if(undefined==ckInput.title||null==ckInput.title||"undefined"==ckInput.title){
				   titles.push("");
				 }else{
				  titles.push(ckInput.title);				  
				 }
				}	
				$("#${id}_id").val(ids.join(",").replace(/u_/ig,""));
				if(names==""){
				$("#${id}_name").val(ids.join(",").replace(/u_/ig,""));
				}else{
				$("#${id}_name").val(names.join(","));
				}
				
				$("#${id}MA").val(alts.join(",")); 
				$("#${id}MT").val(titles.join(","));  
					
				}else if (v=="clear"){
					$("#${id}_id").val("");
					$("#${id}_name").val("");
					$("#${id}MA").val("");
					$("#${id}MT").val("");
                }
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
	});
</script>