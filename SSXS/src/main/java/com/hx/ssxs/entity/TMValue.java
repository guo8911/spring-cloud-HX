package com.hx.ssxs.entity;

import com.hx.ssxs.data.DataType;

public class TMValue {
  private int num;
  
  private String code;
  
  private String id;
  
  private DataType dataType;
  
  private Object value;
  
  private String time = "";
  
  private boolean initFlag = false;
  
  private String source = "";
  
  private String displayValue = "";
  
  public String getDisplayValue() {
    return this.displayValue;
  }
  
  public void setDisplayValue(String displayValue) {
    this.displayValue = displayValue;
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
  
  public String getId() {
    return this.id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public DataType getDataType() {
    return this.dataType;
  }
  
  public void setDataType(DataType dataType) {
    this.dataType = dataType;
  }
  
  public int getNum() {
    return this.num;
  }
  
  public void setNum(int num) {
    this.num = num;
  }
  
  public Object getValue() {
    return this.value;
  }
  
  public void setValue(Object value) {
    this.value = value;
  }
  
  public String getTime() {
    return this.time;
  }
  
  public void setTime(String time) {
    this.time = time;
  }
  
  public boolean isInitFlag() {
    return this.initFlag;
  }
  
  public void setInitFlag(boolean initFlag) {
    this.initFlag = initFlag;
  }
}
