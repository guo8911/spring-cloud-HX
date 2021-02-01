package com.hx.edit.entity;

import java.io.Serializable;

public class SxCheckout implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer proj_id;
	private String user_id;
	private String data;
	public Integer getProj_id() {
		return proj_id;
	}
	public void setProj_id(Integer proj_id) {
		this.proj_id = proj_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
