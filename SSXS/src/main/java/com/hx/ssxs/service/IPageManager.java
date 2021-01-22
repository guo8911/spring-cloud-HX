package com.hx.ssxs.service;

import javax.websocket.Session;

import com.hx.ssxs.entity.SatTMInfo;
import com.hx.ssxs.service.impl.PageImpl;

public interface IPageManager {
  void setData(SatTMInfo paramSatTMInfo, int paramInt);
  
  void openPage(PageImpl paramPageImpl);
  
  void closePage(String paramString1, String paramString2);
  
  void addPageSession(Session paramSession, String paramString1, String paramString2);
  
  PageImpl getPageImpl(String paramString);
}
