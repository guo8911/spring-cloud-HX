package com.hx.ssxs.util;

import javax.servlet.http.HttpServletRequest;

public class PageTools {
  private static boolean isStart = false;
  
  public static String getLocalIp(HttpServletRequest request) {
    String remoteAddr = request.getRemoteAddr();
    String forwarded = request.getHeader("X-Forwarded-For");
    String realIp = request.getHeader("X-Real-IP");
    String ip = null;
    if (realIp == null) {
      if (forwarded == null) {
        ip = remoteAddr;
      } else {
        ip = String.valueOf(remoteAddr) + "/" + forwarded.split(",")[0];
      } 
    } else if (realIp.equals(forwarded)) {
      ip = realIp;
    } else {
      if (forwarded != null) {
		forwarded = forwarded.split(",")[0];
	} 
      ip = String.valueOf(realIp) + "/" + forwarded;
    } 
    return ip;
  }
  
  public static StringBuffer cutSourceIndex(String sources) {
    if (sources == null) {
		return null;
	} 
    StringBuffer source = new StringBuffer(sources);
    for (int index = 2; index < source.length(); index += 3) {
		source.insert(index, " ");
	} 
    return source;
  }
  
  public static String getLocalUrl(HttpServletRequest request) {
    return 
      request.getScheme() + 
      "://" + 
      request.getServerName() + 
      ":" + (
      (request.getServerPort() == 80) ? "" : 
      Integer.valueOf(request.getServerPort())).toString() + request.getContextPath() + 
      "/";
  }
}
