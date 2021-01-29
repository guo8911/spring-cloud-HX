package com.hx.ssxs.websocket;

public class WebSocketFactory {
  private static volatile WebSocketServer webSocketServer = null;
  
  public static WebSocketServer getWebSocketServiceComponent() {
    if (webSocketServer == null) {
		synchronized (WebSocketFactory.class) {
		    if (webSocketServer == null) {
				webSocketServer = new WebSocketServer();
			} 
		  }
	}  
    return webSocketServer;
  }
}
