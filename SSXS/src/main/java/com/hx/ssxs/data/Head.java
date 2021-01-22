package com.hx.ssxs.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 包头实体例
 * 
 * @author YJ
 *
 */

public class Head {
	
	private  byte  ver;   //通讯版本号(1字节)
	
	private  int  syn = 0xEB59904B;   //同步码(4字节)
	
	private  int  taskSign;   //任务标志(4字节)
	
	private  int  sourceSign;   //信源标志(4字节)
	
	private  int  targetSign;   //信宿标志(4字节)
	
	private  int  mesSign;   //信息标志(4字节)
	
	private  int  packageNum;   //包序号(4字节)
	
	private  byte  flag;    //标志字段(1字节)
	
	private  int  sendDate;    //发送日期(2字节)
	
	private  int  sendTime;     //发送时间(4字节)
	
	private  int  dataLength;    //数据域长度(2字节)

	public byte getVer() {
		return ver;
	}

	public void setVer(byte ver) {
		this.ver = ver;
	}
	
	public int getSyn() {
		return syn;
	}

	public void setSyn(int syn) {
		this.syn = syn;
	}

	public int getTaskSign() {
		return taskSign;
	}

	public void setTaskSign(int taskSign) {
		this.taskSign = taskSign;
	}

	public int getSourceSign() {
		return sourceSign;
	}

	public void setSourceSign(int sourceSign) {
		this.sourceSign = sourceSign;
	}

	public int getTargetSign() {
		return targetSign;
	}

	public void setTargetSign(int targetSign) {
		this.targetSign = targetSign;
	}

	public int getMesSign() {
		return mesSign;
	}

	public void setMesSign(int mesSign) {
		this.mesSign = mesSign;
	}

	public int getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(int packageNum) {
		this.packageNum = packageNum;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public int getSendDate() {
		return sendDate;
	}

	public void setSendDate(int sendDate) {
		this.sendDate = sendDate;
	}

	public int getSendTime() {
		return sendTime;
	}

	public void setSendTime(int sendTime) {
		this.sendTime = sendTime;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	@Override
	public String toString() {
		return "Head [ver=" + ver + ", syn=" + syn + ", taskSign=" + taskSign + ", sourceSign="
				+ sourceSign + ", targetSign=" + targetSign + ", mesSign="
				+ mesSign + ", packageNum=" + packageNum + ", flag=" + flag
				+ ", sendDate=" + sendDate + ", sendTime=" + sendTime
				+ ", dataLength=" + dataLength + "]";
	}

	
	/**
	 * 输入的时间戳相对于北京时间2000-01-01 00:00:00 的积日
	 * @param 输入的当前时间戳
	 * @return 积日
	 */
	public int jiDay(long time) {
	
		long s = 0;
		try {
			SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = df.parse("2000-01-01 00:00:00");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			s = calendar.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return (int) (time/(1000*3600*24)-s/(1000*3600*24))-1;
	}


	/**
	 * 输入的时间戳相对于北京时间当日的积秒（毫秒）
	 * @param time 输入的当前时间戳
	 * @return 积秒（毫秒）
	 */
	public int jiSecond(long time) {
		long s = 0;

		try {
			SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String ti = format.format(new Date());
			Date date = df.parse(ti+" 00:00:00");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			s = calendar.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) (time-s)*10;
	}

	
	/**
	 * 获取发送日期时间  
	 * @return
	 */
	public Date getCurrentDate(){
		//积日
		int dateValue = sendDate-1;
		//积毫秒
		long timeValue =  sendTime/10;
		//初始日期 2000-1-1
		Date startTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			startTime = sdf.parse("2000-01-01 00:00:00.000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long currentTime = startTime.getTime() + timeValue + dateValue*24*60*60*1000L;
		return new Date(currentTime);
	}
	
	/**
	 * 获取发送时间  
	 * @return   返回格式 yyyy-MM-dd  HH:mm:ss.SSS
	 */
	public String getDateTime(){
		//积日
		int dateValue = sendDate-1;
		//积毫秒
		long timeValue =  sendTime/10;
		//初始日期 2000-1-1
		Date startTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			startTime = sdf.parse("2000-01-01 00:00:00.000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long currentTime = startTime.getTime() + timeValue + dateValue*24*60*60*1000L;
		
		return sdf.format(new Date(currentTime));
	}
	
	/**
	 * 获取发送日期
	 * @return   返回格式yyyy-MM-dd
	 */
	public String getDate() {

		String string  = getDateTime();
		return string.split(" ")[0];
	}
	
	/**
	 * 获取发送时间
	 * @return   返回格式HH:mm:ss.SSS
	 */
	public String getTime() {

		String string  = getDateTime();
		return string.split(" ")[1];
	}
	
	
}
