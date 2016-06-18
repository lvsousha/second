var uploader;
var Upload = function (){
	return{
		init:function(){
			uploader = new plupload.Uploader({
		        browse_button : 'add_files', //触发文件选择对话框的按钮，为那个元素id
		        url : '/second/upload/upload', //服务器端的上传页面地址
		        max_file_size: '10mb', // 文件上传最大限制。
		        chunk_size: '1mb', // 上传分块每块的大小，这个值小于服务器最大上传限制的值即可。
		        flash_swf_url : 'media/js/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
		        silverlight_xap_url : 'media/js/Moxie.xap', //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
		        drop_element:'filelist',
		        filters:{
		        	mime_types:[{
		            	title: "Image files",
		            	extensions: "jpg,gif,png"
		            },{
		            	title: "Zip files",
		            	extensions: "zip,rar,7z"
		            },{
		            	title: "Office files",
		            	extensions: "doc,xls,xlsx"
		            },{
		            	title: "Other files",
		            	extensions: "*"
		            }],
		            prevent_duplicates : true
		        }
		    });    

		    uploader.init();
		    
		    uploader.bind('FilesAdded',function(uploader,files){
		        $.each(files,function(i){
		        	var file = files[i];
		        	var tr = "<tr id='"+file.id+"''>"+
		        				"<td width='500px' name='name'>"+file.name+"</td>"+
		        				"<td width='200px'>"+file.origSize/1024+"KB</td>"+
		        				"<td width='200px' ><div class='progress progress-striped active'><div name='percent' style='width: "+file.percent+"%;' class='bar'></div></div></td>"+
		        				"<td><button class='btn' onclick='deleteFile(this)'>移除文件</button></td>"+
		        			"</tr>";
		        	$("table#filelist").append(tr);
		        });
		    });
		    uploader.bind('UploadProgress',function(uploader,file){
		    	$("#"+file.id+" [name='percent']").css("width",file.percent+"%");
		    	$("#"+file.id+" [name='percent']").text(file.percent+"%");
		    });
		    uploader.bind('FileUploaded',function(uploader,file,responseObject){
		    	if(responseObject.response != null && responseObject.response != ""){
		    		$("#"+file.id).append("<td>"+responseObject.response+"</td>");
		        	$("#"+file.id+" [name='percent']").get(0).innerHTML = "0%";
		    	}
		    });

		    $("#start_upload").click(function(){
		        uploader.start(); 
		    });
		    
		}
	}
}();

function deleteFile(dom){
	$(dom).parent().parent().remove();
	uploader.removeFile($(dom).parent().parent().attr("id"));
};









