package com.hx.ssxs.load;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.entity.DisplayValueCache;
import com.hx.ssxs.entity.SatInfoManager;
import com.hx.ssxs.entity.SatNet;
import com.hx.ssxs.mapper.SatNetMapper;
import com.hx.ssxs.thread.SatOtherDataThread;
import com.hx.ssxs.thread.SatUDPReceiveThread;

@Component
public class ConnectOtherInfoLoad {
	@Autowired
	private SatNetMapper satNetMapper;
  private Log log = LogFactory.getLog(ConnectOtherInfoLoad.class);
  
  @PostConstruct
  public void load() {
    SatInfoManager sim = null;
    System.out.println("开始初始化非遥测数据...");
    List<SatNet> satNetList = satNetMapper.getOtherConnect();
    List<String> list = new ArrayList<>();
    if (this.log.isDebugEnabled()) {
		this.log.debug("开始创建接收线程...");
	} 
    Thread thread = null;
    if (satNetList != null) {
      for (SatNet satNet:satNetList) {
        String satCode = satNet.getSat_code();
        String type =  satNet.getType();
        String ip = satNet.getIp();
        String port = satNet.getPort();
        String mid = satNet.getMid();
        String sat_id = satNet.getSat_id();
        if ("2".equals(type)) {
          if (!list.contains(mid)) {
			list.add(mid);
		} 
          String name = String.valueOf(satCode) + "&&&" + type;
          DisplayValueCache div = null;
          if (!PageCache.satInfo.containsKey(satCode)) {
			PageCache.satInfo.put(satCode, Integer.valueOf(Integer.parseInt(mid)));
		} 
          thread = new Thread((Runnable)new SatUDPReceiveThread(type, ip, port, 
                satCode, Integer.parseInt(mid), 65535), name);
          synchronized (PageCache.map) {
            sim = (SatInfoManager)PageCache.map.get(Integer.valueOf(Integer.parseInt(mid)));
            sim.addThread(thread);
          } 
        } 
      } 
    } 
    if (this.log.isDebugEnabled()) {
		this.log.debug("创建接收线程完成！");
	} 
    if (this.log.isDebugEnabled()) {
		this.log.debug("正在加载非遥测数据...");
	} 
    if (list.size() != 0) {
      for (String mid : list) {
        thread = new Thread((Runnable)new SatOtherDataThread(
              Integer.parseInt(mid)), String.valueOf(mid) + "&&Otherhandle");
        synchronized (PageCache.map) {
          sim = (SatInfoManager)PageCache.map.get(Integer.valueOf(Integer.parseInt(mid)));
          sim.addThread(thread);
        } 
      } 
      if (this.log.isDebugEnabled()) {
		this.log.debug("加载非遥测数据完成！");
	} 
      if (this.log.isDebugEnabled()) {
		this.log.debug("正在启动非遥测数据接收线程...");
	} 
      for (String mid : list) {
        synchronized (PageCache.map) {
          sim = (SatInfoManager)PageCache.map.get(Integer.valueOf(Integer.parseInt(mid)));
          sim.receiveThreadRun();
        } 
      } 
      if (this.log.isDebugEnabled()) {
		this.log.debug("启动非遥测数据接收线程完成！");
	} 
      System.out.println("初始化非遥测数据完成！");
    } 
  }
}
