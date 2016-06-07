var table;
var setValues = function(formId,spend){
	var fields = $("#"+formId).serializeArray();
	$.each(fields, function(i, field){
		var temp = spend;
		var name = field.name;
		var names = field.name.split(".")
		for(var key in names){
			var value = names[key];
			temp = temp[value];
		}
		$("[name='"+name+"']").attr("value",temp);
	});
};
var TableEditable = function (){
	return{
		init:function(){
			table = $('#spendtable').DataTable({
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
			       "ajax":function(data, callback, settings){
			    	   $.ajax({
			    		   type: "post",
			    		   url:"/second/spend/list",
			    		   dataType: "json",
			    		   data: {data:JSON.stringify(data)},
			    		   success: function(result){
			    			   callback(result);
			    		   }
			    	   })
			       },
				   "columns":[{
					   "data":"id",
					   "defaultContent":"",
					   "title":"Id",
					   "visible":false
				   },{
					   "data":"subject",
					   "defaultContent":"",
					   "title":"subject"
				   },{
					   "data":"price",
					   "defaultContent":"",
					   "title":"price"
				   },{
					   "data": "created",
					   "defaultContent":"",
					   "title":"created"
				   },{
					   "data":"createdby.username",
					   "defaultContent":"",
					   "title":"createdby"
				   }],
				   "columnDefs":[{
//					   targets: 3,
//					   render: function (a, b, c, d){
//						   console.log(a);
//						   console.log(b);
//						   console.log(c);
//						   console.log(d);
//						   console.log("-----");
//			               return "2015-12-31";
//					   }
				   }],
				   "initComplete" : function (){
//					   console.log("initComplete");
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
					   if(api.rows() != null && api.rows().length >0){
						   setValues("spend",api.row(0).data());
					   }
				   } 
				   
			});
			$('#spendtable tbody')
				.on( 'click','tr',function () {
				   var data = table.row(this).data();
				   setValues("spend",data);
				})
				.on( 'dblclick','tr',function () {
				   var data = table.row(this).data();
				   setValues("spend",data);
				   $("a[href='#tab_1_2']").click();
				});
		}
	}
}();