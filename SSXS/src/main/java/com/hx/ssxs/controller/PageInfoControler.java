package com.hx.ssxs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.entity.DeviceInfo;
import com.hx.ssxs.entity.PageOperateInfo;
import com.hx.ssxs.entity.SxGuding;
import com.hx.ssxs.entity.ViewSxProject;
import com.hx.ssxs.service.IPageInfoService;
import com.hx.ssxs.util.PageTools;
import com.hx.ssxs.util.RedisUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author Jerome Guo
 * 20210126
 * */
@Controller
@RequestMapping({"/rest/pageInfoAction"})
public class PageInfoControler {
  private static Log log = LogFactory.getLog(PageInfoControler.class);
  
  @Autowired
  private IPageInfoService iPageInfoService;
  @Value("${sysManagerAddr}")
  private String sysManagerAddr;
  @Autowired
  private RedisUtil redisUtil;
  
  @RequestMapping({"/getTree"})
  @ResponseBody
  public List<ViewSxProject> getTree(String keyname, HttpServletRequest request) {
    if (log.isDebugEnabled()) {
		log.debug("[获取页面树信息，keyname=" + keyname + "]");
	} 
    return this.iPageInfoService.getTree(keyname, request);
  }
  
  @RequestMapping({"/getPageOfGD"})
  @ResponseBody
  public SxGuding getPageOfGD(int id) {
    if (log.isDebugEnabled()) {
		log.debug("[获取固定页面信息，id=" + id + "]");
	} 
    return this.iPageInfoService.getPageOfGD(id);
  }
  
  @RequestMapping({"/updateSelectPage"})
  @ResponseBody
  public String updateSelectPage(String tabid, String mid, HttpServletRequest request) {
    if (log.isDebugEnabled()) {
		log.debug("[获取固定页面信息，id=" + tabid + "]");
	} 
    String clientIp = PageTools.getLocalIp(request);
    return JSONArray.toJSONString(this.iPageInfoService.updateSelectPage(tabid, mid, 
          clientIp));
  }
  
  @RequestMapping({"/getPageFile"})
  @ResponseBody
  public String getPageFile(int proId, int mid, boolean readOnly) {
    if (log.isDebugEnabled()) {
		log.debug("[获取页面信息，proId=" + proId + ",readOnly=" + readOnly + "]");
	} 
    return this.iPageInfoService.getPageFile(proId, mid, readOnly);
  }
  
  @RequestMapping({"/checkOutFile"})
  @ResponseBody
  public Boolean checkOutFile(int proId) {
    if (log.isDebugEnabled()) {
		log.debug("[监测页面是否正在编辑，proId=" + proId + "]");
	} 
    return this.iPageInfoService.checkOutFile(proId);
  }
  
  @RequestMapping({"/getDeviceInfo"})
  @ResponseBody
  public List<DeviceInfo> getDeviceInfo() {
    if (log.isDebugEnabled()) {
		log.debug("[获取测控设备]");
	} 
    return this.iPageInfoService.getDeviceInfo();
  }
  
  @RequestMapping({"/getClientIp"})
  @ResponseBody
  public String getClientIp(HttpServletRequest request) {
    return PageTools.getLocalIp(request);
  }
  /*
   * 查询为空，天知道有什么用，原程序有此接口，暂时继续保留
   * */
  @RequestMapping({"/getParamInfo"})
  @ResponseBody
  public String getParamInfo(String mid) {
    if (log.isDebugEnabled()) {
		log.debug("[获取航天器参数信息mid=" + mid + "]");
	} 
    return this.iPageInfoService.getParamInfo(mid);
  }
  /*
   * 恒返回true，天知道有什么用，原程序有此接口，暂时继续保留
   * */
  @RequestMapping({"/changeReceiveDataState"})
  @ResponseBody
  public String changeReceiveDataState(HttpServletRequest request, String mid, String show_id) {
    if (log.isDebugEnabled()) {
		log.debug("[改变数据接收状态mid=" + mid + ",show_id=" + show_id + "]");
	} 
    String clientIp = PageTools.getLocalIp(request);
    boolean flag = this.iPageInfoService.changeReceiveDataState(mid, show_id, clientIp);
    if (flag) {
		return "true";
	} 
    return "false";
  }
  
  @RequestMapping({"/changeSelectTerm"})
  @ResponseBody
  public String changeSelectTerm(String dev_mids, HttpServletRequest request) {
    if (log.isDebugEnabled()) {
		log.debug("[改变数据接收状态devmids=" + dev_mids + "]");
	} 
    String clientIp = PageTools.getLocalIp(request);
    boolean flag = this.iPageInfoService.changeSelectTerm(dev_mids, clientIp);
    if (flag) {
		return "true";
	} 
    return "false";
  }
  
  @RequestMapping({"/deletePageCacheInfo"})
  @ResponseBody
  public String deletePageCacheInfo(String tabid, HttpServletRequest request) {
    if (log.isDebugEnabled()) {
		log.debug("[改变数据接收状态tabid=" + tabid + "]");
	} 
    String clientIp = PageTools.getLocalIp(request);
//    PageOperateInfo poi = (PageOperateInfo)PageCache.selectMap.get(clientIp);
    PageOperateInfo poi = (PageOperateInfo) redisUtil.getHash("selectMap", clientIp);
    if (poi != null) {
		poi.setSelectPageID(null);
	} 
    redisUtil.setHash("selectMap", clientIp, poi);
    return "true";
  }
  
  @RequestMapping({"/getTrackCountInfo"})
  @ResponseBody
  public String getTrackCountInfo(int mid) {
    if (log.isDebugEnabled()) {
		log.debug("[获取轨道计算结果数据mid=" + mid + "]");
	} 
    return this.iPageInfoService.getTrackCountInfo(mid);
  }
  
  @RequestMapping({"/getForecastInfo"})
  @ResponseBody
  public String getForecastInfo(int mid) {
    if (log.isDebugEnabled()) {
		log.debug("[获取跟踪预报信息数据mid=" + mid + "]");
	} 
    return this.iPageInfoService.getForecastInfo(mid);
  }
  
  @RequestMapping({"/getSysManagerAddr"})
  @ResponseBody
  public String getSysManagerAddr(HttpServletRequest request) {
    Map<String, Object> map = new HashMap<>();
    String pageUrl = sysManagerAddr;
    map.put("result", Boolean.valueOf(true));
    map.put("url", pageUrl);
    return JSON.toJSONString(map);
  }
  
  @RequestMapping({"/getSatDownForecastInfo"})
  @ResponseBody
  public String getSatDownForecastInfo(String lastime, int mid) {
    if (log.isDebugEnabled()) {
		log.debug("[获取星下点预报信息数据mid=" + mid + ",lastime=" + lastime + "]");
	} 
    return this.iPageInfoService.getSatDownForecastInfo(lastime, mid);
  }
}
