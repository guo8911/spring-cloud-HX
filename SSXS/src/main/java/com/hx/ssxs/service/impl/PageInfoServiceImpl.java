package com.hx.ssxs.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.cache.PageConCache;
import com.hx.ssxs.entity.DeviceInfo;
import com.hx.ssxs.entity.PageDevice;
import com.hx.ssxs.entity.PageOperateInfo;
import com.hx.ssxs.entity.PageTM;
import com.hx.ssxs.entity.PageTMBean;
import com.hx.ssxs.entity.SatInfoManager;
import com.hx.ssxs.entity.SxCheckout;
import com.hx.ssxs.entity.SxFile;
import com.hx.ssxs.entity.SxFollow;
import com.hx.ssxs.entity.SxGuding;
import com.hx.ssxs.entity.SxProjectSat;
import com.hx.ssxs.entity.SxSatDown;
import com.hx.ssxs.entity.SxTrackCount;
import com.hx.ssxs.entity.ViewSxProject;
import com.hx.ssxs.mapper.DeviceInfoMapper;
import com.hx.ssxs.mapper.SxCheckoutMapper;
import com.hx.ssxs.mapper.SxFileMapper;
import com.hx.ssxs.mapper.SxFollowMapper;
import com.hx.ssxs.mapper.SxGudingMapper;
import com.hx.ssxs.mapper.SxProjectMapper;
import com.hx.ssxs.mapper.SxSatDownMapper;
import com.hx.ssxs.mapper.SxTrackCountMapper;
import com.hx.ssxs.mapper.ViewSxProjectMapper;
import com.hx.ssxs.service.IPageInfoService;
import com.hx.ssxs.util.DateTools;
import com.hx.ssxs.util.PageTools;
import com.hx.ssxs.util.RedisUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PageInfoServiceImpl implements IPageInfoService {
  private static Log log = LogFactory.getLog(PageInfoServiceImpl.class);
  
  public static Map<String, List<Map<String, Object>>> paramList = new ConcurrentHashMap<String, List<Map<String, Object>>>();
  
  @Autowired
  private ViewSxProjectMapper viewSxProjectMapper;
  @Autowired
  private SxProjectMapper sxProjectMapper;
  @Autowired
  private SxGudingMapper sxGudingMapper;
  @Autowired
  private SxFileMapper sxFileMapper;
  @Autowired
  private SxCheckoutMapper sxCheckoutMapper;
  @Autowired
  private DeviceInfoMapper deviceInfoMapper;
  @Autowired
  private SxTrackCountMapper sxTrackCountMapper;
  @Autowired
  private SxFollowMapper sxFollowMapper;
  @Autowired
  private SxSatDownMapper sxSatDownMapper;
  @Autowired
  private RedisUtil redisUtil;
  @Value("${satCycle1}")
  private String satCycle1;
  
  @Override
public List<ViewSxProject> getTree(String keyname, HttpServletRequest request) {
	  List<ViewSxProject> viewSxProjectList = viewSxProjectMapper.getProj();
	  List<SxProjectSat> sxProjectSatList = sxProjectMapper.getSatProject();
//    String localurl = PageTools.getLocalUrl(request);
    for (ViewSxProject viewSxProject : viewSxProjectList) {
      if ("2".equals(viewSxProject.getType())) {
    	  viewSxProject.setIcon("asterisk_orange.png");
      } else if ("1".equals(viewSxProject.getType())) {
    	  viewSxProject.setIcon("file.ico");
      } 
      if (Objects.equals(viewSxProject.getOwner(), 0)) {
    	  viewSxProject.setIcon("sat.gif");
      } else if ("0".equals(viewSxProject.getType())) {
		viewSxProject.setIcon("folder.ico");
	} 
    } 
    for (int j = 0; j < viewSxProjectList.size(); j++) {
      for (int i = 0; i < sxProjectSatList.size(); i++) {
    	  if ("0".equals(viewSxProjectList.get(j).getOwner() + "")) {
				if (null == sxProjectSatList.get(i).getSat_name()
						|| null == viewSxProjectList.get(j).getName()) {
					continue;
				}
				if (sxProjectSatList.get(i).getSat_name().equals(viewSxProjectList.get(j).getName())) {
					viewSxProjectList.get(j).setSat_id(sxProjectSatList.get(i).getSat_id());
					viewSxProjectList.get(j).setMid(sxProjectSatList.get(i).getMid());
					viewSxProjectList.get(j).setSat_code(sxProjectSatList.get(i).getSat_code());
				}
			}
      } 
    } 
    if (keyname == null || keyname.equals("")) {
		return viewSxProjectList;
	} 
    List<ViewSxProject> rsts = new ArrayList<>();
    for (ViewSxProject map : viewSxProjectList) {
      if (map.getName().contains(keyname)) {
        addParents(viewSxProjectList, map, rsts);
        rsts.add(map);
        addChildren(viewSxProjectList, map, rsts);
      } 
    } 
    return rsts;
  }
  
  private void addParents(List<ViewSxProject> nodes, ViewSxProject node, List<ViewSxProject> rsts) {
    int owner = node.getOwner() ;
    if (owner == 0) {
		return;
	}
    for (ViewSxProject map : nodes) {
      if (Objects.equals(map.getId(),owner) && !rsts.contains(map)) {
        rsts.add(map);
        addParents(nodes, map, rsts);
      } 
    } 
  }
  
  private void addChildren(List<ViewSxProject> nodes, ViewSxProject node, List<ViewSxProject> rsts) {
    if (!"0".equals(node.getType())) {
		return;
	} 
    int id = node.getId();
    for (ViewSxProject map : nodes) {
      if (Objects.equals(map.getOwner(),id) && !rsts.contains(map)) {
        rsts.add(map);
        addChildren(nodes, map, rsts);
      } 
    } 
  }
  
  @Override
public SxGuding getPageOfGD(int id) {
	  SxGuding map = sxGudingMapper.getPageOfGD(id);
    return map;
  }
  
  @Override
public String getPageFile(int proId, int mid, boolean readOnly,String clientip) {
	  SxFile pageInfo = sxFileMapper.getPageFile(proId);
    if (pageInfo != null) {
      String data = pageInfo.getData();
      addPageConCache(data, proId, mid,clientip);
      return data;
    } 
    return null;
  }
  
  @Override
public Boolean checkOutFile(int proId) {
	  SxCheckout map = sxCheckoutMapper.checkOutFile(proId);
    if (map != null) {
		return true;
	} 
    return false;
  }
  
  @Override
public List<DeviceInfo> getDeviceInfo() {
	  List<DeviceInfo> list = deviceInfoMapper.getDeviceInfo();
    PageDevice pd = null;
    DeviceInfo map1 = null;
    for (int i = 0; i < list.size(); i++) {
      map1 = list.get(i);
      pd = new PageDevice();
      String device_code = map1.getDevice_coding();
      String device_id = map1.getPk_id()+"";
      String device_mid = map1.getMid()+"";
      String device_name = map1.getDevice_name();
      pd.setDevice_id(device_id);
      pd.setDevice_mid(device_mid);
      pd.setDevcie_code(device_code);
      pd.setDevice_name(device_name);
      pd.setRowid(i);
      PageConCache.getInstance().putDeviceInfo(device_mid, pd);
    } 
    return list;
  }
  
  @Override
public String getParamInfo(String mid) {
    List<Map<String, Object>> list = paramList.get(mid);
    Map<String, Object> map = new HashMap<>();
    map.put("Rows", list);
    if(list !=null) {
    	map.put("Total", list.size());
    }
    return JSONArray.toJSONString(map);
  }
  
  private void addPageConCache(String data, int proId, int mid,String clientip) {
    Object obj = JSON.parse(data);
    Map<String, Object> map = (Map<String, Object>)obj;
    obj = map.get("graphs");
    if (obj == null) {
		return;
	} 
    List<Map<String, Object>> list = (List<Map<String, Object>>)obj;
    PageTMBean tb = null;
    PageTM pt = null;
    List<PageTM> TBlist = null;
    List<PageTMBean> pageList = new ArrayList<>();
    boolean isgrid = true;
    for (int i = 0; i < list.size(); i++) {
      tb = new PageTMBean();
      map = list.get(i);
      String id = ""+map.get("id");
      tb.setModuleId(id);
      String pid = ""+map.get("pid");
      tb.setPid(pid);
      Map<String, Object> map1 = (Map<String, Object>)map.get("properties");
      if ("4".equals(pid)) {
        tb.setDev(Integer.parseInt(""+map1.get("dev")));
        tb.setDisplayContent(""+map1.get("displayContent"));
      } 
      List<Map<String, Object>> list1 = (List<Map<String, Object>>)map1.get("params");
      if ("2".equals(pid)) {
        if (map1.get("colNum") != null) {
          int colums = Integer.parseInt(""+map1.get("colNum"));
          if (colums != 0) {
			tb.setColNum(colums);
		} 
        } 
      } else if ("3".equals(pid) || "5".equals(pid)) {
        if (map1.get("dev") != null) {
          int colums = Integer.parseInt(""+map1.get("dev"));
          if (colums != 0) {
			tb.setColNum(colums);
		} 
        } 
        isgrid = false;
      } else if ("4".equals(pid) && 
        map1.get("dev") != null) {
        int colums = Integer.parseInt(""+map1.get("dev"));
        if (colums != 0) {
			tb.setColNum(colums);
		} 
      } 
      TBlist = new ArrayList<>();
      if (list1 != null) {
        for (int j = 0; j < list1.size(); j++) {
          pt = new PageTM();
          map = list1.get(j);
          String tm_name = ""+map.get("name");
          String tm_code = ""+map.get("code");
          int num = Integer.parseInt(map.get("parId")+"");
          pt.setTm_code(tm_code);
          pt.setTm_name(tm_name);
          pt.setNum(num);
          TBlist.add(pt);
        } 
        tb.setList(TBlist);
      } 
      pageList.add(tb);
    } 
    PageImpl page = new PageImpl(redisUtil);
    page.load(pageList);
    page.setIsgrid(isgrid);
    page.setPageid(proId+"");
    synchronized (PageCache.simMap) {
      SatInfoManager sim = (SatInfoManager)PageCache.simMap.get(Integer.valueOf(mid));
      if (sim != null) {
        sim.getPmi().openPage(page,mid,clientip);
      } else if (log.isErrorEnabled()) {
        log.error("[mid=" + mid + "的航天器未配置网络接口！]");
      } 
    } 
  }
  
  @Override
public String getTrackCountInfo(int mid) {
	  List<SxTrackCount> list = sxTrackCountMapper.getTrackCount(mid);
    Map<String, Object> map = new HashMap<>();
    List<SxTrackCount> result = new ArrayList<>();
    for (int i = 0; i < list.size() && 
      i <= 19; i++) {
    	result.add(list.get(i)); 
    }
    map.put("Rows", result);
    map.put("Total", Integer.valueOf(result.size()));
    return JSONArray.toJSONString(map);
  }
  @Override
  public String getForecastInfo(int mid) {
    List<SxFollow> list = sxFollowMapper.getForecast(mid);
    Map<String, Object> map = new HashMap<>();
    List<SxFollow> result = new ArrayList<>();
    for (int i = 0; i < list.size() && 
      i <= 19; i++) {
		result.add(list.get(i));
	} 
    map.put("Rows", result);
    map.put("Total", Integer.valueOf(result.size()));
    return JSONArray.toJSONString(map);
  }
  
  @Override
public boolean changeSelectTerm(String devmids, String clientIp) {
    if (devmids == null) {
		synchronized (PageCache.selectMap) {
			PageOperateInfo poi = (PageOperateInfo) redisUtil.getHash("selectMap", clientIp);
//		    PageOperateInfo poi = (PageOperateInfo)PageCache.selectMap.get(clientIp);
		    if (poi != null) {
		      poi.setFirstDev_mid(0);
		      poi.setSecondDev_mid(0);
		      poi.setThirdDev_mid(0);
		      poi.setAutostate(true);
		    } else {
		      poi = new PageOperateInfo();
		      poi.setCurrntIP(clientIp);
		      poi.setAutostate(true);
//		      PageCache.selectMap.put(clientIp, poi);
		    } 
		    redisUtil.setHash("selectMap", clientIp, poi);
		    return true;
		  }
	}  
    String[] devmid = devmids.split(",");
    synchronized (PageCache.selectMap) {
    	PageOperateInfo poi = (PageOperateInfo) redisUtil.getHash("selectMap", clientIp);
//      PageOperateInfo poi = (PageOperateInfo)PageCache.selectMap.get(clientIp);
      if (poi == null) {
		poi = new PageOperateInfo();
	} 
      poi.setFirstDev_mid(0);
      poi.setSecondDev_mid(0);
      poi.setThirdDev_mid(0);
      boolean flag = false;
      if (devmid.length > 2) {
		poi.setThirdDev_mid(Integer.parseInt(devmid[2]));
	} 
      if (devmid.length > 1) {
		poi.setSecondDev_mid(Integer.parseInt(devmid[1]));
	} 
      if (devmid.length > 0) {
        poi.setFirstDev_mid(Integer.parseInt(devmid[0]));
        flag = true;
      } 
      if (!flag) {
        poi.setAutostate(true);
      } else {
        poi.setAutostate(false);
      } 
      redisUtil.setHash("selectMap", clientIp, poi);
    } 
    return true;
  }
  
  @Override
public boolean changeReceiveDataState(String mid, String show_id, String clientIp) {
    return true;
  }
  /**
   * 方法保留，暂时没有用处
   * */
  @Override
  public String getSatDownForecastInfo(String lastime, int mid) {
	  String cycle = satCycle1;
    if (cycle == null || "".equals(cycle)) {
      if (log.isDebugEnabled()) {
		log.debug("[航天器mid=" + mid + "运行周期为空]");
	} 
      cycle = "100";
    } 
    if (lastime == null || "".equals(lastime)) {
      lastime = DateTools.getCurryDateTime(); 
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    long endTime = 0L;
    try {
      endTime = sdf.parse(lastime).getTime() + (Integer.parseInt(cycle) * 60000);
    } catch (ParseException e) {
      e.printStackTrace();
    } 
    String endtime = sdf.format(Long.valueOf(endTime));
    SxSatDown sxSatDown =new SxSatDown();
    sxSatDown.setMid(mid);
    sxSatDown.setLasttime(lastime);
    sxSatDown.setEndtime(endtime);
    List<SxSatDown> list = sxSatDownMapper.getSatDownForecastInfo(sxSatDown);
    Map<String, Object> map = new HashMap<>();
    map.put("Rows", list);
    map.put("Total", Integer.valueOf(list.size()));
    return JSONArray.toJSONString(map);
  }
  
  @Override
public Object updateSelectPage(String tabid, String mid, String clientIp) {
    synchronized (PageCache.selectMap) {
//      PageOperateInfo poi = (PageOperateInfo)PageCache.selectMap.get(clientIp);
      PageOperateInfo poi = (PageOperateInfo) redisUtil.getHash("selectMap", clientIp);
      if (poi == null) {
		poi = new PageOperateInfo();
	} 
      poi.setSelectPageID(tabid);
      poi.setSendFirst(true);
//      PageCache.selectMap.put(clientIp, poi);
      redisUtil.setHash("selectMap", clientIp, poi);
    } 
    return Boolean.valueOf(true);
  }
}
