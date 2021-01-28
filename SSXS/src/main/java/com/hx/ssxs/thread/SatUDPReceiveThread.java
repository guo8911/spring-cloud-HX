package com.hx.ssxs.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.data.DataPackage;
import com.hx.ssxs.entity.SatInfoManager;
import com.hx.ssxs.protocol.PackImpl;
import com.hx.ssxs.udp.UDPServerImpl;

public class SatUDPReceiveThread implements Runnable {
  private Log log = LogFactory.getLog(SatUDPReceiveThread.class);
  
  private String type;
  
  private String ip;
  
  private String port;
  
  private Integer mid;
  
  private SatInfoManager sim = null;
  
  private UDPServerImpl usi = null;
  
  private int data_length;
  
  private DataPackage datapack = null;
  
  private PackImpl packimpl = new PackImpl();
  
  private long realCount = 0L;
  
  private String code = null;
  
  public SatUDPReceiveThread(String type, String ip, String port, String satCode, int mid, int data_length) {
    this.ip = ip;
    this.type = type;
    this.port = port;
    this.mid = Integer.valueOf(mid);
    this.code = satCode;
    this.data_length = data_length;
    this.sim = (SatInfoManager)PageCache.map.get(Integer.valueOf(mid));
    if (this.sim == null) {
      this.sim = new SatInfoManager(Integer.valueOf(mid));
      PageCache.map.put(Integer.valueOf(mid), this.sim);
    } 
  }
  
  public void run() {
	byte[] data = null;
    while (true)
    {
      this.usi = 
        new UDPServerImpl(this.ip, Integer.parseInt(this.port), 
        Integer.parseInt(this.type));
      if (this.log.isDebugEnabled())
        this.log.debug("[航天器代号：" + this.code + " 通道类型：" + this.type + 
          " 组播地址:" + this.ip + " 端口:" + this.port + "]");
      try
      {
        while (this.usi.isStatus()) {
          data = this.usi.recieveData(this.data_length);
          if (data != null) {
            this.datapack = this.packimpl.unpack(data);
            if (this.datapack != null) {
              if ("2".equals(this.type)) {
                this.sim.getSodt().offData(this.datapack);
              } else if ("1".equals(this.type)) {
                this.realCount += 1L;
                if (this.log.isDebugEnabled()) {
                  this.log.debug("[总数据包：" + this.realCount + "]");
                }
                this.sim.getProcoolDataThread().offData(this.datapack);
                this.datapack = null;
              }
            }
          }
        }

      }
      catch (Exception e)
      {
        if (this.log.isErrorEnabled()) {
          this.log.error("接收数据发生错误", e);
        }
      }
      if (this.log.isDebugEnabled()) {
        this.log.debug("开始重连");
      }
      try
      {
        this.usi.close();
      } catch (Exception e1) {
        if (this.log.isErrorEnabled()) {
          this.log.error("关闭UDPServer出错", e1);
        }
      }
      try
      {
        Thread.currentThread(); Thread.sleep(5000L);
      }
      catch (InterruptedException localInterruptedException)
      {
      }
    }
  }
}
