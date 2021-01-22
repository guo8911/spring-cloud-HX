package com.hx.ssxs.entity;

import java.util.List;

public class PageTMBean {
  private String pid;
  
  private String moduleId;
  
  private int colNum;
  
  private List<PageTM> list;
  
  private int dev;
  
  private String displayContent;
  
  public String getPid() {
    return this.pid;
  }
  
  public void setPid(String pid) {
    this.pid = pid;
  }
  
  public int getDev() {
    return this.dev;
  }
  
  public void setDev(int dev) {
    this.dev = dev;
  }
  
  public String getDisplayContent() {
    return this.displayContent;
  }
  
  public void setDisplayContent(String displayContent) {
    this.displayContent = displayContent;
  }
  
  public String getModuleId() {
    return this.moduleId;
  }
  
  public void setModuleId(String moduleId) {
    this.moduleId = moduleId;
  }
  
  public int getColNum() {
    return this.colNum;
  }
  
  public void setColNum(int colNum) {
    this.colNum = colNum;
  }
  
  public List<PageTM> getList() {
    return this.list;
  }
  
  public void setList(List<PageTM> list) {
    this.list = list;
  }
}
