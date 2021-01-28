package com.hx.ssxs.protocol;

import java.util.Map;

public abstract class AbstractAdapter implements IAdapter{

	private Map<String,String> prop;
	
	public void init(Map<String, String> map){
		this.prop = map;
	}
	
	public String getParameter(String key){
		return this.prop.get(key);
	}
	

}
