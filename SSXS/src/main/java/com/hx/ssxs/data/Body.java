package com.hx.ssxs.data;


/**
 * 包体数据域 
 * 
 * @author yj
 *
 */

public class Body {
	  
	private  Source  source;   //数据原码
	
	private  RealTimeData  realTimeData;  //实时数据
	
	private  DelayData   delayData;    //延时数据
	
	private   XmlData   xmlData;     //xml格式数据

	
	
	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public RealTimeData getRealTimeData() {
		return realTimeData;
	}

	public void setRealTimeData(RealTimeData realTimeData) {
		this.realTimeData = realTimeData;
	}

	public DelayData getDelayData() {
		return delayData;
	}

	public void setDelayData(DelayData delayData) {
		this.delayData = delayData;
	}

	public XmlData getXmlData() {
		return xmlData;
	}

	public void setXmlData(XmlData xmlData) {
		this.xmlData = xmlData;
	}

	@Override
	public String toString() {
		return "Body [source=" + source + ", realTimeData=" + realTimeData
				+ ", delayData=" + delayData + ", xmlData=" + xmlData + "]";
	}
	
	

}
