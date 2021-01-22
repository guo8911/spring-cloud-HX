package com.hx.ssxs.data;

import java.util.Arrays;

/**
 * 参数
 * @author yj
 */
public class Parameter {

	/**
	 * 参数序号
	 */
	private  int  tmNum;
	/**
	 * 参数类型 0：浮点型，1:有符号整型，2：无符号整型,3：字符型
	 */
	private  int tmType;
	/**
	 * 参数超限标志
	 */
	private  int tmLevel;
	/**
	 * 参数结果
	 */
	private  String tmValue;
	/**
	 * 参数原码
	 */
	private  byte[] tmSource;
	
	
	
	public int getTmNum() {
		return tmNum;
	}

	public void setTmNum(int tmNum) {
		this.tmNum = tmNum;
	}

	public int getTmType() {
		return tmType;
	}

	public void setTmType(int tmType) {
		this.tmType = tmType;
	}

	public int getTmLevel() {
		return tmLevel;
	}

	public void setTmLevel(int tmLevel) {
		this.tmLevel = tmLevel;
	}

	public String getTmValue() {
		return tmValue;
	}

	public void setTmValue(String tmValue) {
		this.tmValue = tmValue;
	}

	public byte[] getTmSource() {
		return tmSource;
	}

	public void setTmSource(byte[] tmSource) {
		this.tmSource = tmSource;
	}

	@Override
	public String toString() {
		return "Parameter [tmNum=" + tmNum + ", tmType=" + tmType
				+ ", tmLevel=" + tmLevel + ", tmValue=" + tmValue + ", tmSource="
				+ Arrays.toString(tmSource) + "]";
	}
	
	/**
	 * 获取参数状态字节数组  2字节
	 * @return
	 */
	public byte[] getParameterState(){
		byte[] bytes = new byte[2];
		bytes[0] = (byte) tmType;
		bytes[1] = (byte) tmLevel;
		return bytes;
	}
}
