package com.hx.edit.entity;

/**
 * 类功能: 登陆用户bean
 * 
 * @author chen.jie
 * @date 2014-12-13
 */
public class LoginUserBean {

	/**
	 * 用户编号
	 */
	private String userId;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 机构编号
	 */
	private String organizationId;

	/**
	 * 机构名称
	 */
	private String organizationName;
	
	/**
	 * 用户IP地址
	 */
	private String clientIp;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

}
