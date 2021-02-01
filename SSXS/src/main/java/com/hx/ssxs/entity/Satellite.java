package com.hx.ssxs.entity;

import java.io.Serializable;

public class Satellite implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer sat_id;
    private Integer mid;
    private String sat_name;
    private String sat_code;
    private Integer status;
    private String sat_alias;
    private String multicast_address;
    private String udp_port;
    private String create_user;
    private String create_time;
    private String receive_tmdata_address;
    private String receive_tmdata_port;
    private String send_result_address;
    private String send_result_port;
    private String channel;
    private String version;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSat_alias() {
		return sat_alias;
	}
	public void setSat_alias(String sat_alias) {
		this.sat_alias = sat_alias;
	}
	public String getMulticast_address() {
		return multicast_address;
	}
	public void setMulticast_address(String multicast_address) {
		this.multicast_address = multicast_address;
	}
	public String getUdp_port() {
		return udp_port;
	}
	public void setUdp_port(String udp_port) {
		this.udp_port = udp_port;
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
	public String getReceive_tmdata_address() {
		return receive_tmdata_address;
	}
	public void setReceive_tmdata_address(String receive_tmdata_address) {
		this.receive_tmdata_address = receive_tmdata_address;
	}
	public String getReceive_tmdata_port() {
		return receive_tmdata_port;
	}
	public void setReceive_tmdata_port(String receive_tmdata_port) {
		this.receive_tmdata_port = receive_tmdata_port;
	}
	public String getSend_result_address() {
		return send_result_address;
	}
	public void setSend_result_address(String send_result_address) {
		this.send_result_address = send_result_address;
	}
	public String getSend_result_port() {
		return send_result_port;
	}
	public void setSend_result_port(String send_result_port) {
		this.send_result_port = send_result_port;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
}
