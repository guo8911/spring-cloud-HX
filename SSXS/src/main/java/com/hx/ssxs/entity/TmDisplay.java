package com.hx.ssxs.entity;

import java.io.Serializable;

public class TmDisplay implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String pk_id;
	private int sat_id;
	private String conf_id;
	private int real_value;
	private String display_value;
	public String getPk_id() {
		return pk_id;
	}
	public void setPk_id(String pk_id) {
		this.pk_id = pk_id;
	}
	public int getSat_id() {
		return sat_id;
	}
	public void setSat_id(int sat_id) {
		this.sat_id = sat_id;
	}
	public String getConf_id() {
		return conf_id;
	}
	public void setConf_id(String conf_id) {
		this.conf_id = conf_id;
	}
	public int getReal_value() {
		return real_value;
	}
	public void setReal_value(int real_value) {
		this.real_value = real_value;
	}
	public String getDisplay_value() {
		return display_value;
	}
	public void setDisplay_value(String display_value) {
		this.display_value = display_value;
	}

}
