package com.hx.ssxs.entity;

import java.io.Serializable;

public class ViewSxProject implements Serializable  {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int owner;
	private String type;
	private String fk;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFk() {
		return fk;
	}
	public void setFk(String fk) {
		this.fk = fk;
	}
	
}
