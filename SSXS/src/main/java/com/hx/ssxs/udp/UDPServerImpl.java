package com.hx.ssxs.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hx.ssxs.protocol.AbstractAdapter;

public class UDPServerImpl extends AbstractAdapter {
  private static Log log = LogFactory.getLog(UDPServerImpl.class);
  
  private MulticastSocket client = null;
  
  private byte[] dataBuf = null;
  
  private byte[] data = null;
  
  private DatagramPacket dp = null;
  
  private InetAddress group;
  
  private String address;
  
  private int proitory;
  
  private int port;
  
  private boolean status;
  
  public boolean isStatus() {
    return this.status;
  }
  
  public void setStatus(boolean status) {
    this.status = status;
  }
  
  public UDPServerImpl(String address2, int port2, int proitory) {
    this.address = address2;
    this.port = port2;
    this.proitory = proitory;
    try {
      this.group = InetAddress.getByName(this.address);
      this.client = new MulticastSocket(this.port);
      this.client.joinGroup(this.group);
      this.client.setNetworkInterface(NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));
      this.status = true;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
		log.error("创建udp client发生异常", e);
	} 
      if (this.client != null) {
		close();
	} 
    } 
  }
  
  public boolean multicastSend(byte[] data) {
    boolean result = false;
    try {
      if (log.isDebugEnabled()) {
		log.debug("组件UDPAdapterImpl[multicastSend]开始执行,发送数据长度为[" + data.length + "]");
	} 
      DatagramPacket dp = new DatagramPacket(data, data.length, this.group, this.port);
      this.client.send(dp);
      result = true;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
		log.error("组播发送方法[multicastSend]发生异常", e);
	} 
    } 
    return result;
  }
  
  public byte[] recieveData(int data_length) {
    try {
      if (this.data == null) {
		this.data = new byte[data_length];
	} 
      if (this.dp == null) {
		this.dp = new DatagramPacket(this.data, data_length);
	} 
      this.client.receive(this.dp);
      int dataLen = this.dp.getLength();
      if (log.isDebugEnabled()) {
		log.debug("实际接收到的数据长度为[" + dataLen + "]");
	} 
      this.dataBuf = new byte[dataLen];
      System.arraycopy(this.data, 0, this.dataBuf, 0, dataLen);
    } catch (Exception e1) {
      if (log.isDebugEnabled()) {
		log.debug("[遥测数据接收异常！" + e1 + "]");
	} 
      this.status = false;
    } 
    return this.dataBuf;
  }
  
  public void close() {
    try {
      this.client.leaveGroup(this.group);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
		log.error("client leaveGroup error", e);
	} 
    } 
    try {
      this.client.close();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
		log.error("client close error", e);
	} 
    } 
  }
}
