package com.hx.ssxs.data;

import java.util.List;

/**
 *   延时数据  组参数
 * 
 * @author yj
 *
 */
public class DelayGroupParameter {
	
	
	private  int  gp_jd;   //本组参数积日(4字节)
	
	private  double  gp_t;    //本组参数积秒(8字节)，实型
	
	private   int  pnum;	//本组参数个数(2字节)
	
	private  List<Parameter>  parameters;   //本组所有参数
	
	

	public int getGp_jd() {
		return gp_jd;
	}

	public void setGp_jd(int gp_jd) {
		this.gp_jd = gp_jd;
	}

	public double getGp_t() {
		return gp_t;
	}

	public void setGp_t(double gp_t) {
		this.gp_t = gp_t;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "DelayGroupParameter [gp_jd=" + gp_jd + ", gp_t=" + gp_t
				+ ", pnum=" + pnum + ", parameters=" + parameters + "]";
	}
	
}