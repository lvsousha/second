$("#save").click(function(){
	var id = $("#spend [name='id']").val();
	var url = "/second/spend/insertRow";
	if(id != null && id != ""){
		url = "/second/spend/updateRow";
	}
	console.log(url);
	$.ajax({
		url : url,
		data :$("#spend").serialize(),
		type : "post",
		dataType : "json",
		cache : false,
		async : true,
		success : function(data){
			if(data.success == true){
				var spend = data.spend;
				console.log(spend);
				setValues("spend",spend);
				table.draw();
			}else{
				alert(data);
			}
		},
		error:function(data){
			console.log(data);
		}
	});
	
});

$("#new").click(function(){
	var fields = $("#spend").serializeArray();
	$.each(fields, function(i, field){
		temp = "";
		$("#spend [name='"+field.name+"']").attr("value",temp);
	});
});
