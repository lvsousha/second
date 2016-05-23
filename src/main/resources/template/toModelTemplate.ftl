package com.stone.model;

<#list table.fields as field>
<#if field.reference??>
import com.stone.model.${field.reference?cap_first};
</#if>
</#list>

<#list table.javaPackages as javaPackage>
import ${javaPackage};
</#list>

import java.io.Serializable;

public class ${table.tablename?cap_first} implements Serializable {
	private static final long serialVersionUID = 1447039680623L;

<#list table.fields as field>
    private ${field.javatype} ${field.name};
</#list>

<#list table.fields as field>
	public void set${field.name?cap_first}(${field.javatype} ${field.name}){
		this.${field.name} = ${field.name};
	}

	public ${field.javatype} get${field.name?cap_first}(){
		return ${field.name};
	}
</#list>
}