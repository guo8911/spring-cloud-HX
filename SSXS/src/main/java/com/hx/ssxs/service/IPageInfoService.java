package com.hx.ssxs.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.hx.ssxs.entity.DeviceInfo;
import com.hx.ssxs.entity.SxGuding;
import com.hx.ssxs.entity.ViewSxProject;

public interface IPageInfoService {
  List<ViewSxProject> getTree(String paramString, HttpServletRequest paramHttpServletRequest);
  
  SxGuding getPageOfGD(int paramString);
  
  String getPageFile(int paramString, int paramInt, boolean paramBoolean);
  
  Boolean checkOutFile(int paramString);
  
  List<DeviceInfo> getDeviceInfo();
  
  String getParamInfo(String paramString);
  
  String getTrackCountInfo(int paramString);
  
//  String getForecastInfo(String paramString);
  
  boolean changeSelectTerm(String paramString1, String paramString2);
  
  boolean changeReceiveDataState(String paramString1, String paramString2, String paramString3);
  
//  String getSatDownForecastInfo(String paramString1, String paramString2);
//  
  Object updateSelectPage(String paramString1, String paramString2, String paramString3);
}
