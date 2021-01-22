package com.hx.ssxs.data;

/**
 *    未经处理的数据原码
 *    
 * @author yj
 *
 */
public class Source {
	
	/**
	 * 设备标识
	 */
	private int devMid;
	
	private byte[] content;   // 数据原码内容

	public int getDevMid() {
		return devMid;
	}

	public void setDevMid(int devMid) {
		this.devMid = devMid;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
