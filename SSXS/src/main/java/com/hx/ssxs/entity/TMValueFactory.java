package com.hx.ssxs.entity;

//import com.yk.data.Parameter;
//import com.yk.data.realTime.GroupParameter;
//import com.yk.ssxs.handle.IPageManager;
//import com.yk.ssxs.handle.impl.PageImpl;
//import com.yk.ssxs.thread.SatPageHandleThread;
//import com.yk.ssxs.util.ByteUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hx.ssxs.data.GroupParameter;
import com.hx.ssxs.data.Parameter;
import com.hx.ssxs.service.IPageManager;
import com.hx.ssxs.service.impl.PageImpl;
import com.hx.ssxs.thread.SatPageHandleThread;
import com.hx.ssxs.util.ByteUtils;

public class TMValueFactory {
  private Log log = LogFactory.getLog(TMValueFactory.class);
  
  private Map<String, TMValue> TMValueCache = new ConcurrentHashMap<>();
  
  private Map<Integer, TMValue> TMValueCacheDelay = new ConcurrentHashMap<>();
  
  private SatPageHandleThread sph = null;
  
  private DisplayValueCache div = null;
  
  private SatTMInfo sti = null;
  
  private SimpleDateFormat sdf = new SimpleDateFormat(
      "yyyy-dd-MM HH:mm:ss.SSS");
  
  private int mid;
  
  public TMValueFactory(Integer mid) {
    this.mid = mid.intValue();
  }
  
  public SatPageHandleThread getSph() {
    return this.sph;
  }
  
  public void setSph(SatPageHandleThread sph) {
    this.sph = sph;
  }
  
  public void checking(GroupParameter gp, String time, int mid) {
    List<Parameter> list = gp.getParameters();
    int flag = gp.getFlag();
    List<Param> listchange = new ArrayList<>();
    for (Parameter param1 : list) {
      String devmid = (new StringBuilder(String.valueOf(gp.getSou()))).toString();
      Param param = addParam(param1, time, flag, devmid, mid);
      if (param != null)
        listchange.add(param); 
    } 
    if (this.sph != null) {
      this.sti = new SatTMInfo();
      this.sti.setListparam(listchange);
      this.sti.setDataTime(time);
      this.sti.setDev_mid(gp.getSou());
      this.sti.setMid(mid);
      this.sph.offer(this.sti);
    } 
  }
  
  public boolean checkingFirst(int firstDev, int secondDev, int thirdDev, String pageid, SatInfoManager sim, String clientip) {
    boolean flag = false;
    Set<Map.Entry<String, TMValue>> entrySelect = this.TMValueCache.entrySet();
    List<Param> listchange1 = new ArrayList<>();
    List<Param> listchange2 = new ArrayList<>();
    List<Param> listchange3 = new ArrayList<>();
    for (Map.Entry<String, TMValue> cacheParam : entrySelect) {
      String keys = cacheParam.getKey();
      String[] key = keys.split("&&");
      if (key[0].equals((new StringBuilder(String.valueOf(firstDev))).toString())) {
        TMValue tm = cacheParam.getValue();
        if (tm.getValue() == null)
          continue; 
        long time1 = 0L;
        String time = "";
        try {
          if (!"".equals(tm.getTime()) && 
            this.sdf.parse(tm.getTime()).getTime() > time1) {
            time = tm.getTime();
            time1 = this.sdf.parse(tm.getTime()).getTime();
          } 
        } catch (ParseException e) {
          e.printStackTrace();
        } 
        Param param = getParamSelectFirst(tm);
        listchange1.add(param);
        if (this.sph != null) {
          this.sti = new SatTMInfo();
          this.sti.setListparam(listchange1);
          this.sti.setDataTime(time);
          this.sti.setDev_mid(firstDev);
          this.sti.setMid(this.mid);
          IPageManager pmi = sim.getPmi();
          if (pmi != null) {
            PageImpl pi = pmi.getPageImpl(pageid);
            if (pi != null) {
              Session session = pi.getMapSession(clientip);
              if (session != null && session.isOpen())
                try {
                  flag = pi.sendAfterSelectByHistory(this.sti, 
                      this.mid, session, clientip);
                } catch (Exception e) {
                  e.printStackTrace();
                }  
            } 
          } 
        } 
        continue;
      } 
      if (key[0].equals((new StringBuilder(String.valueOf(secondDev))).toString())) {
        TMValue tm = cacheParam.getValue();
        if (tm.getValue() == null)
          continue; 
        Param param = getParamSelectFirst(tm);
        long time1 = 0L;
        String time = "";
        try {
          if (!"".equals(tm.getTime()) && 
            this.sdf.parse(tm.getTime()).getTime() > time1) {
            time = tm.getTime();
            time1 = this.sdf.parse(tm.getTime()).getTime();
          } 
        } catch (ParseException e) {
          e.printStackTrace();
        } 
        if ("".equals(time))
          continue; 
        listchange2.add(param);
        if (this.sph != null) {
          this.sti = new SatTMInfo();
          this.sti.setListparam(listchange2);
          this.sti.setDataTime(time);
          this.sti.setDev_mid(secondDev);
          this.sti.setMid(this.mid);
          IPageManager pmi = sim.getPmi();
          if (pmi != null) {
            PageImpl pi = pmi.getPageImpl(pageid);
            if (pi != null) {
              Session session = pi.getMapSession(clientip);
              if (session != null && session.isOpen())
                try {
                  flag = pi.sendAfterSelectByHistory(this.sti, 
                      this.mid, session, clientip);
                } catch (Exception e) {
                  e.printStackTrace();
                }  
            } 
          } 
        } 
        continue;
      } 
      if (key[0].equals((new StringBuilder(String.valueOf(thirdDev))).toString())) {
        TMValue tm = cacheParam.getValue();
        if (tm.getValue() == null)
          continue; 
        Param param = getParamSelectFirst(tm);
        long time1 = 0L;
        String time = "";
        try {
          if (!"".equals(tm.getTime()) && 
            this.sdf.parse(tm.getTime()).getTime() > time1) {
            time = tm.getTime();
            time1 = this.sdf.parse(tm.getTime()).getTime();
          } 
        } catch (ParseException e) {
          e.printStackTrace();
        } 
        if ("".equals(time))
          continue; 
        listchange3.add(param);
        if (this.sph != null) {
          this.sti = new SatTMInfo();
          this.sti.setListparam(listchange3);
          this.sti.setDataTime(time);
          this.sti.setDev_mid(thirdDev);
          this.sti.setMid(this.mid);
          IPageManager pmi = sim.getPmi();
          if (pmi != null) {
            PageImpl pi = pmi.getPageImpl(pageid);
            if (pi != null) {
              Session session = pi.getMapSession(clientip);
              if (session != null && session.isOpen())
                try {
                  flag = pi.sendAfterSelectByHistory(this.sti, 
                      this.mid, session, clientip);
                } catch (Exception e) {
                  e.printStackTrace();
                }  
            } 
          } 
        } 
      } 
    } 
    return flag;
  }
  
