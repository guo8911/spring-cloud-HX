package com.hx.ssxs.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hx.ssxs.service.IPageInfoService;
//import com.xpoplarsoft.framework.parameter.SystemParameter;
//import com.xpoplarsoft.framework.utils.DateTools;
//import com.yk.ssxs.bean.PageOperateInfo;
//import com.yk.ssxs.bean.PageTMBean;
//import com.yk.ssxs.bean.SatInfoManager;
//import com.yk.ssxs.cache.PageCache;
//import com.yk.ssxs.cache.PageConCache;
//import com.yk.ssxs.handle.impl.PageImpl;
//import com.yk.ssxs.page.dao.IPageInfoDao;
//import com.yk.ssxs.pagebean.PageDevice;
//import com.yk.ssxs.pagebean.PageTM;
//import com.yk.ssxs.util.PageTools;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageInfoServiceImpl implements IPageInfoService {
  private static Log log = LogFactory.getLog(PageInfoServiceImpl.class);
  
  public static Map<String, List<Map<String, Object>>> paramList = new ConcurrentHashMap<String, List<Map<String, Object>>>();
  
//  @Autowired
//  IPageInfoDao dao;
//  
//  public List<Map<String, Object>> getTree(String keyname, HttpServletRequest request) {
//    List<Map<String, Object>> maps = this.dao.getTree();
//    List<Map<String, Object>> satmap = this.dao.getSatProject();
//    String localurl = PageTools.getLocalUrl(request);
//    for (Map<String, Object> map : maps) {
//      if (map.get("type").toString().equals("2")) {
//        map.put("icon", String.valueOf(localurl) + "/icons/16X16/asterisk_orange.png");
//      } else if (map.get("type").toString().equals("1")) {
//        map.put("icon", String.valueOf(localurl) + "main/img/file.ico");
//      } 
//      if (map.get("owner").toString().equals("0")) {
//        map.put("icon", String.valueOf(localurl) + "main/img/sat.gif");
//        continue;
//      } 
//      if (!map.get("owner").toString().equals("0") && map.get("type").toString().equals("0"))
//        map.put("icon", String.valueOf(localurl) + "main/img/folder.ico"); 
//    } 
//    for (int j = 0; j < maps.size(); j++) {
//      for (int i = 0; i < satmap.size(); i++) {
//    	  if ("0".equals(maps.get(j).get("owner") + "")) {
//				if (null == satmap.get(i).get("name")
//						|| null == maps.get(j).get("name")) {
//					continue;
//				}
//				if (satmap.get(i).get("name")
//						.equals(maps.get(j).get("name"))) {
//					maps.get(j).put("sat_id", satmap.get(i).get("sat_id"));
//					maps.get(j).put("mid", satmap.get(i).get("mid"));
//					maps.get(j).put("icon", localurl + "main/img/sat.gif");
//					maps.get(j).put("sat_code",
//							satmap.get(i).get("sat_code"));
//				}
//			}
//      } 
//    } 
//    if (keyname == null || keyname.equals(""))
//      return maps; 
//    List<Map<String, Object>> rsts = new ArrayList<>();
//    for (Map<String, Object> map : maps) {
//      if (map.get("name").toString().contains(keyname)) {
//        addParents(maps, map, rsts);
//        rsts.add(map);
//        addChildren(maps, map, rsts);
//      } 
//    } 
//    return rsts;
//  }
//  
//  private void addParents(List<Map<String, Object>> nodes, Map<String, Object> node, List<Map<String, Object>> rsts) {
//    String owner = node.get("owner").toString();
//    if (owner.equals("0"))
//      return; 
//    for (Map<String, Object> map : nodes) {
//      if (map.get("id").toString().equals(owner) && !rsts.contains(map)) {
//        rsts.add(map);
//        addParents(nodes, map, rsts);
//      } 
//    } 
//  }
//  
//  private void addChildren(List<Map<String, Object>> nodes, Map<String, Object> node, List<Map<String, Object>> rsts) {
//    if (!node.get("type").toString().equals("0"))
//      return; 
//    String id = node.get("id").toString();
//    for (Map<String, Object> map : nodes) {
//      if (map.get("owner").toString().equals(id) && !rsts.contains(map)) {
//        rsts.add(map);
//        addChildren(nodes, map, rsts);
//      } 
//    } 
//  }
//  
//  public Map<String, Object> getPageOfGD(String id) {
//    Map<String, Object> map = this.dao.getPageOfGD(id);
//    return map;
//  }
//  
//  public String getPageFile(String proId, int mid, boolean readOnly) {
//    Map<String, Object> pageInfo = this.dao.getPageFile(proId);
//    if (pageInfo != null) {
//      String data = (String)pageInfo.get("data");
//      addPageConCache(data, proId, mid);
//      return data;
//    } 
//    return null;
//  }
//  
//  public String checkOutFile(String proId) {
//    Map<String, Object> map = this.dao.checkOutFile(proId);
//    if (map != null)
//      return "true"; 
//    return "false";
//  }
//  
//  public String getDeviceInfo() {
//    List<Map<String, Object>> list = this.dao.getDeviceInfo();
//    PageDevice pd = null;
//    Map<String, Object> map1 = null;
//    for (int i = 0; i < list.size(); i++) {
//      map1 = list.get(i);
//      pd = new PageDevice();
//      String device_code = map1.get("device_coding").toString();
//      String device_id = map1.get("pk_id").toString();
//      String device_mid = map1.get("mid").toString();
//      String device_name = map1.get("device_name").toString();
//      pd.setDevice_id(device_id);
//      pd.setDevice_mid(device_mid);
//      pd.setDevcie_code(device_code);
//      pd.setDevice_name(device_name);
//      pd.setRowid(i);
//      PageConCache.getInstance().putDeviceInfo(device_mid, pd);
//    } 
//    return JSONArray.toJSONString(list);
//  }
//  
//  public String getParamInfo(String mid) {
//    List<Map<String, Object>> list = paramList.get(mid);
//    Map<String, Object> map = new HashMap<>();
//    map.put("Rows", list);
//    map.put("Total", Integer.valueOf(list.size()));
//    return JSONArray.toJSONString(map);
//  }
//  
//  private void addPageConCache(String data, String proId, int mid) {
//    Object obj = JSON.parse(data);
//    Map<String, Object> map = (Map<String, Object>)obj;
//    obj = map.get("graphs");
//    if (obj == null)
//      return; 
//    List<Map<String, Object>> list = (List<Map<String, Object>>)obj;
//    PageTMBean tb = null;
//    PageTM pt = null;
//    List<PageTM> TBlist = null;
//    List<PageTMBean> pageList = new ArrayList<>();
//    boolean isgrid = true;
//    for (int i = 0; i < list.size(); i++) {
//      tb = new PageTMBean();
//      map = list.get(i);
//      String id = (String)map.get("id");
//      tb.setModuleId(id);
//      String pid = (String)map.get("pid");
//      tb.setPid(pid);
//      Map<String, Object> map1 = (Map<String, Object>)map.get("properties");
//      if ("4".equals(pid)) {
//        tb.setDev(Integer.parseInt((String)map1.get("dev")));
//        tb.setDisplayContent((String)map1.get("displayContent"));
//      } 
//      List<Map<String, Object>> list1 = (List<Map<String, Object>>)map1.get("params");
//      if ("2".equals(pid)) {
//        if (map1.get("colNum") != null) {
//          int colums = Integer.parseInt((String)map1.get("colNum"));
//          if (colums != 0)
//            tb.setColNum(colums); 
//        } 
//      } else if ("3".equals(pid) || "5".equals(pid)) {
//        if (map1.get("dev") != null) {
//          int colums = Integer.parseInt((String)map1.get("dev"));
//          if (colums != 0)
//            tb.setColNum(colums); 
//        } 
//        isgrid = false;
//      } else if ("4".equals(pid) && 
//        map1.get("dev") != null) {
//        int colums = Integer.parseInt((String)map1.get("dev"));
//        if (colums != 0)
//          tb.setColNum(colums); 
//      } 
//      TBlist = new ArrayList<>();
//      if (list1 != null) {
//        for (int j = 0; j < list1.size(); j++) {
//          pt = new PageTM();
//          map = list1.get(j);
//          String tm_name = (String)map.get("name");
//          String tm_code = (String)map.get("code");
//          int num = Integer.parseInt((String)map.get("parId"));
//          pt.setTm_code(tm_code);
//          pt.setTm_name(tm_name);
//          pt.setNum(num);
//          TBlist.add(pt);
//        } 
//        tb.setList(TBlist);
//      } 
//      pageList.add(tb);
//    } 
//    PageImpl page = new PageImpl();
//    page.load(pageList);
//    page.setIsgrid(isgrid);
//    page.setPageid(proId);
//    synchronized (PageCache.map) {
//      SatInfoManager sim = (SatInfoManager)PageCache.map.get(Integer.valueOf(mid));
//      if (sim != null) {
//        sim.getPmi().openPage(page);
//      } else if (log.isErrorEnabled()) {
//        log.error("[mid=" + mid + "的航天器未配置网络接口！]");
//      } 
//    } 
//  }
//  
//  public String getTrackCountInfo(String mid) {
//    List<Map<String, Object>> list = this.dao.getTrackCountInfo(mid);
//    Map<String, Object> map = new HashMap<>();
//    List<Map<String, Object>> result = new ArrayList<>();
//    for (int i = 0; i < list.size() && 
//      i <= 19; i++)
//      result.add(list.get(i)); 
//    map.put("Rows", result);
//    map.put("Total", Integer.valueOf(result.size()));
//    return JSONArray.toJSONString(map);
//  }
//  
//  public String getForecastInfo(String mid) {
//    List<Map<String, Object>> list = this.dao.getForecastInfo(mid);
//    Map<String, Object> map = new HashMap<>();
//    List<Map<String, Object>> result = new ArrayList<>();
//    for (int i = 0; i < list.size() && 
//      i <= 19; i++)
//      result.add(list.get(i)); 
//    map.put("Rows", result);
//    map.put("Total", Integer.valueOf(result.size()));
//    return JSONArray.toJSONString(map);
//  }
//  
//  public boolean changeSelectTerm(String devmids, String clientIp) {
//    if (devmids == null)
//      synchronized (PageCache.selectMap) {
//        PageOperateInfo poi = (PageOperateInfo)PageCache.selectMap.get(clientIp);
//        if (poi != null) {
//          poi.setFirstDev_mid(0);
//          poi.setSecondDev_mid(0);
//          poi.setThirdDev_mid(0);
//          poi.setAutostate(true);
//        } else {
//          poi = new PageOperateInfo();
//          poi.setCurrntIP(clientIp);
//          poi.setAutostate(true);
//          PageCache.selectMap.put(clientIp, poi);
//        } 
//        return true;
//      }  
//    String[] devmid = devmids.split(",");
//    synchronized (PageCache.selectMap) {
//      PageOperateInfo poi = (PageOperateInfo)PageCache.selectMap.get(clientIp);
//      if (poi == null)
//        poi = new PageOperateInfo(); 
//      poi.setFirstDev_mid(0);
//      poi.setSecondDev_mid(0);
//      poi.setThirdDev_mid(0);
//      boolean flag = false;
//      if (devmid.length > 2)
//        poi.setThirdDev_mid(Integer.parseInt(devmid[2])); 
//      if (devmid.length > 1)
//        poi.setSecondDev_mid(Integer.parseInt(devmid[1])); 
//      if (devmid.length > 0) {
//        poi.setFirstDev_mid(Integer.parseInt(devmid[0]));
//        flag = true;
//      } 
//      if (!flag) {
//        poi.setAutostate(true);
//      } else {
//        poi.setAutostate(false);
//      } 
//    } 
//    return true;
//  }
//  
//  public boolean changeReceiveDataState(String mid, String show_id, String clientIp) {
//    return true;
//  }
//  
//  public String getSatDownForecastInfo(String lastime, String mid) {
//    String cycle = SystemParameter.getInstance().getParameter("satCycle" + mid);
//    if (cycle == null && "".equals(cycle)) {
//      if (log.isDebugEnabled())
//        log.debug("[航天器mid=" + mid + "运行周期为空]"); 
//      cycle = "100";
//    } 
//    if ("".equals(lastime))
//      lastime = DateTools.getCurryDateTime(); 
//    List<Map<String, Object>> list = this.dao.getSatDownForecastInfo(lastime, mid, Integer.parseInt(cycle));
//    Map<String, Object> map = new HashMap<>();
//    map.put("Rows", list);
//    map.put("Total", Integer.valueOf(list.size()));
//    return JSONArray.toJSONString(map);
//  }
//  
//  public Object updateSelectPage(String tabid, String mid, String clientIp) {
//    synchronized (PageCache.selectMap) {
//      PageOperateInfo poi = (PageOperateInfo)PageCache.selectMap.get(clientIp);
//      if (poi == null)
//        poi = new PageOperateInfo(); 
//      poi.setSelectPageID(tabid);
//      poi.setSendFirst(true);
//      PageCache.selectMap.put(clientIp, poi);
//    } 
//    return Boolean.valueOf(true);
//  }
}
