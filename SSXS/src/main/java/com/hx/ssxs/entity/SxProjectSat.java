package com.hx.ssxs.entity;

public class SxProjectSat extends SxProject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sat_id;
    private int mid;
    private String sat_name;
    private String sat_code;
	public int getSat_id() {
		return sat_id;
	}
	public void setSat_id(int sat_id) {
		this.sat_id = sat_id;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
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
