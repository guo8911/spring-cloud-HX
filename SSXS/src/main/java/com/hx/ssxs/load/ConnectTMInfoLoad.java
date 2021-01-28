package com.hx.ssxs.load;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.data.DataType;
import com.hx.ssxs.entity.ConfigDisplay;
import com.hx.ssxs.entity.DeviceInfo;
import com.hx.ssxs.entity.DisplayValueCache;
import com.hx.ssxs.entity.SatInfoManager;
import com.hx.ssxs.entity.SatNet;
import com.hx.ssxs.entity.TMValue;
import com.hx.ssxs.entity.Tm;
import com.hx.ssxs.entity.TmDisplay;
import com.hx.ssxs.entity.UnpackConfig;
import com.hx.ssxs.mapper.DeviceInfoMapper;
import com.hx.ssxs.mapper.SatNetMapper;
import com.hx.ssxs.mapper.TmDisplayMapper;
import com.hx.ssxs.mapper.TmMapper;
import com.hx.ssxs.mapper.UnpackConfigMapper;
import com.hx.ssxs.thread.SatPageHandleThread;
import com.hx.ssxs.thread.SatUDPReceiveThread;
import com.hx.ssxs.thread.SatUDPorocoolThread;
@Component
public class ConnectTMInfoLoad {
	@Autowired
	private TmDisplayMapper tmDisplayMapper;
	@Autowired
	private SatNetMapper satNetMapper;
	@Autowired
	private UnpackConfigMapper unpackConfigMapper;
	@Autowired
	private DeviceInfoMapper deviceInfoMapper;
	@Autowired
	private TmMapper tmMapper;
  private Log log = LogFactory.getLog(ConnectTMInfoLoad.class);
  
  @PostConstruct
  public void load() {
    SatInfoManager sim = null;
    System.out.println("开始初始化遥测数据...");
    List<SatNet> satNetList = satNetMapper.getSatTMConnect();
    List<String> list = new ArrayList<>();
    if (this.log.isDebugEnabled())
      this.log.debug("开始创建接收线程..."); 
    Thread thread = null;
    if (satNetList != null) {
      for (SatNet satNet : satNetList) {
        String satCode = satNet.getSat_code();
        String type = satNet.getType();
        String ip = satNet.getIp();
        String port = satNet.getPort();
        String mid = satNet.getMid();
        String sat_id = satNet.getSat_id();
        String name = String.valueOf(satCode) + "&&&" + type;
        thread = new Thread((Runnable)new SatUDPReceiveThread(type, ip, port, satCode, Integer.parseInt(mid), 65535), name);
        sim = (SatInfoManager)PageCache.map.get(Integer.valueOf(Integer.parseInt(mid)));
        sim.addThread(thread);
        if (!list.contains(mid)) {
          list.add(mid);
          DisplayValueCache div = null;
          Map<String, ConfigDisplay> display = new HashMap<>();
          List<TmDisplay> tmDisplayList = tmDisplayMapper.queryTMDisplayBySatCode(satCode);
          if(tmDisplayList !=null) {
        	  for(TmDisplay tmDisplay : tmDisplayList) {
        		  String conf_id = tmDisplay.getConf_id();
        		  ConfigDisplay dis = display.get(conf_id);
        		  if (dis == null) {
        	          dis = new ConfigDisplay(conf_id);
        	          display.put(conf_id, dis);
        	        } 
        	        dis.put(tmDisplay.getReal_value(), tmDisplay.getDisplay_value());
        	  }
          }
          div = new DisplayValueCache();
          div.setMapc(display);
          Map<String, String> mapConfig = new HashMap<>();
          List<UnpackConfig> unpackConfigList = unpackConfigMapper.getConfigDis(Integer.valueOf(sat_id));
          if(unpackConfigList !=null) {
        	  for(UnpackConfig unpackConfig : unpackConfigList) {
        		  String tm_code = unpackConfig.getField_code();
        	      String conf_id = unpackConfig.getPk_id();
        	      mapConfig.put(tm_code, conf_id);
        	  }
          }
          div.setMap(mapConfig);
          sim.getTmf().displayValueLoad(div);
        } 
        if (!PageCache.satInfo.containsKey(satCode))
          PageCache.satInfo.put(satCode, Integer.valueOf(mid)); 
      } 
    } 
    if (this.log.isDebugEnabled())
      this.log.debug("创建接收线程完成！"); 
    if (this.log.isDebugEnabled())
      this.log.debug("正在加载遥测数据..."); 
    System.out.println("正在加载遥测数据...");
    if (list.size() != 0) {
      TMValue tmcache = null;
      List<DeviceInfo> deviceInfoList = deviceInfoMapper.getDeviceInfo();
      for (String mid : list) {
        thread = new Thread((Runnable)new SatUDPorocoolThread(Integer.parseInt(mid)), "afterproxcool" + mid);
        sim = (SatInfoManager)PageCache.map.get(Integer.valueOf(Integer.parseInt(mid)));
        sim.addThread(thread);
        HashMap<String, TMValue> mapTM = new HashMap<>();
        List<Tm> tmList = tmMapper.getParamInfo(Integer.valueOf(mid));
        if (tmList != null) {
          for (DeviceInfo devMap : deviceInfoList) {
            String dev_mid = devMap.getMid()+"";
            for (Tm tm : tmList) {
              tmcache = new TMValue();
              int num = tm.getTm_num();
              tmcache.setNum(num);
              String code = tm.getTm_code();
              tmcache.setCode(code);
              String tm_id = tm.getTm_id()+"";
              tmcache.setId(tm_id);
              String dataType = tm.getData_type()+"";
              if ("0".equals(dataType)) {
                tmcache.setDataType(DataType.INT);
              } else if ("1".equals(dataType)) {
                tmcache.setDataType(DataType.DOUBLE);
              } else if ("2".equals(dataType)) {
                tmcache.setDataType(DataType.STRING);
              } 
              mapTM.put(String.valueOf(dev_mid) + "&&" + num + "&&" + mid, tmcache);
            } 
            System.out.println(String.valueOf(dev_mid) + "航天器" + mid + "参数放入缓存！");
          } 
          sim.getTmf().load(mapTM);
          thread = new Thread((Runnable)new SatPageHandleThread(Integer.parseInt(mid)), String.valueOf(mid) + "&&TMhandle");
          sim.addThread(thread);
        } 
      } 
      System.out.println("加载遥测数据完成！");
      if (this.log.isDebugEnabled())
        this.log.debug("加载遥测数据完成！"); 
      if (this.log.isDebugEnabled())
        this.log.debug("正在启动遥测数据接收线程..."); 
      for (String mid : list) {
        sim = (SatInfoManager)PageCache.map.get(Integer.valueOf(Integer.parseInt(mid)));
        sim.receiveThreadRun();
      } 
      if (this.log.isDebugEnabled())
        this.log.debug("启动遥测数据接收线程完成！"); 
      System.out.println("初始化遥测数据完成！");
    } 
  }
}
