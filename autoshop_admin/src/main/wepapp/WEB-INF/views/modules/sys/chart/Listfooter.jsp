<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

    <!-- Bootstrap -->
   <%--  <script src="${ctx}/static/btp/bootstrap/dist/js/bootstrap.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/bootstrap.js"></script>
    <!-- FastClick -->
   <%--  <script src="${ctx}/static/btp/fastclick/lib/fastclick.js"></script> --%>
    <script src="${ctx}/static/zzw/js/fastclick.js"></script>
    <!-- NProgress -->
    <%-- <script src="${ctx}/static/btp/nprogress/nprogress.js"></script> --%>
    <script src="${ctx}/static/zzw/js/nprogress.js"></script>
    <!-- iCheck -->
    <%-- <script src="${ctx}/static/btp/iCheck/icheck.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/icheck.js"></script>
    <!-- Datatables -->
    <%-- <script src="${ctx}/static/btp/datatables.net/js/jquery.dataTables.min.js"></script> --%>
    <%-- <script src="${ctx}/static/btp/datatables.net/js/jquery.dataTables.js"></script> --%>
    <script src="${ctx}/static/zzw//js/jquery.dataTables.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-bs/js/dataTables.bootstrap.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/dataTables.bootstrap.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-buttons/js/dataTables.buttons.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/dataTables.buttons.min.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/buttons.bootstrap.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-buttons/js/buttons.flash.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/buttons.flash.js"></script>
   <%--  <script src="${ctx}/static/btp/datatables.net-buttons/js/buttons.html5.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/buttons.html5.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-buttons/js/buttons.print.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/buttons.print.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-buttons/js/buttons.colVis.js"></script> --%>
    <script src="${ctx}/static/zzw/js/buttons.colVis.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/dataTables.fixedHeader.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-keytable/js/dataTables.keyTable.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/dataTables.keyTable.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-responsive/js/dataTables.responsive.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/dataTables.responsive.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script> --%>
    <script src="${ctx}/static/zzw/js/responsive.bootstrap.js"></script>
    <%-- <script src="${ctx}/static/btp/datatables.net-scroller/js/dataTables.scroller.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/dataTables.scroller.js"></script>
    <%-- <script src="${ctx}/static/btp/jszip/dist/jszip.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/jszip.js"></script>
    <%-- <script src="${ctx}/static/btp/pdfmake/build/pdfmake.min.js"></script> --%>
    <script src="${ctx}/static/zzw/js/pdfmake.js"></script>
   <%--  <script src="${ctx}/static/btp/pdfmake/build/vfs_fonts.js"></script> --%>
	<script src="${ctx}/static/zzw/js/vfs_fonts.js"></script>
    <!-- Custom Theme Scripts -->
   <%--  <script src="${ctx}/static/btp/build/js/custom.js"></script> --%>
    <script src="${ctx}/static/zzw/js/custom.js"></script>
    <script type="text/javascript">
    	function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    //debugger;
	    
	   //var tableName="#datatable-buttons";
		var tableName="#datatable-responsive";
	    //color: #73879C;
	    //background: #2A3F54;
	    
    	$(function () { 
           var table = $(tableName).DataTable(/* {
            dom: 'Bfrtip',
		        buttons: [
		            'colvis'
		        ]
           } */);           
           //显示隐藏列
           $('.toggle-vis').on('change', function (e) {
               e.preventDefault();
               //debugger;
               console.log($(this).attr('data-column'));
               var column = table.column($(this).attr('data-column'));
               column.visible(!column.visible());
           });
 
            //删除选中行
           $(tableName+' tbody').on('click', 'tr input[name="check-all"]', function () {
               var $tr = $(this).parents('tr');
               $tr.addClass('selected');
               var $tmp = $('[name=check-all]:checkbox');
               $('#checkAll').prop('checked', $tmp.length == $tmp.filter(':checked').length);
 
           });
           
           $('#buttonDelete').click(function () {
               table.row('.selected').remove().draw(false);
           });
 
           $('#columnOpr').click(function () {
               $('.showul').toggle();
           });
           
           $(tableName+' tfoot th').each(function () {
		        var title = $(tableName+' thead th').eq($(this).index()).text();
		        //if(!title=="sys.oper"){
		          $(this).html('<input type="text" placeholder="Search '+title+'" />');
		  });
			
		  if(table&&table.columns().length>0){
				  // Apply the search
		    table.columns().eq(0).each(function(colIdx) {
		        $('input',table.column(colIdx).footer()).on('keyup change',function () {
		            table.column(colIdx).search(this.value).draw();
		        });
		    });
		 }
	   
	    
	    
	    $("#btn-export").click(function(){
				top.$.jBox.confirm("export data?","tip",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/a/test/mqtt/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
			
		$("#btnImport").click(function(){
			$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
				bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
		});
		
new $.fn.dataTable.Buttons(table, {buttons: ['colvis','copy','csv','print']});
table.buttons().container().appendTo($('.col-sm-6:eq(0)',table.table().container()));
			    
       });
       
       

	

  </script>