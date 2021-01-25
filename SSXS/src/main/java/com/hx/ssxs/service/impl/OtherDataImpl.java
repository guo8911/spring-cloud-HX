package com.hx.ssxs.service.impl;

import com.google.gson.Gson;
import com.hx.ssxs.cache.PageConCache;
import com.hx.ssxs.data.Head;
import com.hx.ssxs.entity.CmdTeleControl;
import com.hx.ssxs.entity.PageDevice;
import com.hx.ssxs.service.IOtherDataHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OtherDataImpl implements IOtherDataHandle {
  private static Log log = LogFactory.getLog(OtherDataImpl.class);
  
  private Map<String, List<String>> pageMap = new ConcurrentHashMap<>();
  
  private Map<String, Session> map = new ConcurrentHashMap<>();
  
  private int mid;
  
  public OtherDataImpl(Integer mid) {
    this.mid = mid.intValue();
  }
  
  @Override
public void addSession(String key, Session session) {
    String[] keys = key.split("&&");
    List<String> list = null;
    if ((list = this.pageMap.get(keys[1])) != null) {
      if (!list.contains(keys[0])) {
		list.add(keys[0]);
	} 
    } else {
      list = new ArrayList<>();
      list.add(keys[0]);
      this.pageMap.put(keys[1], list);
    } 
    this.map.put(key, session);
  }
  
  @Override
public void removeSession(String key) {
    String[] keys = key.split("&&");
    this.map.remove(key);
    ((List)this.pageMap.get(keys[1])).remove(keys[0]);
  }
  
  @Override
public void handleData(Head head, int mid, List<Map<String, String>> list) {
    int mesign = head.getMesSign();
    if (mesign == 262401) {
      CmdHandle(list);
    } else if (mesign == 393472) {
      LinkDataHandle(list);
    } else if (mesign == 1114368) {
      SendReceiveHandle(list);
    } else if (mesign == 196864) {
      ExternalTestHandle(list);
    } else if (mesign == 327936) {
      LoopCheckHandle(list);
    } 
  }
  
  private void LoopCheckHandle(List<Map<String, String>> list) {
    List<String> listClientip = this.pageMap.get("12");
    if (listClientip == null) {
		return;
	} 
    if (log.isDebugEnabled()) {
		log.debug("[小环比对信息处理数据！]");
	} 
    Map<String, Object> returnData = new HashMap<>();
    Map<String, String> result = null;
    for (int i = 0; i < list.size(); i++) {
      result = new HashMap<>();
      String dev_mid = ((Map)list.get(i)).get("dev_mid").toString();
      result.put("dev_mid", dev_mid);
      PageDevice pd = PageConCache.getInstance().getDeviceInfo(dev_mid);
      if (pd != null) {
		result.put("dev_name", 
            (pd.getDevice_name() != null) ? pd.getDevice_name() : "");
	} 
      result.put("con_time", (((Map)list.get(i)).get("con_time") != null) ? ""+((Map)list
          .get(i)).get("con_time") : "");
      result.put("cmd_time", (((Map)list.get(i)).get("cmd_time") != null) ? ""+((Map)list
          .get(i)).get("cmd_time") : "");
      result.put("content", (((Map)list.get(i)).get("content") != null) ? ""+((Map)list
          .get(i)).get("content") : "");
      result.put("result", (((Map)list.get(i)).get("result") != null) ? ""+((Map)list
          .get(i)).get("result") : "");
    } 
    returnData.put("data", result);
    returnData.put("type", Integer.valueOf(2));
    Gson json = new Gson();
    for (String clientip : listClientip) {
      Session session = this.map.get(String.valueOf(clientip) + "&&12&&" + this.mid);
      try {
        if (session == null && !session.isOpen()) {
          this.map.remove(String.valueOf(clientip) + "&&12&&" + this.mid);
          continue;
        } 
        session.getBasicRemote().sendText(json.toJson(returnData));
      } catch (Exception e) {
        if (log.isDebugEnabled()) {
			log.debug("[" + clientip + "小环比对页面关闭！]");
		} 
      } 
    } 
    if (log.isDebugEnabled()) {
		log.debug("[小环比对处理数据完成！]");
	} 
  }
  
  private void ExternalTestHandle(List<Map<String, String>> list) {
    List<String> listClientip = this.pageMap.get("12");
    if (listClientip == null) {
		return;
	} 
    if (log.isDebugEnabled()) {
		log.debug("[外测信息处理数据！]");
	} 
    Map<String, Object> returnData = new HashMap<>();
    Map<String, String> result = new HashMap<>();
    for (int i = 0; i < list.size(); i++) {
      String time = ""+((Map)list.get(i)).get("send_time");
      if (log.isDebugEnabled()) {
		log.debug("[外测处理前时间=" + time + "]");
	} 
      String dev_mid = ((Map)list.get(i)).get("dev_mid").toString();
      PageDevice pd = null;
      if (dev_mid != null) {
		pd = PageConCache.getInstance().getDeviceInfo(dev_mid);
	} 
      if (pd != null) {
        result.put("dev_mid", (((Map)list.get(i)).get("dev_mid") != null) ? ""+((Map)list
            .get(i)).get("dev_mid") : "");
        result.put("dev_name", pd.getDevice_name());
      } 
      result.put("send_time", (((Map)list.get(i)).get("send_time") != null) ? ""+((Map)list
          .get(i)).get("send_time") : "");
      result.put("range", (((Map)list.get(i)).get("ranges") != null) ? ""+((Map)list.get(i))
          .get("ranges") : "");
      result.put(
          "range_percent", 
          (((Map)list.get(i)).get("range_percent") != null) ? ""+((Map)list.get(i)).get(
            "range_percent") : "");
      result.put("angle", (((Map)list.get(i)).get("angle") != null) ? ""+((Map)list.get(i))
          .get("angle") : "");
      result.put(
          "angle_percent", 
          (((Map)list.get(i)).get("angle_percent") != null) ? ""+((Map)list.get(i)).get(
            "angle_percent") : "");
      result.put("pitch", (((Map)list.get(i)).get("pitch") != null) ? ""+((Map)list.get(i))
          .get("pitch") : "");
      result.put(
          "pitch_percent", 
          (((Map)list.get(i)).get("pitch_percent") != null) ? ""+((Map)list.get(i)).get(
            "pitch_percent") : "");
    } 
    returnData.put("data", result);
    returnData.put("type", Integer.valueOf(1));
    if (log.isDebugEnabled()) {
		log.debug("[外测结果显示处理数据完成！]");
	} 
    Gson json = new Gson();
    for (String clientip : listClientip) {
      Session session = this.map.get(String.valueOf(clientip) + "&&12&&" + this.mid);
      try {
        if (session == null && !session.isOpen()) {
          this.map.remove(String.valueOf(clientip) + "&&12&&" + this.mid);
          continue;
        } 
        session.getBasicRemote().sendText(json.toJson(returnData));
      } catch (Exception e) {
        if (log.isDebugEnabled()) {
			log.debug("[" + clientip + "外测处理页面关闭]");
		} 
      } 
    } 
    if (log.isDebugEnabled()) {
		log.debug("[外测结果显示处理数据发送！]");
	} 
  }
  
  private void SendReceiveHandle(List<Map<String, String>> list) {
    List<String> listClientip = this.pageMap.get("3");
    if (listClientip == null) {
		return;
	} 
    if (log.isDebugEnabled()) {
		log.debug("[设备收发汇总信息处理数据！]");
	} 
    Map<String, Object> result = new HashMap<>();
    List<Object> res = new ArrayList();
    Map<String, Object> mapLink = null;
    for (int i = 0; i < list.size(); i++) {
      mapLink = new HashMap<>();
      String dev_mid = ""+((Map)list.get(i)).get("DevId");
      mapLink.put("dev_mid", dev_mid);
      String datatype = ""+((Map)list.get(i)).get("Direction");
      mapLink.put("dataType", datatype);
      String info = ""+((Map)list.get(i)).get("InfoType");
      mapLink.put("info", info);
      res.add(mapLink);
    } 
    if (res.isEmpty()) {
		return;
	} 
    result.put("data", res);
    result.put("type", Integer.valueOf(1));
    Gson json = new Gson();
    for (String clientip : listClientip) {
      Session session = this.map.get(String.valueOf(clientip) + "&&3&&" + this.mid);
      try {
        if (session == null && !session.isOpen()) {
          this.map.remove(String.valueOf(clientip) + "&&3&&" + this.mid);
          continue;
        } 
        session.getBasicRemote().sendText(json.toJson(result));
      } catch (Exception e) {
        if (log.isDebugEnabled()) {
			log.debug("[" + clientip + "设备收发汇总页面已关闭]");
		} 
      } 
    } 
  }
  
  private void CmdHandle(List<Map<String, String>> list) {
    CmdTeleControl cmdTe = null;
    Gson json = new Gson();
    if (log.isDebugEnabled()) {
		log.debug("[遥控指令数据--mid=" + this.mid + "," + json.toJson(list) + "]");
	} 
    List<String> listClientip = this.pageMap.get("11");
    if (listClientip == null) {
		return;
	} 
    if (log.isDebugEnabled()) {
		log.debug("[遥控指令处理数据！]");
	} 
    for (int i = 0; i < list.size(); i++) {
      cmdTe = new CmdTeleControl();
      String dev_mid = ""+((Map)list.get(i)).get("dev_id");
      cmdTe.setDev_mid_se(dev_mid);
      PageDevice pd = PageConCache.getInstance().getDeviceInfo(dev_mid);
      if (pd != null) {
		cmdTe.setDev_name_se(pd.getDevice_name());
	} 
      cmdTe.setCode(""+((Map)list.get(i)).get("code"));
      cmdTe.setDeclare((((Map)list.get(i)).get("description") != null) ? ""+((Map)list.get(
            i)).get("description") : "");
      cmdTe.setTime_se((((Map)list.get(i)).get("send_time") != null) ? ""+((Map)list.get(i))
          .get("send_time") : "");
      cmdTe.setTime_exe((((Map)list.get(i)).get("execute_time") != null) ? ""+((Map)list
          .get(i)).get("execute_time") : "");
      cmdTe.setCom_result((((Map)list.get(i)).get("comparison_result") != null) ? ""+((Map)list
          .get(i)).get("comparison_result") : "");
      cmdTe.setExe_re((((Map)list.get(i)).get("execute_result") != null) ? ""+((Map)list
          .get(i)).get("execute_result") : "");
      cmdTe.setDegree_exe((((Map)list.get(i)).get("execute_count") != null) ? ""+((Map)list
          .get(i)).get("execute_count") : "");
    } 
    for (String clientip : listClientip) {
      Session session = this.map.get(String.valueOf(clientip) + "&&11&&" + this.mid);
      try {
        if (session == null && !session.isOpen()) {
          this.map.remove(String.valueOf(clientip) + "&&11&&" + this.mid);
          continue;
        } 
        session.getBasicRemote().sendText(json.toJson(cmdTe));
        if (log.isDebugEnabled()) {
			log.debug("[遥控指令---" + json.toJson(cmdTe) + "]");
		} 
      } catch (Exception e) {
        if (log.isDebugEnabled()) {
			log.debug("[" + clientip + "遥控指令页面关闭" + e + "]");
		} 
      } 
    } 
    if (log.isDebugEnabled()) {
		log.debug("[遥控指令显示处理数据完成！]");
	} 
  }
  
  private void LinkDataHandle(List<Map<String, String>> list) {
    List<String> listClientip = this.pageMap.get("3");
    if (listClientip == null) {
		return;
	} 
    if (log.isDebugEnabled()) {
		log.debug("[链路监视信息处理数据！]");
	} 
    Map<String, Object> result = new HashMap<>();
    List<Object> res = new ArrayList();
    Map<String, Object> mapLink = null;
    for (int i = 0; i < list.size(); i++) {
      mapLink = new HashMap<>();
      mapLink.put("dev_mid", ((Map)list.get(i)).get("dev_mid"));
      mapLink.put("time", ((Map)list.get(i)).get("time"));
      res.add(mapLink);
    } 
    result.put("data", res);
    result.put("type", Integer.valueOf(2));
    Gson json = new Gson();
    for (String clientip : listClientip) {
      Session session = this.map.get(String.valueOf(clientip) + "&&3&&" + this.mid);
      try {
        if (session == null && !session.isOpen()) {
          this.map.remove(String.valueOf(clientip) + "&&3&&" + this.mid);
          continue;
        } 
        session.getBasicRemote().sendText(json.toJson(result));
      } catch (Exception e) {
        if (log.isDebugEnabled()) {
			log.debug("[" + clientip + "链路监视页面已关闭]");
		} 
      } 
    } 
    if (log.isDebugEnabled()) {
		log.debug("[链路监视信息处理数据完成！]");
	} 
  }
}
