package com.hx.ssxs.service.impl;

import com.google.gson.Gson;
import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.cache.PageConCache;
import com.hx.ssxs.entity.PageDevice;
import com.hx.ssxs.entity.PageOperateInfo;
import com.hx.ssxs.entity.PageTM;
import com.hx.ssxs.entity.PageTMBean;
import com.hx.ssxs.entity.Param;
import com.hx.ssxs.entity.SatTMInfo;
import com.hx.ssxs.service.IPage;
import com.hx.ssxs.util.DateTools;
import java.text.DecimalFormat;
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

public class PageImpl implements IPage {
  private static Log log = LogFactory.getLog(PageImpl.class);
  
  private String pageid;
  
  private boolean isgrid;
  
  private List<PageTMBean> list = new ArrayList<>();
  
  private Map<String, Session> mapSession = new ConcurrentHashMap<>();
  
  private Map<String, Object> paramMap = new HashMap<>();
  
  private Map<Object, Object> tablemap = null;
  
  private Map<String, Object> tmMap = null;
  
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  
  private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  private Map<String, Object> pageInfo = new HashMap<>();
  
  private List<Map<String, Object>> result = null;
  
  private DecimalFormat df = new DecimalFormat("0.00000");
  
  @Override
public void load(List<PageTMBean> pageContent) {
    this.list.addAll(pageContent);
  }
  
  public List<PageTMBean> getList() {
    return this.list;
  }
  
  public Session getMapSession(String clientIP) {
    return this.mapSession.get(String.valueOf(clientIP) + "&&" + this.pageid);
  }
  
  public void setMapSession(Map<String, Session> mapSession) {
    this.mapSession = mapSession;
  }
  
  public void putClientSession(String clientIP, Session session, String pageid) {
    this.mapSession.put(String.valueOf(clientIP) + "&&" + pageid, session);
  }
  
  private void removeSession(String clientIp, String pageid) {
    this.mapSession.remove(String.valueOf(clientIp) + "&&" + pageid);
  }
  
  @Override
public boolean close(String clientIp, String pageid) {
    boolean flag = false;
    removeSession(clientIp, pageid);
    if (this.mapSession.isEmpty()) {
		flag = true;
	} 
    return flag;
  }
  
  public void setData(SatTMInfo sti, int mid) {
    boolean isSendUNchange = false;
    List<Map<String, Object>> result = null;
    long dataTime = 0L;
    try {
      dataTime = this.sdf.parse(sti.getDataTime()).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
    } 
    Map<String, Param> map1 = new HashMap<>();
    Map<String, Param> map = new HashMap<>();
    if (sti.getListparam().size() != 0) {
		for (int i = 0; i < sti.getListparam().size(); i++) {
		    Param param = sti.getListparam().get(i);
		    if (param != null) {
				map.put(param.getCode(), param);
			} 
		  }
	}  
    if (log.isDebugEnabled()) {
		log.debug("[开始组装发送数据！]");
	} 
    Set<Map.Entry<String, Session>> entryset = this.mapSession.entrySet();
    if (entryset.size() == 0) {
		return;
	} 
    for (Map.Entry<String, Session> entry : entryset) {
      String[] keys = (""+entry.getKey()).split("&&");
      String clientip = keys[0];
      PageOperateInfo poi = null;
      synchronized (PageCache.selectMap) {
        poi = (PageOperateInfo)PageCache.selectMap.get(clientip);
      } 
      if (this.isgrid && 
        !poi.isSendFirst()) {
		continue;
	} 
      if (this.isgrid && 
        !this.pageid.equals(poi.getSelectPageID())) {
		continue;
	} 
      long currentTime = System.currentTimeMillis();
      int dev_mid = sti.getDev_mid();
      int column = 0;
      if (poi != null) {
        if (poi.isAutostate()) {
          if (1 == sti.getColumn()) {
            column = 1;
          } else if (2 == sti.getColumn()) {
            column = 2;
          } else if (3 == sti.getColumn()) {
            column = 3;
          } 
        } else if (poi.getFirstDev_mid() == sti.getDev_mid()) {
          column = 1;
          dev_mid = poi.getFirstDev_mid();
          poi.setFirstTime(currentTime);
        } else if (poi.getSecondDev_mid() == sti.getDev_mid()) {
          column = 2;
          dev_mid = poi.getSecondDev_mid();
          poi.setSecondTime(currentTime);
        } else if (poi.getThirdDev_mid() == sti.getDev_mid()) {
          column = 3;
          dev_mid = poi.getThirdDev_mid();
          poi.setThirdTime(currentTime);
        } else {
          continue;
        } 
        if (column != 0) {
          result = formData(mid, column, dataTime, map, dev_mid);
          if (log.isDebugEnabled()) {
			log.debug("[遥测配置页面处理数据完成！]");
		} 
          sendData(false, column, result, entry.getValue());
          this.paramMap.clear();
        } 
      } 
    } 
  }
  
