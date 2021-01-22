package com.hx.ssxs.data;

import java.util.List;


/**
 * 	延时遥感数据   包参数
 * 
 * @author yj
 *
 */
public class PackageParameter {
	
	
	private  int  type;  //数据类型(4字节)
	
	private  int  sou;   //信源设备编码(4字节)
	
	private  int  jd;       //数据时间（积日）(4字节)
	
	private  double  t;		//数据时间（积秒）(8字节),实型
	
	private  int  flag;		//标志位          0x00表示定长    0xFF表示变长(2字节)

	private  int plen;       //数据长度（含PNum）(2字节)
	
	private  int  pnum;		//本组参数总个数 (2字节)
	
	private  List<DelayGroupParameter>  delayGroupParameters;  //本包所有组参数

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSou() {
		return sou;
	}

	public void setSou(int sou) {
		this.sou = sou;
	}

	public int getJd() {
		return jd;
	}

	public void setJd(int jd) {
		this.jd = jd;
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getPlen() {
		return plen;
	}

	public void setPlen(int plen) {
		this.plen = plen;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public List<DelayGroupParameter> getDelayGroupParameters() {
		return delayGroupParameters;
	}

	public void setDelayGroupParameters(
			List<DelayGroupParameter> delayGroupParameters) {
		this.delayGroupParameters = delayGroupParameters;
	}

	@Override
	public String toString() {
		return "PackageParameter [type=" + type + ", sou="
				+ sou + ", jd=" + jd + ", t=" + t + ", flag="
				+ flag + ", plen=" + plen + ", pnum=" + pnum
				+ ", delayGroupParameters=" + delayGroupParameters + "]";
	}
}
