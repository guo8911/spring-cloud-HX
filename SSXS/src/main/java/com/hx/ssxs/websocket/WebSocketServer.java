package com.hx.ssxs.websocket;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.entity.SatInfoManager;

@ServerEndpoint("/WebSocket/{userId}")
@Component
public class WebSocketServer {
  private static Log logger = LogFactory.getLog(WebSocketServer.class);
  
  private static String userId;
  
  private Map<String, List<Session>> sessionMap = new ConcurrentHashMap<>();
  
  public Map<String, List<Session>> getSessionMap() {
    return this.sessionMap;
  }
  
  public void setSessionMap(Map<String, List<Session>> sessionList) {
    this.sessionMap = this.sessionMap;
  }
  
  @OnOpen
  public void onOpen(@PathParam("userId") String userId, Session session, EndpointConfig config) {
    String[] con = userId.split("_");
    String key = null;
    String pageId = con[1];
    SatInfoManager sim = (SatInfoManager)PageCache.simMap.get(Integer.valueOf(con[0]));
    if ("1".equals(con[4])) {
      if ("4".equals(con[1])) {
        key = String.valueOf(con[3]) + "&&" + con[0];
        sim.getTmSource().addSession(key, session);
      } else {
        key = String.valueOf(con[3]) + "&&" + con[1] + "&&" + con[0];
        sim.getOth().addSession(key, session);
      } 
    } else {
      try {
        sim.getPmi().addPageSession(session, con[3], pageId,con[0]);
      } catch (Exception e) {
        if (logger.isDebugEnabled()) {
			logger.debug("[添加session信息出错]" + e);
		} 
      } 
    } 
    if (logger.isInfoEnabled()) {
		logger.info("[onOpen用户userId=" + userId + ",session=" + session + 
		      "]");
	} 
  }
  
  @OnClose
  public void onClose(@PathParam("userId") String userId, Session session, CloseReason reason) {
    if (session != null) {
		try {
		    String[] con = userId.split("_");
		    synchronized (PageCache.simMap) {
		      SatInfoManager sim = (SatInfoManager)PageCache.simMap.get(
		          Integer.valueOf(Integer.parseInt(con[0])));
		      String pageid = con[1];
		      if ("2".equals(con[4])) {
				sim.getPmi().closePage(pageid, con[3],con[0]);
			} 
		    } 
		  } catch (Exception e) {
		    if (logger.isErrorEnabled()) {
				logger.error("用户userId=[" + userId + "]连接关闭异常", e);
			} 
		  }
	}  
    if (logger.isInfoEnabled()) {
		logger.info("用户userId=[" + userId + "]连接关闭");
	} 
  }
  
  @OnMessage
  public void onMessage(String message, Session session) {
    if (logger.isInfoEnabled()) {
		logger.info("[组件]WebSocketServer[onMessage]开始运行");
	} 
    if (logger.isInfoEnabled()) {
		logger.info("[组件]WebSocketServer[onMessage]结束运行");
	} 
  }
  
  public void sendMessageBySessionList(String message, List<Session> sessionList) {
    if (sessionList != null) {
		for (Session session : sessionList) {
		    if (session != null && session.isOpen()) {
				try {
				    synchronized (session) {
				      session.getBasicRemote().sendText(message);
				    } 
				  } catch (Exception e) {
				    if (logger.isErrorEnabled()) {
						logger.error("用户userId=[" + userId + 
						      "]执行方法sendMessageBySessionList执行发送信息异常", 
						      e);
					} 
				  }
			}  
		  }
	}  
    if (logger.isInfoEnabled()) {
		logger.info("sendMessageBySessionList用户sessionList=[" + sessionList + 
		      "]");
	} 
  }
  
  public static void sendMessageBySession(String message, Session session) {
    if (logger.isInfoEnabled()) {
		logger.info("[组件]WebSocketServer[sendMessage]开始运行");
	} 
    try {
      if (session != null && session.isOpen()) {
		session.getBasicRemote().sendText(message);
	} 
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
		logger.error(
            "用户userId=[" + 
            session.getUserProperties().get("userId") + 
            "]执行方法sendMessageBySession执行发送信息异常", e);
	} 
    } 
    if (logger.isInfoEnabled()) {
		logger.info("[组件]WebSocketServer[sendMessage]结束运行");
	} 
  }
  
  @OnError
  public void onError(Session session, Throwable error) {
    if (logger.isErrorEnabled()) {
		logger.error("资源id=[" + userId + "]的连接出现错误", error);
	} 
  }
  
  public void removeSession(String mid, String pageid, String clientIp, Session session) {
    this.sessionMap = WebSocketFactory.getWebSocketServiceComponent()
      .getSessionMap();
    try {
      List<Session> sessionList = null;
      if ("1".equals(pageid) || "4".equals(pageid) || "5".equals(pageid)) {
        sessionList = this.sessionMap.get(String.valueOf(mid) + "_" + pageid + "_" + 
            clientIp);
      } else {
        sessionList = this.sessionMap.get(String.valueOf(mid) + pageid);
      } 
      if ((((sessionList != null) ? 1 : 0) & ((sessionList.size() > 0) ? 1 : 0)) != 0) {
		sessionList.remove(session);
	} 
      if (sessionList.size() == 0) {
        this.sessionMap.remove(sessionList);
        Set<Map.Entry<String, List<Session>>> entryset = this.sessionMap
          .entrySet();
        if (entryset.size() == 0) {
			return;
		} 
        boolean flag = false;
        for (Map.Entry<String, List<Session>> entry : entryset) {
          String key = entry.getKey();
          String info = null;
          if ("1".equals(pageid) || "4".equals(pageid) || 
            "5".equals(pageid)) {
            info = String.valueOf(mid) + "_" + pageid + "_" + clientIp;
          } else {
            info = String.valueOf(mid) + pageid;
          } 
          if (key.contains(info)) {
            flag = true;
            break;
          } 
        } 
      } 
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
		if ("1".equals(pageid) || "4".equals(pageid) || 
          "5".equals(pageid)) {
          logger.error("在key=[" + mid + "_" + pageid + "_" + clientIp + 
              "]中移除Session=[" + session + "]出现错误", e);
        } else {
          logger.error("在key=[" + mid + pageid + "]中移除Session=[" + 
              session + "]出现错误", e);
        }
	}  
    } 
  }
  
  public void removeSessionListFromMap(String srcID) {
    this.sessionMap = 
      WebSocketFactory.getWebSocketServiceComponent().getSessionMap();
    if (this.sessionMap != null && this.sessionMap.containsKey(srcID)) {
		this.sessionMap.remove(srcID);
	} 
  }
}
