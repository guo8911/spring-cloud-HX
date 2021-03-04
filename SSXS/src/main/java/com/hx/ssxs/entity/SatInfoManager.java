package com.hx.ssxs.entity;

import java.util.ArrayList;
import java.util.List;

import com.hx.ssxs.service.IOtherDataHandle;
import com.hx.ssxs.service.IPageManager;
import com.hx.ssxs.service.ITMSourceHandle;
import com.hx.ssxs.service.impl.OtherDataImpl;
import com.hx.ssxs.service.impl.PageManagerImpl;
import com.hx.ssxs.service.impl.SourceHandle;
import com.hx.ssxs.thread.SatOtherDataThread;
import com.hx.ssxs.thread.SatPageHandleThread;
import com.hx.ssxs.thread.SatUDPorocoolThread;
import com.hx.ssxs.util.RedisUtil;

public class SatInfoManager {
  private List<Thread> list = new ArrayList<>();
  
  private ITMSourceHandle tht = null;
  
  private IPageManager pmi = null;
  
  private TMValueFactory tmf = null;
  
  private Integer satmid = null;
  
  private SatUDPorocoolThread procoolDataThread = null;
  
  private SatPageHandleThread sph = null;
  
  private SatOtherDataThread sodt = null;
  
  private IOtherDataHandle oth = null;
  
  private ITMSourceHandle tmSource = null;
  
  public SatOtherDataThread getSodt() {
    return this.sodt;
  }
  
  public void setSodt(SatOtherDataThread sodt) {
    this.sodt = sodt;
  }
  
  public SatPageHandleThread getSph() {
    return this.sph;
  }
  
  public void setSph(SatPageHandleThread sph) {
    this.sph = sph;
    this.tmf.setSph(sph);
  }
  
  public SatUDPorocoolThread getProcoolDataThread() {
    return this.procoolDataThread;
  }
  
  public void setProcoolDataThread(SatUDPorocoolThread procoolDataThread) {
    this.procoolDataThread = procoolDataThread;
  }
  
  public SatInfoManager(Integer mid, RedisUtil redisUtil) {
    this.pmi = (IPageManager)new PageManagerImpl(mid, redisUtil);
    this.tmf = new TMValueFactory(mid);
    this.oth = (IOtherDataHandle)new OtherDataImpl(mid);
    this.tmSource = (ITMSourceHandle)new SourceHandle(mid, redisUtil);
    this.satmid = mid;
  }
  
  public ITMSourceHandle getTmSource() {
    return this.tmSource;
  }
  
  public void setTmSource(ITMSourceHandle tmSource) {
    this.tmSource = tmSource;
  }
  
  public IOtherDataHandle getOth() {
    return this.oth;
  }
  
  public void setOth(IOtherDataHandle oth) {
    this.oth = oth;
  }
  
  public Integer getSatmid() {
    return this.satmid;
  }
  
  public void setSatmid(Integer satmid) {
    this.satmid = satmid;
  }
  
  public ITMSourceHandle getTht() {
    return this.tht;
  }
  
  public void setTht(ITMSourceHandle tht) {
    this.tht = tht;
  }
  
  public IPageManager getPmi() {
    return this.pmi;
  }
  
  public void setPmi(IPageManager pmi) {
    this.pmi = pmi;
  }
  
  public TMValueFactory getTmf() {
    return this.tmf;
  }
  
  public void setTmf(TMValueFactory tmf) {
    this.tmf = tmf;
  }
  
  public void receiveThreadRun() {
    for (Thread surt : this.list) {
      if (surt.getState() == Thread.State.NEW) {
		surt.start();
	} 
    } 
  }
  
  public void addThread(Thread thread) {
    this.list.add(thread);
  }
}
