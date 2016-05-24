package com.stone.model;


import java.util.Date;
import java.math.BigDecimal;

import java.io.Serializable;

public class Spend implements Serializable {
	private static final long serialVersionUID = 1447039680623L;

    private Integer id;
    private String subject;
    private BigDecimal price;
    private User createdby;
    private User updatedby;
    private Date created;
    private Date updated;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setSubject(String subject){
		this.subject = subject;
	}

	public String getSubject(){
		return subject;
	}
	public void setPrice(BigDecimal price){
		this.price = price;
	}

	public BigDecimal getPrice(){
		return price;
	}
	public void setCreatedby(User createdby){
		this.createdby = createdby;
	}

	public User getCreatedby(){
		return createdby;
	}
	public void setUpdatedby(User updatedby){
		this.updatedby = updatedby;
	}

	public User getUpdatedby(){
		return updatedby;
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