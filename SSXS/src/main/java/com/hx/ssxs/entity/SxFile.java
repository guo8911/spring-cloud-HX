package com.hx.ssxs.entity;

import java.io.Serializable;
import java.util.Date;

public class SxFile implements Serializable  {
	private static final long serialVersionUID = 1L;
	private int proj_id;
	private String data;
	private Date date;
	private String user_id;
	public int getProj_id() {
		return proj_id;
	}
	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
