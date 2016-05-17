package com.stone.model;

import java.util.List;

public class Table {

	private String basepack;
	private String tablename;
	private List<Field> imports;
	private List<Field> fields;
	private String prefix;
	private String columnPrefix;
	private List<String> foreigns;
	private List<String> indexs;
	private List<String> javaPackages;
	public String getBasepack() {
		return basepack;
	}
	public void setBasepack(String basepack) {
		this.basepack = basepack;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public List<Field> getImports() {
		return imports;
	}
	public void setImports(List<Field> imports) {
		this.imports = imports;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public List<String> getIndexs() {
		return indexs;
	}
	public void setIndexs(List<String> indexs) {
		this.indexs = indexs;
	}
	public List<String> getForeigns() {
		return foreigns;
	}
	public void setForeigns(List<String> foreigns) {
		this.foreigns = foreigns;
	}
	public List<String> getJavaPackages() {
		return javaPackages;
	}
	public void setJavaPackages(List<String> javaPackages) {
		this.javaPackages = javaPackages;
	}
	public String getColumnPrefix() {
		return columnPrefix;
	}
	public void setColumnPrefix(String columnPrefix) {
		this.columnPrefix = columnPrefix;
	}
}
