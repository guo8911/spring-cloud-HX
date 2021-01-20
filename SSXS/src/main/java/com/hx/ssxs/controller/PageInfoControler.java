package com.hx.ssxs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
//import com.xpoplarsoft.framework.parameter.SystemParameter;
//import com.yk.ssxs.bean.PageOperateInfo;
//import com.yk.ssxs.cache.PageCache;
//import com.yk.ssxs.util.PageTools;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/pageInfoAction"})
public class PageInfoControler {
  private static Log log = LogFactory.getLog(PageInfoControler.class);
  
//  @Autowired
//  IPageInfoService service;
//  
//  @RequestMapping({"/getTree"})
//  @ResponseBody
//  public String getTree(String keyname, HttpServletRequest request) {
//    if (log.isDebugEnabled())
//      log.debug("[获取页面树信息，keyname=" + keyname + "]"); 
//    return JSONArray.toJSONString(this.service.getTree(keyname, request));
//  }
//  
//  @RequestMapping({"/getPageOfGD"})
//  @ResponseBody
//  public String getPageOfGD(String id) {
//    if (log.isDebugEnabled())
//      log.debug("[获取固定页面信息，id=" + id + "]"); 
//    return JSONArray.toJSONString(this.service.getPageOfGD(id));
//  }
//  
//  @RequestMapping({"/updateSelectPage"})
//  @ResponseBody
//  public String updateSelectPage(String tabid, String mid, HttpServletRequest request) {
//    if (log.isDebugEnabled())
//      log.debug("[获取固定页面信息，id=" + tabid + "]"); 
//    String clientIp = PageTools.getLocalIp(request);
//    return JSONArray.toJSONString(this.service.updateSelectPage(tabid, mid, 
//          clientIp));
//  }
//  
//  @RequestMapping({"/getPageFile"})
//  @ResponseBody
//  public String getPageFile(String proId, String mid, boolean readOnly) {
//    if (log.isDebugEnabled())
//      log.debug("[获取页面信息，proId=" + proId + ",readOnly=" + readOnly + "]"); 
//    return this.service.getPageFile(proId, Integer.parseInt(mid), readOnly);
//  }
//  
//  @RequestMapping({"/checkOutFile"})
//  @ResponseBody
//  public String checkOutFile(String proId) {
//    if (log.isDebugEnabled())
//      log.debug("[监测页面是否正在编辑，proId=" + proId + "]"); 
//    return this.service.checkOutFile(proId);
//  }
//  
//  @RequestMapping({"/getDeviceInfo"})
//  @ResponseBody
//  public String getDeviceInfo() {
//    if (log.isDebugEnabled())
//      log.debug("[获取测控设备]"); 
//    return this.service.getDeviceInfo();
//  }
//  
//  @RequestMapping({"/getClientIp"})
//  @ResponseBody
//  public String getClientIp(HttpServletRequest request) {
//    return PageTools.getLocalIp(request);
//  }
//  
//  @RequestMapping({"/getParamInfo"})
//  @ResponseBody
//  public String getParamInfo(String mid) {
//    if (log.isDebugEnabled())
//      log.debug("[获取航天器参数信息mid=" + mid + "]"); 
//    return this.service.getParamInfo(mid);
//  }
//  
//  @RequestMapping({"/changeReceiveDataState"})
//  @ResponseBody
//  public String changeReceiveDataState(HttpServletRequest request, String mid, String show_id) {
//    if (log.isDebugEnabled())
//      log.debug("[改变数据接收状态mid=" + mid + ",show_id=" + show_id + "]"); 
//    String clientIp = PageTools.getLocalIp(request);
//    boolean flag = this.service.changeReceiveDataState(mid, show_id, clientIp);
//    if (flag)
//      return "true"; 
//    return "false";
//  }
//  
//  @RequestMapping({"/changeSelectTerm"})
//  @ResponseBody
//  public String changeSelectTerm(String dev_mids, HttpServletRequest request) {
//    if (log.isDebugEnabled())
//      log.debug("[改变数据接收状态devmids=" + dev_mids + "]"); 
//    String clientIp = PageTools.getLocalIp(request);
//    boolean flag = this.service.changeSelectTerm(dev_mids, clientIp);
//    if (flag)
//      return "true"; 
//    return "false";
//  }
//  
//  @RequestMapping({"/deletePageCacheInfo"})
//  @ResponseBody
//  public String deletePageCacheInfo(String tabid, HttpServletRequest request) {
//    if (log.isDebugEnabled())
//      log.debug("[改变数据接收状态tabid=" + tabid + "]"); 
//    String clientIp = PageTools.getLocalIp(request);
//    PageOperateInfo poi = (PageOperateInfo)PageCache.selectMap.get(clientIp);
//    if (poi != null)
//      poi.setSelectPageID(null); 
//    return "true";
//  }
//  
//  @RequestMapping({"/getTrackCountInfo"})
//  @ResponseBody
//  public String getTrackCountInfo(String mid) {
//    if (log.isDebugEnabled())
//      log.debug("[获取轨道计算结果数据mid=" + mid + "]"); 
//    return this.service.getTrackCountInfo(mid);
//  }
//  
//  @RequestMapping({"/getForecastInfo"})
//  @ResponseBody
//  public String getForecastInfo(String mid) {
//    if (log.isDebugEnabled())
//      log.debug("[获取跟踪预报信息数据mid=" + mid + "]"); 
//    return this.service.getForecastInfo(mid);
//  }
//  
//  @RequestMapping({"/getSysManagerAddr"})
//  @ResponseBody
//  public String getSysManagerAddr(HttpServletRequest request) {
//    Map<String, Object> map = new HashMap<>();
//    String pageUrl = SystemParameter.getInstance().getParameter(
//        "sysManagerAddr");
//    map.put("result", Boolean.valueOf(true));
//    map.put("url", pageUrl);
//    return JSON.toJSONString(map);
//  }
//  
//  @RequestMapping({"/getSatDownForecastInfo"})
//  @ResponseBody
//  public String getSatDownForecastInfo(String lastime, String mid) {
//    if (log.isDebugEnabled())
//      log.debug("[获取星下点预报信息数据mid=" + mid + ",lastime=" + lastime + "]"); 
//    return this.service.getSatDownForecastInfo(lastime, mid);
//  }
}
