var Login = function () {
    
    return {
        //main function to initiate the module
        init: function () {
        	
           $('.login-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	                username: {
	                    required: "Username is required."
	                },
	                password: {
	                    required: "Password is required."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   
	            	$('.alert-error', $('.login-form')).text('Enter any username and password');
	            	$('.alert-error', $('.login-form')).show();
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	            	label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	                $.ajax({
	                	url : "/second/content/login",
						data : $(".login-form").serialize(),
						type : "post",
						dataType : "json",
						cache : false,
						async : true,
						success : function(data){
							console.log("click_success");
							if(data.success == true){
								window.location.href = "/second/index";
							}else{
								$('.alert-error', $('.login-form')).text(data.errorMessage);
				            	$('.alert-error', $('.login-form')).show();
							}
						},
						error:function(data){
							alert("error");
							console.log(data);
						}
	                })
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                	$.ajax({
		                	url : "/second/content/login",
							data : $(".login-form").serialize(),
							type : "post",
							dataType : "json",
							cache : false,
							async : true,
							success : function(data){
								console.log("enter_key_success");
								if(data.success == true){
									window.location.href = "/second/index";
								}else{
									$('.alert-error', $('.login-form')).text(data.errorMessage);
					            	$('.alert-error', $('.login-form')).show();
								}
							},
							error:function(data){
								alert("error");
								console.log(data);
							}
		                })
	                }
	                return false;
	            }
	        });

	        $('.forget-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                email: {
	                    required: true,
	                    email: true
	                }
	            },

	            messages: {
	                email: {
	                    required: "Email is required."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   

	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element).closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
                	$.ajax({
	                	url : "/second/content/forgetPassword",
						data : $(".forget-form").serialize(),
						type : "post",
						dataType : "json",
						cache : false,
						async : true,
						success : function(data){
							console.log("enter_key_success");
							if(data.success == true){
								$('.login-form').show();
					            $('.forget-form').hide();
							}else{
								$('.alert-error', $('.forget-form')).text(data.errorMessage);
				            	$('.alert-error', $('.forget-form')).show();
							}
						},
						error:function(data){
							alert("error");
							console.log(data);
						}
	                })
                }
	        });

	        $('.forget-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.forget-form').validate().form()) {
	                	$.ajax({
		                	url : "/second/content/forgetPassword",
							data : $(".forget-form").serialize(),
							type : "post",
							dataType : "json",
							cache : false,
							async : true,
							success : function(data){
								console.log("enter_key_success");
								if(data.success == true){
									$('.login-form').show();
						            $('.forget-form').hide();
								}else{
									$('.alert-error', $('.forget-form')).text(data.errorMessage);
					            	$('.alert-error', $('.forget-form')).show();
								}
							},
							error:function(data){
								alert("error");
								console.log(data);
							}
		                })
	                }
	                return false;
	            }
	        });

	        jQuery('#forget-password').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.forget-form').show();
	        });

	        jQuery('#back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.forget-form').hide();
	        });

	        $('.register-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                rpassword: {
	                    equalTo: "#register_password"
	                },
	                email: {
	                    required: true,
	                    email: true
	                },
	                tnc: {
	                    required: true
	                }
	            },

	            messages: { // custom messages for radio buttons and checkboxes
	                tnc: {
	                    required: "Please accept TNC first."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   

	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                if (element.attr("name") == "tnc") { // insert checkbox errors after the container                  
	                    error.addClass('help-small no-left-padding').insertAfter($('#register_tnc_error'));
	                } else {
	                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	                }
	            },

	            submitHandler: function (form) {
	                $.ajax({
	                	url : "/second/content/register",
						data : $(".register-form").serialize(),
						type : "post",
						dataType : "json",
						cache : false,
						async : true,
						success : function(data){
							console.log("click_success");
							if(data.success == true){
								window.location.href = "/second/index";
							}else{
								$('.alert-error', $('.register-form')).text(data.errorMessage);
				            	$('.alert-error', $('.register-form')).show();
							}
						},
						error:function(data){
							alert("error");
							console.log(data);
						}
	                })
	            }
	        });
	        
	        $('.register-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.register-form').validate().form()) {
	                	$.ajax({
		                	url : "/second/content/register",
							data : $(".register-form").serialize(),
							type : "post",
							dataType : "json",
							cache : false,
							async : true,
							success : function(data){
								console.log("enter_key_success");
								if(data.success == true){
									window.location.href = "/second/index";
								}else{
									$('.alert-error', $('.register-form')).text(data.errorMessage);
					            	$('.alert-error', $('.register-form')).show();
								}
							},
							error:function(data){
								alert("error");
								console.log(data);
							}
		                })
	                }
	                return false;
	            }
	        });

	        jQuery('#register-btn').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.register-form').show();
	        });

	        jQuery('#register-back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.register-form').hide();
	        });
        }

    };

}();