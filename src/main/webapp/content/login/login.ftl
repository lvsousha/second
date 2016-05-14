<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>Metronic | Login Page</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />" +
			"
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="${request.contextPath}/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath}/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath}/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath}/media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath}/media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath}/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath}/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${request.contextPath}/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="${request.contextPath}/media/css/login.css" rel="stylesheet" type="text/css"/>
	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="${request.contextPath}/media/image/favicon.ico" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<img src="${request.contextPath}/media/image/logo-big.png" alt="" /> 
	</div>
	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->
	<div class="content">
		<#include  "login/loginForm.ftl">
		<#include  "login/forgetPasswordForm.ftl">
		<#include  "login/registerForm.ftl">  
	</div>
	<!-- END LOGIN -->

	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">
		2013 &copy; Metronic. Admin Dashboard Template.
	</div>
	<!-- END COPYRIGHT -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->
	<script src="${request.contextPath}/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="${request.contextPath}/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${request.contextPath}/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="${request.contextPath}/media/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>
	<script src="${request.contextPath}/media/js/excanvas.min.js"></script>
	<script src="${request.contextPath}/media/js/respond.min.js"></script>  
	<![endif]-->   

	<script src="${request.contextPath}/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${request.contextPath}/media/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="${request.contextPath}/media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="${request.contextPath}/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${request.contextPath}/media/js/jquery.validate.min.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="${request.contextPath}/media/js/app.js" type="text/javascript"></script>
	<script src="${request.contextPath}/media/js/login.js" type="text/javascript"></script>      
	<!-- END PAGE LEVEL SCRIPTS --> 

	<script>

		jQuery(document).ready(function() {     
		  App.init();
		  Login.init();
		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>