package com.hx.edit.service.impl;

//import com.alibaba.fastjson.JSONArray;
//import com.editor.dao.IProjectDao;
//import com.editor.service.IProjectService;
//import com.editor.util.ClobUtil;
//import com.xpoplarsoft.framework.db.DBResult;
//import com.xpoplarsoft.framework.interfaces.bean.LoginUserBean;
//import com.xpoplarsoft.framework.parameter.SystemParameter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.hx.edit.entity.LoginUserBean;
import com.hx.edit.entity.Satellite;
import com.hx.edit.entity.SxCheckout;
import com.hx.edit.entity.SxFile;
import com.hx.edit.entity.SxGuding;
import com.hx.edit.entity.SxProject;
import com.hx.edit.entity.Tm;
import com.hx.edit.entity.ViewSxProject;
import com.hx.edit.mapper.SatelliteMapper;
import com.hx.edit.mapper.SxCheckoutMapper;
import com.hx.edit.mapper.SxFileMapper;
import com.hx.edit.mapper.SxGudingMapper;
import com.hx.edit.mapper.SxProjectMapper;
import com.hx.edit.mapper.TmMapper;
import com.hx.edit.mapper.ViewSxProjectMapper;
import com.hx.edit.service.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService {
  @Autowired
  private SatelliteMapper satelliteMapper;
  @Autowired
  private ViewSxProjectMapper viewSxProjectMapper;
  @Autowired
  private SxGudingMapper sxGudingMapper;
  @Autowired
  private SxProjectMapper sxProjectMapper;
  @Autowired
  private SxCheckoutMapper sxCheckoutMapper;
  @Autowired
  private SxFileMapper sxFileMapper;
  @Autowired
  private TmMapper tmMapper;
  @Value("${GudingAddr}")
  private String gudingAddr;
  @Override
public List<Satellite> getSat() {
    return satelliteMapper.getSat();
  }
  
  @Override
public List<ViewSxProject> getProj(String key) {
    List<ViewSxProject> maps = viewSxProjectMapper.getProj();
    if (key == null || key.equals("")) {
		return maps;
	} 
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
    if ("0".equals(owner)) {
		return;
	} 
    for (ViewSxProject map : maps) {
      if ((map.getId()+"").equals(owner) && !rsts.contains(map)) {
        rsts.add(map);
        addParents(maps, map, rsts);
      } 
    } 
  }
  
  private void addChildren(List<ViewSxProject> maps, ViewSxProject map2, List<ViewSxProject> rsts) {
    if (!"0".equals(map2.getType())) {
		return;
	} 
    String id = map2.getId()+"";
    for (ViewSxProject map : maps) {
      if ((map.getOwner()+"").equals(id) && !rsts.contains(map)) {
        rsts.add(map);
        addChildren(maps, map, rsts);
      } 
    } 
  }
  @Override
public List<SxGuding> getGuding() {
	    return sxGudingMapper.getGuding();
	  }
  
  @Override
public String addNode(String name, int owner, String type, LoginUserBean loginUser) {
	SxProject sxProject =new SxProject();
	sxProject.setName(name);
	sxProject.setOwner(owner);
	sxProject.setType(type+"");
	try {
		int i=sxProjectMapper.addNode(sxProject);
		if(i!=0) {
			return sxProject.getId()+"";
		}
	}catch (Exception e){
//		System.out.println(e.getMessage());
		if(e.getMessage().contains("NameUnique")) {
			return "R";
		}
	}
	
	return "";
  }
  
  @Override
public String editNode(int id, String name, LoginUserBean loginUser) {
	SxProject sxProject =new SxProject();
	sxProject.setId(id);
	sxProject.setName(name);
	try {
		int i=sxProjectMapper.editNode(sxProject);
		if(i!=0) {
			return "T";
		}
	}catch (Exception e){
	//			System.out.println(e.getMessage());
		if(e.getMessage().contains("NameUnique")) {
			return "R";
		}
	}
	
	return "F";
  }
  @Override
@Transactional
  public String delNode(int id, LoginUserBean loginUser) {
	  try {
		  sxFileMapper.deleteFile(id);
		  sxProjectMapper.deleteProject(id);
	  }catch(Exception e) {
		  return "F";
	  }
	  
    return "T";
  }
  @Override
@Transactional
  public String addFile(String name, int owner, String type, String data, String uid, LoginUserBean loginUser) {
	  SxProject sxProject =new SxProject();
	  sxProject.setName(name);
	  sxProject.setOwner(owner);
	  sxProject.setType(type);
	  SxFile sxFile = new SxFile();
	  sxFile.setData(data);
	  sxFile.setDate(new Date());
	  sxFile.setUser_id(uid);
	  try {
			int i=sxProjectMapper.addNode(sxProject);
			if(i!=0) {
				sxFile.setProj_id(sxProject.getId());
				sxFileMapper.addFile(sxFile);
				return sxProject.getId()+"";
			}
		}catch (Exception e){
//			System.out.println(e.getMessage());
			if(e.getMessage().contains("NameUnique")) {
				return "R";
			}
		}

	  return "";
  }
  
  @Override
public String copyFile(int srcId, int owner, String name, String uid, LoginUserBean loginUser) {
    String data = null;
    SxCheckout sxCheckout = sxCheckoutMapper.getCheckout(srcId);
    if (sxCheckout != null) {
      data = sxCheckout.getData();
    } else {
    	SxFile sxFile = sxFileMapper.getFile(srcId);
      if (sxFile != null) {
		data = sxFile.getData();
	} 
    } 
    return addFile(name, owner, "1", data, uid, loginUser);
  }
  
  @Override
public boolean checkout(int proId, String uid) {
	  String data=sxFileMapper.getDataIntoCheckout(proId);
	  if(data !=null) {
		  try {
			  SxCheckout sxCheckout =new SxCheckout();
			  sxCheckout.setProj_id(proId);
			  sxCheckout.setUser_id(uid);
			  sxCheckout.setData(data);
			  sxCheckoutMapper.addCheckout(sxCheckout);
			  return true;
		  }catch(Exception e) {
			  if(e.getMessage().contains("unique")) {
				    SxCheckout sxCheckout = sxCheckoutMapper.getCheckout(proId);
				    if(sxCheckout.getUser_id().equals(uid)) {
				    	return true;
				    }
				}
		  }
		  
	  }
	  return false;
  }
  
  @Override
public boolean checkin(int proId) {
	  try {
		  sxCheckoutMapper.delCheckout(proId);
		  return true;
	  }catch(Exception e) {
		  return false;
	  } 
	  
  }
  
  @Override
public boolean save(int proId, String data, LoginUserBean loginUser) {
	  SxFile sxFile = new SxFile();
	  sxFile.setProj_id(proId);
	  sxFile.setData(data);
	  sxFile.setDate(new Date());
	  try {
		  sxFileMapper.save(sxFile);
		  return true;
	  }catch(Exception e) {
		  return false;
	  }
	  }
	  
  @Override
public boolean checkallin(String uid) {
	  try {
		  sxCheckoutMapper.delUserCheckout(uid);
		  return true;
	  }catch(Exception e) {
		  return false;
	  }
  }
  
  @Override
public String getLastFile(int proId, boolean readOnly) {
	SxCheckout sxCheckout = new SxCheckout();
	SxFile sxFile = new SxFile();
	String data = null;
	if (readOnly) {
		 sxCheckout = sxCheckoutMapper.getCheckout(proId);
	  if (sxCheckout != null) {
		data =sxCheckout.getData();
	} 
	} else {
		sxFile = sxFileMapper.getFile(proId);
	  if (sxFile != null) {
		data = sxFile.getData();
	} 
	} 
	return data;
  }
  
  @Override
public String getTm(int satId, String key, int page, int pagesize) {
	  Tm tm=new Tm();
	  tm.setSat_id(satId);
	  tm.setTm_name("%" + key + "%");
	  int total =tmMapper.getTmCount(tm);
	  int start = pagesize * (page - 1);
	  tm.setStart(start);
	  tm.setSize(pagesize);
	  List<Tm> listTm = tmMapper.getTm(tm);
	  return "{\"Rows\":" +JSONArray.toJSONString(listTm) + ",\"Total\":" + 
      total + "}";
  }
  
  @Override
public String getGudingUrl(int id) {
    return gudingAddr + sxGudingMapper.getGudingUrl(id) + "?read=true";
  }
}
