package com.hx.ssxs.entity;

import java.util.HashMap;
import java.util.Map;

public class ConfigDisplay {
  private String configPk;
  
  private Map<Integer, String> displayMap;
  
  public ConfigDisplay(String configPk) {
    this.configPk = configPk;
    this.displayMap = new HashMap<>();
  }
  
  public void put(Integer realValue, String displayValue) {
    this.displayMap.put(realValue, displayValue);
  }
  
  public String get(Integer realvalue) {
    return this.displayMap.get(realvalue);
  }
  
  public void clean() {
    this.displayMap.clear();
  }
  
  public String getConfigPk() {
    return this.configPk;
  }
}
