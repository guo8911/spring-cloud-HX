package com.hx.ssxs.entity;

public class SxProjectSat extends SxProject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sat_id;
    private Integer mid;
    private String sat_name;
    private String sat_code;
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
