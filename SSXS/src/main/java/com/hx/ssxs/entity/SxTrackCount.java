package com.hx.ssxs.entity;

import java.io.Serializable;

public class SxTrackCount implements Serializable  {
	private static final long serialVersionUID = 1L;
	private int pk_id;
	private String dataTime;
	private String half_roller;
	private String bias;
	private String tend_corner;
	private String spot_equator;
	private String place_angle;
	private String flat_angle;
	private int sat_id;
	private String submiter_id;
	private String submit_time;
	public int getPk_id() {
		return pk_id;
	}
	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
	}
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public String getHalf_roller() {
		return half_roller;
	}
	public void setHalf_roller(String half_roller) {
		this.half_roller = half_roller;
	}
	public String getBias() {
		return bias;
	}
	public void setBias(String bias) {
		this.bias = bias;
	}
	public String getTend_corner() {
		return tend_corner;
	}
	public void setTend_corner(String tend_corner) {
		this.tend_corner = tend_corner;
	}
	public String getSpot_equator() {
		return spot_equator;
	}
	public void setSpot_equator(String spot_equator) {
		this.spot_equator = spot_equator;
	}
	public String getPlace_angle() {
		return place_angle;
	}
	public void setPlace_angle(String place_angle) {
		this.place_angle = place_angle;
	}
	public String getFlat_angle() {
		return flat_angle;
	}
	public void setFlat_angle(String flat_angle) {
		this.flat_angle = flat_angle;
	}
	public int getSat_id() {
		return sat_id;
	}
	public void setSat_id(int sat_id) {
		this.sat_id = sat_id;
	}
	public String getSubmiter_id() {
		return submiter_id;
	}
	public void setSubmiter_id(String submiter_id) {
		this.submiter_id = submiter_id;
	}
	public String getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(String submit_time) {
		this.submit_time = submit_time;
	}

}
