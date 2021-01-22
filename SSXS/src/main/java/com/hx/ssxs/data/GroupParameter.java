package com.hx.ssxs.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  实时数据  每一组数据
 * 
 * @author  yj
 *
 */

public class GroupParameter {
	
	private  int  type;  //数据类型(4字节)
	
	private  int  sou;   //信源设备编码(4字节)
	
	private  int  jd;       //数据时间（积日）(4字节)
	
	private  double  t;		//数据时间（积秒）(8字节)
	
	private  int  flag;		//标志位          0x00表示定长    0xFF表示变长(2字节)

	private  int plen;       //数据长度（含PNum）(2字节)
	
	private  int  pnum;		//本组参数总个数 (2字节)
	
	private  List<Parameter>  parameters;  //参数
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSou() {
		return sou;
	}
	public void setSou(int sou) {
		this.sou = sou;
	}
	public int getJd() {
		return jd;
	}
	public void setJd(int jd) {
		this.jd = jd;
	}
	
	public double getT() {
		return t;
	}
	public void setT(double t) {
		this.t = t;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getPlen() {
		return plen;
	}
	public void setPlen(int plen) {
		this.plen = plen;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public List<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	@Override
	public String toString() {
		return "GroupParameter [type=" + type + ", sou="
				+ sou + ", jd=" + jd + ", t=" + t + ", flag="
				+ flag + ", plen=" + plen + ", pnum=" + pnum + ", parameters="
				+ parameters + "]";
	}
	
	/**
	 * 根据积日与积秒值计算时间，时间零点“2000-1-1 00:00:00（北京时间）”。
	 * @param secondStr
	 * @param millisecondStr
	 * @return
	 */
	public Date getDataTime() {
		SimpleDateFormat sdfLen = new SimpleDateFormat("yyyy-MM-dd");
		//根据当前积日积秒计算数据时间
		Date result = null;
		//积日毫秒值
		long timeValue = this.jd*24*60*60*1000L;
		//实型积秒的毫秒值
		long secondValue = (long) (this.t * 1000L);
		try {
			//初始时间
			Date oldTime = sdfLen.parse("2000-01-01");
			//毫秒值
			long l = oldTime.getTime() + timeValue + secondValue;
			result = new Date(l);
		} catch (ParseException e) {
			e.printStackTrace();
			//异常时，存入当前时间
			result = new Date();
		}
		return result;
	}
	
	
	/**
	 * 根据积日与积秒值计算时间，时间零点“2000-1-1 00:00:00（北京时间）”。
	 * @param secondStr
	 * @param millisecondStr
	 * @return
	 */
	public String getDataTimeString() {
		SimpleDateFormat sdfLen = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		//根据当前积日积秒计算数据时间
		String result = null;
		//积日毫秒值
		long timeValue = this.jd*24*60*60*1000L;
		//实型积秒的毫秒值
		long secondValue = (long) (this.t * 1000L);
		try {
			//初始时间
			Date oldTime = sdfLen.parse("2000-01-01");
			//毫秒值
			long l = oldTime.getTime() + timeValue + secondValue;
			Date temp = new Date(l);
			result = sdf.format(temp);
		} catch (ParseException e) {
			e.printStackTrace();
			//异常时，存入当前时间
			result = sdf.format(new Date());
		}
		return result;
	}

}
