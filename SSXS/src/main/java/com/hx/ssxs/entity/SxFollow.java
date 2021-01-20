package com.hx.ssxs.entity;

import java.io.Serializable;

public class SxFollow implements Serializable  {
	private static final long serialVersionUID = 1L;
	private int pk_id;
	private String start_time;
	private String end_time;
	private String device_identify;
	private String arc_identify;
	private int arc_length;
	private Float most_elevation;
	private Float pull_bear;
	private Float pull_elevation;
	private Float off_bear;
	private Float off_elevation;
	private int sat_id;
	private String submiter_id;
	private String submit_time;
	private String guoding_time;
	public int getPk_id() {
		return pk_id;
	}
	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getDevice_identify() {
		return device_identify;
	}
	public void setDevice_identify(String device_identify) {
		this.device_identify = device_identify;
	}
	public String getArc_identify() {
		return arc_identify;
	}
	public void setArc_identify(String arc_identify) {
		this.arc_identify = arc_identify;
	}
	public int getArc_length() {
		return arc_length;
	}
	public void setArc_length(int arc_length) {
		this.arc_length = arc_length;
	}
	public Float getMost_elevation() {
		return most_elevation;
	}
	public void setMost_elevation(Float most_elevation) {
		this.most_elevation = most_elevation;
	}
	public Float getPull_bear() {
		return pull_bear;
	}
	public void setPull_bear(Float pull_bear) {
		this.pull_bear = pull_bear;
	}
	public Float getPull_elevation() {
		return pull_elevation;
	}
	public void setPull_elevation(Float pull_elevation) {
		this.pull_elevation = pull_elevation;
	}
	public Float getOff_bear() {
		return off_bear;
	}
	public void setOff_bear(Float off_bear) {
		this.off_bear = off_bear;
	}
	public Float getOff_elevation() {
		return off_elevation;
	}
	public void setOff_elevation(Float off_elevation) {
		this.off_elevation = off_elevation;
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
	public String getGuoding_time() {
		return guoding_time;
	}
	public void setGuoding_time(String guoding_time) {
		this.guoding_time = guoding_time;
	}

}
