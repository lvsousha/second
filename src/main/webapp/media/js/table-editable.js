var TableEditable = function () {

    return {

        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                var temp = [];
                for(var key in aData){
                	temp.push(aData[key]);
                }
//                console.log(aData[i]);
                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(temp[i], nRow, i, false);
                }

                oTable.fnDraw();
            }

            function editRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
//                console.log(aData);
                var jqTds = $('>td', nRow);
                var i = 0 ;
                var isnew = false
                for(var key in aData){
                   if(key == "Delete"){
                	   isnew = true;
                   }
                   if(i < $(jqTds).size()-2){
                	   if(key == "subject" || key == "price"){
                		   $(jqTds).get(i).innerHTML = '<input type="text" class="m-wrap small" value="' + aData[key] + '">';
                	   }else{
                		   $(jqTds).get(i).innerHTML = '<input type="text" class="m-wrap small" readonly="readonly" value="' + aData[key] + '">';
                	   }                	   
                   }
//                   console.log(i);
                   i++;
                }
//                console.log($(jqTds));
//                $(jqTds).each(function(i){
//                	$(jqTds).get(i).innerHTML = '<input type="text" class="m-wrap small" readonly="readonly" value="' + tmp[i] + '">';
//                })
//                jqTds[0].innerHTML = '<input type="text" class="m-wrap small" readonly="readonly" value="' + aData.id + '">';
//                jqTds[1].innerHTML = '<input type="text" class="m-wrap small" value="' + aData.subject + '">';
//                jqTds[2].innerHTML = '<input type="text" class="m-wrap small" value="' + aData.price + '">';
//                jqTds[3].innerHTML = '<input type="text" class="m-wrap small" readonly="readonly"  value="' + aData.created + '">';
//                jqTds[4].innerHTML = '<input type="text" class="m-wrap small" readonly="readonly"  value="' + aData.createdby + '">';
                jqTds[$(jqTds).size()-2].innerHTML = '<a class="edit" href="">Save</a>';
                if(isnew){
                	jqTds[$(jqTds).size()-1].innerHTML = '<a class="cancel" data-mode="new"  href="">Cancel</a>';  
                }else{
                	jqTds[$(jqTds).size()-1].innerHTML = '<a class="cancel" href="">Cancel</a>';                	
                }
            }

            function saveRow(oTable, nRow, spend) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(spend.id, nRow, 0, false);
                oTable.fnUpdate(spend.subject, nRow, 1, false);
                oTable.fnUpdate(spend.price, nRow, 2, false);
                oTable.fnUpdate(spend.created, nRow, 3, false);
                oTable.fnUpdate(spend.createdby, nRow, 4, false);
                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 5, false);
                oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow, 6, false);
                oTable.fnDraw();
            }

            function cancelEditRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate(jqInputs[4].value, nRow, 4, false);
                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 5, false);
                oTable.fnDraw();
            }

            var oTable = $('#sample_editable_1').dataTable({
            	"autoWidth": false,
            	"aFilter": false,
            	"aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ records per page",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                },
                "columns":[{
         		   "data":"id",
         		   "title":"id",
         		   "visible":true
         	   },{
         		   "data":"subject",
         		   "title":"subject"
         	   },{
         		   "data": "price",
         		   "defaultContent":"",
         		   "title":"price"
         	   },{
         		   "data":"created",
         		   "title":"created"
         	   },{
         		   "data":"createdby",
         		   "title":"createdby"
         	   },{
         		   "data":null,
        		   "defaultContent":"",
         		   "title":"Edit"
         	   },{
         		  "data":null,
         		  "title":"Delete",
         		  "defaultContent":"",
         	   }],
         	  "columnDefs":[{
	       		   targets: 5,
	       		   render: function (a, b, c, d){
	       			   var html = "<a class='edit' href='javascript:;'>Edit</a>";
	                   return html;
	       		   }
         	  },{
         		   targets: 6,
	       		   render: function (a, b, c, d){
	       			   var html = "<a class='delete' href='javascript:;'>Delete</a>";
	                   return html;
	       		   }
         	  }],
         	   "initComplete" : function (){
         		   var api = this.api();
         		   api.columns().indexes().flatten().each(function(i){			   
         			   var column = api.column(i);
         			   var select = $('<br/><input type="text" class="m-wrap small" />')
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
            

            jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
            jQuery('#sample_editable_1_wrapper .dataTables_length select').select2({
                showSearchInput : false //hide search box with special css class
            }); // initialzie select2 dropdown

            var nEditing = null;

            $('#sample_editable_1_new').click(function (e) {
                e.preventDefault();
                var api = oTable.api();
                var columns = api.columns();
//                console.log(api.columns());
                var node = {};
                for(var i=0; i<columns[0].length; i++){
                	var key = columns.header()[i].innerHTML;
                	key = key.substring(0,key.indexOf("<"));
                	if(i < columns[0].length-2){
                		node[key] = '';
                	}else if(i == columns[0].length-2){
                		node[key] = '<a class="edit" href="">Save</a>';
                	}else{
                		node[key] = '<a class="cancel" data-mode="new" href="" >Cancel</a>';
                	}
                }
                var aiNew = oTable.fnAddData(node);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                nEditing = nRow;
            });

            $('#sample_editable_1 a.delete').live('click', function (e) {
                e.preventDefault();

                if (confirm("Are you sure to delete this row ?") == false) {
                    return;
                }
                var nRow = $(this).parents('tr')[0];
                var jqInputs = $(this).parent().siblings();
            	var spend = {id:jqInputs[0].innerHTML,subject:jqInputs[1].innerHTML,price:jqInputs[2].innerHTML};
//                console.log(spend);
                jQuery.ajax({
                	url : "/second/spend/deleteRow",
					data : spend,
					type : "post",
					dataType : "json",
					cache : false,
					async : true,
					success : function(data){
						if(data.success == true){
							oTable.fnDeleteRow(nRow);
						}else{
							alert("Deleted Failue! :)");
						}
					},
					error:function(data){
						alert("Deleted Failue! :)");
//						console.log(data);
					}
                })                
            });

            $('#sample_editable_1 a.cancel').live('click', function (e) {
                e.preventDefault();
//                console.log($(this).attr("data-mode"));
                if ($(this).attr("data-mode") == "new") {
                    alert("NEWin");
                	var nRow = $(this).parents('tr')[0];
                    oTable.fnDeleteRow(nRow);
                } else {
                    alert("in");
                	restoreRow(oTable, nEditing);
                    nEditing = null;
                }
            });

            $('#sample_editable_1 a.edit').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == "Save") {
                    /* Editing this row and want to save it */
                	var jqInputs = $('input', nRow);
                	var spend = {id:jqInputs[0].value,subject:jqInputs[1].value,price:jqInputs[2].value};
                	if(spend.id == ""){
                		url = "/second/spend/insertRow";
                	}else{
                		url = "/second/spend/updateRow"
                	}
//                    console.log(spend);
                    $.ajax({
                    	url : url,
    					data :spend,
    					type : "post",
    					dataType : "json",
    					cache : false,
    					async : true,
    					success : function(data){
    						if(data.success == true){
//    							console.log(data.spend);
    							saveRow(oTable, nEditing, data.spend);
    		                    nEditing = null;
    						}else{
    							alert("Updated Failue!:)");
    						}
    					},
    					error:function(data){
    						alert("error");
//    						console.log(data);
    					}
                    })    
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow);
                    nEditing = nRow;
                }
            });
        }

    };

}();