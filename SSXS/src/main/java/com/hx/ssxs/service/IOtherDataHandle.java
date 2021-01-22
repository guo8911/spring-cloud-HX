package com.hx.ssxs.service;

import java.util.List;
import java.util.Map;
import javax.websocket.Session;

import com.hx.ssxs.data.Head;

public interface IOtherDataHandle {
  void handleData(Head paramHead, int paramInt, List<Map<String, String>> paramList);
  
  void addSession(String paramString, Session paramSession);
  
  void removeSession(String paramString);
}
