package com.hx.ssxs.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hx.ssxs.entity.PageOperateInfo;
import com.hx.ssxs.entity.SatInfoManager;

public class PageCache {
  public static Map<Integer, SatInfoManager> map = new ConcurrentHashMap<>();
  
  public static volatile Map<String, Integer> satInfo = new ConcurrentHashMap<>();
  
  public static Map<String, PageOperateInfo> selectMap = new ConcurrentHashMap<>();
}
