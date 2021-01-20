package com.hx.ssxs.entity;

import java.io.Serializable;

public class SatNet implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String pk_id;
	private String ip;
	private String port;
	private String net_name;
	private String net_code;
	private String sat_id;
	private String net_desc;
	private String type;
	private String mid;
	private String sat_name;
	private String sat_code;
	public String getPk_id() {
		return pk_id;
	}
	public void setPk_id(String pk_id) {
		this.pk_id = pk_id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getNet_name() {
		return net_name;
	}
	public void setNet_name(String net_name) {
		this.net_name = net_name;
	}
	public String getNet_code() {
		return net_code;
	}
	public void setNet_code(String net_code) {
		this.net_code = net_code;
	}
	public String getSat_id() {
		return sat_id;
	}
	public void setSat_id(String sat_id) {
		this.sat_id = sat_id;
	}
	public String getNet_desc() {
		return net_desc;
	}
	public void setNet_desc(String net_desc) {
		this.net_desc = net_desc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getSat_name() {
		return sat_name;
	}
	public void setSat_name(String sat_name) {
		this.sat_name = sat_name;
	}
	public String getSat_code() {
		return sat_code;
	}
	public void setSat_code(String sat_code) {
		this.sat_code = sat_code;
	}

}
