package com.hx.ssxs.entity;

import java.io.Serializable;

public class UnpackConfig implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String pk_id;
	private int sat_id;
	private String f_id;
	private int seq;
	private String save_site;
	private String field_name;
	private int field_len;
	private String exec_func;
	private String field_desc;
	private String field_code;
	private String protocol_id;
	private int field_start;
	private int position;
	private int len_type;
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
	public String getF_id() {
		return f_id;
	}
	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSave_site() {
		return save_site;
	}
	public void setSave_site(String save_site) {
		this.save_site = save_site;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public int getField_len() {
		return field_len;
	}
	public void setField_len(int field_len) {
		this.field_len = field_len;
	}
	public String getExec_func() {
		return exec_func;
	}
	public void setExec_func(String exec_func) {
		this.exec_func = exec_func;
	}
	public String getField_desc() {
		return field_desc;
	}
	public void setField_desc(String field_desc) {
		this.field_desc = field_desc;
	}
	public String getField_code() {
		return field_code;
	}
	public void setField_code(String field_code) {
		this.field_code = field_code;
	}
	public String getProtocol_id() {
		return protocol_id;
	}
	public void setProtocol_id(String protocol_id) {
		this.protocol_id = protocol_id;
	}
	public int getField_start() {
		return field_start;
	}
	public void setField_start(int field_start) {
		this.field_start = field_start;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getLen_type() {
		return len_type;
	}
	public void setLen_type(int len_type) {
		this.len_type = len_type;
	}

}
