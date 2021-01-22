package com.hx.ssxs.entity;

public class PageTM {
  private String tm;
  
  private String tm_name;
  
  private String tm_code;
  
  private int rowid;
  
  private String source;
  
  private String value;
  
  private int num;
  
  private String displayValue;
  
  public String getDisplayValue() {
    return this.displayValue;
  }
  
  public void setDisplayValue(String displayValue) {
    this.displayValue = displayValue;
  }
  
  public int getNum() {
    return this.num;
  }
  
  public void setNum(int num) {
    this.num = num;
  }
  
  public String getSource() {
    return this.source;
  }
  
  public void setSource(String source) {
    this.source = source;
  }
  
  public String getValue() {
    return this.value;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
  
  public int getRowid() {
    return this.rowid;
  }
  
  public void setRowid(int rowid) {
    this.rowid = rowid;
  }
  
  public String getTm() {
    return this.tm;
  }
  
  public void setTm(String tm) {
    this.tm = tm;
  }
  
  public String getTm_name() {
    return this.tm_name;
  }
  
  public void setTm_name(String tm_name) {
    this.tm_name = tm_name;
  }
  
  public String getTm_code() {
    return this.tm_code;
  }
  
  public void setTm_code(String tm_code) {
    this.tm_code = tm_code;
  }
}
