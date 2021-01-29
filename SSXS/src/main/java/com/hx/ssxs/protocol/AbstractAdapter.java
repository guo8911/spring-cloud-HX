package com.hx.ssxs.protocol;

import java.util.Map;

public abstract class AbstractAdapter implements IAdapter{

	private Map<String,String> prop;
	
	@Override
	public void init(Map<String, String> map){
		this.prop = map;
	}
	
	@Override
	public String getParameter(String key){
		return this.prop.get(key);
	}
	

}