  private Param getParamSelectFirst(TMValue tm) {
    Param param = new Param();
    param.setCode(tm.getCode());
    param.setDataType(tm.getDataType());
    param.setDisplayValue(tm.getDisplayValue());
    param.setNum(tm.getNum());
    param.setSource(tm.getSource());
    param.setTm_id(tm.getId());
    param.setValue((String)tm.getValue());
    param.setChangeFlag(true);
    return param;
  }
  
  private Param addParam(Parameter param1, String time, int flag, String devmid, int mid) {
    int num = 0;
    num = param1.getTmNum();
    TMValue tmValue = this.TMValueCache.get(String.valueOf(devmid) + "&&" + num + "&&" + mid);
    if (tmValue == null)
      return null; 
    Param param = getParam(param1);
    param.setCode(tmValue.getCode());
    String disValue = null;
    try {
      if (this.div != null) {
        String config_id = this.div.getMap().get(tmValue.getCode());
        ConfigDisplay cd = this.div.getMapc().get(config_id);
        disValue = cd.get(Integer.valueOf(Integer.parseInt(param1.getTmValue())));
      } 
    } catch (Exception e) {
      if (this.log.isDebugEnabled())
        this.log.debug("[参数" + tmValue.getCode() + "无显示值！]"); 
    } 
    if (disValue != null)
      param.setDisplayValue(disValue); 
    param.setDataType(tmValue.getDataType());
    param.setTm_id(tmValue.getId());
    if (flag == 0) {
      param.setValue(param1.getTmValue());
      if (param.getValue() != tmValue.getValue()) {
        param.setChangeFlag(true);
        tmValue.setValue(param.getValue());
        tmValue.setSource(param.getSource());
        tmValue.setDisplayValue(param.getDisplayValue());
      } 
    } else {
      param.setValue("");
    } 
    tmValue.setTime(time);
    return param;
  }
  
  private Param getParam(Parameter param1) {
    Param param = new Param();
    param.setNum(param1.getTmNum());
    String source = getSourceByHex(param1.getTmSource());
    while (source.indexOf("0") == 0 && source.length() > 2)
      source = source.substring(1); 
    param.setSource(source);
    return param;
  }
  
  private String getSourceByHex(byte[] source) {
    String tmsour = ByteUtils.bytesToHesString(source).toUpperCase();
    return tmsour;
  }
  
  public void load(HashMap<String, TMValue> tmcache) {
    this.TMValueCache.putAll(tmcache);
    tmcache.clear();
  }
  
  public void displayValueLoad(DisplayValueCache div) {
    this.div = div;
  }
}
