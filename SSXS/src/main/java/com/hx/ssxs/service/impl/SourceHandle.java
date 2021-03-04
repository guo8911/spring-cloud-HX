package com.hx.ssxs.service.impl;

import com.google.gson.Gson;
import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.cache.PageConCache;
import com.hx.ssxs.data.DataPackage;
import com.hx.ssxs.entity.PageDevice;
import com.hx.ssxs.entity.PageOperateInfo;
import com.hx.ssxs.service.ITMSourceHandle;
import com.hx.ssxs.util.DataTools;
import com.hx.ssxs.util.PageTools;
import com.hx.ssxs.util.RedisUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class SourceHandle implements ITMSourceHandle {
  private static Log log = LogFactory.getLog(SourceHandle.class);
  
  private Map<String, Session> map = new ConcurrentHashMap<>();
  
  private int mid;
  
  private RedisUtil redisUtil;
  
  public SourceHandle(Integer mid, RedisUtil redisUtil) {
    this.mid = mid.intValue();
    this.redisUtil = redisUtil;
  }
  
  @Override
public void addSession(String key, Session session) {
    this.map.put(key, session);
  }
  
  @Override
public void removeSession(String key) {
    this.map.remove(key);
  }
  
  @Override
public void handle(DataPackage gp) {
    Set<Map.Entry<String, Session>> entryset = this.map.entrySet();
    if (entryset.size() == 0) {
		return;
	} 
    int packgeNum = gp.getHead().getPackageNum();
    String time = gp.getHead().getDateTime();
    byte[] data = gp.getBody().getSource().getContent();
    String source = PageTools.cutSourceIndex(DataTools.bytesToHesString(data)).toString().toUpperCase();
    int dev_mid = gp.getBody().getSource().getDevMid();
    Map<String, Object> result = new HashMap<>();
    result.put("data_time", time);
    result.put("frameCount", Integer.valueOf(packgeNum));
    result.put("param_source", source);
    PageDevice pd = PageConCache.getInstance().getDeviceInfo((new StringBuilder(String.valueOf(dev_mid))).toString());
    result.put("device", pd.getDevice_name());
    Gson json = new Gson();
    for (Map.Entry<String, Session> entry : entryset) {
      String clientKey = entry.getKey();
      String[] clientKeys = clientKey.split("&&");
//      PageOperateInfo poi = (PageOperateInfo)PageCache.selectMap.get(clientKeys[0]);
      PageOperateInfo poi = (PageOperateInfo) redisUtil.getHash("selectMap", clientKeys[0]);
      boolean flag = true;
      if (poi != null) {
		if (poi.getFirstDev_mid() == dev_mid) {
          flag = false;
        } else if (poi.getSecondDev_mid() == dev_mid) {
          flag = false;
        } else if (poi.getThirdDev_mid() == dev_mid) {
          flag = false;
        }
	}  
      try {
        if (!flag) {
          Session session = entry.getValue();
          if (session != null && session.isOpen()) {
            session.getAsyncRemote().sendText(json.toJson(result));
            continue;
          } 
          this.map.remove(clientKey);
        } 
      } catch (Exception e) {
        if (log.isErrorEnabled()) {
			log.debug("[连接关闭，数据发送异常！]");
		} 
      } 
    } 
  }
}
