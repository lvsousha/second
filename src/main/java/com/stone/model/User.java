package com.stone.model;


import java.util.Date;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1447039680623L;

    private Integer id;
    private String username;
    private String password;
    private String email;
    private Date created;
    private Date updated;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}
	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
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