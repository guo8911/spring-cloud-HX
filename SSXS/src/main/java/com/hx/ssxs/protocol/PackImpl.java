package com.hx.ssxs.protocol;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hx.ssxs.data.Body;
import com.hx.ssxs.data.DataPackage;
import com.hx.ssxs.data.DelayData;
import com.hx.ssxs.data.Head;
import com.hx.ssxs.data.RealTimeData;
import com.hx.ssxs.data.Source;
import com.hx.ssxs.data.XmlData;

/**
 * 数据包操作实现类
 * @author yj
 *
 */
public class PackImpl  implements IPack<DataPackage>{
	
	private static Log log = LogFactory.getLog(PackImpl .class);
	
    private RealTimeDataImpl realTimeDataImpl = null;
    private DelayDataImpl delayDataImpl = null;
    private XmlDataImpl xmlDataImpl = null;
    private SourceDataImpl sourceDataImpl = null;
    
    public PackImpl(){
    	this.realTimeDataImpl = new RealTimeDataImpl();
    	this.delayDataImpl = new DelayDataImpl();
    	this.xmlDataImpl = new XmlDataImpl();
    	this.sourceDataImpl = new SourceDataImpl();
    }
    
	/**
	 *  解析包头
	 * @param data
	 * @return Head对象
	 */
	private static  Head  unpackHead(byte[] data){
		if (log.isDebugEnabled()) {
			log.debug("组件[PackImpl]方法[unpackHead]开始执行");
		}	
		Head  head  = new Head();	
		try {
			int pos = 0;	
			//通讯版本号  1字节
			byte[] version = new byte[1];
			System.arraycopy(data, pos, version, 0, 1);
			head.setVer(version[0]);
			if (version[0]!=0x02) {
				return null;
			}
			pos += 1; 
			
			//同步码   4字节
			byte[] sync = new byte[4];
			System.arraycopy(data, pos, sync, 0, 4);
			int syncValue = NumberBytes.bytesToInt(sync, Constant.BYTEORDER);
			head.setSyn(syncValue);
			pos += 4; 
			
			//任务标志   4字节
			byte[] taskSign = new byte[4];
			System.arraycopy(data, pos, taskSign, 0, 4);
			int mid = NumberBytes.bytesToInt(taskSign, Constant.BYTEORDER);
			head.setTaskSign(mid);
			pos += 4; 
			
			//信源标志(4字节)
			byte[] sourceSign = new byte[4];
			System.arraycopy(data, pos, sourceSign, 0, 4);
			int sid = NumberBytes.bytesToInt(sourceSign, Constant.BYTEORDER);
			head.setSourceSign(sid);
			pos += 4; 
			
			//信宿标志(4字节)
			byte[] targetSign = new byte[4];
			System.arraycopy(data, pos, targetSign, 0, 4);
			int did = NumberBytes.bytesToInt(targetSign, Constant.BYTEORDER);
			head.setTargetSign(did);
			pos += 4; 
			
			//信息标志(4字节)
			byte[] mesSign = new byte[4];
			System.arraycopy(data, pos, mesSign, 0, 4);
			int bid = NumberBytes.bytesToInt(mesSign, Constant.BYTEORDER);
			head.setMesSign(bid);
			pos += 4; 
			
			//包序号(4字节)
			byte[] packageNum = new byte[4];
			System.arraycopy(data, pos, packageNum, 0, 4);
			int no = NumberBytes.bytesToInt(packageNum, Constant.BYTEORDER);
			head.setPackageNum(no);
			pos += 4; 
			
			//标志字段(1字节)
			byte[] flag = new byte[1];
			System.arraycopy(data, pos, flag, 0, 1);
			head.setFlag(flag[0]);
			pos += 1; 
			
			//发送日期(2字节)
			byte[] sendDate = new byte[2];
			System.arraycopy(data, pos, sendDate, 0, 2);
			int date = NumberBytes.bytesToInt(sendDate, Constant.BYTEORDER);
			head.setSendDate(date);
			pos += 2; 
			
			//发送时间(4字节)
			byte[] sendTime = new byte[4];
			System.arraycopy(data, pos, sendTime, 0, 4);
			int time = NumberBytes.bytesToInt(sendTime, Constant.BYTEORDER);
			head.setSendTime(time);
			pos += 4; 
			
			//数据域长度(2字节)
			byte[] dataLength = new byte[4];
			System.arraycopy(data, pos, dataLength, 2, 2);
			int len = NumberBytes.bytesToInt(dataLength, Constant.BYTEORDER);
			head.setDataLength(len);
			
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("组件[PackImpl]方法[unpackHead]发生异常");
			}	
		}
		
