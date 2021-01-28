package com.hx.ssxs.protocol;

import java.util.Map;

public interface IAdapter {

	public void init(Map<String, String> map);
	
	public String getParameter(String key);

}