  public boolean sendAfterSelectByHistory(SatTMInfo sti, int mid, Session session, String clientip) {
    int column = 0;
    int dev_mid = 0;
    long dataTime = 0L;
    try {
      dataTime = this.sdf.parse(sti.getDataTime()).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
    } 
    PageOperateInfo poi = (PageOperateInfo)PageCache.selectMap.get(clientip);
    if (poi != null) {
      if (poi.getFirstDev_mid() == sti.getDev_mid()) {
        column = 1;
        dev_mid = poi.getFirstDev_mid();
      } else if (poi.getSecondDev_mid() == sti.getDev_mid()) {
        column = 2;
        dev_mid = poi.getSecondDev_mid();
      } else if (poi.getThirdDev_mid() == sti.getDev_mid()) {
        column = 3;
        dev_mid = poi.getThirdDev_mid();
      } else {
        return true;
      } 
      Map<String, Param> map = new HashMap<>();
      if (sti.getListparam().size() != 0) {
		for (int i = 0; i < sti.getListparam().size(); i++) {
          Param param = sti.getListparam().get(i);
          if (param != null) {
			map.put(param.getCode(), param);
		} 
        }
	}  
      if (column != 0) {
        this.result = formData(mid, column, dataTime, map, dev_mid);
        if (log.isDebugEnabled()) {
			log.debug("[遥测配置页面处理数据完成！]");
		} 
        sendData(false, column, this.result, session);
        this.paramMap.clear();
      } 
    } 
    return true;
  }
  
