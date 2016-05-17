
create table ${table.prefix}${table.tablename}s(
	<#list table.fields as field>
		${field.prefix}${field.name} ${field.jdbctype} ${field.simpleConstraint}<#if field_has_next>,</#if>
	</#list>
	<#list table.foreigns as foreign>
		${foreign}<#if foreign_has_next>,</#if>
	</#list>
	)
	<#list table.indexs as ind>
		${index}<#if index_has_next>,</#if>
	</#list>
