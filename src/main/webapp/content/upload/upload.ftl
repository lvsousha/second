<!DOCTYPE html>
<#-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>Metronic | UI Features - Tabs & Accordions</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<#-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href= "media/css/bootstrap-responsive.min.css" rel= "stylesheet" type="text/css"/>
	<link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<#-- END GLOBAL MANDATORY STYLES -->
	<#-- BEGIN PAGE LEVEL STYLES -->
	<link rel= "stylesheet" type="text/css" href="media/css/select2_metro.css" />
	<link rel="stylesheet" href="media/css/DT_bootstrap.css" />
	<#-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="media/image/favicon.ico" />

</head>
<#-- END HEAD -->

<#-- BEGIN BODY -->
<body class="page-header-fixed">
	<#-- BEGIN HEADER -->
	<#include "common/head.ftl">
	<#-- END HEADER -->

	<#-- BEGIN CONTAINER -->
	<div class="page-container row-fluid">

		<#-- BEGIN SIDEBAR -->
		<#include "common/left.ftl">
		<#-- END SIDEBAR -->

		<#-- BEGIN PAGE -->
		<div class="page-content">
			<#-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div id="portlet-config" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button"></button>
					<h3>portlet Settings</h3>
				</div>
				<div class="modal-body">
					<p>Here will be a configuration form</p>
				</div>
			</div>
			<#-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<#-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<#-- BEGIN PAGE HEADER-->
				<#include "common/page-head.ftl">
				<#-- END PAGE HEADER-->

				<#-- BEGIN PAGE CONTENT-->
				<div class="row-fluid" >
					<div class="span12" >
						<div class="portlet box blue"  >
							<div class="portlet-title">
								<div class="caption"><i class="icon-cogs"></i>Upload Files</div>
								<div class="tools">
									<button id="add_files" class="btn green start" >
										<i class="icon-upload icon-white" ></i>
										<span>Add Files....</span>
									</button>
									<button id="start_upload" class="btn yellow start" >
										<i class="icon-upload icon-white"></i>
										<span>Start upload</span>
									</button>
								</div>
							</div>
							<div class="portlet-body" >
								<table id="filelist" role="presentation" class="table table-striped">	
									<tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>	
								</table>	
							</div>
						</div>
					</div>	
				</div>
			</div>
		</div>
	</div>
	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<#-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>
	<#--[if lt IE 9]>
	<script src="media/js/excanvas.min.js"></script>
	<script src="media/js/respond.min.js"></script>  
	<#--[endif]-->   
	<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<#-- END CORE PLUGINS -->
	<#-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="media/js/plupload.full.min.js"></script>
	<#-- END PAGE LEVEL PLUGINS -->
	<#-- END CORE PLUGINS -->

	<script src="media/js/app.js"></script>      
	<script src="js/upload/upload.js"></script>     
	<script>

		jQuery(document).ready(function() {       

		   // initiate layout and plugins
			
		   App.init();
		   Upload.init();
		   
//		   FormValidation.init();
		});

	</script>

	<#-- END JAVASCRIPTS -->

</body>

<#-- END BODY -->

</html>