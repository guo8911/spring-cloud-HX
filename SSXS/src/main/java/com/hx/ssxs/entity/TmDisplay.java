package com.hx.ssxs.entity;

import java.io.Serializable;

public class TmDisplay implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String pk_id;
	private Integer sat_id;
	private String conf_id;
	private Integer real_value;
	private String display_value;
	public String getPk_id() {
		return pk_id;
	}
	public void setPk_id(String pk_id) {
		this.pk_id = pk_id;
	}
	public Integer getSat_id() {
		return sat_id;
	}
	public void setSat_id(Integer sat_id) {
		this.sat_id = sat_id;
	}
	public String getConf_id() {
		return conf_id;
	}
	public void setConf_id(String conf_id) {
		this.conf_id = conf_id;
	}
	public Integer getReal_value() {
		return real_value;
	}
	public void setReal_value(Integer real_value) {
		this.real_value = real_value;
	}
	public String getDisplay_value() {
		return display_value;
	}
	public void setDisplay_value(String display_value) {
		this.display_value = display_value;
	}
	@Override
	public String toString() {
		return "TmDisplay [pk_id=" + pk_id + ", sat_id=" + sat_id + ", conf_id=" + conf_id + ", real_value="
				+ real_value + ", display_value=" + display_value + "]";
	}
	
}