  private void sendData(boolean flag, int colnum, List<Map<String, Object>> result, Session session) {
    Gson json = new Gson();
    try {
      if (session != null && session.isOpen()) {
		session.getAsyncRemote().sendText(json.toJson(result));
	} 
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
		log.debug("[连接关闭，数据发送异常！]");
	} 
    } 
    if (log.isDebugEnabled()) {
		log.debug("[遥测配置页面发送数据完成！]");
	} 
  }
  
  private List<Map<String, Object>> formData(int mid, int colnum, long dataTime, Map<String, Param> map, int dev_mid) {
    Param param = null;
    this.result = new ArrayList<>();
    for (PageTMBean ptb : this.list) {
      if ("2".equals(ptb.getPid())) {
        int gridRowId = 0;
        this.tablemap = new HashMap<>();
        this.tmMap = new HashMap<>();
        Map<String, Object> tmmap = null;
        for (PageTM pt : ptb.getList()) {
          param = map.get(pt.getTm_code());
          if (param != null) {
            tmmap = new HashMap<>();
            tmmap.put("id", param.getTm_id());
            tmmap.put("num", Integer.valueOf(param.getNum()));
            tmmap.put("code", param.getCode());
            tmmap.put("dataType", param.getDataType());
            if (!"".equals(param.getDisplayValue()) && param.getDisplayValue() != null) {
              String str = param.getDisplayValue();
              tmmap.put("disValue", str);
            } 
            String va = (new StringBuilder(String.valueOf(param.getValue()))).toString();
            if (colnum == 1 && 
              "P24－1W12W13".equals(param.getCode())) {
				System.out.println(String.valueOf(param.getNum()) + ":" + param.getValue());
			} 
            if (va.indexOf(".") > -1) {
				va = this.df.format(Double.parseDouble(va));
			} 
            if ("F0W0F0W3".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } else if ("P430W0W3".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } else if ("PE0W1W4".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } else if ("P90W46W49".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } else if ("P90W37W40".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } else if ("P90W28W31".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } else if ("P90W19W22".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } else if ("P90W10W13".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } else if ("P90W1W4".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } else if ("P20W23W26".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } else if ("P20W17W20".equals(param.getCode())) {
              va = this.sdf1.format(DateTools.jsTurnDate(Long.parseLong(va), "2009-01-01"));
            } 
            tmmap.put("value", va);
            tmmap.put("source", param.getSource());
            tmmap.put("rowid", Integer.valueOf(gridRowId));
            this.tmMap.put((new StringBuilder(String.valueOf(pt.getNum()))).toString(), tmmap);
          } 
          param = null;
          gridRowId++;
        } 
        if (this.tmMap != null && !this.tmMap.isEmpty()) {
          this.tablemap.put(Integer.valueOf(colnum), this.tmMap);
          this.tablemap.put("length", Integer.valueOf(this.tablemap.size()));
        } 
        this.tmMap = null;
      } else {
        if ("4".equals(ptb.getPid())) {
          if (colnum != ptb.getColNum()) {
			continue;
		} 
          this.tmMap = new HashMap<>();
          this.tmMap.put("displayContent", ptb.getDisplayContent());
          if ("1".equals(ptb.getDisplayContent()) || "0".equals(ptb.getDisplayContent())) {
            this.tmMap.put("value", Long.valueOf(dataTime));
          } else {
            PageDevice pd = PageConCache.getInstance().getDeviceInfo((new StringBuilder(String.valueOf(dev_mid))).toString());
            if (pd != null) {
				this.tmMap.put("value", pd.getDevice_name());
			} 
          } 
          this.paramMap.put(ptb.getModuleId(), this.tmMap);
          continue;
        } 
        if ("3".equals(ptb.getPid()) || "5".equals(ptb.getPid())) {
          if (colnum != ptb.getColNum()) {
			continue;
		} 
          this.tmMap = new HashMap<>();
          if (ptb.getList() != null) {
			for (PageTM pt : ptb.getList()) {
              param = map.get(pt.getTm_code());
              if (param != null) {
                String va = (new StringBuilder(String.valueOf(param.getValue()))).toString();
                param.setValue(va);
                this.tmMap.put((new StringBuilder(String.valueOf(pt.getNum()))).toString(), param);
              } 
            }
		}  
        } 
      } 
      if (this.tablemap != null && !this.tablemap.isEmpty()) {
        this.paramMap.put(ptb.getModuleId(), this.tablemap);
        this.tablemap = null;
        continue;
      } 
      if (this.tmMap != null && !this.tmMap.isEmpty()) {
        this.paramMap.put(ptb.getModuleId(), this.tmMap);
        this.tmMap = null;
      } 
    } 
    this.pageInfo.put("pageId", this.pageid);
    this.pageInfo.put("data", this.paramMap);
    this.pageInfo.put("mid", Integer.valueOf(mid));
    this.pageInfo.put("time", Long.valueOf(dataTime));
    this.result.add(this.pageInfo);
    return this.result;
  }
  
  public String getPageid() {
    return this.pageid;
  }
  
  public void setPageid(String pageid) {
    this.pageid = pageid;
  }
  
  public boolean isgrid() {
    return this.isgrid;
  }
  
  public void setIsgrid(boolean isgrid) {
    this.isgrid = isgrid;
  }
}
