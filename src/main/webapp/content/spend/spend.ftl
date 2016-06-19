<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />
	<title>Metronic | UI Features - Tabs & Accordions</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/bootstrap-responsive.min.css" rel= "stylesheet" type="text/css"/>
	<link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel= "stylesheet" type="text/css" href="media/css/select2_metro.css" />
	<link rel="stylesheet" href="media/css/DT_bootstrap.css" />
	<!-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="media/image/favicon.ico" />

</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
	<#include "common/head.ftl">
	<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->
	<div class="page-container row-fluid">

		<!-- BEGIN SIDEBAR -->
		<#include "common/left.ftl">
		<!-- END SIDEBAR -->

		<!-- BEGIN PAGE -->
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div id="portlet-config" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button"></button>
					<h3>portlet Settings</h3>
				</div>
				<div class="modal-body">
					<p>Here will be a configuration form</p>
				</div>
			</div>
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
				
				<!-- BEGIN PAGE HEADER-->
				<#include "common/page-head.ftl">
				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid ">
					<div class="span12">
						<!-- BEGIN INLINE TABS PORTLET-->
						<div class="portlet box green">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>Inline Tabs</div>
								<div class="tools">
									<button id="export" class="btn green start" >
										<i class="icon-upload icon-white" ></i>
										<span>Export</span>
									</button>
								</div>
							</div>
							<div class="portlet-body">
								<div class="row-fluid">
									<div class="span12">
										<!--BEGIN TABS-->
										<div class="tabbable tabbable-custom">
											<ul class="nav nav-tabs">
												<li class="active"><a href="#tab_1_1" data-toggle="tab">Spend List</a></li>
												<li><a href="#tab_1_2" data-toggle="tab">Spend Form</a></li>
												<li><a href="#tab_1_3" data-toggle="tab">Section 3</a></li>
											</ul>
											<div class="tab-content">
												<div class="tab-pane active" id="tab_1_1">
													<table class="table table-striped table-hover table-bordered" id="spendtable">
														<thead>
															<tr>
																<th></th>
																<th></th>
																<th></th>
																<th></th>
																<th></th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
												<div class="tab-pane" id="tab_1_2">
													<!-- BEGIN FORM-->	
													<form class="form-horizontal" name="spend" id="spend">	
														<div class="control-group hidden">	
															<label class="control-label">Id</label>	
															<div class="controls">	
																<input name="id" type="text" placeholder="id" class="m-wrap small" />
															</div>	
														</div>
														<div class="control-group">	
															<label class="control-label">Subject</label>	
															<div class="controls">	
																<input name="subject" type="text" placeholder="subject" class="m-wrap small" />
															</div>	
														</div>
														<div class="control-group">
															<label class="control-label">Price<span class="required">*</span></label>	
															<div class="controls">
																<input name="price" type="text" class="span6 m-wrap"/>	
															</div>	
														</div>
														<div class="control-group">
															<label class="control-label">CreatedBy</label>	
															<div class="controls">	
																<input name="createdby.username" class="span6 m-wrap" type="text" placeholder="CreatedBy" readonly />
															</div>	
														</div>
														<div class="control-group">	
															<label class="control-label">Created</label>	
															<div class="controls">	
																<div class="input-append date form_datetime">	
																	<input name="created" size="16" type="text" value="" readonly class="m-wrap">	
																	<span class="add-on"><i class="icon-calendar"></i></span>	
																</div>	
															</div>	
														</div>
														<div class="form-actions">	
															<button type="button" id="new" class="btn">New</button>
															<button type="button" id="save" class="btn blue"><i class="icon-ok"></i> Save</button>	
															<button type="button" class="btn">Cancel</button>	
														</div>	
													</form>	
													<!-- END FORM--> 
												</div>
												<div class="tab-pane" id="tab_1_3">
													<p>Howdy, I'm in Section 3.</p>
													<p>
														Duis autem vel eum iriure dolor in hendrerit in vulputate.
														Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat
													</p>
													<p>
														<a class="btn yellow" href="ui_tabs_accordions.html#tab_1_3" target="_blank">Activate this tab via URL</a>
													</p>
												</div>
											</div>
										</div>
										<!--END TABS-->
									</div>									
								</div>							
							</div>
						</div>
						<!-- END INLINE TABS PORTLET-->
					</div>
				</div>
			</div>
		</div>
	<div>
	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="media/js/excanvas.min.js"></script>
	<script src="media/js/respond.min.js"></script>  
	<![endif]-->   
	<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript"  src="media/js/select2.min.js"></script>
	<script type="text/javascript"  src="DataTables-1.10.7/media/js/jquery.dataTables.js"></script>
	<script type="text/javascript"  src="media/js/DT_bootstrap.js"></script>
	<script type="text/javascript" src="media/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="media/js/additional-methods.min.js"></script>
	<script type="text/javascript" src="media/js/chosen.jquery.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- END CORE PLUGINS -->

	<script src="media/js/app.js"></script>      
	<script src="js/spend/table.js"></script>     
	<script src="js/spend/spendform.js"></script>
	<script src="media/js/form-validation.js"></script>
	<script>

		jQuery(document).ready(function() {       

		   // initiate layout and plugins
			
		   App.init();
		   TableEditable.init();
		   
//		   FormValidation.init();
		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>