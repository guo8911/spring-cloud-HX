package com.hx.edit.entity;

import java.io.Serializable;

public class SxProject implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer owner;
	private String type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
