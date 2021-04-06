package com.hx.ssxs.service;

import javax.websocket.Session;

import com.hx.ssxs.entity.SatTMInfo;
import com.hx.ssxs.service.impl.PageImpl;

public interface IPageManager {
  void setData(SatTMInfo paramSatTMInfo, int paramInt);
  
  void openPage(PageImpl paramPageImpl,Integer mid,String clientip);
  
  void closePage(String paramString1, String paramString2,String mid);
  
  void addPageSession(Session paramSession, String paramString1, String paramString2,String mid);
  
  PageImpl getPageImpl(String paramString,Integer mid);
}