		return head;
	}

	/**
	 * 组包
	 */
	@Override
	public byte[] pack(DataPackage obj) {
		
		if (log.isInfoEnabled()) {
			log.info("组件[PackImpl]方法[pack]开始执行,执行对象[" + obj.toString() + "]");
		}
		byte[] truePackData = null;
		try {
			byte[] packData = new byte[99999];
			Head h = obj.getHead();

			int pos = 0;
			
			// 版本号(1字节)
			packData[pos] = h.getVer();
			if (h.getVer() != 0x02) { // 协议版本号错误无法 打包 、拆包
				return null;
			}
			pos += 1;
			
			// 同步码 4字节
			byte[] sync = new byte[4];
			sync = NumberBytes.intToBytes(h.getSyn(),
					Constant.BYTEORDER);
			System.arraycopy(sync, 0, packData, pos, 4);
			pos += 4;
			
			// 任务标志 4字节
			byte[] taskSign = new byte[4];
			taskSign = NumberBytes.intToBytes(h.getTaskSign(),
					Constant.BYTEORDER);
			System.arraycopy(taskSign, 0, packData, pos, 4);
			pos += 4;
			
			// 信源标识 4字节
			byte[] sourceSign = new byte[4];
			sourceSign = NumberBytes.intToBytes(h.getSourceSign(),
					Constant.BYTEORDER);
			System.arraycopy(sourceSign, 0, packData, pos, 4);
			pos += 4;
			
			// 信宿标志(4字节)
			byte[] targetSign = new byte[4];
			targetSign = NumberBytes.intToBytes(h.getTargetSign(),
					Constant.BYTEORDER);
			System.arraycopy(targetSign, 0, packData, pos, 4);
			pos += 4;
			
			// 信息标志(4字节)
			byte[] mesSign = new byte[4];
			mesSign = NumberBytes
					.intToBytes(h.getMesSign(), Constant.BYTEORDER);
			System.arraycopy(mesSign, 0, packData, pos, 4);
			pos += 4;
			
			// 包序号(4字节)
			byte[] packageNum = new byte[4];
			packageNum = NumberBytes.intToBytes(h.getPackageNum(),
					Constant.BYTEORDER);
			System.arraycopy(packageNum, 0, packData, pos, 4);
			pos += 4;
			
			// 标志字段(1字节)
			packData[pos] = h.getFlag();
			pos += 1;
			
			// 发送日期(2字节)
			byte[] sendDate = new byte[2];
			sendDate = NumberBytes.intToBytes(h.getSendDate(),
					Constant.BYTEORDER);
			System.arraycopy(sendDate, 2, packData, pos, 2);
			pos += 2;
			
			// 发送时间(4字节)
			byte[] sendTime = new byte[4];
			sendTime = NumberBytes.intToBytes(h.getSendTime(),
					Constant.BYTEORDER);
			System.arraycopy(sendTime, 0, packData, pos, 4);
			pos += 4;
			
			Body body = obj.getBody();

			byte[] dataArea = null;

			/*
			 * 根据不同信息标志进行组包
			 */
			switch (h.getMesSign()) {
			case 0x00010000://遥测数据原码
			case 0x00020001://数传数据原码
			case 0x00030001://测距数据原码
			case 0x00030002://测速数据原码
			case 0x00040001://遥控指令/注入数据原码
			case 0x00050000://小环比对原码
			case 0x00030000://外测原始帧信息
			case 0x00060000://链路监视原始帧信息
			case 0x00100000://小固存遥测数据原始帧信息
				dataArea = sourceDataImpl.pack(body.getSource());
				break;
			case 0x00010101://实时遥测数据处理结果
				dataArea = realTimeDataImpl.pack(body.getRealTimeData());
				break;
			case 0x00010102://延时遥测数据处理结果
				dataArea = delayDataImpl.pack(body.getDelayData());
				break;
			case 0x00030100://外测数据处理结果
			case 0x00040101://遥控指令/注入数据处理结果
			case 0x00050100://小环比对处理结果
			case 0x00060100://链路监视信息
			case 0x00080100://进程心跳信息
			case 0x00090100://软件运行日志
			case 0x00110100://设备收发汇总信息
			case 0x00120100://跟踪预报信息
			case 0x00120101://轨道计算信息
			case 0x00120102://星下点信息
				dataArea = xmlDataImpl.pack(body.getXmlData());
				break;
			case 0x00070100://进程管理信息
				break;
			}

			// 数据域长度(2字节)
			byte[] dataLength = new byte[4];
			dataLength = NumberBytes.intToBytes(dataArea.length,
					Constant.BYTEORDER);
			System.arraycopy(dataLength, 2, packData, pos, 2);
			pos += 2;
			
			// 包头和包体合在一起，构成一个完整的数据包
			System.arraycopy(dataArea, 0, packData, pos, dataArea.length);

			truePackData = new byte[34 + dataArea.length];
			System.arraycopy(packData, 0, truePackData, 0, truePackData.length);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("组件[PackImpl]方法[unpack]发生异常");
			}
		}	
		return truePackData;
	}
	
	/**
	 *  拆包
	 */
	@Override
	public DataPackage unpack(byte[] data) {
		
		if (log.isInfoEnabled()) {
//			log.info("组件[PackImpl]方法[unpack]开始执行,data数据长度=[" + data.length + "]");
		}
		
		DataPackage  dp  =  new DataPackage();		
		Head  head  = unpackHead(data);	
		
		if (head==null) {    //协议版本号错误无法 打包 、拆包
			return null;
		}

		
		Body  body  = new Body();
		
		int len = head.getDataLength();            
		byte[] dataArea = new byte[len];
		System.arraycopy(data, 34, dataArea, 0 , len);   //拿到数据信息dataArea[]
		
		
		try {
			int bid = head.getMesSign();
			/*  
			 * 根据不同信息标志进行拆包
			 */
			switch (bid) {
			case 0x00010000://遥测数据原码
			case 0x00020001://数传数据原码
			case 0x00030001://测距数据原码
			case 0x00030002://测速数据原码
			case 0x00040001://遥控指令/注入数据原码
			case 0x00050000://小环比对原码
			case 0x00030000://外测原始帧信息
			case 0x00060000://链路监视原始帧信息
			case 0x00100000://小固存遥测数据原始帧信息
				Source source = sourceDataImpl.unpack(dataArea);
				body.setSource(source);
				break;
			case 0x00010101:
				RealTimeData realTimeData = realTimeDataImpl.unpack(dataArea);
				body.setRealTimeData(realTimeData);
				break;
			case 0x00010102:
				DelayData delayData = delayDataImpl.unpack(dataArea);
				body.setDelayData(delayData);
				break;
			case 0x00030100://外测数据处理结果
			case 0x00040101://遥控指令/注入数据处理结果
			case 0x00050100://小环比对处理结果
			case 0x00060100://链路监视信息
			case 0x00080100://进程心跳信息
			case 0x00090100://软件运行日志
			case 0x00110100://设备收发汇总信息
			case 0x00120100://跟踪预报信息
			case 0x00120101://轨道计算信息
			case 0x00120102://星下点信息
				XmlData xmlData = xmlDataImpl.unpack(dataArea);
				body.setXmlData(xmlData);
				break;
			case 0x00070100://进程管理信息
				break;
			}
			
			dp.setBody(body);
			dp.setHead(head);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("组件[PackImpl]方法[unpack]发生异常");
			}
		}
	
		return dp;
	}

	public static void main(String[] args) {
		byte[] s = {2, 0, 0, 0, 9, 0, 0, 0, 8, 1, 5, 2, 0, 0, 2, 0, 1, 0, 0, 0, 0, 1, 27, 127, 34, -127, -7, 48, 4, 0};
		System.out.println(unpackHead(s));
	}

	@Override
	public List<DataPackage> multiUnpack(byte[] data) {
		return null;
	}

	@Override
	public byte[] multiPack(List<DataPackage> list) {
		return null;
	}
}