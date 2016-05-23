package com.stone.model;


import java.util.Date;

import java.io.Serializable;

public class Menu implements Serializable {
	private static final long serialVersionUID = 1447039680623L;

    private Integer id;
    private String menuname;
    private Menu parentmenu;
    private Date created;
    private Date updated;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setMenuname(String menuname){
		this.menuname = menuname;
	}

	public String getMenuname(){
		return menuname;
	}
	public void setParentmenu(Menu parentmenu){
		this.parentmenu = parentmenu;
	}

	public Menu getParentmenu(){
		return parentmenu;
	}
	public void setCreated(Date created){
		this.created = created;
	}

	public Date getCreated(){
		return created;
	}
	public void setUpdated(Date updated){
		this.updated = updated;
	}

	public Date getUpdated(){
		return updated;
	}
}