package com.hx.ssxs.entity;

import java.util.ArrayList;
import java.util.List;

public class SatTMInfo {
  private List<Param> listparam = new ArrayList<>();
  
  private String dataTime;
  
  private int column;
  
  private int dev_mid;
  
  private int mid;
  
  public int getMid() {
    return this.mid;
  }
  
  public void setMid(int mid) {
    this.mid = mid;
  }
  
  public int getDev_mid() {
    return this.dev_mid;
  }
  
  public void setDev_mid(int dev_mid) {
    this.dev_mid = dev_mid;
  }
  
  public int getColumn() {
    return this.column;
  }
  
  public void setColumn(int column) {
    this.column = column;
  }
  
  public List<Param> getListparam() {
    return this.listparam;
  }
  
  public void setListparam(List<Param> listparam) {
    this.listparam.addAll(listparam);
  }
  
  public String getDataTime() {
    return this.dataTime;
  }
  
  public void setDataTime(String dataTime) {
    this.dataTime = dataTime;
  }
}
