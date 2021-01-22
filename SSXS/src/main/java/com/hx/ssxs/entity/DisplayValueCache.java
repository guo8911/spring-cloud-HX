package com.hx.ssxs.entity;

import java.util.Map;

public class DisplayValueCache {
  private volatile Map<String, String> map = null;
  
  private volatile Map<String, ConfigDisplay> mapc = null;
  
  public Map<String, ConfigDisplay> getMapc() {
    return this.mapc;
  }
  
  public void setMapc(Map<String, ConfigDisplay> mapc) {
    this.mapc = mapc;
  }
  
  public Map<String, String> getMap() {
    return this.map;
  }
  
  public void setMap(Map<String, String> map) {
    this.map = map;
  }
}
