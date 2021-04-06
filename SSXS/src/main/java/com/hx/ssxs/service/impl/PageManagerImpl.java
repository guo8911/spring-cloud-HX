package com.hx.ssxs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.entity.PageOperateInfo;
import com.hx.ssxs.entity.SatTMInfo;
import com.hx.ssxs.service.IPageManager;
import com.hx.ssxs.util.RedisUtil;

public class PageManagerImpl implements IPageManager {
  private static Log log = LogFactory.getLog(PageManagerImpl.class);
  
  private Map<String, PageImpl> pageMap = new ConcurrentHashMap<>();
  
  private List<String> listSelect = null;
  
  private PageImpl pi = null;
  
  private int mid;
  
  private RedisUtil redisUtil;
  
  public PageManagerImpl(Integer mid,RedisUtil redisUtil) {
    this.mid = mid.intValue();
    this.redisUtil = redisUtil;
  }
  
  @Override
public PageImpl getPageImpl(String pageid,Integer mid) {
	  return this.pageMap.get(pageid);
  }
  
  public void setMap(Map<String, PageImpl> map, int mid,String clientip ) {
//    this.pageMap.putAll(map);
	  map.forEach((key, value) -> {
		  redisUtil.setHash("pageMap", clientip+"_"+mid+"_"+key, value);
      });
  }
  
  @Override
public void setData(SatTMInfo listparam, int mid) {
	  if (this.pageMap == null) {
		return;
	}  
    if (mid != this.mid && 
      log.isDebugEnabled()) {
		log.debug("[页面管理器错乱！]");
	} 
    synchronized (this.pageMap) {
//    	Set<Map.Entry<Object,Object>> entryset = redisUtil.getHashs("pageMap").entrySet();
      Set<Map.Entry<String, PageImpl>> entryset = this.pageMap.entrySet();
      if (entryset.size() == 0) {
		return;
	} 
      if (this.listSelect == null) {
		this.listSelect = new ArrayList<>();
	} 
      synchronized (PageCache.selectMap) {
//        Set<Map.Entry<String, PageOperateInfo>> entrySelect = PageCache.selectMap.entrySet();
        Set<Map.Entry<Object,Object>> entrySelect = redisUtil.getHashs("selectMap").entrySet();
        for (Map.Entry<Object,Object> select : entrySelect) {
          PageOperateInfo poi = (PageOperateInfo) select.getValue();
          String pageid = poi.getSelectPageID();
          if (pageid == null) {
			continue;
		} 
          if (!this.listSelect.contains(pageid)) {
			this.listSelect.add(poi.getSelectPageID());
		} 
        } 
      } 
      for (Entry<String, PageImpl> entry : entryset) {
        this.pi = entry.getValue();
        if (this.pi.isgrid() && 
          !this.listSelect.contains(entry.getKey())) {
			continue;
		} 
        this.pi.setData(listparam, mid);
        this.pi = null;
      } 
      this.listSelect.clear();
    } 
  }
  
  @Override
public void openPage(PageImpl page,Integer mid,String clientip) {
    synchronized (this.pageMap) {
    	PageImpl pi = (PageImpl) redisUtil.getHash("pageMap", clientip+"_"+mid+"_"+page.getPageid());
//      PageImpl pi = this.pageMap.get(page.getPageid());
      if (pi == null) {
//        this.pageMap.put(page.getPageid(), page);
    	  redisUtil.setHash("pageMap", clientip+"_"+mid+"_"+page.getPageid(), page);
      } else {
        pi.setIsgrid(page.isgrid());
        pi.load(page.getList());
        redisUtil.setHash("pageMap", clientip+"_"+mid+"_"+page.getPageid(), pi);
      } 
    } 
  }
  
  @Override
public void closePage(String pageId, String clientIp,String mid) { 
    PageImpl pi = this.pageMap.get(pageId);
//    PageImpl pi = (PageImpl) redisUtil.getHash("pageMap", mid+"_"+pageId);
    boolean flag = pi.close(clientIp, pageId);
    if (flag) {
		this.pageMap.remove(pageId);
    	redisUtil.deletehash("pageMap", clientIp+"_"+mid+"_"+pageId);
	} 
  }
  
  @Override
public void addPageSession(Session session, String clientip, String pageid,String mid) {
    synchronized (this.pageMap) {
//      PageImpl pi = this.pageMap.get(pageid);
    	PageImpl pi = (PageImpl) redisUtil.getHash("pageMap", clientip+"_"+mid+"_"+pageid);
      if (pi == null) {
        pi = new PageImpl(redisUtil);
        pi.setPageid(pageid);
        redisUtil.setHash("pageMap", clientip+"_"+mid+"_"+pageid, pi);
      } 
      pi.putClientSession(clientip, session, pageid);
      pi.setRedisUtil(redisUtil);
      this.pageMap.put(pageid, pi);
    } 
  }
}
