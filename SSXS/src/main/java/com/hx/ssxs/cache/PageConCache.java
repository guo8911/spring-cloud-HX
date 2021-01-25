package com.hx.ssxs.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hx.ssxs.entity.PageDevice;
import com.hx.ssxs.entity.PageTM;

public class PageConCache {
  private Map<String, Map<String, PageTM>> bigtmInfo = new ConcurrentHashMap<>();
  
  private volatile Map<String, PageDevice> deviceMap = new ConcurrentHashMap<>();
  
  private static PageConCache pageConCache = null;
  
  public static PageConCache getInstance() {
    if (pageConCache == null) {
		pageConCache = new PageConCache();
	} 
    return pageConCache;
  }
  
  public void putTmInfo(String dev_mid, Map<String, PageTM> tmInfoMap) {
    this.bigtmInfo.put(dev_mid, tmInfoMap);
  }
  
  public void putDeviceInfo(String dev_mid, PageDevice pd) {
    this.deviceMap.put(dev_mid, pd);
  }
  
  public PageDevice getDeviceInfo(String dev_mid) {
    return this.deviceMap.get(dev_mid);
  }
}
