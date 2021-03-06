package com.hx.ssxs.entity;

import java.io.Serializable;
import java.util.Date;

public class Tm implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer tm_id;
	private Integer sat_id;
	private Integer tm_num;
	private String tm_name;
	private String tm_alias;
	private String tm_code;
	private Integer tm_type;
	private String tm_bdh;
	private Integer status;
	private String create_user;
	private String create_time;
	private Integer data_type;
	private String expression;
	private String tm_unit;
	private Integer start;
	private Integer size;
	public Integer getTm_id() {
		return tm_id;
	}
	public void setTm_id(Integer tm_id) {
		this.tm_id = tm_id;
	}
	public Integer getSat_id() {
		return sat_id;
	}
	public void setSat_id(Integer sat_id) {
		this.sat_id = sat_id;
	}
	public Integer getTm_num() {
		return tm_num;
	}
	public void setTm_num(Integer tm_num) {
		this.tm_num = tm_num;
	}
	public String getTm_name() {
		return tm_name;
	}
	public void setTm_name(String tm_name) {
		this.tm_name = tm_name;
	}
	public String getTm_alias() {
		return tm_alias;
	}
	public void setTm_alias(String tm_alias) {
		this.tm_alias = tm_alias;
	}
	public String getTm_code() {
		return tm_code;
	}
	public void setTm_code(String tm_code) {
		this.tm_code = tm_code;
	}
	public Integer getTm_type() {
		return tm_type;
	}
	public void setTm_type(Integer tm_type) {
		this.tm_type = tm_type;
	}
	public String getTm_bdh() {
		return tm_bdh;
	}
	public void setTm_bdh(String tm_bdh) {
		this.tm_bdh = tm_bdh;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Integer getData_type() {
		return data_type;
	}
	public void setData_type(Integer data_type) {
		this.data_type = data_type;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getTm_unit() {
		return tm_unit;
	}
	public void setTm_unit(String tm_unit) {
		this.tm_unit = tm_unit;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}

}
