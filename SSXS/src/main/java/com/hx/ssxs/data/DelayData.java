package com.hx.ssxs.data;

import java.util.List;

/**
 *   延时遥感数据
 * @author yj
 *
 */

public class DelayData {

	private  int  num;  //包含的数据包个数(4字节)
	
	private  List<PackageParameter>  packageParameters;  //所有的包数据
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List<PackageParameter> getPackageParameters() {
		return packageParameters;
	}
	public void setPackageParameters(List<PackageParameter> packageParameters) {
		this.packageParameters = packageParameters;
	}
	@Override
	public String toString() {
		return "DelayData [num=" + num + ", packageParameters="
				+ packageParameters + "]";
	}
		
}