package com.hx.ssxs.data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 	xml格式数据
 *   
 * @author Administrator
 *
 */

@SuppressWarnings("serial")
public class XmlData  implements Serializable{

	private List<Map<String, String>>  contain;   //xml内容


	public List<Map<String, String>> getContain() {
		return contain;
	}

	public void setContain(List<Map<String, String>> contain) {
		this.contain = contain;
	}


	public void clear() {
		this.contain=null;		
	}

	@Override
	public String toString() {
		return "XmlData [contain=" + contain + "]";
	}

	
}
