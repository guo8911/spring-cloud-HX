package com.hx.ssxs.entity;

public class PageOperateInfo {
  private String currntIP;
  
  private boolean autostate = false;
  
  private String selectPageID;
  
  private String dataSource;
  
  private int firstDev_mid;
  
  private int secondDev_mid;
  
  private int thirdDev_mid;
  
  private long firstTime;
  
  private long secondTime;
  
  private long thirdTime;
  
  private boolean isSendFirst = true;
  
  public long getFirstTime() {
    return this.firstTime;
  }
  
  public void setFirstTime(long firstTime) {
    this.firstTime = firstTime;
  }
  
  public long getSecondTime() {
    return this.secondTime;
  }
  
  public void setSecondTime(long secondTime) {
    this.secondTime = secondTime;
  }
  
  public long getThirdTime() {
    return this.thirdTime;
  }
  
  public void setThirdTime(long thirdTime) {
    this.thirdTime = thirdTime;
  }
  
  public boolean isAutostate() {
    return this.autostate;
  }
  
  public void setAutostate(boolean autostate) {
    this.autostate = autostate;
  }
  
  public boolean isSendFirst() {
    return this.isSendFirst;
  }
  
  public void setSendFirst(boolean isSendFirst) {
    this.isSendFirst = isSendFirst;
  }
  
  public String getCurrntIP() {
    return this.currntIP;
  }
  
  public void setCurrntIP(String currntIP) {
    this.currntIP = currntIP;
  }
  
  public String getSelectPageID() {
    return this.selectPageID;
  }
  
  public void setSelectPageID(String selectPageID) {
    this.selectPageID = selectPageID;
  }
  
  public String getDataSource() {
    return this.dataSource;
  }
  
  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }
  
  public int getFirstDev_mid() {
    return this.firstDev_mid;
  }
  
  public void setFirstDev_mid(int firstDev_mid) {
    this.firstDev_mid = firstDev_mid;
  }
  
  public int getSecondDev_mid() {
    return this.secondDev_mid;
  }
  
  public void setSecondDev_mid(int secondDev_mid) {
    this.secondDev_mid = secondDev_mid;
  }
  
  public int getThirdDev_mid() {
    return this.thirdDev_mid;
  }
  
  public void setThirdDev_mid(int thirdDev_mid) {
    this.thirdDev_mid = thirdDev_mid;
  }
}
