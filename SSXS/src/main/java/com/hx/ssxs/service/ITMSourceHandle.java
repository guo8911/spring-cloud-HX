package com.hx.ssxs.service;

import javax.websocket.Session;

import com.hx.ssxs.data.DataPackage;

public interface ITMSourceHandle {
  void addSession(String paramString, Session paramSession);
  
  void removeSession(String paramString);
  
  void handle(DataPackage paramDataPackage);
}
