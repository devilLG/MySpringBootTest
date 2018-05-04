//清除表单内容
function myResult(rel){
	debugger;
  	$("#"+rel+" select").each(function(i,val){
  		val.value="";
  	});
	$("#"+rel+" input[type='text']").each(function(i,b){b.value="";});
	$("#"+rel+" input[type='hidden']").each(function(i,b){b.value="";});
	$("#"+rel).submit();
}

//配置toastr
toastr.options = {
	"closeButton": true,
	"debug": false,
	"positionClass": "toast-top-center",
	"onclick": null,
	"showDuration": "1000",
	"hideDuration": "1000",
	"timeOut": "3000",
	"extendedTimeOut": "1000",
	"showEasing": "swing",
	"hideEasing": "linear",
	"showMethod": "fadeIn",
	"hideMethod": "fadeOut"
};