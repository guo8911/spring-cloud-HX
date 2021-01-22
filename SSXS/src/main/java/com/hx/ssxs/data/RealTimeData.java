package com.hx.ssxs.data;

import java.util.List;

/**
 *  	实时遥测数据
 * @author yj
 *
 */

public class RealTimeData {
	
	private  int  num;    //包含的参数组数 (4字节)
	
	private  List<GroupParameter>  groupParameters;   //所有组参数

	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<GroupParameter> getGroupParameters() {
		return groupParameters;
	}

	public void setGroupParameters(List<GroupParameter> groupParameters) {
		this.groupParameters = groupParameters;
	}

	@Override
	public String toString() {
		return "RealTimeData [num=" + num + ", groupParameters="
				+ groupParameters + "]";
	}
	
	

}
