<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zcl.dao.${table.name?cap_first}Dao">

    <resultMap type="com.zcl.model.${table.name?cap_first}" id="${table.name}Result">
        <#list table.fields as field>
        	<#if !field.reference??>
        		<result property="${field.name}" column="${field.prefix}${field.name}" />
        	</#if>
        </#list>
        <#list table.fields as field>
    		<#if field.reference??>
    			<association property="${field.name}" column="${field.prefix}${field.name}" select="com.zcl.dao.${field.name?cap_first}Dao.select"/>
    		</#if>
    </#list>
    </resultMap>

    <insert id="insert" parameterType="com.zcl.model.${table.name?cap_first}">
		insert into ${table.prefix}${table.name}s(
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
				<#if field.reference??>
					<choose><when test="${field.name} == null">NULL</when><otherwise>${r'#{'}${field.name}.id,jdbcType=INTEGER${r'}'}</otherwise></choose><#if field_has_next>,</#if>
				<#else>
					${r'#{'}${field.name},jdbcType=${field.insertType}${r'}'}<#if field_has_next>,</#if>
				</#if>
				</#if>
		        </#list>
		)
	</insert>

    <select id="selectAll" resultMap="${table.name}Result">
        select *
        	from ${table.prefix}${table.name}s
    </select>

    <select id="select" parameterType="int" resultMap="${table.name}Result">
        select *
        	from ${table.prefix}${table.name}s where ${table.columnPrefix}id=${r'#{'}id${r'}'}
    </select>
    
    <select id="selectByName" parameterType="String" resultMap="${table.name}Result">
    select *
    	from ${table.prefix}${table.name}s where ${table.columnPrefix}name=${r'#{'}name${r'}'}
</select>

    <update id="update" parameterType="com.zcl.model.${table.name?cap_first}">
        update
        	${table.prefix}${table.name}s set
        	<#list table.fields as field>
        		<#if field.reference??>
        			${field.prefix}${field.name}=<choose><when test="${field.name} == null">NULL</when><otherwise>${r'#{'}${field.name}.id,jdbcType=INTEGER${r'}'}</otherwise></choose><#if field_has_next>,</#if>
        		<#else>
        			${field.prefix}${field.name}=${r'#{'}${field.name}${r'}'}<#if field_has_next>,</#if>
        		</#if>
	        </#list>
        	where ${table.columnPrefix}id=${r'#{'}id${r'}'}
    </update>

    <delete id="delete" parameterType="int">
        delete
        	from ${table.prefix}${table.name}s
        	where ${table.columnPrefix}id=${r'#{'}id${r'}'}
    </delete>

</mapper>