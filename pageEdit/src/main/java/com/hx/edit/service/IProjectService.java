package com.hx.edit.service;

//import com.xpoplarsoft.framework.interfaces.bean.LoginUserBean;
import java.util.List;
import java.util.Map;

import com.hx.edit.entity.LoginUserBean;
import com.hx.edit.entity.Satellite;
import com.hx.edit.entity.SxGuding;
import com.hx.edit.entity.ViewSxProject;

public interface IProjectService {
  List<Satellite> getSat();
  
  List<ViewSxProject> getProj(String paramString);
  
  List<SxGuding> getGuding();
  
  String addNode(String paramString, int paramInt1, String type, LoginUserBean paramLoginUserBean);
  
  String editNode(int id, String paramString2, LoginUserBean paramLoginUserBean);
  
  void delNode(int paramString, LoginUserBean paramLoginUserBean);
  
  
  String addFile(String paramString1, int paramInt1, String paramInt2, String paramString2, String paramString3, LoginUserBean paramLoginUserBean);

  String copyFile(int paramString1, int paramInt, String paramString2, String paramString3, LoginUserBean paramLoginUserBean);
  
  boolean checkout(int paramString1, String paramString2);
  
  boolean checkin(int paramString);
  
  boolean save(int paramString1, String paramString2, LoginUserBean paramLoginUserBean);
  
  boolean checkallin(String paramString);
  
  String getLastFile(int paramString, boolean paramBoolean);
  
  String getTm(int paramString1, String paramString2, int paramInt1, int paramInt2);
  
  String getGudingUrl(int paramString);

}
