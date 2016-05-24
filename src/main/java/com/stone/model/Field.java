package com.stone.model;

import java.util.List;

public class Field {

	private String javatype;
	private String jdbctype;
	private String inserttype;
	private String name;
	private String length;
	private String key;
	private String references;
	private String defaultValue;
	private String precision;
	private String scale;
	private String prefix;
	private String index;
	private String increase = "";
	private String isNull = " null";
	private String simpleConstraint;
	private List<String> foreigns;
	private List<String> indexs;
	public String getJavatype() {
		return javatype;
	}
	public void setJavatype(String javatype) {
		this.javatype = javatype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJdbctype() {
		return jdbctype;
	}
	public void setJdbctype(String jdbctype) {
		this.jdbctype = jdbctype;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getReferences() {
		return references;
	}
	public void setReferences(String references) {
		this.references = references;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	private Boolean istable = false;
	public Boolean getIstable() {
		return istable;
	}
	public void setIstable(Boolean istable) {
		this.istable = istable;
	}
	public String getInserttype() {
		return inserttype;
	}
	public void setInserttype(String inserttype) {
		this.inserttype = inserttype;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getIncrease() {
		return increase;
	}
	public void setIncrease(String increase) {
		this.increase = increase;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public List<String> getForeigns() {
		return foreigns;
	}
	public void setForeigns(List<String> foreigns) {
		this.foreigns = foreigns;
	}
	public List<String> getIndexs() {
		return indexs;
	}
	public void setIndexs(List<String> indexs) {
		this.indexs = indexs;
	}
	public String getSimpleConstraint() {
		return simpleConstraint;
	}
	public void setSimpleConstraint(String simpleConstraint) {
		this.simpleConstraint = simpleConstraint;
	}

}
