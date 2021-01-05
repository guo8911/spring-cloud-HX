package com.hx.edit.service.impl;

//import com.alibaba.fastjson.JSONArray;
//import com.editor.dao.IProjectDao;
//import com.editor.service.IProjectService;
//import com.editor.util.ClobUtil;
//import com.xpoplarsoft.framework.db.DBResult;
//import com.xpoplarsoft.framework.interfaces.bean.LoginUserBean;
//import com.xpoplarsoft.framework.parameter.SystemParameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.edit.entity.Satellite;
import com.hx.edit.entity.SxGuding;
import com.hx.edit.entity.ViewSxProject;
import com.hx.edit.mapper.SatelliteMapper;
import com.hx.edit.mapper.SxGudingMapper;
import com.hx.edit.mapper.ViewSxProjectMapper;
import com.hx.edit.service.IProjectService;

@Service
public class ProjectService implements IProjectService {
  @Autowired
  private SatelliteMapper satelliteMapper;
  @Autowired
  private ViewSxProjectMapper viewSxProjectMapper;
  @Autowired
  private SxGudingMapper sxGudingMapper;
  public List<Satellite> getSat() {
    return satelliteMapper.getSat();
  }
  
  public List<ViewSxProject> getProj(String key) {
    List<ViewSxProject> maps = viewSxProjectMapper.getProj();
    if (key == null || key.equals(""))
      return maps; 
    List<ViewSxProject> rsts = new ArrayList<>();
    for (ViewSxProject map : maps) {
      if (map.getName().contains(key)) {
        addParents(maps, map, rsts);
        rsts.add(map);
        addChildren(maps, map, rsts);
      } 
    } 
    List<ViewSxProject> rsts1 = new ArrayList<>();
    for (ViewSxProject map : maps) {
      for (ViewSxProject rst : rsts) {
        if (map.getId()==rst.getId()) {
          rsts1.add(rst);
          break;
        } 
      } 
    } 
    return rsts1;
  }
  
  private void addParents(List<ViewSxProject> maps, ViewSxProject map2, List<ViewSxProject> rsts) {
    String owner = map2.getOwner()+ "";
    if (owner.equals("0"))
      return; 
    for (ViewSxProject map : maps) {
      if ((map.getId()+"").equals(owner) && !rsts.contains(map)) {
        rsts.add(map);
        addParents(maps, map, rsts);
      } 
    } 
  }
  
  private void addChildren(List<ViewSxProject> maps, ViewSxProject map2, List<ViewSxProject> rsts) {
    if (!map2.getType().equals("0"))
      return; 
    String id = map2.getId()+"";
    for (ViewSxProject map : maps) {
      if ((map.getOwner()+"").equals(id) && !rsts.contains(map)) {
        rsts.add(map);
        addChildren(maps, map, rsts);
      } 
    } 
  }
  
//  public String addNode(String name, int owner, int type, LoginUserBean loginUser) {
//    return this.projectDao.addNode(name, owner, type, loginUser);
//  }
//  
//  public String editNode(String id, String name, LoginUserBean loginUser) {
//    return this.projectDao.editNode(id, name, loginUser);
//  }
//  
//  public boolean delNode(String id, LoginUserBean loginUser) {
//    for (String el : getChildren(this.projectDao.getProj(), id))
//      id = String.valueOf(id) + "," + el; 
//    return this.projectDao.delNode(id, loginUser);
//  }
//  
//  private List<String> getChildren(List<Map<String, Object>> maps, String id) {
//    List<String> ids = new ArrayList<>();
//    for (Map<String, Object> map : maps) {
//      if (map.get("owner").toString().equals(id)) {
//        String aid = map.get("id").toString();
//        ids.add(aid);
//        ids.addAll(getChildren(maps, aid));
//      } 
//    } 
//    return ids;
//  }
//  
  public List<SxGuding> getGuding() {
    return sxGudingMapper.getGuding();
  }
  
//  public String addFile(String name, int owner, int type, String data, String uid, LoginUserBean loginUser) {
//    return this.projectDao.addFile(name, owner, type, data, uid, loginUser);
//  }
//  
//  public String copyFile(String srcId, int owner, String name, String uid, LoginUserBean loginUser) {
//    String data = null;
//    Map<String, Object> map = this.projectDao.getCheckout(srcId);
//    if (map != null) {
//      data = ClobUtil.getClob(map.get("data"));
//    } else {
//      map = this.projectDao.getFile(srcId);
//      if (map != null)
//        data = ClobUtil.getClob(map.get("data")); 
//    } 
//    return this.projectDao.addFile(name, owner, 1, data, uid, loginUser);
//  }
//  
//  public boolean checkout(String proId, String uid) {
//    String ret = this.projectDao.AddCheckout(proId, uid);
//    if (ret.equals("T"))
//      return true; 
//    if (ret.equals("R")) {
//      Map<String, Object> map = this.projectDao.getCheckout(proId);
//      if (map.get("user_id").toString().equals(uid))
//        return true; 
//    } 
//    return false;
//  }
//  
//  public boolean checkin(String proId) {
//    return this.projectDao.delCheckout(proId);
//  }
//  
//  public boolean checkallin(String uid) {
//    return this.projectDao.delUserCheckout(uid);
//  }
//  
//  public String getLastFile(String proId, boolean readOnly) {
//    Map<String, Object> map = null;
//    String data = null;
//    if (readOnly) {
//      map = this.projectDao.getCheckout(proId);
//      if (map != null)
//        data = map.get("data").toString(); 
//    } else {
//      map = this.projectDao.getFile(proId);
//      if (map != null)
//        data = map.get("data").toString(); 
//    } 
//    return data;
//  }
//  
//  public boolean save(String proId, String data, LoginUserBean loginUser) {
//    return this.projectDao.save(proId, data, loginUser);
//  }
//  
//  public String getTm(String satId, String key, int page, int pagesize) {
//    DBResult result = this.projectDao.getRawTm(satId, key, page, pagesize);
//    int start = pagesize * (page - 1);
//    int end = pagesize * page;
//    int total = result.getRows();
//    List<Map<String, Object>> list = new ArrayList<>();
//    String[] column_names = result.getColName();
//    for (int i = start; i < total && i < end; i++) {
//      Map<String, Object> map = new HashMap<>();
//      byte b;
//      int j;
//      String[] arrayOfString;
//      for (j = (arrayOfString = column_names).length, b = 0; b < j; ) {
//        String name = arrayOfString[b];
//        map.put(name.toLowerCase(), result.getObject(i, name));
//        b++;
//      } 
//      list.add(map);
//    } 
//    return "{\"Rows\":" + JSONArray.toJSONString(list) + ",\"Total\":" + 
//      total + "}";
//  }
//  
//  public String getGudingUrl(String id) {
//    return String.valueOf(SystemParameter.getInstance().getParameter("GudingAddr")) + 
//      this.projectDao.getGudingUrl(id) + "?read=true";
//  }
}
