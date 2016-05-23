<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.stone.mapper.${table.tablename?cap_first}Mapper">

    <resultMap type="com.stone.model.${table.tablename?cap_first}" id="${table.tablename}Result">
        <#list table.fields as field>
        	<#if !field.references??>
        		<result property="${field.name}" column="${field.prefix}${field.name}" />
        	</#if>
        </#list>
        <#list table.fields as field>
    		<#if field.references??>
    			<association property="${field.name}" column="${field.prefix}${field.name}" select="com.stone.mapper.${field.name?cap_first}Mapper.select${table.tablename?cap_first}"/>
    		</#if>
    </#list>
    </resultMap>

    <insert id="insert" parameterType="com.stone.model.${table.tablename?cap_first}">
		insert into ${table.prefix}${table.tablename}s(
				<#list table.fields as field>
				<#if field_index gt 0 >
					<#if field_has_next>
						${field.prefix}${field.name},
					<#else>
						${field.prefix}${field.name}
					</#if>
				</#if>
		        </#list>
		) values (
				<#list table.fields as field>
				<#if field_index gt 0 >
				<#if field.references??>
					<choose><when test="${field.name} == null">NULL</when><otherwise>${r'#{'}${field.name}.id,jdbctype=INTEGER${r'}'}</otherwise></choose><#if field_has_next>,</#if>
				<#else>
					${r'#{'}${field.name},jdbctype=${field.inserttype}${r'}'}<#if field_has_next>,</#if>
				</#if>
				</#if>
		        </#list>
		)
	</insert>

    <select id="select" parameterType="int" resultMap="${table.tablename}Result">
        select *
        	from ${table.prefix}${table.tablename}s where ${table.columnPrefix}id=${r'#{'}id${r'}'}
    </select>
 

    <update id="update" parameterType="com.stone.model.${table.tablename?cap_first}">
        update
        	${table.prefix}${table.tablename}s set
        	<#list table.fields as field>
        		<#if field.references??>
        			${field.prefix}${field.name}=<choose><when test="${field.name} == null">NULL</when><otherwise>${r'#{'}${field.name}.id,jdbctype=INTEGER${r'}'}</otherwise></choose><#if field_has_next>,</#if>
        		<#else>
        			${field.prefix}${field.name}=${r'#{'}${field.name}${r'}'}<#if field_has_next>,</#if>
        		</#if>
	        </#list>
        	where ${table.columnPrefix}id=${r'#{'}id${r'}'}
    </update>

    <delete id="delete" parameterType="int">
        delete
        	from ${table.prefix}${table.tablename}s
        	where ${table.columnPrefix}id=${r'#{'}id${r'}'}
    </delete>

</mapper>