package com.hx.ssxs.entity;

import java.io.Serializable;

public class DeviceInfo implements Serializable  {
	private static final long serialVersionUID = 1L;
	private int pk_id;
	private String device_coding;
	private String device_name;
	private String device_manufact;
	private String device_version;
	private String remark;
	private String d_task_type;
	private String state;
	private String longitude;
	private String latitude;
	private String height;
	private String work_state;
	private int mid;
	private String device_type;
	private String track_lead_time;
	private String aerial_number;
	public int getPk_id() {
		return pk_id;
	}
	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
	}
	public String getDevice_coding() {
		return device_coding;
	}
	public void setDevice_coding(String device_coding) {
		this.device_coding = device_coding;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getDevice_manufact() {
		return device_manufact;
	}
	public void setDevice_manufact(String device_manufact) {
		this.device_manufact = device_manufact;
	}
	public String getDevice_version() {
		return device_version;
	}
	public void setDevice_version(String device_version) {
		this.device_version = device_version;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getD_task_type() {
		return d_task_type;
	}
	public void setD_task_type(String d_task_type) {
		this.d_task_type = d_task_type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWork_state() {
		return work_state;
	}
	public void setWork_state(String work_state) {
		this.work_state = work_state;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getTrack_lead_time() {
		return track_lead_time;
	}
	public void setTrack_lead_time(String track_lead_time) {
		this.track_lead_time = track_lead_time;
	}
	public String getAerial_number() {
		return aerial_number;
	}
	public void setAerial_number(String aerial_number) {
		this.aerial_number = aerial_number;
	}

}
