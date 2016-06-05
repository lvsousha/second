<html>
<head>
<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="/second/DataTables-1.10.7/media/css/jquery.dataTables.css">
 
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="/second/DataTables-1.10.7/media/js/jquery.js"></script>
 
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="/second/DataTables-1.10.7/media/js/jquery.dataTables.js"></script>
</head>
<body>
<div style="float:left; position:relative;">
<button class="showcol">列段显示/隐藏</button>
<ul class="showul" >
    <li>
        <input type="checkbox" class="toggle-vis" data-column="0" />
        企业名称
    </li>
    <li>
        <input type="checkbox" class= "toggle-vis" data-column="1" />
        法人
    </li>
    <li>
        <input type="checkbox" class= "toggle-vis" data-column="2" />
        身份证号
    </li>
    <li>
        <input type="checkbox" class= "toggle-vis" data-column="3" />
        工商注册号
    </li>
</ul>
</div>
<table id="table" >
    <thead>
    </thead>
    <tbody>
    </tbody>
</table>
<script>
$(document).ready( function () {
   var table = $('#table').DataTable({
	   "serverSide":true,
	   "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "All"] ],
	   "pageLength": 10,//改变初始的页面长度(每页显示的记录数)
	   "pagingType": "full_numbers",
	   "scrollY": "400px",//dt高度
       "lengthChange": true,//是否允许用户自定义显示数量
       "bPaginate": true, //翻页功能
       "bFilter": true, //列筛序功能
       "searching": true,//本地搜索
       "ordering": false, //排序功能
       "Info": true,//页脚信息
       "autoWidth": true,//自动宽度
	   "oLanguage": {//国际语言转化
           "oAria": {
               "sSortAscending": " - click/return to sort ascending",
               "sSortDescending": " - click/return to sort descending"
           },
           "sLengthMenu": "显示 _MENU_ 记录",
           "sZeroRecords": "对不起，查询不到任何相关数据",
           "sEmptyTable": "未有相关数据",
           "sLoadingRecords": "正在加载数据-请等待...",
           "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
           "sInfoEmpty": "当前显示0到0条，共0条记录",
           "sInfoFiltered": "（数据库中共为 _MAX_ 条记录）",
           "sProcessing": "正在加载数据...",
           "sSearch": "模糊查询：",
           "sUrl": "",
           //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
           "oPaginate": {
               "sFirst": "首页",
               "sPrevious": " 上一页 ",
               "sNext": " 下一页 ",
               "sLast": " 尾页 "
           },
           "oAria": {
               "sSortAscending": ": 以升序排列此列",
               "sSortDescending": ": 以降序排列此列"
           }
       },
//	   "ajax":{
//		   url:"/second/example/list",
//		   type:"post"
//	   },
       "ajax":function(data, callback, settings){
    	   console.log(data);
    	   $.ajax({
    		   type: "post",
    		   url:"/second/example/list",
    		   dataType: "json",
    		   data: {data:JSON.stringify(data)},
    		   success: function(result){
    			   callback(result);
    		   }
    	   })
       },
//	   "data":[{
//		   entname:'ddd',
//		   frname:'dd'
//	   }],

	   "columns":[{
		   "data":"entname",
		   "defaultContent":"",
		   "title":"企业名称"
	   },{
		   "data":"frname",
		   "defaultContent":"",
		   "title":"法人"
	   },{
		   "data": "cardnum",
		   "defaultContent":"",
		   "title":"身份证号"
	   },{
		   "data":"regno",
		   "defaultContent":"",
		   "title":"工商注册号"
	   },{
		   "data":null,
		   "defaultContent":"",
		   "title":"输入框"
	   }],
	   "columnDefs":[{
		   targets: 4,
		   render: function (a, b, c, d){
			   var html = "<input />";
               return html;
		   }
	   }],
	   "initComplete" : function (){
//		   console.log("initComplete");
		   var api = this.api();
		   api.columns().indexes().flatten().each(function(i){			   
			   var column = api.column(i);
			   var select = $('<br/><input class="addselect" />')
			   					.appendTo($(column.header()))
			   					.keypress(function(e){
			   						if(e.which == 13){
			   							var val = $.fn.dataTable.util.escapeRegex($(this).val());
			   							column.search(val).draw();			   							
			   						}
			   					})
		   })
	   } 
	   
    });
    $('.showcol').click(function () {
        $('.showul').toggle();
    });
   $('.toggle-vis').on('change', function (e) {
       e.preventDefault();
       var column = table.column($(this).attr('data-column'));
       column.visible(!column.visible());
   });
   
   $('#table tbody').on( 'click','tr',function () {
	   var rowNode = table
	    .row.add( {"name":       "Tiger Nixon",})
	    .draw(false)
	    .node();
	} );
       
   
} );
</script>
</body>
</html>