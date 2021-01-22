package com.hx.ssxs.service;

import java.util.List;

import com.hx.ssxs.entity.PageTMBean;

public interface IPage {
  void load(List<PageTMBean> paramList);
  
  boolean close(String paramString1, String paramString2);
}
