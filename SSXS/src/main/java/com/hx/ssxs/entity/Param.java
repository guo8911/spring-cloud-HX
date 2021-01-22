package com.hx.ssxs.entity;

import com.hx.ssxs.data.DataType;

public class Param {
  private boolean changeFlag = false;
  
  private String value;
  
  private String code = "";
  
  private int num;
  
  private String source = "";
  
  private String displayValue = "";
  
  private String tm_id = "";
  
  private DataType dataType;
  
  public String getTm_id() {
    return this.tm_id;
  }
  
  public void setTm_id(String tm_id) {
    this.tm_id = tm_id;
  }
  
  public DataType getDataType() {
    return this.dataType;
  }
  
  public void setDataType(DataType dataType) {
    this.dataType = dataType;
  }
  
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
  
  public String getCode() {
    return this.code;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getValue() {
    return this.value;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
  
  public boolean isChangeFlag() {
    return this.changeFlag;
  }
  
  public void setChangeFlag(boolean changeFlag) {
    this.changeFlag = changeFlag;
  }
}
