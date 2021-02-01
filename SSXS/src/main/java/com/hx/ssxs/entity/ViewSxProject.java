package com.hx.ssxs.entity;

import java.io.Serializable;

public class ViewSxProject implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer owner;
	private String type;
	private String fk;
	private String icon;
	private Integer sat_id;
	private Integer mid;
	private String sat_code;
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
	public String getFk() {
		return fk;
	}
	public void setFk(String fk) {
		this.fk = fk;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSat_id() {
		return sat_id;
	}
	public void setSat_id(Integer sat_id) {
		this.sat_id = sat_id;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getSat_code() {
		return sat_code;
	}
	public void setSat_code(String sat_code) {
		this.sat_code = sat_code;
	}
	
}
