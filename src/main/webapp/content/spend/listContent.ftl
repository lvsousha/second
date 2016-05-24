<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box green">
							<div class="portlet-title">
								<div class="caption"><i class="icon-globe"></i>Responsive Table With Expandable details</div>
								<div class="tools">
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
							<div class="portlet-body">
								<table class="table table-striped table-bordered table-hover table-full-width" id="sample_1">
									<thead>
										<tr>
											<th>Id</th>
											<th>Subject</th>
											<th class="hidden-480">Price</th>
											<th class="hidden-480">Created</th>
											<th class="hidden-480">CreatedBy</th>
										</tr>
									</thead>
									<tbody>
									<#list listcontent as spend>
										<tr>
											<td>spend.id</td>
											<td>spend.subject</td>
											<td>spend.price</td>
											<td>spend.created</td>
											<td>spend.createdby.username</td>
										</tr>
									</#list>									
									</tbody>
								</table>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->

						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box blue">
							<div class="portlet-title">
								<div class="caption"><i class="icon-globe"></i>Show/Hide Columns</div>
								<div class="actions">
									<div class="btn-group">
										<a class="btn" href="#" data-toggle="dropdown">
										Columns
										<i class="icon-angle-down"></i>
										</a>
										<div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
											<label><input type="checkbox" checked data-column="0">Rendering engine</label>
											<label><input type="checkbox" checked data-column="1">Browser</label>
											<label><input type="checkbox" checked data-column="2">Platform(s)</label>
											<label><input type="checkbox" checked data-column="3">Engine version</label>
											<label><input type="checkbox" checked data-column="4">CSS grade</label>
										</div>
									</div>
								</div>
							</div>
							<div class="portlet-body">
								<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
									<thead>
										<tr>
											<th>Id</th>
											<th>Subject</th>
											<th class="hidden-480">Price</th>
											<th class="hidden-480">Created</th>
											<th class="hidden-480">CreatedBy</th>
										</tr>
									</thead>
									<tbody>
									<#list listcontent as spend>
										<tr>
											<td>spend.id</td>
											<td>spend.subject</td>
											<td>spend.price</td>
											<td>spend.created</td>
											<td>spend.createdby.username</td>
										</tr>
									</#list>
									</tbody>
								</table>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>
<!-- END PAGE CONTENT-->