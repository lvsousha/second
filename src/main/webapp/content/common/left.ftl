<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar nav-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->        
			<ul class="page-sidebar-menu">
				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone"></div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li>
					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
					<form class="sidebar-search">
						<div class="input-box">
							<a href="javascript:;" class="remove"></a>
							<input type="text" placeholder="Search..." />
							<input type="button" class="submit" value=" " />
						</div>
					</form>
					<!-- END RESPONSIVE QUICK SEARCH FORM -->
				</li>
				<li class="start">
					<a href="index.html">
						<i class="icon-home"></i> 
						<span class="title">Dashboard</span>
						<span class="selected"></span>
					</a>
				</li>
			<#list left?keys as key>
			<#if parentName=key >
				<li class="active">
			<#else>
				<li class="">
			</#if>
					<a href="javascript:;">
						<i class="icon-cogs"></i> 
						<span class="title">${key}</span>
						<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
					<#list left[key] as menu>
					<#if childName=menu.menuname >
						<li class='active'>
					<#else>
						<li >
					</#if>
							<a href="${menu.url}">${menu.menuname}</a>
						</li>
					</#list>
					</ul>
				</li>
			</#list>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
		<!-- END SIDEBAR -->