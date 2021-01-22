package com.hx.ssxs.data;

import java.io.Serializable;
/**
 * 数据包对象
 * @author yj
 *
 */

@SuppressWarnings("serial")
public class DataPackage implements Serializable{
	
	private  Head  head;   //包头
	
	private  Body  body;   //包体数据域
	
	
	public DataPackage() {
		head = new Head();
		body = new Body();
	}

	public Head getHead() {
		if (null == this.head) {
			head = new Head();
		}
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Body getBody() {
		if (null == this.body) {
			body = new Body();
		}
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "DataPackage [head=" + head + ", body=" + body + "]";
	}
	
}
