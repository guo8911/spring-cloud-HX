package com.hx.ssxs.entity;

import java.io.Serializable;
import java.util.Date;

public class SxSatDown implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer pk_id;
	private String datatime;
	private Float longitude;
	private Float latitude;
	private Float altitude;
	private Integer sat_id;
	
	private String sat_name;
	private String sat_code;
	private Integer mid;
	private String lasttime;
	private String endtime;
	public Integer getPk_id() {
		return pk_id;
	}
	public void setPk_id(Integer pk_id) {
		this.pk_id = pk_id;
	}
	public String getDatatime() {
		return datatime;
	}
	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Float getAltitude() {
		return altitude;
	}
	public void setAltitude(Float altitude) {
		this.altitude = altitude;
	}
	public Integer getSat_id() {
		return sat_id;
	}
	public void setSat_id(Integer sat_id) {
		this.sat_id = sat_id;
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
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

}
